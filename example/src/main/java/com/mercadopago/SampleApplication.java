package com.mercadopago;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.mercadopago.android.px.addons.ESCManagerBehaviour;
import com.mercadopago.android.px.addons.FakeEscManagerBehaviourImpl;
import com.mercadopago.android.px.addons.FakeLocaleBehaviourImpl;
import com.mercadopago.android.px.addons.MockSecurityBehaviour;
import com.mercadopago.android.px.addons.PXBehaviourConfigurer;
import com.mercadopago.android.px.di.Dependencies;
import com.mercadopago.android.px.font.FontConfigurator;
import com.mercadopago.android.px.internal.util.HttpClientUtil;
import com.squareup.leakcanary.LeakCanary;
import okhttp3.OkHttpClient;

public class SampleApplication extends Application {

    @Override
    protected void attachBaseContext(final Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeLeakCanary();
        FontConfigurator.configure();
    }

    private void initializeLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        Stetho.initializeWithDefaults(this);

        // Create client base, add interceptors
        OkHttpClient.Builder baseClient = HttpClientUtil.createBaseClient(this, 10, 10, 10)
            .addNetworkInterceptor(new StethoInterceptor());

        // customClient: client with TLS protocol setted
        final OkHttpClient customClient = HttpClientUtil.enableTLS12(baseClient)
            .build();

        HttpClientUtil.setCustomClient(customClient);

        Dependencies.getInstance().initialize(getApplicationContext());

        final ESCManagerBehaviour escManagerBehaviour = new FakeEscManagerBehaviourImpl();
        new PXBehaviourConfigurer()
            .with(new MockSecurityBehaviour(escManagerBehaviour))
            .with(escManagerBehaviour)
            .with(FakeLocaleBehaviourImpl.INSTANCE)
            .configure();
    }
}