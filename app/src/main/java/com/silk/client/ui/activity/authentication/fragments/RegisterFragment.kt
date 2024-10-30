package com.silk.client.ui.activity.authentication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.silk.client.R
import com.silk.client.databinding.FragmentRegisterBinding
import com.silk.client.ui.activity.authentication.AuthFragmentsCommunicator
import com.silk.client.ui.activity.authentication.FragmentsAuthCommunicator
import com.silk.client.ui.custom_components.InputTextView


class RegisterFragment : Fragment(), AuthFragmentsCommunicator {
    private val TAG = RegisterFragment::class.java.simpleName
    private var _binding: FragmentRegisterBinding?= null
    private val binding get() = _binding!!

    private lateinit var faCommunicator: FragmentsAuthCommunicator
    private var retrievedMessage: String?= null

    companion object{
        private const val DELIVERED_MESSAGE = "DELIVERED_MESSAGE"
        fun newInstance(message: String): RegisterFragment{
            val fragment = RegisterFragment()
            fragment.retrievedMessage = message
            val bundle = Bundle()
            bundle.putString(DELIVERED_MESSAGE, message)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        faCommunicator = activity as FragmentsAuthCommunicator

        setUpView()

        return binding.root
    }

    private fun setUpView(){
        binding.apply {
            itvFirstName.apply {
                setTitle(getString(R.string.itvTxtFirstNameTitle))
                setHint(getString(R.string.itvTxtFirstNameHint))
                setInputType(InputTextView.INPUT_TYPE.REGULAR)
            }

            itvLastName.apply {
                setTitle(getString(R.string.itvTxtLastNameTitle))
                setHint(getString(R.string.itvTxtLastNameHint))
                setInputType(InputTextView.INPUT_TYPE.REGULAR)
            }

            itvKTP.apply {
                setTitle(getString(R.string.itvTxtKTPTitle))
                setHint(getString(R.string.itvTxtKTPHint))
                setInputType(InputTextView.INPUT_TYPE.NUM_REGULAR)
            }

            itvEmail.apply {
                setTitle(getString(R.string.itvTxtEmailTitle))
                setHint(getString(R.string.itvTxtEmailHint))
                setInputType(InputTextView.INPUT_TYPE.EMAIL)
            }

            itvPhoneNum.apply {
                setTitle(getString(R.string.itvTxtPhoneTitle))
                setHint(getString(R.string.itvTxtPhoneHint))
                setInputType(InputTextView.INPUT_TYPE.NUM_PHONE)
            }

            itvPassword.apply {
                setTitle(getString(R.string.itvTxtPasswordTitle))
                setHint(getString(R.string.itvTxtPasswordHint))
                setInputType(InputTextView.INPUT_TYPE.PASSWORD)

                val listener = object: InputTextView.TextBoxListener{
                    override fun onClickReveal() {
                        revealPassword()
                    }
                }

                setListener(listener)
            }

            itvConfPassword.apply {
                setTitle(getString(R.string.itvTxtConfirmPasswordTitle))
                setHint(getString(R.string.itvTxtConfirmPasswordHint))
                setInputType(InputTextView.INPUT_TYPE.PASSWORD)

                val listener = object : InputTextView.TextBoxListener{
                    override fun onClickReveal() {
                        revealPassword()
                    }

                }

                setListener(listener)
            }


        }
    }

    override fun getFormCompleteStatus(): Boolean {
        var result = true

        val listItv = listOf(
            binding.itvFirstName,
            binding.itvLastName,
            binding.itvKTP,
            binding.itvEmail,
            binding.itvPhoneNum,
            binding.itvPassword,
            binding.itvConfPassword
        )

        for(itv in listItv){
            if(itv.getText().isNullOrEmpty()){
                itv.setOnBlankWarning()
                result = false
            }
        }

        if(binding.itvPassword.getText() != binding.itvConfPassword.getText()){
            binding.itvConfPassword.setOnBlankWarning(getString(R.string.itvTxtWarningMismatch))
            result = false
        }

        return result
    }

    override fun onFormCompleted() {
        faCommunicator.executeRegister(
            firstName = binding.itvFirstName.getText(),
            lastName = binding.itvLastName.getText(),
            ktp = binding.itvKTP.getText(),
            email = binding.itvEmail.getText(),
            phoneNum = binding.itvPhoneNum.getText(),
            password = binding.itvPassword.getText()
        )
    }

    override fun setWarning() {}

    override fun onResume() {
        super.onResume()
        binding.apply {
            itvFirstName.clearText()
            itvLastName.clearText()
            itvKTP.clearText()
            itvEmail.clearText()
            itvPhoneNum.clearText()
            itvPassword.clearText()
            itvConfPassword.clearText()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}