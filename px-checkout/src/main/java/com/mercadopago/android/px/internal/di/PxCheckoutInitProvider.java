package com.mercadopago.android.px.internal.di;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import com.mercadopago.android.px.internal.core.ConnectionHelper;

public class PxCheckoutInitProvider extends ContentProvider {

    @Override
    public boolean onCreate() {
        //Fix for loading some vector images in api < 21
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }
        NetworkModule.initialize(getContext());
        Session.initialize(getContext(), NetworkModule.INSTANCE);
        ConnectionHelper.getInstance().initialize(getContext());
        return false;
    }

    @Override
    public void attachInfo(final Context context, final ProviderInfo info) {
        super.attachInfo(context, info);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull final Uri uri, @Nullable final String[] projection, @Nullable final String selection,
        @Nullable final String[] selectionArgs, @Nullable final String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull final Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull final Uri uri, @Nullable final ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull final Uri uri, @Nullable final String selection, @Nullable final String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull final Uri uri, @Nullable final ContentValues values, @Nullable final String selection,
        @Nullable final String[] selectionArgs) {
        return 0;
    }
}