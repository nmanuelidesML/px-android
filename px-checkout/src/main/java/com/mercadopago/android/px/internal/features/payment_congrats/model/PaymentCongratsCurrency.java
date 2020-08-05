package com.mercadopago.android.px.internal.features.payment_congrats.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class PaymentCongratsCurrency implements Parcelable {
    private String symbol;
    private int decimalPlaces;
    private Character decimalSeparator;
    private Character thousandsSeparator;

    public PaymentCongratsCurrency() { }

    public PaymentCongratsCurrency(final String symbol, final int decimalPlaces, final Character decimalSeparator,
        final Character thousandsSeparator) {
        this.symbol = symbol;
        this.decimalPlaces = decimalPlaces;
        this.decimalSeparator = decimalSeparator;
        this.thousandsSeparator = thousandsSeparator;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.symbol);
        dest.writeInt(this.decimalPlaces);
        dest.writeSerializable(this.decimalSeparator);
        dest.writeSerializable(this.thousandsSeparator);
    }

    protected PaymentCongratsCurrency(Parcel in) {
        this.symbol = in.readString();
        this.decimalPlaces = in.readInt();
        this.decimalSeparator = (Character) in.readSerializable();
        this.thousandsSeparator = (Character) in.readSerializable();
    }

    public static final Parcelable.Creator<PaymentCongratsCurrency> CREATOR =
        new Parcelable.Creator<PaymentCongratsCurrency>() {
            @Override
            public PaymentCongratsCurrency createFromParcel(Parcel source) {
                return new PaymentCongratsCurrency(source);
            }

            @Override
            public PaymentCongratsCurrency[] newArray(int size) {
                return new PaymentCongratsCurrency[size];
            }
        };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public int getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(final int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public Character getDecimalSeparator() {
        return decimalSeparator;
    }

    public void setDecimalSeparator(final Character decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
    }

    public Character getThousandsSeparator() {
        return thousandsSeparator;
    }

    public void setThousandsSeparator(final Character thousandsSeparator) {
        this.thousandsSeparator = thousandsSeparator;
    }

    @NonNull
    @Override
    public String toString() {
        return "Currency [symbol=" + symbol + ", decimalPlaces=" + decimalPlaces
            + ", thousandsSeparator=" + thousandsSeparator + "]";
    }
}
