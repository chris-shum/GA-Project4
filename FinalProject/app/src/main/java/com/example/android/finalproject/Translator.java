package com.example.android.finalproject;

import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 * Created by ShowMe on 3/29/16.
 */
public class Translator {
    private static Translator ourInstance = null;

    public static Language translateToLanguage = Language.ENGLISH;
    private String mUsername;
    public String mNativeLanguage;
    String mLanguage;

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getNativeLanguage() {
        return mNativeLanguage;
    }

    public void setNativeLanguage(String mNativeLanguage) {
        this.mNativeLanguage = mNativeLanguage;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String mLanguage) {
        this.mLanguage = mLanguage;
    }

    public static Translator getInstance() {
        if (ourInstance == null) {
            ourInstance = new Translator();
        }
        return ourInstance;
    }

    public Translator() {
    }

    public void translatedText(final TextView inputView, String translatedToLanguage) throws Exception {
        //Replace client_id and client_secret with your own.
        Translate.setClientId("Project4Shum");
        Translate.setClientSecret("AXhYWTlsSQuWjQ21EnuuzmR64ymaAONk/Oe1wnfU0AI=");
        String clickedString = inputView.getText().toString();
        BackgroundTranslation backgroundTranslation = new BackgroundTranslation() {
            @Override
            protected void onPostExecute(String translatedString) {
                super.onPostExecute(translatedString);
                if (inputView instanceof EditText) {
                    inputView.setText(translatedString);
                } else {
                    Toast.makeText(inputView.getContext(), translatedString, Toast.LENGTH_SHORT).show();
                }
            }
        };
        setTranslateToLanguage(translatedToLanguage);
        backgroundTranslation.execute(clickedString);
    }

    public static void setTranslateToLanguage(String language) {
        switch (language) {
            case "English":
                translateToLanguage = Language.ENGLISH;
                break;
            case "Spanish":
                translateToLanguage = Language.SPANISH;
                break;
            case "French":
                translateToLanguage = Language.FRENCH;
                break;
            default:
                translateToLanguage = Language.ENGLISH;
        }
    }

    public class BackgroundTranslation extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... inputString) {
            String translatedString = null;
            try {
                translatedString = Translate.execute(inputString[0], translateToLanguage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return translatedString;
        }
    }

}
