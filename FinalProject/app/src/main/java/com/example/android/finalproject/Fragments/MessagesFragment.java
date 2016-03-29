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


/**
 * A simple {@link Fragment} subclass.
 */
public class MessagesFragment extends Fragment {

    EditText mUsername;
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
        mUsername = (EditText) view.findViewById(R.id.messagesUserNameEditText);
        mRoomName = (EditText) view.findViewById(R.id.messagesRoomNameEditText);
        mSubmit = (Button) view.findViewById(R.id.messagesSubmitButton);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mUsername.getText().toString();
                String nativeLanguage = "English";
                String language = mRoomName.getText().toString();
                Intent intent = new Intent(getActivity(), ChatRoomActivity.class);
                intent.putExtra("Name", name);
                intent.putExtra("NativeLanguage", nativeLanguage);
                intent.putExtra("Language", language);
                if (name.equals("")) {
                    mUsername.setError("Please enter a username");
                } else if (language.equals("")) {
                    mRoomName.setError("Please enter a room name");
                } else {
                    getActivity().startActivity(intent);
                }
            }
        });
        return view;
    }

}
