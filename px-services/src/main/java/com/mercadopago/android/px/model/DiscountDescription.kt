package com.mercadopago.android.px.model

import android.os.Parcel
import com.mercadopago.android.px.internal.util.KParcelable
import com.mercadopago.android.px.internal.util.parcelableCreator
import com.mercadopago.android.px.model.internal.Text

data class DiscountDescription(
        val title: Text,
        val subtitle: Text?,
        val badge: TextUrl?,
        val summary: Text,
        val description: Text,
        val legalTerms: TextUrl
): KParcelable {
    constructor(parcel: Parcel) : this(
            parcel.readParcelable(Text::class.java.classLoader)!!,
            parcel.readParcelable(Text::class.java.classLoader),
            parcel.readParcelable(TextUrl::class.java.classLoader),
            parcel.readParcelable(Text::class.java.classLoader)!!,
            parcel.readParcelable(Text::class.java.classLoader)!!,
            parcel.readParcelable(TextUrl::class.java.classLoader)!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(title, flags)
        parcel.writeParcelable(subtitle, flags)
        parcel.writeParcelable(badge, flags)
        parcel.writeParcelable(summary, flags)
        parcel.writeParcelable(description, flags)
        parcel.writeParcelable(legalTerms, flags)
    }

    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::DiscountDescription)
    }
}