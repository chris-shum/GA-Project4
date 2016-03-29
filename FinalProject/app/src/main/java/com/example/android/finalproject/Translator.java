package com.example.android.finalproject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 * Created by ShowMe on 3/29/16.
 */
public class Translator {
    private static Translator ourInstance = new Translator();

    public static Language translateToLanguage = Language.ENGLISH;
    private String mUsername;
    public String mNativeLanguage;
    String mLanguage;
    public static boolean myText = true;


    public static Language getTranslateToLanguage() {
        return translateToLanguage;
    }

    public static void setTranslateToLanguage(Language translateToLanguage) {
        Translator.translateToLanguage = translateToLanguage;
    }

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
        return ourInstance;
    }

    private Translator() {
    }

    public void translatedText(String inputText, final TextView inputView, String translatedToLanguage) throws Exception {
        //Replace client_id and client_secret with your own.
        Translate.setClientId("Project4Shum");
        Translate.setClientSecret("AXhYWTlsSQuWjQ21EnuuzmR64ymaAONk/Oe1wnfU0AI=");
        String clickedString = inputText;
        BackgroundTranslation backgroundTranslation = new BackgroundTranslation() {
            @Override
            protected void onPostExecute(String translatedString) {
                super.onPostExecute(translatedString);
                if (inputView instanceof EditText) {
                    inputView.setText(translatedString);
                    Log.d("Translate", "Translated: " +translatedString);
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
                Log.d("Translate", "It worked");
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("Translate", "It didn't work");
            }
            Log.d("Translate", "Original: " + inputString[0]);
            return translatedString;
        }
    }

}
