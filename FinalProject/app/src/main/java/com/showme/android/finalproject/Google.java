package com.showme.android.finalproject;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by ShowMe on 3/30/16.
 */
public class Google {

    public Google() {
    }

    private GoogleApiClient mGoogleApiClient;

    public GoogleApiClient getmGoogleApiClient() {
        return mGoogleApiClient;
    }

    public void setmGoogleApiClient(GoogleApiClient mGoogleApiClient) {
        this.mGoogleApiClient = mGoogleApiClient;
    }

    private static Google ourInstance = null;

    public static Google getInstance() {
        if (ourInstance == null) {
            ourInstance = new Google();
        }
        return ourInstance;
    }
}
