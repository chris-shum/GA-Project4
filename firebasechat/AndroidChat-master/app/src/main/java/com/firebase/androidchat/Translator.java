package com.firebase.androidchat;

import android.os.AsyncTask;
import android.util.Log;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 * Created by ShowMe on 3/22/16.
 */
public class Translator {

    public static Language nativeLanguage = Language.ENGLISH;


    public static void main(String clickedText, String nativeLanguage) throws Exception {
        //Replace client_id and client_secret with your own.
        Translate.setClientId("Project4Shum");
        Translate.setClientSecret("AXhYWTlsSQuWjQ21EnuuzmR64ymaAONk/Oe1wnfU0AI=");

        // Translate an english string to spanish
        String clickedString = clickedText;
        BackgroundTranslation backgroundTranslation = new BackgroundTranslation();
        setNativeLanguage(nativeLanguage);
        backgroundTranslation.execute(clickedString);
    }
    public static void setNativeLanguage(String nativeLanguage){
        switch (nativeLanguage){
            case "English":
                Translator.nativeLanguage = Language.ENGLISH;

                break;
            case "Spanish":
                Translator.nativeLanguage = Language.SPANISH;

                break;
            default:
                 Translator.nativeLanguage = Language.ENGLISH;
        }
    }
}

class BackgroundTranslation extends AsyncTask<String, Void, String> {


    protected String doInBackground(String... inputString) {
        String translatedString = null;

        try {
            translatedString = Translate.execute(inputString[0], Translator.nativeLanguage);
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
