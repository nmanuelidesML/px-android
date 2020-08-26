package com.mercadopago.android.px.internal.features.payment_congrats.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
data class PXPaymentCongratsTracking(
        val campaignId: String,
        val currencyId: String,
        val paymentStatusDetail: String,
        val paymentId: Long?,
        val totalAmount: BigDecimal
) : Parcelable