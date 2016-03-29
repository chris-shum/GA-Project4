package com.example.android.finalproject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.finalproject.RecyclerViewTest.Chat;
import com.example.android.finalproject.RecyclerViewTest.ChatHolder;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.memetix.mst.language.Language;

public class ChatRoomActivity extends ListActivity {

    private static final String FIREBASE_URL = "https://blazing-inferno-2663.firebaseio.com/";
    private Firebase mFirebaseRef;
    private ValueEventListener mConnectedListener;
    private String mUsername;
    public String mNativeLanguage;
    String mLanguage;
    EditText mInputText;
    public static Language translateToLanguage = Language.ENGLISH;
    Toolbar chatRoomToolbar;

    private RecyclerView mMessages;
    private FirebaseRecyclerAdapter<Chat, ChatHolder> mRecycleViewAdapter;
    private Query mChatRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        mFirebaseRef.setAndroidContext(this);
        mInputText = (EditText) findViewById(R.id.messageInput);

        Intent intent = getIntent();
        mUsername = intent.getStringExtra("Name");
        mNativeLanguage = intent.getStringExtra("NativeLanguage");
        mLanguage = intent.getStringExtra("Language");
        final Translator translator = Translator.getInstance();
        translator.setUsername(mUsername);
        translator.setNativeLanguage(mNativeLanguage);
        translator.setLanguage(mLanguage);

        chatRoomToolbar = (Toolbar) findViewById(R.id.chatRoomToolbar);
        chatRoomToolbar.setTitle("Chatting as " + mUsername + " in the " + mLanguage + " room");

        // Setup our Firebase mFirebaseRef
        if (mLanguage == null) {
            mFirebaseRef = new Firebase(FIREBASE_URL).child("default");
        } else {
            mFirebaseRef = new Firebase(FIREBASE_URL).child(mLanguage);
        }
        mChatRef = mFirebaseRef.limitToLast(50);


        // Setup our input methods. Enter key on the keyboard or pushing the send button
        final EditText inputText = (EditText) findViewById(R.id.messageInput);
        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_NULL && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    sendMessage();
                }
                return true;
            }
        });
        findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        findViewById(R.id.sendButton).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                try {
                    translator.translatedText(mInputText ,mLanguage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        mMessages = (RecyclerView) findViewById(R.id.listRecyclerView);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(false);
        manager.setStackFromEnd(true);

        mMessages.setHasFixedSize(false);
        mMessages.setLayoutManager(manager);


        mRecycleViewAdapter = new FirebaseRecyclerAdapter<Chat, ChatHolder>(Chat.class, R.layout.message, ChatHolder.class, mChatRef) {
            @Override
            public void populateViewHolder(ChatHolder chatView, Chat chat, int position) {
                chatView.setName(chat.getAuthor());
                chatView.setText(chat.getMessage());
                String author = chat.getAuthor();
                if (author != null && author.equals(mUsername)) {
                    chatView.setIsSender(true);
                } else {
                    chatView.setIsSender(false);
                }
            }
        };
        mMessages.setAdapter(mRecycleViewAdapter);

        // Finally, a little indication of connection status
        mConnectedListener = mFirebaseRef.getRoot().child(".info/connected").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean connected = (Boolean) dataSnapshot.getValue();
                if (connected) {
                    Toast.makeText(ChatRoomActivity.this, "Connected to Firebase", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChatRoomActivity.this, "Disconnected from Firebase", Toast.LENGTH_SHORT).show();
                }
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

    private void sendMessage() {
        mInputText = (EditText) findViewById(R.id.messageInput);
        String input = mInputText.getText().toString();
        if (!input.equals("")) {
            // Create our 'model', a Chat object
            Chat chat = new Chat(input, mUsername);
            // Create a new, auto-generated child of that chat location, and save our chat data there
            mFirebaseRef.push().setValue(chat);
            mInputText.setText("");
            mMessages.post(new Runnable() {
                @Override
                public void run() {
                    mMessages.smoothScrollToPosition(mRecycleViewAdapter.getItemCount());
                }
            });
        }
    }
}
