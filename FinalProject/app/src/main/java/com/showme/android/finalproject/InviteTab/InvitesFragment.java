package com.showme.android.finalproject.InviteTab;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.showme.android.finalproject.ChatRoomActivityStuff.Chat;
import com.showme.android.finalproject.MainActivity;
import com.showme.android.finalproject.R;
import com.showme.android.finalproject.Singletons.TranslatorSingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class InvitesFragment extends Fragment {
    private static final String FIREBASE_URL = "https://blazing-inferno-2663.firebaseio.com/";
    private Firebase mFirebaseRef;
    private ChildEventListener mConnectedListener;

    private RecyclerView mMessages;
    private FirebaseRecyclerAdapter<Chat, InvitesMessagesHolder> mRecycleViewAdapter;
    private Query mChatRef;


    public InvitesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invites, container, false);
        mFirebaseRef.setAndroidContext(container.getContext());
        TranslatorSingleton translator = TranslatorSingleton.getInstance();
        mFirebaseRef = new Firebase(FIREBASE_URL).child(translator.getLoginUsername());
        mChatRef = mFirebaseRef.limitToLast(50);
        // Inflate the layout for this fragment
        mMessages = (RecyclerView) view.findViewById(R.id.listRecyclerViewInvites);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setReverseLayout(true);
        manager.setStackFromEnd(true);

        mMessages.setHasFixedSize(false);
        mMessages.setLayoutManager(manager);


        mRecycleViewAdapter = new FirebaseRecyclerAdapter<Chat, InvitesMessagesHolder>(Chat.class, R.layout.invitemessages, InvitesMessagesHolder.class, mChatRef) {
            @Override
            public void populateViewHolder(InvitesMessagesHolder chatView, Chat chat, int position) {
                chatView.setName(chat.getAuthor());
                chatView.setText(chat.getMessage());
                String author = chat.getMessage();
                String word = author;
                word = word.replace(".", "");
                word = word.replace("#", "");
                word = word.replace("$", "");
                word = word.replace("[", "");
                word = word.replace("]", "");
                TranslatorSingleton translator = TranslatorSingleton.getInstance();
                if (word != null && word.equals(translator.getLoginUsername())) {
                    chatView.setIsSender(true);
                } else {
                    chatView.setIsSender(false);
                }
            }
        };
        mMessages.setAdapter(mRecycleViewAdapter);
        TranslatorSingleton translator = TranslatorSingleton.getInstance();
        // Finally, a little indication of connection status
        mConnectedListener = mFirebaseRef.getRoot().child(translator.getLoginUsername()).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mMessages.post(new Runnable() {
                    @Override
                    public void run() {
                        mMessages.smoothScrollToPosition(mRecycleViewAdapter.getItemCount() - 1);
                    }
                });

            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                // No-op
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        mFirebaseRef.getRoot().child(".info/connected").removeEventListener(mConnectedListener);
        mRecycleViewAdapter.cleanup();
    }

    public void notification() {
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(getContext())
                        .setSmallIcon(android.R.drawable.ic_dialog_email)
                        .setContentTitle("App Name")
                        .setContentText("You have a new chat room invite!");
        Intent resultIntent = new Intent(getContext(), MainActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        getContext(),
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        // NOTIFICATION_ID allows you to update the notification later on.
        mNotificationManager.notify(12345, mBuilder.build());


    }

}