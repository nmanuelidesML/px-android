package com.mercadopago.android.px.model

import android.os.Parcel
import com.mercadopago.android.px.internal.util.KParcelable
import com.mercadopago.android.px.internal.util.parcelableCreator
import com.mercadopago.android.px.model.internal.Text

data class DiscountOverview(val description: List<Text>,
                            val amount: Text,
                            val brief: List<Text>?,
                            val url: String?): KParcelable {
    constructor(parcel: Parcel) : this(
            parcel.createTypedArrayList(Text.CREATOR)!!,
            parcel.readParcelable<Text>(Text::class.java.classLoader)!!,
            parcel.createTypedArrayList(Text.CREATOR),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(description)
        parcel.writeParcelable(amount, flags)
        parcel.writeTypedList(brief)
        parcel.writeString(url)
    }

    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::DiscountOverview)
    }
}