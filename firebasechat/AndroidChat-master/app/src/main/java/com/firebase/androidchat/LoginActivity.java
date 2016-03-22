package com.firebase.androidchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class LoginActivity extends Activity {

    EditText mUserName;
    Spinner mLanguageSelect;
    ArrayList<String> mLanguages;
    ArrayAdapter<String> mAdapter;
    Button mSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserName = (EditText) findViewById(R.id.userNameEditText);
        mLanguageSelect = (Spinner) findViewById(R.id.languageSelectSpinner);
        mLanguages = new ArrayList<>();
        mLanguages.add("English");
        mLanguages.add("Spanish");
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mLanguages);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLanguageSelect.setAdapter(mAdapter);
        mSubmit = (Button) findViewById(R.id.submitButton);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mUserName.getText().toString();
                String language = mLanguageSelect.getSelectedItem().toString();
                Log.d("Log", "Name " + name);
                Log.d("Log", "Language " + language);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("Name", name);
                intent.putExtra("Language", language);
                startActivity(intent);
            }
        });

    }
}
