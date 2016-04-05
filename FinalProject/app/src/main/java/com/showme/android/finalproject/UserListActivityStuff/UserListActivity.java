package com.showme.android.finalproject.UserListActivityStuff;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.showme.android.finalproject.ChatRoomActivityStuff.Chat;
import com.showme.android.finalproject.Login.LoginActivity;
import com.showme.android.finalproject.R;
import com.showme.android.finalproject.Singletons.GoogleSingleton;
import com.showme.android.finalproject.Singletons.TranslatorSingleton;

public class UserListActivity extends AppCompatActivity {

    private static final String FIREBASE_URL = "https://blazing-inferno-2663.firebaseio.com/";
    private Firebase mFirebaseRef;
    private ChildEventListener mConnectedListener;

    private RecyclerView mMessages;
    private FirebaseRecyclerAdapter<Chat, UserListHolder> mRecycleViewAdapter;
    private Query mChatRef;

    Toolbar userListToolBar;
    EditText userNameEnter;
    FloatingActionButton sentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        mFirebaseRef.setAndroidContext(this);

        // Setup our Firebase mFirebaseRef

        mFirebaseRef = new Firebase(FIREBASE_URL).child("8675309userlist");
        mChatRef = mFirebaseRef.limitToLast(50);

        userListToolBar = (Toolbar) findViewById(R.id.userListToolbar);
        userListToolBar.setTitle("Recent Users");
        setSupportActionBar(userListToolBar);

        userListToolBar.setTitleTextColor(Color.WHITE);


        userNameEnter = (EditText) findViewById(R.id.messageInput);
        sentButton = (FloatingActionButton) findViewById(R.id.sendButton);

        sentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userNameEnter.getText().toString();
                GoogleSingleton google = GoogleSingleton.getInstance();
                if (username.equals("")) {
                    userNameEnter.setError("Please enter a username");
                } else {
                    google.setInvitedUsername(username);
                    sendInvite();
                    userNameEnter.setText("");
                }
            }
        });


    }

    public void sendInvite() {
        GoogleSingleton google = GoogleSingleton.getInstance();
        TranslatorSingleton translator = TranslatorSingleton.getInstance();
        String word = google.getInvitedUsername();
        word = word.replace(".", "");
        word = word.replace("#", "");
        word = word.replace("$", "");
        word = word.replace("[", "");
        word = word.replace("]", "");
        mFirebaseRef = new Firebase(FIREBASE_URL).child(word);
        Chat chat = new Chat(translator.getmRoomName(), translator.getLoginUsername());
        // Create a new, auto-generated child of that chat location, and save our chat data there
        mFirebaseRef.push().setValue(chat);
    }

    @Override
    public void onStart() {
        super.onStart();

        mMessages = (RecyclerView) findViewById(R.id.listRecyclerView2);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(true);
        manager.setStackFromEnd(true);

        mMessages.setHasFixedSize(false);
        mMessages.setLayoutManager(manager);


        mRecycleViewAdapter = new FirebaseRecyclerAdapter<Chat, UserListHolder>(Chat.class, R.layout.invitemessages, UserListHolder.class, mChatRef) {
            @Override
            public void populateViewHolder(UserListHolder chatView, Chat chat, int position) {
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

        // Finally, a little indication of connection status
        mConnectedListener = mFirebaseRef.getRoot().child("8675309userlist").addChildEventListener(new ChildEventListener() {

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(UserListActivity.this, LoginActivity.class);
        intent.putExtra("Logout", true);
        startActivity(intent);
        return true;

    }
}
