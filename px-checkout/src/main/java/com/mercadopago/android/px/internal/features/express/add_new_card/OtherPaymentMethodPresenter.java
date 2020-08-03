package com.mercadopago.android.px.internal.features.express.add_new_card;

import android.support.annotation.NonNull;
import com.mercadopago.android.px.internal.base.BasePresenter;
import com.mercadopago.android.px.internal.repository.PaymentSettingRepository;
import com.mercadopago.android.px.internal.tracking.TrackingRepository;
import com.mercadopago.android.px.internal.util.CardFormWithFragmentWrapper;

public class OtherPaymentMethodPresenter extends BasePresenter<AddNewCard.View> implements AddNewCard.Actions {

    private final PaymentSettingRepository settingRepository;
    @NonNull private final TrackingRepository trackingRepository;

    /* default */ OtherPaymentMethodPresenter(@NonNull final PaymentSettingRepository settingRepository,
        @NonNull final TrackingRepository trackingRepository) {
        this.settingRepository = settingRepository;
        this.trackingRepository = trackingRepository;
    }

    @Override
    public void onAddNewCardSelected() {
        getView().startCardForm(new CardFormWithFragmentWrapper(settingRepository, trackingRepository));
    }
}