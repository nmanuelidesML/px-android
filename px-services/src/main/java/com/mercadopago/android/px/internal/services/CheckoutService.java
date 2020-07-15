package com.mercadopago.android.px.internal.services;

import android.support.annotation.Nullable;
import com.mercadopago.android.px.internal.callbacks.MPCall;
import com.mercadopago.android.px.model.PaymentMethod;
import com.mercadopago.android.px.model.PaymentMethodSearch;
import com.mercadopago.android.px.model.internal.InitResponse;
import com.mercadopago.android.px.services.BuildConfig;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CheckoutService {

    String CHECKOUT_VERSION = "v2";
    String ENVIRONMENT = BuildConfig.API_ENVIRONMENT_NEW;
//"https://run.mocky.io/v3/8a4b5d87-8a03-4e82-8942-09dfadcb7cf5/" xxxhdpi
//"https://run.mocky.io/v3/6df50412-affb-41b6-9357-62d87354bcc5/" xxhdpi
//"https://run.mocky.io/v3/4c600568-5816-4891-9170-27dd7031dcdf/" varios descuentos
//"https://run.mocky.io/v3/87e083ed-3f14-4784-a68f-2db0ed0fc952/" has_not_discount
//"https://run.mocky.io/v3/bdcd2223-cd0c-429e-942d-9aca7a9a63a8/" grupos

//"https://run.mocky.io/v3/22ecd520-fe81-4f79-b42f-5e4723415b14/" varios descuentos hdpi
//"https://run.mocky.io/v3/5351208f-213d-4ef0-afd2-7729b92144da/" varios descuentos con split hdpi
//"https://run.mocky.io/v3/c6f8cc8b-65bd-4f9e-b9d2-690a446766c7/" varios descuentos xxhdpi

    @POST(ENVIRONMENT + "/px_mobile/" + CHECKOUT_VERSION + "/checkout")
    MPCall<InitResponse> checkout(
        @Query("access_token") String privateKey,
        @Body Map<String, Object> body);

    @POST(ENVIRONMENT + "/px_mobile/" + CHECKOUT_VERSION + "/checkout/{preference_id}")
    MPCall<InitResponse> checkout(
        @Path(value = "preference_id", encoded = true) String preferenceId,
        @Query("access_token") String privateKey,
        @Body Map<String, Object> body);

    /**
     * Old api call version ; used by MercadoPagoServices.
     *
     * @param publicKey
     * @param amount
     * @param excludedPaymentTypes
     * @param excludedPaymentMethods
     * @param siteId
     * @param processingMode
     * @param cardsWithEsc
     * @param differentialPricingId
     * @param defaultInstallments
     * @param expressEnabled
     * @param accessToken
     * @return payment method search
     */
    @GET("{environment}/px_mobile_api/payment_methods?api_version=1.8")
    MPCall<PaymentMethodSearch> getPaymentMethodSearch(
        @Path(value = "environment", encoded = true) String environment,
        @Query("public_key") String publicKey,
        @Query("amount") BigDecimal amount,
        @Query("excluded_payment_types") String excludedPaymentTypes,
        @Query("excluded_payment_methods") String excludedPaymentMethods,
        @Query("site_id") String siteId,
        @Query("processing_mode") String processingMode,
        @Query("cards_esc") String cardsWithEsc,
        @Nullable @Query("differential_pricing_id") Integer differentialPricingId,
        @Nullable @Query("default_installments") final Integer defaultInstallments,
        @Query("express_enabled") final boolean expressEnabled,
        @Nullable @Query("access_token") String accessToken);

    @GET("{environment}/payment_methods")
    MPCall<List<PaymentMethod>> getPaymentMethods(
        @Path(value = "environment", encoded = true) String environment,
        @Query("public_key") String publicKey,
        @Query("access_token") String privateKey);
}