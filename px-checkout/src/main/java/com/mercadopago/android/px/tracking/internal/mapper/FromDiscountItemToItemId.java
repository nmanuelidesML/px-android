package com.mercadopago.android.px.tracking.internal.mapper;

import android.support.annotation.NonNull;
import com.mercadopago.android.px.internal.features.payment_congrats.model.PaymentCongratsResponse;
import com.mercadopago.android.px.internal.viewmodel.mappers.NonNullMapper;
import com.mercadopago.android.px.model.internal.CongratsResponse;

public class FromDiscountItemToItemId extends NonNullMapper<CongratsResponse.Discount.Item, String> {

    @Override
    public String map(@NonNull final CongratsResponse.Discount.Item discountItem) {
        return discountItem.getCampaignId();
    }
}