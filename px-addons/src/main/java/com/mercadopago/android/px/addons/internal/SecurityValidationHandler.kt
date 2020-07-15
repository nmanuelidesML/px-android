package com.mercadopago.android.px.addons.internal

interface SecurityValidationHandler {
    fun onSecurityValidated(isSuccess: Boolean = true, securityValidated: Boolean = false)
}