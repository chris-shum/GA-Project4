package com.example.android.finalproject.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.finalproject.ChatRoomActivity;
import com.example.android.finalproject.R;
import com.example.android.finalproject.Translator;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessagesFragment extends Fragment {

    EditText mRoomName;
    Button mSubmit;

    public MessagesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_messages, container, false);
        mRoomName = (EditText) view.findViewById(R.id.messagesRoomNameEditText);
        mSubmit = (Button) view.findViewById(R.id.messagesSubmitButton);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Translator translator = Translator.getInstance();
                String language = mRoomName.getText().toString();
                translator.setNativeLanguage("English");

                Intent intent = new Intent(getActivity(), ChatRoomActivity.class);
                if (language.equals("")) {
                    mRoomName.setError("Please enter a room name");
                } else {
                    translator.setLanguage(language);
                    getActivity().startActivity(intent);
                }
            }
        });
        return view;
    }

}
