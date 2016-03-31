package com.showme.android.finalproject.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.showme.android.finalproject.Google;
import com.showme.android.finalproject.MainActivity;
import com.showme.android.finalproject.R;
import com.showme.android.finalproject.Translator;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    Toolbar chatRoomToolbar;

    boolean logout;


    //Signin button
    private SignInButton signInButton;
    FloatingActionButton mButton;

    //Signing Options
    private GoogleSignInOptions gso;

    //google api client
    private GoogleApiClient mGoogleApiClient;

    //Signin constant to check the activity result
    private int RC_SIGN_IN = 100;

    //TextViews
    private TextView textViewName;
    private TextView textViewEmail;
    private NetworkImageView profilePhoto;
    TextView mWelcome;
    TextView mLogggedInAs;

    //Image Loader
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Google google = Google.getInstance();


        chatRoomToolbar = (Toolbar) findViewById(R.id.loginToolbar);
        chatRoomToolbar.setTitle("App Name");
        setSupportActionBar(chatRoomToolbar);

        //Initializing Views
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        profilePhoto = (NetworkImageView) findViewById(R.id.profileImage);
        mButton = (FloatingActionButton) findViewById(R.id.loginButton);
        mWelcome = (TextView) findViewById(R.id.welcome);
        mLogggedInAs = (TextView) findViewById(R.id.loggingInAs);

        //Initializing google signin option
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //Initializing signinbutton
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setScopes(gso.getScopeArray());

        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        google.setmGoogleApiClient(mGoogleApiClient);

        Intent intent = getIntent();
        logout = intent.getBooleanExtra("Logout", false);
        if (logout) {
            signOut();
        }

        //Setting onclick listener to signing button
        signInButton.setOnClickListener(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


    //This function will option signing intent
    private void signIn() {
        //Creating an intent
        Google google = Google.getInstance();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(google.getmGoogleApiClient());

        //Starting intent for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //If signin
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin
            handleSignInResult(result);
        }
    }


    //After the signing we are calling this function
    private void handleSignInResult(GoogleSignInResult result) {
        //If the login succeed
        if (result.isSuccess()) {
            //Getting google account
            GoogleSignInAccount acct = result.getSignInAccount();

            //Displaying name and email
            textViewName.setText(acct.getDisplayName());
            textViewEmail.setText(acct.getEmail());

            //Initializing image loader
            imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext())
                    .getImageLoader();

            imageLoader.get(acct.getPhotoUrl().toString(),
                    ImageLoader.getImageListener(profilePhoto,
                            R.mipmap.ic_launcher,
                            R.mipmap.ic_launcher));

            //Loading image
            profilePhoto.setImageUrl(acct.getPhotoUrl().toString(), imageLoader);
            signInButton.setVisibility(View.GONE);
            mWelcome.setVisibility(View.VISIBLE);
            mLogggedInAs.setVisibility(View.VISIBLE);
            mButton.setVisibility(View.VISIBLE);

            Translator translator = Translator.getInstance();
            String username = acct.getEmail();
            String[] breakup = username.split("@");
            translator.setUsername(breakup[0]);
        } else {
            //If login fails
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == signInButton) {
            //Calling signin
            signIn();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public void signOut() {
        try {
            // clearing app data
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("pm clear com.showme.android.finalproject");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
