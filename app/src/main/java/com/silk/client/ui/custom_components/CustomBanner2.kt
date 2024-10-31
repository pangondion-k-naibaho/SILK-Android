package com.silk.client.ui.custom_components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.silk.client.R
import com.silk.client.databinding.CardBanner1Binding
import com.silk.client.databinding.CardBanner2Binding

class CustomBanner2: ConstraintLayout {
    private lateinit var mContext: Context
    private lateinit var binding: CardBanner2Binding

    private var listener: BannerListener?= null

    constructor(context: Context): super(context){
        init(context, null)
    }

    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet){
        init(context, attributeSet)
    }

    private fun init(context: Context, attrs: AttributeSet?){
        mContext = context

        binding = CardBanner2Binding.bind(
            LayoutInflater.from(mContext)
                .inflate(R.layout.card_banner_2, this, true)
        )

        binding.clAction.setOnClickListener { listener?.onActionClicked() }
    }

    interface BannerListener{
        fun onActionClicked()
    }
}