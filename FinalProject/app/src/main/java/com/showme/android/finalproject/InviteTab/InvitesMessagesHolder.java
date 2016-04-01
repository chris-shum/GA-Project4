package com.showme.android.finalproject.InviteTab;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.showme.android.finalproject.ChatRoomActivityStuff.ChatRoomActivity;
import com.showme.android.finalproject.R;
import com.showme.android.finalproject.Singletons.TranslatorSingleton;

/**
 * Created by ShowMe on 3/28/16.
 */
public class InvitesMessagesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    RelativeLayout relativeLayout;

    public InvitesMessagesHolder(View itemView) {
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
                TranslatorSingleton translator = TranslatorSingleton.getInstance();
                String word = clickedText.getText().toString();
                word = word.replace(".", "");
                word = word.replace("#", "");
                word = word.replace("$", "");
                word = word.replace("[", "");
                word = word.replace("]", "");
                translator.setLanguage(word);
                Intent intent = new Intent(itemView.getContext(), ChatRoomActivity.class);
                itemView.getContext().startActivity(intent);
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
