package com.mercadopago.android.px.font

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.widget.TextView
import com.mercadolibre.android.ui.font.Font
import com.mercadolibre.android.ui.font.TypefaceHelper

class SampleTypefaceSetter (private val sampleFontTypefaceMapper: FontTypefaceMapper): TypefaceHelper.TypefaceSetter {

    override fun <T : TextView?> setTypeface(view: T, font: Font) {
        (view as TextView?)?.also {
            it.typeface = sampleFontTypefaceMapper.getTypeface(it.context, font)
        }
    }

    override fun setTypeface(context: Context, paint: Paint, font: Font) {
        paint.typeface = sampleFontTypefaceMapper.getTypeface(context, font)
    }

    override fun getTypeface(context: Context, font: Font): Typeface? {
        return sampleFontTypefaceMapper.getTypeface(context, font)
    }
}