package com.showme.android.finalproject.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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
                PublicChatRoomFragment tab1 = new PublicChatRoomFragment();
                return tab1;
            case 1:
                PrivateChatRoomFragment tab2 = new PrivateChatRoomFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabNumber;
    }
}