package com.silk.client.ui.activity.authentication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.silk.client.R
import com.silk.client.data.Constants.PREFERENCES.Companion.APP_PREFERENCES
import com.silk.client.data.Constants.PREFERENCES.Companion.TOKEN_KEY
import com.silk.client.databinding.ActivityAuthenticationBinding
import com.silk.client.ui.activity.authentication.fragments.LoginFragment
import com.silk.client.ui.activity.authentication.fragments.RegisterFragment
import com.silk.client.ui.activity.dashboard.DashboardActivity
import com.silk.client.ui.viewmodels.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthenticationActivity : AppCompatActivity(), FragmentsAuthCommunicator {
    private val TAG = AuthenticationActivity::class.java.simpleName
    private lateinit var binding: ActivityAuthenticationBinding

    private lateinit var adapterFragmentAuthentication: AdapterFragmentAuthentication
    private lateinit var loginFragment: LoginFragment
    private lateinit var registerFragment: RegisterFragment

    private val authViewModel: AuthViewModel by viewModel()

    companion object{
        fun newIntent(context: Context): Intent = Intent(context, AuthenticationActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        observeStatus()

        setUpView()
    }
    
    private fun observeStatus(){
        authViewModel.isLoading.observe(this, {
            setLayoutForLoading(it)
        })
        
        authViewModel.isFail.observe(this, {
            if(it){
                Toast.makeText(this@AuthenticationActivity, getString(R.string.toastLoginFailed), Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@AuthenticationActivity, getString(R.string.toastLoginSuccess), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setUpView(){
        binding.vpContent.apply {
            adapterFragmentAuthentication = AdapterFragmentAuthentication(
                supportFragmentManager,
                lifecycle
            )

            loginFragment = LoginFragment.newInstance("Login")
            registerFragment = RegisterFragment.newInstance("Register")

            val listFragment = listOf(loginFragment, registerFragment)

            adapterFragmentAuthentication.fragmentList.addAll(listFragment)

            offscreenPageLimit = listFragment.size
            adapter = adapterFragmentAuthentication
            currentItem = 0
            isUserInputEnabled = false
            registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when(position){
                        0 ->{
                            binding.tvAuthRegisterDesc.text = getString(R.string.tvAuthRegisterDesc)
                            binding.tvAuthRegisterTitle.text = getString(R.string.tvAuthRegisterTitle)
                            binding.btnAuthenticate.text = getString(R.string.btnTxtLogin)
                        }
                        else ->{
                            binding.tvAuthRegisterDesc.text = getString(R.string.tvAuthLoginDesc)
                            binding.tvAuthRegisterTitle.text = getString(R.string.tvAuthLoginTitle)
                            binding.btnAuthenticate.text = getString(R.string.btnTxtRegister)
                        }
                    }
                }
            })
        }


        binding.tvAuthRegisterDesc.apply {
            setOnClickListener{
                val currentItem = binding.vpContent.currentItem
                val nextItem = if(currentItem < adapterFragmentAuthentication.itemCount-1){
                    currentItem + 1
                }else{
                    0
                }

                binding.vpContent.currentItem = nextItem
            }
        }

        binding.btnAuthenticate.setOnClickListener {
            val currentItem = binding.vpContent.currentItem

            val afCommunicator = when(currentItem){
                0 -> loginFragment
                else -> registerFragment
            }

            val status = afCommunicator.getFormCompleteStatus()

            if(status == true){
                afCommunicator.onFormCompleted()
            }

        }

    }

    private fun setLayoutForLoading(isLoading: Boolean){
        if(isLoading) {
            binding.loadingLayout.visibility = View.VISIBLE
            binding.pbLoading.visibility = View.VISIBLE
        } else {
            binding.loadingLayout.visibility = View.GONE
            binding.pbLoading.visibility = View.GONE
        }
    }

    private fun setLayoutForPopUp(isShown: Boolean){
        if(isShown){
            binding.loadingLayout.visibility = View.VISIBLE
            binding.pbLoading.visibility = View.GONE
        }else{
            binding.loadingLayout.visibility = View.GONE
            binding.pbLoading.visibility = View.GONE
        }
    }

    override fun executeLogin(email: String, password: String) {
        Log.d(TAG, "execute email: $email, password: $password for login")
        authViewModel.loginUser(email, password)

        authViewModel.loginResponse.observe(this,{response->
            Log.d(TAG, "response: $response")
            if(!response!!.token.isNullOrEmpty()){
                val appPreferences = this@AuthenticationActivity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
                val editor = appPreferences.edit()

                editor.putString(TOKEN_KEY, response.token)
                editor.apply()
                if(editor.commit()){
                    startActivity(DashboardActivity.newIntent(this@AuthenticationActivity))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                }
            }
        })
    }

    override fun executeRegister(
        firstName: String,
        lastName: String,
        ktp: String,
        email: String,
        phoneNum: String,
        password: String
    ) {
        Toast.makeText(this@AuthenticationActivity, getString(R.string.toastUnderDev), Toast.LENGTH_SHORT).show()
    }
}