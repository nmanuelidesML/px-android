package com.mercadopago.android.px.internal.viewmodel

import android.content.Context
import android.support.v4.content.ContextCompat
import com.mercadopago.android.px.R

class DiscountBriefColor : IDetailColor {
    override fun getColor(context: Context) = ContextCompat.getColor(context, R.color.px_expressCheckoutTextColorBriefDiscount)
}