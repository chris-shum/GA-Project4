package com.example.android.finalproject.Fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.example.android.finalproject.R;
import com.example.android.finalproject.Translator;

/**
 * Created by ShowMe on 3/29/16.
 */
public class DialogueLanguageSelectFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Blah")
                .setItems(R.array.languages_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Translator translator = new Translator();
                        translator.setLanguage("English");
                    }
                });
        return builder.create();
    }
}
