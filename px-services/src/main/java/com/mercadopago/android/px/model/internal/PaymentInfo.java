package com.mercadopago.android.px.model.internal;

import android.os.Parcel;
import android.os.Parcelable;

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
    public final String rawAmount;
    public final String paymentMethodName;
    public final String lastFourDigits;
    public final String paymentMethodId;

    public PaymentInfo(final Builder builder) {
        rawAmount = builder.rawAmount;
        paymentMethodName = builder.paymentMethodName;
        lastFourDigits = builder.lastFourDigits;
        paymentMethodId = builder.paymentMethodId;
    }

    protected PaymentInfo(Parcel in) {
        rawAmount = in.readString();
        paymentMethodName = in.readString();
        lastFourDigits = in.readString();
        paymentMethodId = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(this.rawAmount);
        dest.writeString(this.paymentMethodName);
        dest.writeString(this.lastFourDigits);
        dest.writeString(this.paymentMethodId);
    }

    public static class Builder {
        /* default */ String rawAmount;
        /* default */ String paymentMethodName;
        /* default */ String lastFourDigits;
        /* default */ String paymentMethodId;

        /**
         * Instantiates a PayerInfo object
         *
         * @return PayerInfo
         */
        public PaymentInfo build() {
            return new PaymentInfo(this);
        }

        /**
         * Adds the of the payment method used to pay
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
    }
}
