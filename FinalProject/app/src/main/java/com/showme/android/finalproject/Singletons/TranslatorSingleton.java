package com.showme.android.finalproject.Singletons;

import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.widget.EditText;
import android.widget.TextView;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 * Created by ShowMe on 3/29/16.
 */
public class TranslatorSingleton {
    private static TranslatorSingleton ourInstance = null;

    public static Language translateToLanguage = Language.ENGLISH;
    private String mLoginUsername;
    public String mNativeLanguage;
    String mLanguage;
    String mRoomName;

    public String getmRoomName() {
        return mRoomName;
    }

    public void setmRoomName(String mRoomName) {
        this.mRoomName = mRoomName;
    }

    public String getLoginUsername() {
        return mLoginUsername;
    }

    public void setLoginUsername(String mUsername) {
        this.mLoginUsername = mUsername;
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

    public static TranslatorSingleton getInstance() {
        if (ourInstance == null) {
            ourInstance = new TranslatorSingleton();
        }
        return ourInstance;
    }

    public TranslatorSingleton() {
    }

    public void translatedText(final TextView inputView, String translatedToLanguage) throws Exception {
        //Replace client_id and client_secret with your own.
        final String clickedString = inputView.getText().toString();
        BackgroundTranslation backgroundTranslation = new BackgroundTranslation() {
            @Override
            protected void onPostExecute(String translatedString) {
                super.onPostExecute(translatedString);
                if (inputView instanceof EditText) {
                    inputView.setText(translatedString);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(inputView.getContext());
                    builder.setMessage(Html.fromHtml("<b>" + translatedString+"</b><br><br>"+clickedString));
                    AlertDialog alert = builder.create();
                    alert.show();
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
            case "Chinese":
                translateToLanguage = Language.CHINESE_SIMPLIFIED;
                break;
            case "German":
                translateToLanguage = Language.GERMAN;
                break;
            case "Italian":
                translateToLanguage = Language.ITALIAN;
                break;
            case "Japanese":
                translateToLanguage = Language.JAPANESE;
                break;
            case "Korean":
                translateToLanguage = Language.KOREAN;
                break;
            case "Portuguese":
                translateToLanguage = Language.PORTUGUESE;
                break;
            case "Russian":
                translateToLanguage = Language.RUSSIAN;
                break;
            case "Polish":
                translateToLanguage = Language.POLISH;
                break;
            case "Hebrew":
                translateToLanguage = Language.HEBREW;
                break;
            case "Swedish":
                translateToLanguage = Language.SWEDISH;
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
