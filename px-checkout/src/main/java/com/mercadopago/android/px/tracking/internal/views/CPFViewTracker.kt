package com.mercadopago.android.px.tracking.internal.views

import com.mercadopago.android.px.model.PaymentMethod

class CPFViewTracker(paymentMethod: PaymentMethod) : PaymentMethodDataTracker(
    "$BASE_PATH$PAYMENTS_PATH/select_method/ticket/cpf",
    paymentMethod
)