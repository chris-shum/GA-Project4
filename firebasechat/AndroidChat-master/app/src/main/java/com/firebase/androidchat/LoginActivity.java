package com.firebase.androidchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class LoginActivity extends Activity {

    EditText mUserName;
    Spinner mNativeLanguageSelect;
    Spinner mLanguageSelect;
    ArrayAdapter<CharSequence> mAdapter;
    Button mSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserName = (EditText) findViewById(R.id.loginActivityUserNameEditText);
        mNativeLanguageSelect = (Spinner) findViewById(R.id.nativeLanguageSelectSpinner);
        mLanguageSelect = (Spinner) findViewById(R.id.languageSelectSpinner);
        mAdapter = ArrayAdapter.createFromResource(this, R.array.languages_array, android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mNativeLanguageSelect.setAdapter(mAdapter);
        mLanguageSelect.setAdapter(mAdapter);

        mSubmit = (Button) findViewById(R.id.submitButton);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mUserName.getText().toString();
                String nativeLanguage = mNativeLanguageSelect.getSelectedItem().toString();
                String language = mLanguageSelect.getSelectedItem().toString();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("Name", name);
                intent.putExtra("NativeLanguage", nativeLanguage);
                intent.putExtra("Language", language);
                startActivity(intent);
            }
        });
    }
}
