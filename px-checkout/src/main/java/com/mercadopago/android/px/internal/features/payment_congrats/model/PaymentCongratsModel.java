package com.mercadopago.android.px.internal.features.payment_congrats.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.mercadopago.android.px.internal.util.TextUtil;
import com.mercadopago.android.px.model.ExitAction;
import com.mercadopago.android.px.model.ExternalFragment;
import com.mercadopago.android.px.model.PaymentData;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PaymentCongratsModel implements Parcelable {
    public static final Parcelable.Creator<PaymentCongratsModel> CREATOR =
        new Parcelable.Creator<PaymentCongratsModel>() {
            @Override
            public PaymentCongratsModel createFromParcel(final Parcel source) {
                return new PaymentCongratsModel(source);
            }

            @Override
            public PaymentCongratsModel[] newArray(final int size) {
                return new PaymentCongratsModel[size];
            }
        };

    public static final String SUCCESS = "success";
    public static final String PENDING = "further_action_needed";
    public static final String ERROR = "error";
    public static final String UNKNOWN = "unknown";

    //Basic data
    @NonNull private final CongratsType congratsType;
    @NonNull private final String title;
    @Nullable private final String subtitle;
    @NonNull private final String imageUrl;
    @Nullable private final String help;
    private final int iconId;
    @Nullable private final String statementDescription;
    private final boolean shouldShowPaymentMethod;
    @NonNull private final List<PaymentInfo> paymentsInfo;

    //Receipt data
    private final boolean shouldShowReceipt;

    // Exit Buttons
    @Nullable private final ExitAction exitActionPrimary;
    @Nullable private final ExitAction exitActionSecondary;
    // custom views for integrators
    @Nullable private final ExternalFragment topFragment;
    @Nullable private final ExternalFragment bottomFragment;
    @Nullable private final ExternalFragment importantFragment;
    @Nullable private final PaymentCongratsResponse paymentCongratsResponse;

    //Internal PX Tracking data
    @Nullable private final Long paymentId;
    @NonNull private final PXPaymentCongratsTracking pxPaymentCongratsTracking;
    @NonNull private final String flow;
    @NonNull private final String paymentStatus;
    @NonNull private final PaymentData paymentData;
    @Nullable private final BigDecimal discountCouponsAmount;

    /* default */ PaymentCongratsModel(final Builder builder) {
        congratsType = builder.congratsType;
        title = builder.title;
        subtitle = builder.subtitle;
        imageUrl = builder.imageUrl;
        help = builder.help;
        iconId = builder.iconId;
        paymentId = builder.paymentId;
        exitActionPrimary = builder.exitActionPrimary;
        exitActionSecondary = builder.exitActionSecondary;
        statementDescription = builder.statementDescription;
        shouldShowPaymentMethod = builder.shouldShowPaymentMethod;
        paymentsInfo = builder.paymentsInfo;
        shouldShowReceipt = builder.shouldShowReceipt;
        topFragment = builder.topFragment;
        bottomFragment = builder.bottomFragment;
        importantFragment = builder.importantFragment;
        paymentCongratsResponse = builder.paymentCongratsResponse;
        flow = builder.flow;
        paymentStatus = builder.paymentStatus;
        paymentData = builder.paymentData;
        discountCouponsAmount = builder.discountCouponsAmount;
        pxPaymentCongratsTracking = builder.pxPaymentCongratsTracking;
    }

    protected PaymentCongratsModel(final Parcel in) {
        congratsType = CongratsType.fromName(in.readString());
        title = in.readString();
        subtitle = in.readString();
        imageUrl = in.readString();
        help = in.readString();
        iconId = in.readInt();
        paymentId = in.readLong();
        shouldShowReceipt = (Boolean) in.readValue(Boolean.class.getClassLoader());
        exitActionPrimary = in.readParcelable(ExitAction.class.getClassLoader());
        exitActionSecondary = in.readParcelable(ExitAction.class.getClassLoader());
        statementDescription = in.readString();
        shouldShowPaymentMethod = (Boolean) in.readValue(Boolean.class.getClassLoader());
        paymentsInfo = in.createTypedArrayList(PaymentInfo.CREATOR);
        topFragment = in.readParcelable(ExternalFragment.class.getClassLoader());
        bottomFragment = in.readParcelable(ExternalFragment.class.getClassLoader());
        importantFragment = in.readParcelable(ExternalFragment.class.getClassLoader());
        paymentCongratsResponse = in.readParcelable(PaymentCongratsResponse.class.getClassLoader());
        flow = in.readString();
        paymentStatus = in.readString();
        paymentData = (PaymentData) in.readSerializable();
        if (in.readByte() == 0) {
            discountCouponsAmount = null;
        } else {
            discountCouponsAmount = new BigDecimal(in.readString());
        }
        pxPaymentCongratsTracking = in.readParcelable(PXPaymentCongratsTracking.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(congratsType.name());
        dest.writeString(title);
        dest.writeString(subtitle);
        dest.writeString(imageUrl);
        dest.writeString(help);
        dest.writeInt(iconId);
        dest.writeLong(paymentId);
        dest.writeValue(shouldShowReceipt);
        dest.writeParcelable(exitActionPrimary, flags);
        dest.writeParcelable(exitActionSecondary, flags);
        dest.writeString(statementDescription);
        dest.writeValue(shouldShowPaymentMethod);
        dest.writeTypedList(paymentsInfo);
        dest.writeParcelable(topFragment, flags);
        dest.writeParcelable(bottomFragment, flags);
        dest.writeParcelable(importantFragment, flags);
        dest.writeParcelable(paymentCongratsResponse, flags);
        dest.writeString(flow);
        dest.writeString(paymentStatus);
        dest.writeSerializable(paymentData);
        if (discountCouponsAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(discountCouponsAmount.toString());
        }
        dest.writeParcelable(pxPaymentCongratsTracking, flags);
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getSubtitle() {
        return subtitle;
    }

    @NonNull
    public String getImageUrl() {
        return imageUrl;
    }

    @Nullable
    public String getHelp() {
        return help;
    }

    public int getIconId() {
        return iconId;
    }

    @Nullable
    public Long getPaymentId() {
        return paymentId;
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

    @NonNull
    public List<PaymentInfo> getPaymentsInfo() {
        return paymentsInfo;
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

    @NonNull
    public CongratsType getCongratsType() {
        return congratsType;
    }

    @Nullable
    public PaymentCongratsResponse getPaymentCongratsResponse() {
        return paymentCongratsResponse;
    }

    @NonNull
    public String getFlow() {
        return flow;
    }

    @NonNull
    public String getPaymentStatus() {
        return paymentStatus;
    }

    @NonNull
    public PaymentData getPaymentData() {
        return paymentData;
    }

    @Nullable
    public BigDecimal getDiscountCouponsAmount() {
        return discountCouponsAmount;
    }

    @NonNull
    public PXPaymentCongratsTracking getPxPaymentCongratsTracking() {
        return pxPaymentCongratsTracking;
    }

    public enum CongratsType {
        APPROVED,
        REJECTED,
        PENDING;

        public static CongratsType fromName(final String text) {
            for (final CongratsType congratsType : CongratsType.values()) {
                if (congratsType.name().equalsIgnoreCase(text)) {
                    return congratsType;
                }
            }
            throw new IllegalStateException("Invalid congratsType");
        }
    }

    public static class Builder {
        //Basic data
        /* default */ CongratsType congratsType;
        /* default */ String title;
        /* default */ String subtitle;
        /* default */ String imageUrl;
        /* default */ String help;
        /* default */ int iconId;
        /* default */ List<PaymentInfo> paymentsInfo = new ArrayList<>();

        /* default */ boolean shouldShowReceipt = false;
        /* default */ String currencyId;

        // Exit Buttons
        /* default */ ExitAction exitActionPrimary;
        /* default */ ExitAction exitActionSecondary;

        /* default */ String statementDescription;

        /* default */ boolean shouldShowPaymentMethod = false;

        // custom views for integrators
        /* default */ ExternalFragment topFragment;
        /* default */ ExternalFragment bottomFragment;
        /* default */ ExternalFragment importantFragment;

        /* default */ PaymentCongratsResponse paymentCongratsResponse;

        // MLBusinessComponents
        /* default */ PaymentCongratsResponse.Loyalty loyalty;
        /* default */ PaymentCongratsResponse.Discount discount;
        /* default */ List<PaymentCongratsResponse.CrossSelling> crossSelling;
        /* default */ PaymentCongratsResponse.ExpenseSplit expenseSplit;
        /* default */ PaymentCongratsResponse.Action receiptAction;
        /* default */ boolean customSorting = false;

        //Internal PX Tracking data
        /* default */ Long paymentId;
        /* default */ PXPaymentCongratsTracking pxPaymentCongratsTracking;
        /* default */ String flow;
        /* default */ String paymentStatus;
        /* default */ PaymentData paymentData;
        /* default */ BigDecimal discountCouponsAmount;

        public Builder() {
        }

        public PaymentCongratsModel build() {
            if (exitActionPrimary == null && exitActionSecondary == null) {
                throw new IllegalStateException("At least one button should be provided for PaymentCongrats");
            }
            switch (congratsType) {
            case APPROVED:
                paymentStatus = SUCCESS;
                break;
            case PENDING:
                paymentStatus = PENDING;
                break;
            case REJECTED:
                paymentStatus = ERROR;
                break;
            }
            paymentCongratsResponse =
                new PaymentCongratsResponse(loyalty, discount, expenseSplit, crossSelling, receiptAction,
                    customSorting);

            return new PaymentCongratsModel(this);
        }

        /**
         * Sets up the congrats type (green, red, orange)
         *
         * @param congratsType enum with type attribute
         * @return builder
         */
        public Builder withCongratsType(final CongratsType congratsType) {
            this.congratsType = congratsType;
            return this;
        }

        /**
         * Sets up the header data
         *
         * @param title Title shown in the congrats's header
         * @param imageUrl url for the header's image
         * @return
         */
        public Builder withHeader(final String title, final String imageUrl) {
            this.title = title;
            this.imageUrl = imageUrl;
            return this;
        }

        /**
         * When subtitle is set, then default subtitle will be replaced on the screen with it.
         *
         * @param subtitle subtitle text
         * @return builder
         */
        /* default */ Builder withSubtitle(final String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        /**
         * If value is set, then receipt view will appear.
         *
         * @param paymentId the receipt id to be shown.
         * @param shouldShowReceipt if the receipt should be drawn, default value is "false"
         * @param receiptAction a button that takes you to the payment receipt
         * @return builder
         */
        public Builder withReceipt(final Long paymentId, final boolean shouldShowReceipt,
            final PaymentCongratsResponse.Action receiptAction) {
            this.paymentId = paymentId;
            this.receiptAction = receiptAction;
            this.shouldShowReceipt = shouldShowReceipt;
            return this;
        }

        /**
         * if help is set, then a small box with help instructions will appear
         *
         * @param help a help message
         * @return builder
         */
        public Builder withHelp(final String help) {
            this.help = help;
            return this;
        }

        /**
         * @param iconId header's icon
         * @return builder
         */
        public Builder withIconId(final int iconId) {
            this.iconId = iconId;
            return this;
        }

        /**
         * @param paymentInfo a object containing the info of the payment made
         * @return builder
         */
        public Builder withPaymentMethodInfo(final PaymentInfo paymentInfo) {
            paymentsInfo.add(paymentInfo);
            return this;
        }

        /**
         * @param paymentInfo a object containing the info of the payment made
         * @return builder
         */
        public Builder withSplitPaymentMethod(final PaymentInfo paymentInfo) {
            paymentsInfo.add(paymentInfo);
            return this;
        }

        /**
         * if Exit action is set, then a big primary button will appear and the click action will trigger a resCode that
         * will be the same of the Exit action added.
         *
         * @param label text show in primary action
         * @param resCode resCode in exit case
         * @return builder
         */
        public Builder withFooterMainAction(final String label, final int resCode) {
            this.exitActionPrimary = new ExitAction(label, resCode);
            return this;
        }

        /**
         * if Exit action is set, then a big secondary button will appear and the click action will trigger a resCode
         * that will be the same of the Exit action added.
         *
         * @param label text show in secondary action
         * @param resCode resCode in exit case
         * @return builder
         */
        public Builder withFooterSecondaryAction(final String label, final int resCode) {
            this.exitActionSecondary = new ExitAction(label, resCode);
            return this;
        }

        /**
         * If "shouldShowPaymentMethod" is set to true and the payment method is credit card then the
         * statementDescription will be shown on payment method view.
         *
         * @param statementDescription disclaimer text
         * @return builder
         */
        public Builder withStatementDescription(final String statementDescription) {
            this.statementDescription = statementDescription;
            return this;
        }

        /**
         * If value true is set, then payment method box will appear with the amount value and payment method options
         * that were selected by the user.
         *
         * @param shouldShowPaymentMethod visibility mode, default value is "false"
         * @return builder
         */
        public Builder withShouldShowPaymentMethod(final boolean shouldShowPaymentMethod) {
            this.shouldShowPaymentMethod = shouldShowPaymentMethod;
            return this;
        }

        /**
         * Custom fragment that will appear before payment method description inside Business result screen.
         *
         * @param externalFragment a fragment to be displayed
         * @return builder
         */

        /* default */ Builder withTopFragment(@NonNull final ExternalFragment externalFragment) {
            this.topFragment = externalFragment;
            return this;
        }

        /**
         * Custom fragment that will appear before payment method description inside Business result screen.
         *
         * @param zClass fragment class
         * @param args args for fragment class
         * @return builder
         */
        public Builder withTopFragment(@NonNull final Class<? extends Fragment> zClass, @Nullable final Bundle args) {
            this.topFragment = new ExternalFragment(zClass, args);
            return this;
        }

        /**
         * Custom fragment that will appear after payment method description inside Business result screen.
         *
         * @param externalFragment a fragment to be displayed
         * @return builder
         */

        /* default */ Builder withBottomFragment(@NonNull final ExternalFragment externalFragment) {
            this.bottomFragment = externalFragment;
            return this;
        }

        /**
         * Custom fragment that will appear after payment method description inside Business result screen.
         *
         * @param zClass fragment class
         * @param args args for fragment class
         * @return builder
         */
        public Builder withBottomFragment(@NonNull final Class<? extends Fragment> zClass,
            @Nullable final Bundle args) {
            this.bottomFragment = new ExternalFragment(zClass, args);
            return this;
        }

        /**
         * Custom fragment that will appear at the top of all information inside Business result screen.
         *
         * @param externalFragment a fragment to be displayed
         * @return builder
         */

        /* default */ Builder withImportantFragment(@NonNull final ExternalFragment externalFragment) {
            this.importantFragment = externalFragment;
            return this;
        }

        /**
         * Custom fragment that will appear at the top of all information inside Business result screen.
         *
         * @param zClass fragment class
         * @param args args for fragment class
         * @return builder
         */
        public Builder withImportantFragment(@NonNull final Class<? extends Fragment> zClass,
            @Nullable final Bundle args) {
            this.importantFragment = new ExternalFragment(zClass, args);
            return this;
        }

        /**
         * @param loyalty an object containing the needed info to display loyalty MLBusinessComponent
         * @return builder with the added object
         */
        public Builder withLoyalty(final PaymentCongratsResponse.Loyalty loyalty) {
            this.loyalty = loyalty;
            return this;
        }

        /**
         * @param discount an object containing the needed info to display discount MLBusinessComponent
         * @return builder with the added object
         */
        public Builder withDiscounts(final PaymentCongratsResponse.Discount discount) {
            this.discount = discount;
            return this;
        }

        /**
         * @param crossSelling a list of crossSelling objects containing the needed info to display the cross selling
         * MLBusinessComponent
         * @return builder with the added object
         */
        public Builder withCrossSelling(final List<PaymentCongratsResponse.CrossSelling> crossSelling) {
            this.crossSelling = crossSelling;
            return this;
        }

        /**
         * @param expenseSplit an object containing the needed info to display the money split MLBusinessComponent
         * @return builder with the added object
         */
        public Builder withExpenseSplit(final PaymentCongratsResponse.ExpenseSplit expenseSplit) {
            this.expenseSplit = expenseSplit;
            return this;
        }

        /**
         * @param customSorting allows to activate custom order
         * @return builder with the added boolean
         */
        /* default */ Builder withCustomSorting(final boolean customSorting) {
            this.customSorting = customSorting;
            return this;
        }

        public Builder withTracking(@NonNull final PXPaymentCongratsTracking tracking) {
            pxPaymentCongratsTracking = tracking;
            return this;
        }

        /**
         * @param flow the name of the flow building the congrats (e.g "buyer_qr")
         * @return builder with the added String
         */
        /* default */ Builder withFlow(final String flow) {
            this.flow = flow;
            return this;
        }

        /**
         * @param paymentStatus the status of the payment
         * @return builder with the added String
         */
        /* default */ Builder withPaymentStatus(final String paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        /**
         * @param paymentData info about the payment for internal tracking purposes
         * @return builder with the added paymentData
         */
        /* default */ Builder withPaymentData(final PaymentData paymentData) {
            this.paymentData = paymentData;
            return this;
        }

        /**
         * @param discountCouponsAmount the total amount of discounts from the coupons
         * @return builder with the added discount coupons amount
         */
        /* default */ Builder withDiscountCouponsAmount(final BigDecimal discountCouponsAmount) {
            this.discountCouponsAmount = discountCouponsAmount;
            return this;
        }
    }
}
