package com.showme.android.finalproject.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.showme.android.finalproject.ChatRoomActivityStuff.ChatRoomActivity;
import com.showme.android.finalproject.R;
import com.showme.android.finalproject.Singletons.TranslatorSingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class PublicChatRoomFragment extends Fragment {

    Spinner mNativeLanguageSelect;
    Spinner mLanguageSelect;
    ArrayAdapter<CharSequence> mAdapter;
    FloatingActionButton mSubmit;

    public PublicChatRoomFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_public_chat_room, container, false);
        // Inflate the layout for this fragment
        mNativeLanguageSelect = (Spinner) view.findViewById(R.id.nativeLanguageSelectSpinner);
        mLanguageSelect = (Spinner) view.findViewById(R.id.languageSelectSpinner);
        mAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.languages_array, android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mNativeLanguageSelect.setAdapter(mAdapter);
        mLanguageSelect.setAdapter(mAdapter);
        mSubmit = (FloatingActionButton) view.findViewById(R.id.submitButton);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TranslatorSingleton translator = TranslatorSingleton.getInstance();
                translator.setNativeLanguage(mNativeLanguageSelect.getSelectedItem().toString());
                translator.setLanguage(mLanguageSelect.getSelectedItem().toString());
                translator.setmRoomName(mLanguageSelect.getSelectedItem().toString());

                Intent intent = new Intent(getActivity(), ChatRoomActivity.class);
                getActivity().startActivity(intent);

            }
        });
        return view;
    }
}
