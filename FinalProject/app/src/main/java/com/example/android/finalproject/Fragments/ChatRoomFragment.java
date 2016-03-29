package com.example.android.finalproject.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.android.finalproject.ChatRoomActivity;
import com.example.android.finalproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatRoomFragment extends Fragment {

    EditText mUserName;
    Spinner mNativeLanguageSelect;
    Spinner mLanguageSelect;
    ArrayAdapter<CharSequence> mAdapter;
    Button mSubmit;

    public ChatRoomFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_room_login, container, false);
        // Inflate the layout for this fragment
        mUserName = (EditText) view.findViewById(R.id.loginActivityUserNameEditText);
        mNativeLanguageSelect = (Spinner) view.findViewById(R.id.nativeLanguageSelectSpinner);
        mLanguageSelect = (Spinner) view.findViewById(R.id.languageSelectSpinner);
        mAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.languages_array, android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mNativeLanguageSelect.setAdapter(mAdapter);
        mLanguageSelect.setAdapter(mAdapter);
        mSubmit = (Button) view.findViewById(R.id.submitButton);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mUserName.getText().toString();
                String nativeLanguage = mNativeLanguageSelect.getSelectedItem().toString();
                String language = mLanguageSelect.getSelectedItem().toString();
                Intent intent = new Intent(getActivity(), ChatRoomActivity.class);
                intent.putExtra("Name", name);
                intent.putExtra("NativeLanguage", nativeLanguage);
                intent.putExtra("Language", language);
                if (name.equals("")) {
                    mUserName.setError("Please enter a username");
                } else {
                    getActivity().startActivity(intent);
                }
            }
        });
        return view;
    }
}
