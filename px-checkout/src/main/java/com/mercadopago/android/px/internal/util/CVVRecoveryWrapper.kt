package com.mercadopago.android.px.internal.util

import com.mercadopago.android.px.addons.ESCManagerBehaviour
import com.mercadopago.android.px.internal.extensions.isNotNullNorEmpty
import com.mercadopago.android.px.internal.repository.CardTokenRepository
import com.mercadopago.android.px.model.PaymentRecovery
import com.mercadopago.android.px.model.Token
import com.mercadopago.android.px.model.exceptions.CardTokenException
import com.mercadopago.android.px.tracking.internal.events.CVVRecoveryFrictionTracker
import com.mercadopago.android.px.tracking.internal.model.Reason

internal class CVVRecoveryWrapper(cardTokenRepository: CardTokenRepository,
    private val escManagerBehaviour: ESCManagerBehaviour, private val paymentRecovery: PaymentRecovery) {

    private val tokenCreationWrapper: TokenCreationWrapper =
        TokenCreationWrapper.Builder(cardTokenRepository, escManagerBehaviour).with(paymentRecovery).build()
    private val card = paymentRecovery.card
    private val token = paymentRecovery.token

    suspend fun recoverWithCVV(cvv: String): Token? {
        var token: Token? = null
        try {
            if (hasToCloneToken() && tokenCreationWrapper.validateCVVFromToken(cvv)) {
                token = tokenCreationWrapper.cloneToken(cvv)
            } else if (isSavedCardWithESC() || hasToRecoverTokenFromESC()) {
                token = tokenCreationWrapper.createTokenWithEsc(cvv)
            } else if (isSavedCardWithoutESC()) {
                token = tokenCreationWrapper.createTokenWithoutEsc(cvv)
            }
        } catch (exception: CardTokenException) {
            CVVRecoveryFrictionTracker.with(card, Reason.from(paymentRecovery))?.track()
        } finally {
            return token
        }
    }

    private fun hasToCloneToken() = token?.run { cardId.isNullOrEmpty() } ?: false

    private fun hasToRecoverTokenFromESC() = paymentRecovery.isStatusDetailInvalidESC &&
            (token?.cardId.isNotNullNorEmpty() || card?.id.isNotNullNorEmpty())

    private fun isSavedCardWithESC() = card != null && escManagerBehaviour.isESCEnabled

    private fun isSavedCardWithoutESC() = card != null && !escManagerBehaviour.isESCEnabled
}