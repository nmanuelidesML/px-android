package com.mercadopago.android.px.font

import android.content.Context
import android.graphics.Typeface
import android.support.annotation.FontRes
import android.support.v4.content.res.ResourcesCompat
import com.mercadolibre.android.ui.font.Font

abstract class FontTypefaceMapper {

    fun getTypeface(context: Context, font: Font): Typeface? {
        return ResourcesCompat.getFont(context, getFontResource(font))
    }

    @FontRes
    abstract fun getFontResource(font: Font): Int
}