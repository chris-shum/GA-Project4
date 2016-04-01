package com.showme.android.finalproject.UserListActivityStuff;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.showme.android.finalproject.R;
import com.showme.android.finalproject.Singletons.GoogleSingleton;

/**
 * Created by ShowMe on 3/28/16.
 */
public class UserListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    RelativeLayout relativeLayout;

    public UserListHolder(View itemView) {
        super(itemView);
        mView = itemView;
        relativeLayout = (RelativeLayout) itemView.findViewById(R.id.message_container);
    }

    public void setName(String name) {
        TextView field = (TextView) mView.findViewById(R.id.name_text);
        field.setText(name);
    }

    public void setText(String text) {
        TextView field = (TextView) mView.findViewById(R.id.message_text);
        field.setText(text);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView clickedText = (TextView) mView.findViewById(R.id.message_text);
                //here is where you send a message to the chatroom
                GoogleSingleton google = GoogleSingleton.getInstance();
                google.setInvitedUsername(clickedText.getText().toString());
                UserListActivity userListActivity = new UserListActivity();
                userListActivity.sendInvite();
                Toast.makeText(mView.getContext(), clickedText.getText().toString()+" has been invited.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setIsSender(Boolean isSender) {
        RelativeLayout messageContainer = (RelativeLayout) mView.findViewById(R.id.message_container);

        if (isSender) {
            messageContainer.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
    }
}
