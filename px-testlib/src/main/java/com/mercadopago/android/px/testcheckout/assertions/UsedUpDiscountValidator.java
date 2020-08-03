package com.mercadopago.android.px.testcheckout.assertions;

import android.support.annotation.NonNull;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;
import com.mercadopago.android.px.R;
import com.mercadopago.android.px.testcheckout.pages.DiscountDetailPage;
import com.mercadopago.android.px.testcheckout.pages.InstallmentsPage;
import com.mercadopago.android.px.testcheckout.pages.OneTapPage;
import com.mercadopago.android.px.testcheckout.pages.PaymentMethodPage;
import org.hamcrest.Matcher;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasTextColor;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class UsedUpDiscountValidator extends DefaultValidator {
    @Override
    public void validate(@NonNull final DiscountDetailPage discountDetailPage) {
        final Matcher<View> summary = withId(com.mercadopago.android.px.R.id.summary);
        final Matcher<View> description = withId(com.mercadopago.android.px.R.id.description);
        final Matcher<View> detail = withId(com.mercadopago.android.px.R.id.detail);
        onView(summary).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(description).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(detail).check(matches(withText(com.mercadopago.android.px.R.string.px_used_up_discount_detail)));
    }

    @Override
    public void validate(@NonNull final PaymentMethodPage paymentMethodPage) {
        validateAmountView();
    }

    @Override
    public void validate(@NonNull final InstallmentsPage installmentsPage) {
        validateAmountView();
    }

    @Override
    public void validate(@NonNull final OneTapPage oneTapPage) {
        // TODO implement.
    }

    private void validateAmountView() {
        final Matcher<View> amountDescription = withId(R.id.amount_description);
        final Matcher<View> maxCouponAmount = withId(R.id.max_coupon_amount);
        final Matcher<View> amountBeforeDiscount =
            withId(R.id.amount_before_discount);
        final Matcher<View> finalAmount = withId(R.id.final_amount);
        final Matcher<View> arrow = withId(R.id.blue_arrow);
        onView(amountDescription).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(maxCouponAmount).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(amountBeforeDiscount).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(finalAmount).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(arrow).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(amountDescription).check(matches(withText(
            getInstrumentation().getTargetContext().getString(R.string.px_used_up_discount_row))));
        onView(amountDescription).check(matches(hasTextColor(R.color.px_form_text)));
    }
}