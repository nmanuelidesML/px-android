package com.mercadopago.android.px.internal.util

import com.mercadolibre.android.cardform.internal.CardFormWithFragment.Builder.Companion.withAccessToken
import com.mercadopago.android.px.internal.repository.PaymentSettingRepository
import com.mercadopago.android.px.internal.tracking.TrackingRepository
import java.util.*

class CardFormWithFragmentWrapper(
    settingRepository: PaymentSettingRepository,
    private val trackingRepository: TrackingRepository) {

    private val privateKey = settingRepository.privateKey!!
    private val siteId = settingRepository.site.id
    private val excludedPaymentTypes = settingRepository.checkoutPreference?.excludedPaymentTypes
        ?: Collections.emptyList()

    fun getCardFormWithFragment() = withAccessToken(privateKey, siteId, trackingRepository.flowId)
        .setSessionId(trackingRepository.sessionId)
        .setExcludedTypes(excludedPaymentTypes).build()
}