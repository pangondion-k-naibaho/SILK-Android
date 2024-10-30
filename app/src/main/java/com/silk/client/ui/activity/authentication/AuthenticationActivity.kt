package com.silk.client.ui.activity.authentication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.silk.client.R
import com.silk.client.databinding.ActivityAuthenticationBinding
import com.silk.client.ui.activity.authentication.fragments.LoginFragment
import com.silk.client.ui.activity.authentication.fragments.RegisterFragment

class AuthenticationActivity : AppCompatActivity(), FragmentsAuthCommunicator {
    private val TAG = AuthenticationActivity::class.java.simpleName
    private lateinit var binding: ActivityAuthenticationBinding

    private lateinit var adapterFragmentAuthentication: AdapterFragmentAuthentication

    companion object{
        fun newIntent(context: Context): Intent = Intent(context, AuthenticationActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
    }

    private fun setUpView(){
        binding.vpContent.apply {
            adapterFragmentAuthentication = AdapterFragmentAuthentication(
                supportFragmentManager,
                lifecycle
            )

            val loginFragment = LoginFragment.newInstance("Login")
            val registerFragment = RegisterFragment.newInstance("Register")

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

//                text = when(binding.vpContent.currentItem){
//                    0 -> getString(R.string.tvAuthRegisterDesc)
//                    else -> getString(R.string.tvAuthLoginDesc)
//                }
//
//                binding.tvAuthRegisterTitle.text = when(binding.vpContent.currentItem){
//                    0 -> getString(R.string.tvAuthRegisterTitle)
//                    else -> getString(R.string.tvAuthLoginTitle)
//                }
//
//                binding.btnAuthenticate.text = when(binding.vpContent.currentItem){
//                    0 -> getString(R.string.btnTxtLogin)
//                    else -> getString(R.string.btnTxtRegister)
//                }
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

    override fun startLoading() {
        setLayoutForLoading(true)
    }

    override fun stopLoading() {
        setLayoutForLoading(false)
    }
}