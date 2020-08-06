package com.mercadopago.android.px.internal.features.payment_congrats.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mercadopago.android.px.internal.features.business_result.CongratsResponseMapper;
import com.mercadopago.android.px.internal.util.CurrenciesUtil;
import com.mercadopago.android.px.internal.util.PaymentDataHelper;
import com.mercadopago.android.px.internal.viewmodel.BusinessPaymentModel;
import com.mercadopago.android.px.model.BusinessPayment;
import com.mercadopago.android.px.model.Currency;
import com.mercadopago.android.px.model.PaymentData;
import com.mercadopago.android.px.model.internal.Action;
import com.mercadopago.android.px.model.internal.CongratsResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class PaymentCongratsModelMapper {

    /**
     * Takes a BusinessPaymentModel and outputs a PaymentCongratsModel
     *
     * @param businessPaymentModel the data to be converted
     * @return a paymentCongratsModel built from a businessPaymentModel
     */
    public PaymentCongratsModel map(final BusinessPaymentModel businessPaymentModel) {
        final PaymentCongratsResponse paymentCongratsResponse =
            new CongratsResponseMapper().map(businessPaymentModel.getCongratsResponse());
        final BusinessPayment businessPayment = businessPaymentModel.getPayment();
        final PaymentCongratsModel.Builder builder = new PaymentCongratsModel.Builder()
            .withCongratsType(
                PaymentCongratsModel.CongratsType.fromName(businessPayment.getPaymentStatus()))
            .withCrossSelling(paymentCongratsResponse.getCrossSellings())
            .withTitle(businessPayment.getTitle() + " (PaymentCongrats)")
            .withShouldShowPaymentMethod(businessPayment.shouldShowPaymentMethod())
            .withShouldShowReceipt(businessPayment.shouldShowReceipt())
            .withIconId(businessPayment.getIcon())
            .withPaymentsInfo(getPaymentsInfo(businessPaymentModel.getPaymentResult().getPaymentDataList(),
                businessPaymentModel.getCurrency()));

        if (businessPayment.getPrimaryAction() != null && businessPayment.getPrimaryAction().getName() != null) {
            builder.withExitActionPrimary(businessPayment.getPrimaryAction().getName(),
                businessPayment.getPrimaryAction().getResCode());
        }
        if (businessPayment.getSecondaryAction() != null && businessPayment.getSecondaryAction().getName() != null) {
            builder.withExitActionSecondary(businessPayment.getSecondaryAction().getName(),
                businessPayment.getSecondaryAction().getResCode());
        }
        if (businessPayment.getHelp() != null) {
            builder.withHelp(businessPayment.getHelp());
        }
        if (paymentCongratsResponse.getDiscount() != null) {
            builder.withDiscount(paymentCongratsResponse.getDiscount());
        }
        if (businessPayment.getImageUrl() != null) {
            builder.withImageUrl(businessPayment.getImageUrl());
        }
        if (businessPayment.getBottomFragment() != null) {
            builder.withBottomFragment(businessPayment.getBottomFragment());
        }
        if (businessPayment.getTopFragment() != null) {
            builder.withTopFragment(businessPayment.getTopFragment());
        }
        if (businessPayment.getImportantFragment() != null) {
            builder.withImportantFragment(businessPayment.getImportantFragment());
        }
        if (paymentCongratsResponse.getMoneySplit() != null) {
            builder.withMoneySplit(paymentCongratsResponse.getMoneySplit());
        }
        if (paymentCongratsResponse.getScore() != null) {
            builder.withScore(paymentCongratsResponse.getScore());
        }
        if (businessPayment.getReceipt() != null) {
            builder.withReceiptId(businessPayment.getReceipt());
        }
        if (businessPayment.getStatementDescription() != null) {
            builder.withStatementDescription(businessPayment.getStatementDescription());
        }
        if (businessPayment.getSubtitle() != null) {
            builder.withSubtitle(businessPayment.getSubtitle());
        }
        if (paymentCongratsResponse.getViewReceipt() != null) {
            builder.withViewReceipt(paymentCongratsResponse.getViewReceipt());
        }

        return builder.build();
    }

    private PaymentCongratsResponse.Score getScore(final CongratsResponse.Score score) {

        return new PaymentCongratsResponse.Score(
            new PaymentCongratsResponse.Score.Progress(score.getProgress().getPercentage(),
                score.getProgress().getColor(), score.getProgress().getLevel()), score.getTitle(),
            getAction(score.getAction()));
    }

    private List<PaymentCongratsResponse.CrossSelling> getCrossSelling(
        final Iterable<CongratsResponse.CrossSelling> crossSellings) {
        final List<PaymentCongratsResponse.CrossSelling> crossSellingList = new ArrayList<>();
        for (final CongratsResponse.CrossSelling crossSelling : crossSellings) {
            crossSellingList
                .add(new PaymentCongratsResponse.CrossSelling(crossSelling.getTitle(), crossSelling.getIcon(),
                    getAction(crossSelling.getAction()), crossSelling.getContentId()));
        }
        return crossSellingList;
    }

    private PaymentCongratsResponse.Discount getDiscount(final CongratsResponse.Discount discount) {
        final PaymentCongratsResponse.AdditionalEdgeInsets additionalEdgeInsets =
            discount.getTouchpoint().getAdditionalEdgeInsets() == null ? null :
                new PaymentCongratsResponse.AdditionalEdgeInsets(
                    discount.getTouchpoint().getAdditionalEdgeInsets().getTop(),
                    discount.getTouchpoint().getAdditionalEdgeInsets().getLeft(),
                    discount.getTouchpoint().getAdditionalEdgeInsets().getBottom(),
                    discount.getTouchpoint().getAdditionalEdgeInsets().getRight());
        final PaymentCongratsResponse.PXBusinessTouchpoint touchpoint =
            new PaymentCongratsResponse.PXBusinessTouchpoint(discount.getTouchpoint().getId(),
                discount.getTouchpoint().getType(), discount.getTouchpoint().getContent(),
                discount.getTouchpoint().getTracking(), additionalEdgeInsets);

        return new PaymentCongratsResponse.Discount(discount.getTitle(), discount.getSubtitle(),
            getAction(discount.getAction())
            , new PaymentCongratsResponse.Discount.DownloadApp(discount.getActionDownload().getTitle(),
            getAction(discount.getActionDownload().getAction())),
            touchpoint, getDiscountItems(discount.getItems()));
    }

    @Nullable
    private PaymentCongratsResponse.MoneySplit getMoneySplit(@Nullable final CongratsResponse.MoneySplit moneySplit) {
        return moneySplit == null ? null
            : new PaymentCongratsResponse.MoneySplit(
                new PaymentCongratsText(moneySplit.getTitle().getMessage(),
                    moneySplit.getTitle().getBackgroundColor(), moneySplit.getTitle().getTextColor(),
                    moneySplit.getTitle().getWeight()), getAction(moneySplit.getAction()),
                moneySplit.getImageUrl());
    }

    @NotNull
    private PaymentCongratsResponse.Action getAction(final Action action) {
        return new PaymentCongratsResponse.Action(action.getLabel(), action.getTarget());
    }

    private List<PaymentCongratsResponse.Discount.Item> getDiscountItems(
        final List<CongratsResponse.Discount.Item> items) {
        final List<PaymentCongratsResponse.Discount.Item> discountItems = new ArrayList<>();
        for (final CongratsResponse.Discount.Item item : items) {
            discountItems.add(
                new PaymentCongratsResponse.Discount.Item(item.getTitle(), item.getSubtitle(), item.getIcon(),
                    item.getTarget(), item.getCampaignId()));
        }
        return discountItems;
    }

    private List<PaymentInfo> getPaymentsInfo(final Iterable<PaymentData> paymentDataList, final Currency currency) {
        final List<PaymentInfo> paymentsInfoList = new ArrayList<>();
        for (final PaymentData paymentData : paymentDataList) {
            final PaymentInfo.Builder paymentInfo = new PaymentInfo.Builder()
                .withPaymentMethodType(
                    PaymentInfo.PaymentMethodType.fromName(paymentData.getPaymentMethod().getPaymentTypeId()))
                .withPaymentMethodId(paymentData.getPaymentMethod().getId())
                .withPaymentMethodName(paymentData.getPaymentMethod().getName())
                .withAmountPaid(getPrettyAmount(currency,
                    PaymentDataHelper.getPrettyAmountToPay(paymentData)));
            if (paymentData.getToken() != null && paymentData.getToken().getLastFourDigits() != null) {
                paymentInfo.withLastFourDigits(paymentData.getToken().getLastFourDigits());
            }
            if (paymentData.getPayerCost() != null) {
                paymentInfo.withInstallmentsData(
                    paymentData.getPayerCost().getInstallments(),
                    getPrettyAmount(currency, paymentData.getPayerCost().getInstallmentAmount()),
                    getPrettyAmount(currency, paymentData.getPayerCost().getTotalAmount()),
                    paymentData.getPayerCost().getInstallmentRate());
            }
            paymentsInfoList.add(paymentInfo.build());
        }
        return paymentsInfoList;
    }

    private String getPrettyAmount(@NonNull final Currency currency, @NonNull final BigDecimal amount) {
        return CurrenciesUtil.getLocalizedAmountWithoutZeroDecimals(currency, amount);
    }
}
