package com.mercadopago.android.px.internal.features.payment_congrats.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mercadopago.android.px.model.display_info.ResultInfo;
import java.math.BigDecimal;

public class PaymentInfo implements Parcelable {

    public static final Creator<PaymentInfo> CREATOR = new Creator<PaymentInfo>() {
        @Override
        public PaymentInfo createFromParcel(final Parcel in) {
            return new PaymentInfo(in);
        }

        @Override
        public PaymentInfo[] newArray(final int size) {
            return new PaymentInfo[size];
        }
    };
    @NonNull public final String rawAmount;
    @NonNull public final String paymentMethodName;
    @Nullable public final String lastFourDigits;
    @NonNull public final String paymentMethodId;
    @NonNull public final PaymentMethodType paymentMethodType;
    @NonNull public final String amountPaid;
    @Nullable public final String discountName;
    @NonNull public final Integer numberOfInstallments;
    @Nullable public final String installmentsAmount;
    @Nullable public final BigDecimal installmentsRate;
    @Nullable public final ResultInfo consumerCreditsInfo;

    protected PaymentInfo(final Builder builder) {
        rawAmount = builder.rawAmount;
        paymentMethodName = builder.paymentMethodName;
        lastFourDigits = builder.lastFourDigits;
        paymentMethodId = builder.paymentMethodId;
        paymentMethodType = builder.paymentMethodType;
        amountPaid = builder.amountPaid;
        discountName = builder.discountName;
        numberOfInstallments = builder.numberOfInstallments;
        installmentsAmount = builder.installmentsAmount;
        installmentsRate = builder.installmentsRate;
        consumerCreditsInfo = builder.consumerCreditsInfo;
    }

    protected PaymentInfo(final Parcel in) {
        rawAmount = in.readString();
        paymentMethodName = in.readString();
        lastFourDigits = in.readString();
        paymentMethodId = in.readString();
        paymentMethodType = PaymentMethodType.values()[in.readInt()];
        amountPaid = in.readString();
        discountName = in.readString();
        numberOfInstallments = in.readInt();
        installmentsAmount = in.readString();
        if (in.readByte() == 0) {
            installmentsRate = null;
        } else {
            installmentsRate = new BigDecimal(in.readString());
        }
        consumerCreditsInfo = in.readParcelable(ResultInfo.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(rawAmount);
        dest.writeString(paymentMethodName);
        dest.writeString(lastFourDigits);
        dest.writeString(paymentMethodId);
        dest.writeInt(paymentMethodType.ordinal());
        dest.writeString(amountPaid);
        dest.writeString(discountName);
        dest.writeInt(numberOfInstallments);
        dest.writeString(installmentsAmount);
        if (installmentsRate == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(installmentsRate.toString());
        }
        dest.writeParcelable(consumerCreditsInfo, flags);
    }

    public enum PaymentMethodType {
        CREDIT_CARD("credit_card"),
        DEBIT_CARD("debit_card"),
        PREPAID_CARD("prepaid_card"),
        TICKET("ticket"),
        ATM("atm"),
        DIGITAL_CURRENCY("digital_currency"),
        BANK_TRANSFER("bank_transfer"),
        ACCOUNT_MONEY("account_money"),
        PLUGIN("payment_method_plugin");

        public final String value;

        PaymentMethodType(final String value) {
            this.value = value;
        }
    }

    public static class Builder {
        /* default */ String rawAmount;
        /* default */ String paymentMethodName;
        /* default */ String lastFourDigits;
        /* default */ String paymentMethodId;
        /* default */ PaymentMethodType paymentMethodType;
        /* default */ String amountPaid;
        /* default */ String discountName;
        /* default */ Integer numberOfInstallments;
        /* default */ String installmentsAmount;
        /* default */ BigDecimal installmentsRate;
        /* default */ ResultInfo consumerCreditsInfo;

        /**
         * Instantiates a PaymentInfo object
         *
         * @return PaymentInfo
         */
        public PaymentInfo build() {
            return new PaymentInfo(this);
        }

        /**
         * Adds the name of the payment method used to pay
         *
         * @param paymentMethodName the name of the payment method
         * @return Builder
         */
        public Builder withPaymentMethodName(final String paymentMethodName) {
            this.paymentMethodName = paymentMethodName;
            return this;
        }

        /**
         * Adds the raw amount of the payment
         *
         * @param rawAmount the value of the raw Amount for the payment
         * @return Builder
         */
        public Builder withRawAmount(final String rawAmount) {
            this.rawAmount = rawAmount;
            return this;
        }

        /**
         * Adds the total amount actually paid by the user
         *
         * @param amountPaid the amount actually paid by the user
         * @return Builder
         */
        public Builder withAmountPaid(final String amountPaid) {
            this.amountPaid = amountPaid;
            return this;
        }

        /**
         * Adds the lastFourDigits of the credit/debit card
         *
         * @param lastFourDigits the last 4 digits of the credit or debit card number
         * @return Builder
         */
        public Builder withLastFourDigits(final String lastFourDigits) {
            this.lastFourDigits = lastFourDigits;
            return this;
        }

        /**
         * Adds the id of the payment method used to pay
         *
         * @param paymentMethodId the id of the payment method
         * @return Builder
         */
        public Builder withPaymentMethodId(final String paymentMethodId) {
            this.paymentMethodId = paymentMethodId;
            return this;
        }

        /**
         * Adds the type of the payment method used to pay
         *
         * @param paymentMethodType the type of payment method (account money, credit card, debit card, etc)
         * @return Builder
         */
        public Builder withPaymentMethodType(final PaymentMethodType paymentMethodType) {
            this.paymentMethodType = paymentMethodType;
            return this;
        }

        /**
         * Adds the name of the discount to be displayed (e.g.: 20% OFF)
         *
         * @param discountName the text to be displayed showing the discount (e.g.: 20% OFF)
         * @return Builder
         */
        public Builder withDiscountName(final String discountName) {
            this.discountName = discountName;
            return this;
        }

        /**
         * Adds the number of installments
         *
         * @param numberOfInstallments number of installments, if there ara non 0 should be passes as param
         * @return Builder
         */
        public Builder withNumberOfInstallments(final Integer numberOfInstallments) {
            this.numberOfInstallments = numberOfInstallments;
            return this;
        }

        /**
         * Adds the installments amount
         *
         * @param installmentsAmount the amount to be paid for each installment
         * @return Builder
         */
        public Builder withInstallmentsAmount(final String installmentsAmount) {
            this.installmentsAmount = installmentsAmount;
            return this;
        }

        /**
         * Adds the installments rate
         *
         * @param installmentsRate the rate/interest of the installments. If its without a rate or interest "0" should
         * be passed as param
         * @return Builder
         */
        public Builder withInstallmentsRate(final BigDecimal installmentsRate) {
            this.installmentsRate = installmentsRate;
            return this;
        }

        /**
         * Adds info to be displayed about consumerCredits (e.g. the date when the payer will start paying the credit
         * installments)
         *
         * @param consumerCreditsInfo info shown related to consumerCredits
         * @return Builder
         */
        public Builder withConsumerCreditsInfo(final ResultInfo consumerCreditsInfo) {
            this.consumerCreditsInfo = consumerCreditsInfo;
            return this;
        }
    }
}
