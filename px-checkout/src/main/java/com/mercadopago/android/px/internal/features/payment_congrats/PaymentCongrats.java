package com.mercadopago.android.px.internal.features.payment_congrats;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.mercadopago.android.px.internal.util.ListUtil;
import com.mercadopago.android.px.internal.util.TextUtil;
import com.mercadopago.android.px.model.ExitAction;
import com.mercadopago.android.px.model.ExternalFragment;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class PaymentCongrats implements Parcelable {
    //Basic data
    @NonNull private final CongratsType congratsType;
    @NonNull private final String title;
    @Nullable private final String subtitle;
    @NonNull private final String imageUrl; //*
    @NonNull private final String status; //*
    @Nullable private final String help;

    @Nullable private final String receiptId;
    @Nullable private final List<String> receiptIdList;

    // Exit Buttons
    @Nullable private final ExitAction exitActionPrimary; //*
    @Nullable private final ExitAction exitActionSecondary;
    @Nullable private final String statementDescription;

    @Nullable private final Boolean shouldShowPaymentMethod;
    @Nullable private final Boolean shouldShowReceipt;

    // custom views for integrators
    @Nullable private final ExternalFragment topFragment;
    @Nullable private final ExternalFragment bottomFragment;
    @Nullable private final ExternalFragment importantFragment;

    private final int currencyDecimalPlaces;
    @NonNull private final String currencyDecimalSeparator;
    @NonNull private final String currencySymbol;
    @NonNull private final String currencyThousandsSeparator;

    private PaymentCongrats(final Builder builder) {
        congratsType = builder.congratsType;
        title = builder.title;
        subtitle = builder.subtitle;
        imageUrl = builder.imageUrl;
        status = builder.status;
        help = builder.help;
        receiptId = builder.receiptId;
        receiptIdList = builder.receiptIdList;
        exitActionPrimary = builder.exitActionPrimary;
        exitActionSecondary = builder.exitActionSecondary;
        statementDescription = builder.statementDescription;
        shouldShowPaymentMethod = builder.shouldShowPaymentMethod;
        shouldShowReceipt = builder.shouldShowReceipt;
        topFragment = builder.topFragment;
        bottomFragment = builder.bottomFragment;
        importantFragment = builder.importantFragment;
        currencyDecimalPlaces = builder.currencyDecimalPlaces;
        currencyDecimalSeparator = builder.currencyDecimalSeparator;
        currencySymbol = builder.currencySymbol;
        currencyThousandsSeparator = builder.currencyThousandsSeparator;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getSubtitle() {
        return subtitle;
    }

    @NotNull
    public String getImageUrl() {
        return imageUrl;
    }

    @Nullable
    public String getHelp() {
        return help;
    }

    public String getReceipt() {
        return ListUtil.isNotEmpty(receiptIdList) ? receiptIdList.get(0) : receiptId;
    }

    @Nullable
    public ExitAction getExitActionPrimary() {
        return exitActionPrimary;
    }

    @Nullable
    public ExitAction getExitActionSecondary() {
        return exitActionSecondary;
    }

    @Nullable
    public String getStatementDescription() {
        return statementDescription;
    }

    @Nullable
    public Boolean getShouldShowPaymentMethod() {
        return shouldShowPaymentMethod;
    }

    @Nullable
    public Boolean getShouldShowReceipt() {
        return shouldShowReceipt;
    }

    @Nullable
    public ExternalFragment getTopFragment() {
        return topFragment;
    }

    @Nullable
    public ExternalFragment getBottomFragment() {
        return bottomFragment;
    }

    @Nullable
    public ExternalFragment getImportantFragment() {
        return importantFragment;
    }

    public int getCurrencyDecimalPlaces() {
        return currencyDecimalPlaces;
    }

    @NonNull
    public String getCurrencyDecimalSeparator() {
        return currencyDecimalSeparator;
    }

    @NonNull
    public String getCurrencySymbol() {
        return currencySymbol;
    }

    @NonNull
    public String getCurrencyThousandsSeparator() {
        return currencyThousandsSeparator;
    }

    public Boolean hasTopFragment() {
        return getTopFragment() != null;
    }

    public Boolean hasBottomFragment() {
        return getBottomFragment() != null;
    }

    public Boolean hasImportantFragment() {
        return getImportantFragment() != null;
    }

    public Boolean hasHelp() {
        return TextUtil.isNotEmpty(help);
    }

    public enum CongratsType {
        APPROVED("APPROVED"),
        REJECTED("REJECTED"),
        PENDING("PENDING");

        public final String type;

        CongratsType(final String type) {
            this.type = type;
        }

        public static CongratsType fromName(final String text) {
            for (final CongratsType s : CongratsType.values()) {
                if (s.type.equalsIgnoreCase(text)) {
                    return s;
                }
            }
            throw new IllegalStateException("Invalid decorator");
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.congratsType.type);
        dest.writeString(this.title);
        dest.writeString(this.subtitle);
        dest.writeString(this.imageUrl);
        dest.writeString(this.status);
        dest.writeString(this.help);
        dest.writeString(this.receiptId);
        dest.writeStringList(this.receiptIdList);
        dest.writeParcelable(this.exitActionPrimary, flags);
        dest.writeParcelable(this.exitActionSecondary, flags);
        dest.writeString(this.statementDescription);
        dest.writeValue(this.shouldShowPaymentMethod);
        dest.writeValue(this.shouldShowReceipt);
        dest.writeParcelable(this.topFragment, flags);
        dest.writeParcelable(this.bottomFragment, flags);
        dest.writeParcelable(this.importantFragment, flags);
        dest.writeInt(this.currencyDecimalPlaces);
        dest.writeString(this.currencyDecimalSeparator);
        dest.writeString(this.currencySymbol);
        dest.writeString(this.currencyThousandsSeparator);
    }

    protected PaymentCongrats(Parcel in) {
        this.congratsType = CongratsType.fromName(in.readString());
        this.title = in.readString();
        this.subtitle = in.readString();
        this.imageUrl = in.readString();
        this.status = in.readString();
        this.help = in.readString();
        this.receiptId = in.readString();
        this.receiptIdList = in.createStringArrayList();
        this.exitActionPrimary = in.readParcelable(ExitAction.class.getClassLoader());
        this.exitActionSecondary = in.readParcelable(ExitAction.class.getClassLoader());
        this.statementDescription = in.readString();
        this.shouldShowPaymentMethod = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.shouldShowReceipt = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.topFragment = in.readParcelable(ExternalFragment.class.getClassLoader());
        this.bottomFragment = in.readParcelable(ExternalFragment.class.getClassLoader());
        this.importantFragment = in.readParcelable(ExternalFragment.class.getClassLoader());
        this.currencyDecimalPlaces = in.readInt();
        this.currencyDecimalSeparator = in.readString();
        this.currencySymbol = in.readString();
        this.currencyThousandsSeparator = in.readString();
    }

    public static final Parcelable.Creator<PaymentCongrats> CREATOR = new Parcelable.Creator<PaymentCongrats>() {
        @Override
        public PaymentCongrats createFromParcel(Parcel source) {
            return new PaymentCongrats(source);
        }

        @Override
        public PaymentCongrats[] newArray(int size) {
            return new PaymentCongrats[size];
        }
    };

    public static class Builder {
        //Basic data
        /* default */ CongratsType congratsType;
        /* default */ String title;
        /* default */ String subtitle;
        /* default */ String imageUrl;
        /* default */ String status;
        /* default */ String help;

        /* default */ String receiptId;
        /* default */ List<String> receiptIdList;

        // Exit Buttons
        /* default */ ExitAction exitActionPrimary;
        /* default */ ExitAction exitActionSecondary;

        /* default */ String statementDescription;

        /* default */ Boolean shouldShowPaymentMethod = false;
        /* default */ Boolean shouldShowReceipt = false;

        // custom views for integrators
        /* default */ ExternalFragment topFragment;
        /* default */ ExternalFragment bottomFragment;
        /* default */ ExternalFragment importantFragment;

        /* default */ int currencyDecimalPlaces = 2;
        /* default */ String currencyDecimalSeparator = ",";
        /* default */ String currencySymbol = "$";
        /* default */ String currencyThousandsSeparator = ".";

        public Builder() {}

        public PaymentCongrats build() {
            if (exitActionPrimary == null && exitActionSecondary == null) {
                throw new IllegalStateException("At least one button should be provided for BusinessPayment");
            }
            return new PaymentCongrats(this);
        }

        public Builder withCongratsType(final CongratsType congratsType) {
            this.congratsType = congratsType;
            return this;
        }

        public Builder withTitle(final String title) {
            this.title = title;
            return this;
        }

        public Builder withSubtitle(final String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public Builder withImageUrl(final String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder withStatus(final String status) {
            this.status = status;
            return this;
        }

        public Builder withReceipId(final String receiptId) {
            this.receiptId = receiptId;
            return this;
        }

        public Builder withReceiptIdList(final List<String> receiptIdList) {
            this.receiptIdList = receiptIdList;
            return this;
        }

        public Builder withHelp(final String help) {
            this.help = help;
            return this;
        }

        public Builder withExitActionPrimary(final String label, final int resCode) {
            this.exitActionPrimary = new ExitAction(label, resCode);
            return this;
        }

        public Builder withExitActionSecondary(final String label, final int resCode) {
            this.exitActionSecondary = new ExitAction(label, resCode);
            return this;
        }

        public Builder withStatementDescription(final String statementDescription) {
            this.statementDescription = statementDescription;
            return this;
        }

        public Builder withShoulPaymentMethod(final Boolean shouldShowPaymentMethod) {
            this.shouldShowPaymentMethod = shouldShowPaymentMethod;
            return this;
        }

        public Builder withShouldShowReceipt(final Boolean shouldShowReceipt) {
            this.shouldShowReceipt = shouldShowReceipt;
            return this;
        }

        public Builder withTopFragment(@NonNull final Class<? extends Fragment> zClass, @Nullable final Bundle args) {
            this.topFragment = new ExternalFragment(zClass, args);
            return this;
        }

        public Builder withBottomFragment(@NonNull final Class<? extends Fragment> zClass,
            @Nullable final Bundle args) {
            this.bottomFragment = new ExternalFragment(zClass, args);
            return this;
        }

        public Builder withImportantFragment(@NonNull final Class<? extends Fragment> zClass,
            @Nullable final Bundle args) {
            this.importantFragment = new ExternalFragment(zClass, args);
            return this;
        }

        public Builder withCurrencyDecimalPlaces(final int decimalPlaces) {
            this.currencyDecimalPlaces = decimalPlaces;
            return this;
        }

        public Builder withCurrencyDecimalSeparator(final String decimalSeparator) {
            this.currencyDecimalSeparator = decimalSeparator;
            return this;
        }

        public Builder withCurrencySymbol(final String symbol) {
            this.currencySymbol = symbol;
            return this;
        }

        public Builder withCurrencyThousandsSeparator(final String thousandsSeparator) {
            this.currencyThousandsSeparator = thousandsSeparator;
            return this;
        }
    }
}
