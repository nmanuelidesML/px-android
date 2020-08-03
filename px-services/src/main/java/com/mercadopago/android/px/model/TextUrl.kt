package com.mercadopago.android.px.model

import android.os.Parcel
import com.mercadopago.android.px.internal.util.KParcelable
import com.mercadopago.android.px.internal.util.parcelableCreator
import com.mercadopago.android.px.model.internal.Text

data class TextUrl(
        val content: Text,
        val url: String
): KParcelable {
    constructor(parcel: Parcel) : this(
            parcel.readParcelable(Text::class.java.classLoader)!!,
            parcel.readString()!!)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(content, flags)
        parcel.writeString(url)
    }

    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::TextUrl)
    }
}
