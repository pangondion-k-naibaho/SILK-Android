package com.silk.client.ui.custom_components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.silk.client.R
import com.silk.client.databinding.CardBanner3Binding

class CustomBanner3: ConstraintLayout {
    private lateinit var mContext: Context
    private lateinit var binding: CardBanner3Binding

    private var listener: BannerListener?= null

    constructor(context: Context): super(context){
        init(context, null)
    }

    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet){
        init(context, attributeSet)
    }

    private fun init(context: Context, attrs: AttributeSet?){
        mContext = context

        binding = CardBanner3Binding.bind(
            LayoutInflater.from(mContext)
                .inflate(R.layout.card_banner_3, this, true)
        )

        binding.clAction.setOnClickListener { listener?.onActionClicked() }
    }

    interface BannerListener{
        fun onActionClicked()
    }
}