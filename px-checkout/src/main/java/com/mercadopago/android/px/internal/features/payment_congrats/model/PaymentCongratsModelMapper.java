package com.mercadopago.android.px.internal.features.payment_congrats.model;

import com.mercadopago.android.px.internal.viewmodel.BusinessPaymentModel;
import com.mercadopago.android.px.model.BusinessPayment;
import com.mercadopago.android.px.model.internal.CongratsResponse;
import java.util.ArrayList;
import java.util.List;

class PaymentCongratsModelMapper {

    public PaymentCongratsModel map(final BusinessPaymentModel businessPaymentModel) {
        final BusinessPayment businessPayment = businessPaymentModel.getPayment();
        final PaymentCongratsModel.Builder builder = new PaymentCongratsModel.Builder()
            .withCongratsType(
                PaymentCongratsModel.CongratsType.fromName(businessPayment.getPaymentStatus()))
            .withCrossSelling(getCrossSelling(businessPaymentModel.getCongratsResponse().getCrossSellings()))
            .withCurrencyDecimalPlaces(businessPaymentModel.getCurrency().getDecimalPlaces())
            .withCurrencyDecimalSeparator(businessPaymentModel.getCurrency().getDecimalSeparator())
            .withCurrencySymbol(businessPaymentModel.getCurrency().getSymbol())
            .withCurrencyThousandsSeparator(businessPaymentModel.getCurrency().getThousandsSeparator())
            .withDiscount(getDiscount(businessPaymentModel.getCongratsResponse().getDiscount()))
            .withExitActionPrimary(businessPayment.getPrimaryAction().getName(),
                businessPayment.getPrimaryAction().getResCode())
            .withExitActionSecondary(businessPayment.getSecondaryAction().getName(),
                businessPayment.getSecondaryAction().getResCode())
            .withShouldShowPaymentMethod(businessPayment.shouldShowPaymentMethod())
            .withHelp(businessPayment.getHelp())
            .withIconId(businessPayment.getIcon())
            .withImageUrl(businessPayment.getImageUrl())
            .withBottomFragment(businessPayment.getBottomFragment())
            .withTopFragment(businessPayment.getTopFragment())
            .withImportantFragment(businessPayment.getImportantFragment())
            ;

        return builder.build();
    }

    private PaymentCongratsResponse.Score getScore(final CongratsResponse.Score score) {

        return new PaymentCongratsResponse.Score(
            new PaymentCongratsResponse.Score.Progress(score.getProgress().getPercentage(),
                score.getProgress().getColor(), score.getProgress().getLevel()), score.getTitle(),
            new PaymentCongratsResponse.Action(score.getAction().getLabel(), score.getAction().getTarget()));
    }

    private List<PaymentCongratsResponse.CrossSelling> getCrossSelling(
        final Iterable<CongratsResponse.CrossSelling> crossSellings) {
        final List<PaymentCongratsResponse.CrossSelling> crossSellingList = new ArrayList<>();
        for (final CongratsResponse.CrossSelling crossSelling : crossSellings) {
            crossSellingList
                .add(new PaymentCongratsResponse.CrossSelling(crossSelling.getTitle(), crossSelling.getIcon(),
                    new PaymentCongratsResponse.Action(crossSelling.getAction().getLabel(),
                        crossSelling.getAction().getTarget()), crossSelling.getContentId()));
        }
        return crossSellingList;
    }

    private PaymentCongratsResponse.Discount getDiscount(final CongratsResponse.Discount discount) {
        final PaymentCongratsResponse.Action action =
            new PaymentCongratsResponse.Action(discount.getAction().getLabel(), discount.getAction().getTarget());
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
        return new PaymentCongratsResponse.Discount(discount.getTitle(), discount.getSubtitle(), action
            , new PaymentCongratsResponse.Discount.DownloadApp(discount.getActionDownload().getTitle(), action),
            touchpoint, getDiscountItems(discount.getItems()));
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
}
