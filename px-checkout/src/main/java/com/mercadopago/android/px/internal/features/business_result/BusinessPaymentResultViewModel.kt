package com.mercadopago.android.px.internal.features.business_result

import com.mercadopago.android.px.internal.view.PaymentResultBody
import com.mercadopago.android.px.internal.view.PaymentResultHeader
import com.mercadopago.android.px.model.ExitAction

internal data class BusinessPaymentResultViewModel(
    @JvmField val headerModel: PaymentResultHeader.Model,
    @JvmField val bodyModel: PaymentResultBody.Model,
    @JvmField val primaryAction: ExitAction?,
    @JvmField val secondaryAction: ExitAction?/*,
    @JvmField val shouldAutoReturn: Boolean*/
)