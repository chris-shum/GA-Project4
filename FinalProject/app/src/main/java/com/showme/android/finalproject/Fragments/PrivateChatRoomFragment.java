package com.showme.android.finalproject.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.showme.android.finalproject.ChatRoomActivityStuff.ChatRoomActivity;
import com.showme.android.finalproject.R;
import com.showme.android.finalproject.Singletons.TranslatorSingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class PrivateChatRoomFragment extends Fragment {

    EditText mRoomName;
    FloatingActionButton mSubmit;

    public PrivateChatRoomFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_private_chat_room, container, false);
        mRoomName = (EditText) view.findViewById(R.id.messagesRoomNameEditText);
        mSubmit = (FloatingActionButton) view.findViewById(R.id.messagesSubmitButton);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslatorSingleton translator = TranslatorSingleton.getInstance();
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
