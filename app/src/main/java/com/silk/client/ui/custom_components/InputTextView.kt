package com.silk.client.ui.custom_components

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.silk.client.R
import com.silk.client.databinding.InputtextLayoutBinding
import com.silk.client.utils.Extension.Companion.dpToPx

class InputTextView :ConstraintLayout{
    private lateinit var mContext: Context
    private lateinit var binding: InputtextLayoutBinding

    private var listener: TextBoxListener?= null
    private var retrievedInputType: INPUT_TYPE?= null

    constructor(context: Context): super(context){
        init(context, null)
    }

    constructor(context: Context, attributeSet:AttributeSet): super(context, attributeSet){
        init(context, attributeSet)
    }

    enum class INPUT_TYPE{
        REGULAR, EMAIL, PASSWORD, NUM_REGULAR, NUM_PHONE, MULTILINE
    }

    private fun init(context: Context, attrs: AttributeSet?){
        mContext = context

        binding = InputtextLayoutBinding.bind(
            LayoutInflater.from(mContext)
                .inflate(R.layout.inputtext_layout, this, true)
        )

        binding.etInput.apply {
            addTextChangedListener(textWatcher)
        }

        binding.ivReveal.setOnClickListener { listener?.onClickReveal() }
    }

    private val textWatcher = object: TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if(binding.etInput.text.toString().length > 0){
                binding.clEditText.apply {
                    background = ContextCompat.getDrawable(mContext, R.drawable.bg_circular_rectangle_white)
                }
                binding.tvWarning.visibility = View.GONE
            }
        }

    }

    fun setTitle(inputTitle: String){
        binding.tvTitle.text = inputTitle
    }

    fun getInputType(): INPUT_TYPE{
        return retrievedInputType!!
    }

    fun setListener(inputListener: TextBoxListener?){
        listener = inputListener
    }

    fun setInputType(typeInput: INPUT_TYPE){
        retrievedInputType = typeInput

        binding.viewBorder.apply {
            visibility = when(retrievedInputType){
                INPUT_TYPE.EMAIL -> View.VISIBLE
                else -> View.GONE
            }
        }

        binding.ivReveal.apply {
            visibility = when(retrievedInputType){
                INPUT_TYPE.PASSWORD -> View.VISIBLE
                else -> View.GONE
            }
        }

        binding.etInput.apply {
            inputType = when(retrievedInputType){
                INPUT_TYPE.EMAIL -> InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                INPUT_TYPE.PASSWORD -> InputType.TYPE_TEXT_VARIATION_PASSWORD
                INPUT_TYPE.MULTILINE -> InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE
                INPUT_TYPE.NUM_PHONE -> InputType.TYPE_CLASS_PHONE
                INPUT_TYPE.NUM_REGULAR -> InputType.TYPE_CLASS_NUMBER
                else -> InputType.TYPE_CLASS_TEXT
            }

            transformationMethod = when(retrievedInputType){
                INPUT_TYPE.PASSWORD -> PasswordTransformationMethod.getInstance()
                else -> null
            }

            if(retrievedInputType == INPUT_TYPE.MULTILINE){
                val updatedLayoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    150.dpToPx(mContext)
                )

                layoutParams = updatedLayoutParams
                gravity = Gravity.START
                minLines = 3
                maxLines = 8
                setHorizontallyScrolling(false)
                setVerticalScrollBarEnabled(true)
            }

            onFocusChangeListener = OnFocusChangeListener{_, hasFocus ->
                if(!hasFocus){
                    if(text.isEmpty()){
                        binding.clEditText.background = ContextCompat.getDrawable(mContext, R.drawable.bg_circular_rectangle_white_alarm)
                        binding.tvWarning.visibility = View.VISIBLE
                    }else{
                        binding.tvWarning.visibility = View.GONE
                    }
                }
            }
        }
    }

    fun revealPassword(){
        if(binding.etInput.transformationMethod == PasswordTransformationMethod.getInstance()){
            binding.etInput.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }else{
            binding.etInput.transformationMethod = PasswordTransformationMethod.getInstance()
        }
        binding.etInput.setSelection(binding.etInput.text.length)
    }

    fun getText(): String{
        return binding.etInput.text.toString()
    }

    fun clearingFocus(){
        binding.etInput.clearFocus()
    }

    fun setHint(inputHint: String){
        binding.etInput.hint = inputHint
    }

    fun setOnBlankWarning(customWarning: String?= null){
        binding.clEditText.apply {
            background = ContextCompat.getDrawable(mContext, R.drawable.bg_circular_rectangle_white_alarm)
        }
        binding.tvWarning.apply {
            visibility = View.VISIBLE
            text = customWarning?: mContext.getString(R.string.itvTxtWarning)
        }
    }

    fun setText(inputText: String){
        binding.etInput.setText(inputText)
    }

    interface TextBoxListener{
        fun onClickReveal()
    }
}