package com.mercadopago.android.px.internal.features.payment_congrats;

import com.mercadopago.android.px.model.ExitAction;
import com.mercadopago.android.px.model.ExternalFragment;
import java.util.List;

public class PaymentCongrats {
    //Basic data
    private final String title; //*
    private final String subtitle;
    private final String imageUrl; //*
    private final String status; //*
    private final int iconId;
    private final String help;


    // Exit Buttons
    private final ExitAction exitActionPrimery; //*
    private final ExitAction exitActionSecondary;

    //TODO MECHI: validar para que se usa
    private final String statementDescription;
    private final String paymentTypeId;
    private final String paymentMethodId;

    private final String receiptId;
    private final List<String> receiptIdList;
    private final Boolean shouldShowPaymentMethod;
    private final Boolean shouldShowReceipt;

    // custom views for integrators
    private final ExternalFragment topFragment;
    private final ExternalFragment bottomFragment;
    private final ExternalFragment importantFragment;

    private final int decimalPlaces = 2;
    private final String decimalSeparator = ",";
    private final String symbol = "$";
    private final String thousandsSeparator = ".";



}
