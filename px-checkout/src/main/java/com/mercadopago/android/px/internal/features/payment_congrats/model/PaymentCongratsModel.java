package com.mercadopago.android.px.internal.features.payment_congrats.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.mercadopago.android.px.internal.util.TextUtil;
import com.mercadopago.android.px.model.ExitAction;
import com.mercadopago.android.px.model.ExternalFragment;
import com.mercadopago.android.px.model.internal.remedies.RemediesResponse;
import java.math.BigDecimal;
import java.util.List;
import org.jetbrains.annotations.NotNull;

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
    @Nullable private final Long paymentId;
    private final boolean shouldShowReceipt;
    // Exit Buttons
    @Nullable private final ExitAction exitActionPrimary;
    @Nullable private final ExitAction exitActionSecondary;
    // custom views for integrators
    @Nullable private final ExternalFragment topFragment;
    @Nullable private final ExternalFragment bottomFragment;
    @Nullable private final ExternalFragment importantFragment;
    @Nullable private final PaymentCongratsResponse paymentCongratsResponse;
    @Nullable private final BigDecimal totalAmount;
    @NonNull private final String currencyId;
    //Tracking data
    @Nullable private String campaignId;
    @NonNull private String flow;
    @NonNull private String paymentStatus;
    @NonNull private String paymentStatusDetail;
    @NonNull private RemediesResponse remedies;

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
        totalAmount = builder.totalAmount;
        shouldShowReceipt = builder.shouldShowReceipt;
        topFragment = builder.topFragment;
        bottomFragment = builder.bottomFragment;
        importantFragment = builder.importantFragment;
        paymentCongratsResponse = builder.paymentCongratsResponse;
        campaignId = builder.campaignId;
        flow = builder.flow;
        currencyId = builder.currencyId;
        paymentStatus = builder.paymentStatus;
        paymentStatusDetail = builder.paymentStatusDetail;
        remedies = builder.remedies;
    }

    protected PaymentCongratsModel(final Parcel in) {
        congratsType = CongratsType.fromName(in.readString());
        title = in.readString();
        subtitle = in.readString();
        imageUrl = in.readString();
        help = in.readString();
        iconId = in.readInt();
        paymentId = in.readLong();
        exitActionPrimary = in.readParcelable(ExitAction.class.getClassLoader());
        exitActionSecondary = in.readParcelable(ExitAction.class.getClassLoader());
        statementDescription = in.readString();
        shouldShowPaymentMethod = (Boolean) in.readValue(Boolean.class.getClassLoader());
        paymentsInfo = in.createTypedArrayList(PaymentInfo.CREATOR);
        if (in.readByte() == 0) {
            totalAmount = null;
        } else {
            totalAmount = new BigDecimal(in.readString());
        }
        shouldShowReceipt = (Boolean) in.readValue(Boolean.class.getClassLoader());
        topFragment = in.readParcelable(ExternalFragment.class.getClassLoader());
        bottomFragment = in.readParcelable(ExternalFragment.class.getClassLoader());
        importantFragment = in.readParcelable(ExternalFragment.class.getClassLoader());
        paymentCongratsResponse = in.readParcelable(PaymentCongratsResponse.class.getClassLoader());
        campaignId = in.readString();
        flow = in.readString();
        currencyId = in.readString();
        paymentStatus = in.readString();
        paymentStatusDetail = in.readString();
        remedies = in.readParcelable(RemediesResponse.class.getClassLoader());
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
        dest.writeParcelable(exitActionPrimary, flags);
        dest.writeParcelable(exitActionSecondary, flags);
        dest.writeString(statementDescription);
        dest.writeValue(shouldShowPaymentMethod);
        dest.writeTypedList(paymentsInfo);
        if (totalAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(totalAmount.toString());
        }
        dest.writeValue(shouldShowReceipt);
        dest.writeParcelable(topFragment, flags);
        dest.writeParcelable(bottomFragment, flags);
        dest.writeParcelable(importantFragment, flags);
        dest.writeParcelable(paymentCongratsResponse, flags);
        dest.writeString(campaignId);
        dest.writeString(flow);
        dest.writeString(currencyId);
        dest.writeString(paymentStatus);
        dest.writeString(paymentStatusDetail);
        dest.writeParcelable(remedies, flags);
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

    public int getIconId() {
        return iconId;
    }

    @org.jetbrains.annotations.Nullable
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
    public BigDecimal getTotalAmount() {
        return totalAmount;
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

    @Nullable
    public String getCampaignId() {
        return campaignId;
    }

    @NonNull
    public String getFlow() {
        return flow;
    }

    @NonNull
    public String getCurrencyId() {
        return currencyId;
    }

    @NonNull
    public String getPaymentStatus() {
        return paymentStatus;
    }

    @NonNull
    public String getPaymentStatusDetail() {
        return paymentStatusDetail;
    }

    @NonNull
    public RemediesResponse getRemedies() {
        return remedies;
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
        /* default */ List<PaymentInfo> paymentsInfo;
        /* default */ BigDecimal totalAmount;
        /* default */ String currencyId;

        /* default */ Long paymentId;

        // Exit Buttons
        /* default */ ExitAction exitActionPrimary;
        /* default */ ExitAction exitActionSecondary;

        /* default */ String statementDescription;

        /* default */ boolean shouldShowPaymentMethod = false;
        /* default */ boolean shouldShowReceipt = false;

        // custom views for integrators
        /* default */ ExternalFragment topFragment;
        /* default */ ExternalFragment bottomFragment;
        /* default */ ExternalFragment importantFragment;

        /* default */ PaymentCongratsResponse paymentCongratsResponse;

        // MLBusinessComponents
        /* default */ PaymentCongratsResponse.Score score;
        /* default */ PaymentCongratsResponse.Discount discount;
        /* default */ List<PaymentCongratsResponse.CrossSelling> crossSelling;
        /* default */ PaymentCongratsResponse.MoneySplit moneySplit;
        /* default */ PaymentCongratsResponse.Action viewReceipt;
        /* default */ boolean customOrder = false;

        //Tracking data
        /* default */ String campaignId;
        /* default */ String flow;
        /* default */ String paymentStatus;
        /* default */ String paymentStatusDetail;
        /* default */ RemediesResponse remedies;

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
                new PaymentCongratsResponse(score, discount, moneySplit, crossSelling, viewReceipt,
                    customOrder);

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
         * Title shown in the congrats's header
         *
         * @param title congrats's title
         * @return builder
         */
        public Builder withTitle(final String title) {
            this.title = title;
            return this;
        }

        /**
         * When subtitle is set, then default subtitle will be replaced on the screen with it.
         *
         * @param subtitle subtitle text
         * @return builder
         */
        public Builder withSubtitle(final String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        /**
         * Sets up the image in congrats's header
         *
         * @param imageUrl url for the header's image
         * @return builder
         */
        public Builder withImageUrl(final String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        /**
         * If value is set, then paymentId view will appear.
         *
         * @param paymentId the id of the payment to be shown.
         * @return builder
         */
        public Builder withPaymentId(final Long paymentId) {
            this.paymentId = paymentId;
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
         * @param paymentsInfo a list containing the info of the payments made
         * @return builder
         */
        public Builder withPaymentsInfo(final List<PaymentInfo> paymentsInfo) {
            this.paymentsInfo = paymentsInfo;
            return this;
        }

        /**
         * @param totalAmount the total amount of the payment
         * @return builder
         */
        /* default */ Builder withTotalAmount(@Nullable final BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
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
        public Builder withExitActionPrimary(final String label, final int resCode) {
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
        public Builder withExitActionSecondary(final String label, final int resCode) {
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
         * Override the receipt drawing, without depending on the receipt id
         *
         * @param shouldShowReceipt if the receipt should be drawn, default value is "false"
         * @return builder
         */
        public Builder withShouldShowReceipt(final boolean shouldShowReceipt) {
            this.shouldShowReceipt = shouldShowReceipt;
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
         * @param score an object containing the needed info to display score MLBusinessComponent
         * @return builder with the added object
         */
        public Builder withScore(final PaymentCongratsResponse.Score score) {
            this.score = score;
            return this;
        }

        /**
         * @param discount an object containing the needed info to display discount MLBusinessComponent
         * @return builder with the added object
         */
        public Builder withDiscount(final PaymentCongratsResponse.Discount discount) {
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
         * @param moneySplit an object containing the needed info to display the money split MLBusinessComponent
         * @return builder with the added object
         */
        public Builder withMoneySplit(final PaymentCongratsResponse.MoneySplit moneySplit) {
            this.moneySplit = moneySplit;
            return this;
        }

        /**
         * @param viewReceipt a button that takes you to the payment receipt
         * @return builder with the added object
         */
        public Builder withViewReceipt(final PaymentCongratsResponse.Action viewReceipt) {
            this.viewReceipt = viewReceipt;
            return this;
        }

        /**
         * @param customOrder allows to activate custom order
         * @return builder with the added boolean
         */
        /* default */ Builder withCustomOrder(final boolean customOrder) {
            this.customOrder = customOrder;
            return this;
        }

        /**
         * @param campaignId the id of the campaign for the payment
         * @return builder with the added String
         */
        /* default */ Builder withCampaignId(@Nullable final String campaignId) {
            this.campaignId = campaignId;
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
         * @param currencyId the id of the currency being used to pay
         * @return builder with the added String
         */
        /* default */ Builder withCurrencyId(final String currencyId) {
            this.currencyId = currencyId;
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
         * @param paymentStatusDetail a description for the payment status
         * @return builder with the added String
         */
        /* default */ Builder withPaymentStatusDetail(final String paymentStatusDetail) {
            this.paymentStatusDetail = paymentStatusDetail;
            return this;
        }

        /**
         * @param remedies the remedies
         * @return builder with the added remediesResponse
         */
        /* default */ Builder withRemedies(final RemediesResponse remedies) {
            this.remedies = remedies;
            return this;
        }
    }
}
