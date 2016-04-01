package com.showme.android.finalproject.Singletons;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by ShowMe on 3/30/16.
 */
public class GoogleSingleton {

    public GoogleSingleton() {
    }

    String loginUsername;


    public String getInvitedUsername() {
        return loginUsername;
    }

    public void setInvitedUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    private GoogleApiClient mGoogleApiClient;

    public GoogleApiClient getmGoogleApiClient() {
        return mGoogleApiClient;
    }

    public void setmGoogleApiClient(GoogleApiClient mGoogleApiClient) {
        this.mGoogleApiClient = mGoogleApiClient;
    }

    private static GoogleSingleton ourInstance = null;

    public static GoogleSingleton getInstance() {
        if (ourInstance == null) {
            ourInstance = new GoogleSingleton();
        }
        return ourInstance;
    }
}
