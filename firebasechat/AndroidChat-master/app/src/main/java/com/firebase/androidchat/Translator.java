package com.firebase.androidchat;

import android.os.AsyncTask;
import android.util.Log;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 * Created by ShowMe on 3/22/16.
 */
public class Translator extends MainActivity {

    public static Language translateToLanguage = Language.ENGLISH;


    public static void translatedText(String clickedText, String translatedToLanguage) throws Exception {
        //Replace client_id and client_secret with your own.
        Translate.setClientId("Project4Shum");
        Translate.setClientSecret("AXhYWTlsSQuWjQ21EnuuzmR64ymaAONk/Oe1wnfU0AI=");

        // Translate an english string to spanish
        String clickedString = clickedText;
        BackgroundTranslation backgroundTranslation = new BackgroundTranslation();
        setTranslateToLanguage(translatedToLanguage);
        backgroundTranslation.execute(clickedString);
    }

    public static void setTranslateToLanguage(String nativeLanguage) {
        switch (nativeLanguage) {
            case "English":
                Translator.translateToLanguage = Language.ENGLISH;
                break;
            case "Spanish":
                Translator.translateToLanguage = Language.SPANISH;
                break;
            case "French":
                Translator.translateToLanguage = Language.FRENCH;
                break;
            default:
                Translator.translateToLanguage = Language.ENGLISH;
        }
    }
}

class BackgroundTranslation extends AsyncTask<String, Void, String> {

    protected String doInBackground(String... inputString) {
        String translatedString = null;
        try {
            translatedString = Translate.execute(inputString[0], Translator.translateToLanguage);
            Log.d("Translate", "It worked");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Translate", "It didn't work");
        }
        Log.d("Translate", "Original: " + inputString[0]);
        return translatedString;
    }

    @Override
    protected void onPostExecute(String translatedString) {
        Log.d("Translate", "Translated: " + translatedString);
        super.onPostExecute(translatedString);
    }
}
