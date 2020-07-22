package com.mercadopago.android.px.model.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import java.util.List;

public class PaymentCongratsResponse implements Parcelable {

    public static final Creator<PaymentCongratsResponse> CREATOR = new Creator<PaymentCongratsResponse>() {
        @Override
        public PaymentCongratsResponse createFromParcel(final Parcel in) {
            return new PaymentCongratsResponse(in);
        }

        @Override
        public PaymentCongratsResponse[] newArray(final int size) {
            return new PaymentCongratsResponse[size];
        }
    };
    @Nullable private final CongratsResponse.Score score;
    @Nullable private final CongratsResponse.Discount discount;
    @Nullable private final CongratsResponse.MoneySplit moneySplit;
    private final List<CongratsResponse.CrossSelling> crossSelling;
    private final Text topTextBox;
    private final Action viewReceipt;
    private boolean customOrder;

    public PaymentCongratsResponse(@Nullable final CongratsResponse.Score score,
        @Nullable final CongratsResponse.Discount discount,
        @Nullable final CongratsResponse.MoneySplit moneySplit,
        final List<CongratsResponse.CrossSelling> crossSelling,
        final Text topTextBox, final Action viewReceipt) {
        this.score = score;
        this.discount = discount;
        this.moneySplit = moneySplit;
        this.crossSelling = crossSelling;
        this.topTextBox = topTextBox;
        this.viewReceipt = viewReceipt;
    }

    protected PaymentCongratsResponse(Parcel in) {
        score = in.readParcelable(CongratsResponse.Score.class.getClassLoader());
        discount = in.readParcelable(CongratsResponse.Discount.class.getClassLoader());
        moneySplit = in.readParcelable(CongratsResponse.MoneySplit.class.getClassLoader());
        crossSelling = in.createTypedArrayList(CongratsResponse.CrossSelling.CREATOR);
        topTextBox = in.readParcelable(Text.class.getClassLoader());
        viewReceipt = in.readParcelable(Action.class.getClassLoader());
        customOrder = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(score, flags);
        dest.writeParcelable(discount, flags);
        dest.writeParcelable(moneySplit, flags);
        dest.writeTypedList(crossSelling);
        dest.writeParcelable(topTextBox, flags);
        dest.writeParcelable(viewReceipt, flags);
    }

    @Nullable
    public CongratsResponse.Score getScore() {
        return score;
    }

    @Nullable
    public CongratsResponse.Discount getDiscount() {
        return discount;
    }

    @Nullable
    public CongratsResponse.MoneySplit getMoneySplit() {
        return moneySplit;
    }

    public List<CongratsResponse.CrossSelling> getCrossSelling() {
        return crossSelling;
    }

    public Text getTopTextBox() {
        return topTextBox;
    }

    public Action getViewReceipt() {
        return viewReceipt;
    }

    public boolean isCustomOrder() {
        return customOrder;
    }

    public void setCustomOrder(final boolean customOrder) {
        this.customOrder = customOrder;
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
