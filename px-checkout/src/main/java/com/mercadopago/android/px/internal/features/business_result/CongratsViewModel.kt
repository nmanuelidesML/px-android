package com.mercadopago.android.px.internal.features.business_result

import com.mercadolibre.android.mlbusinesscomponents.components.actioncard.MLBusinessActionCardViewData
import com.mercadolibre.android.mlbusinesscomponents.components.common.downloadapp.MLBusinessDownloadAppData
import com.mercadolibre.android.mlbusinesscomponents.components.crossselling.MLBusinessCrossSellingBoxData
import com.mercadolibre.android.mlbusinesscomponents.components.loyalty.MLBusinessLoyaltyRingData
import com.mercadopago.android.px.model.internal.Action
import com.mercadopago.android.px.model.internal.Text

internal data class CongratsViewModel(
        val loyaltyRingData: MLBusinessLoyaltyRingData?,
        val discountBoxData: PXDiscountBoxData?,
        val showAllDiscounts: Action?,
        val downloadAppData: MLBusinessDownloadAppData?,
        val actionCardViewData: MLBusinessActionCardViewData?,
        val crossSellingBoxData: List<MLBusinessCrossSellingBoxData>?,
        val topTextBox: Text,
        val viewReceipt: Action?,
        val customOrder: Boolean)