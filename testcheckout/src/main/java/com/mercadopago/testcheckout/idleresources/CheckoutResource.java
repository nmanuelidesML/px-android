package com.mercadopago.testcheckout.idleresources;

import android.support.test.InstrumentationRegistry;
import com.mercadopago.lite.util.HttpClientUtil;
import com.mercadopago.testlib.HttpResource;
import okhttp3.OkHttpClient;

public class CheckoutResource extends HttpResource {
    @Override
    protected OkHttpClient getClient() {
        return HttpClientUtil.getClient(InstrumentationRegistry.getContext(), 10, 10, 10);
    }
}
