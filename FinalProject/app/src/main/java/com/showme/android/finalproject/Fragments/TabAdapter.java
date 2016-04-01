package com.showme.android.finalproject.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.showme.android.finalproject.InviteTab.InvitesFragment;

/**
 * Created by ShowMe on 3/28/16.
 */
public class TabAdapter extends FragmentStatePagerAdapter {

    int mTabNumber;

    public TabAdapter(FragmentManager fm, int tabNumber) {
        super(fm);
        this.mTabNumber = tabNumber;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                InvitesFragment tab1 = new InvitesFragment();
                return tab1;
            case 1:
                PublicChatRoomFragment tab2 = new PublicChatRoomFragment();
                return tab2;
            case 2:
                PrivateChatRoomFragment tab3 = new PrivateChatRoomFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabNumber;
    }
}