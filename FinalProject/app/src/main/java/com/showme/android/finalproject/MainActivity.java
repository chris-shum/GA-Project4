package com.showme.android.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.showme.android.finalproject.Fragments.TabAdapter;
import com.showme.android.finalproject.Login.LoginActivity;
import com.showme.android.finalproject.Singletons.TranslatorSingleton;

public class MainActivity extends AppCompatActivity {

    LoginActivity loginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginActivity = new LoginActivity();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarOnMainPage);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayoutBar);
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));
        tabLayout.addTab(tabLayout.newTab().setText("Chat Invites"));
        tabLayout.addTab(tabLayout.newTab().setText("Public Chat"));
        tabLayout.addTab(tabLayout.newTab().setText("Private Chat"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (item.getItemId()) {
            case R.id.translate:
                builder.setTitle("I want to speak:");
                builder.setItems(R.array.languages_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        String[] mTestArray = getResources().getStringArray(R.array.languages_array);
                        TranslatorSingleton translator = TranslatorSingleton.getInstance();
                        translator.setLanguage(mTestArray[item]);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            case R.id.nativeLanguage:
                builder.setTitle("I speak:");
                builder.setItems(R.array.languages_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        String[] mTestArray = getResources().getStringArray(R.array.languages_array);
                        TranslatorSingleton translator = TranslatorSingleton.getInstance();
                        translator.setNativeLanguage(mTestArray[item]);
                    }
                });
                AlertDialog alert2 = builder.create();
                alert2.show();
                return true;
            case R.id.signOut:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("Logout", true);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
