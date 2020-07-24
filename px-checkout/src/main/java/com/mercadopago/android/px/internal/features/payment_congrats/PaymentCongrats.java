package com.mercadopago.android.px.internal.features.payment_congrats;

import android.app.Activity;
import android.content.Intent;
import com.mercadopago.android.px.internal.features.business_result.BusinessPaymentResultActivity;
import com.mercadopago.android.px.internal.features.payment_congrats.model.PaymentCongratsModel;
import com.mercadopago.android.px.internal.viewmodel.BusinessPaymentModel;

public class PaymentCongrats {

    private static final String PAYMENT_CONGRATS = "payment_congrats";

    /**
     * Allows to execute a congrats activity
     *
     * @param paymentCongratsModel model with the needed data
     * @param activity caller activity
     * @param requestCode resquestCode for ActivityForResult
     */
    public static void show(final PaymentCongratsModel paymentCongratsModel, final Activity activity,
        final int requestCode, final BusinessPaymentModel businessModel) {
        final Intent intent = new Intent(activity, BusinessPaymentResultActivity.class);
        intent.putExtra(PAYMENT_CONGRATS, paymentCongratsModel);
        intent.putExtra("extra_business_payment_model", businessModel);
        activity.startActivityForResult(intent, requestCode);
    }
}