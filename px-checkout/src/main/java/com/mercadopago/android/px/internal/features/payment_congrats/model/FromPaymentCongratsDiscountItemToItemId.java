package com.mercadopago.android.px.internal.features.payment_congrats.model;

import android.support.annotation.NonNull;
import com.mercadopago.android.px.internal.viewmodel.mappers.NonNullMapper;

public class FromPaymentCongratsDiscountItemToItemId extends NonNullMapper<PaymentCongratsResponse.Discount.Item, String> {

    @Override
    public String map(@NonNull final PaymentCongratsResponse.Discount.Item discountItem) {
        return discountItem.getCampaignId();
    }
}
