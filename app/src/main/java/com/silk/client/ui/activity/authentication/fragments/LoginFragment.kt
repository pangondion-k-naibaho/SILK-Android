package com.silk.client.ui.activity.authentication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.silk.client.R
import com.silk.client.databinding.FragmentLoginBinding
import com.silk.client.ui.activity.authentication.FragmentsAuthCommunicator

class LoginFragment : Fragment() {
    private val TAG = LoginFragment::class.java.simpleName
    private var _binding: FragmentLoginBinding?= null
    private val binding get() = _binding!!

    private lateinit var faCommunicator: FragmentsAuthCommunicator
    private var retrievedMessage: String?= null

    companion object{
        private const val DELIVERED_MESSAGE = "DELIVERED_MESSAGE"
        fun newInstance(message: String): LoginFragment{
            val fragment = LoginFragment()
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
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        faCommunicator = activity as FragmentsAuthCommunicator

        setUpView()

        return binding.root
    }

    private fun setUpView(){

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}