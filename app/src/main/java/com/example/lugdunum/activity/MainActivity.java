package com.example.lugdunum.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.lugdunum.Apollo;
import com.example.lugdunum.CreateUserMutation;
import com.example.lugdunum.LoginQuery;
import com.example.lugdunum.R;
import com.example.lugdunum.User;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageView mPlayButton;
    private ImageView mLoginButton;
    private ImageView mSignUpButton;
    private ImageView mBackButton;
    private EditText mPseudo;
    private EditText mPassword;
    private boolean mEnablePseudo = false;
    private boolean mEnablePassword = false;
    private User mUser;
    private boolean mOnLogin;
    private boolean mUserValidated = false;
    private String message = "Veuillez attendre quelques secondes\nActivation du serveur";

    // Method which permits to change the display of the buttons on the screen
    void changeVisibility (boolean playButtonBecomeVisible){
        if (playButtonBecomeVisible){
            mLoginButton.setVisibility(View.GONE);
            mSignUpButton.setVisibility(View.GONE);
            mPseudo.setVisibility(View.VISIBLE);
            mPassword.setVisibility(View.VISIBLE);
            mPlayButton.setVisibility(View.VISIBLE);
            mBackButton.setVisibility(View.VISIBLE);
        }
        else {
            mLoginButton.setVisibility(View.VISIBLE);
            mSignUpButton.setVisibility(View.VISIBLE);
            mPseudo.setVisibility(View.GONE);
            mPassword.setVisibility(View.GONE);
            mPlayButton.setVisibility(View.GONE);
            mBackButton.setVisibility(View.GONE);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("QQQ " +Thread.currentThread().getName());
            }
        }, 0, 5000);

        mPseudo = (EditText) findViewById(R.id.editTextTextPseudo);
        mPassword = (EditText) findViewById(R.id.editTextTextPassword);
        mLoginButton = (ImageView) findViewById(R.id.loginButton);
        mSignUpButton = (ImageView) findViewById(R.id.signUpButton);
        mPlayButton = (ImageView) findViewById(R.id.playButton);
        mBackButton = (ImageView) findViewById(R.id.backButton);
        mUser = (User) getApplicationContext(); // To have mUser accessible to every activity
        mPlayButton.setEnabled(false);
        mOnLogin = false;


        // Listener which permit to enter a pseudo
        mPseudo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEnablePseudo = (s.toString().length() != 0);
                mPlayButton.setEnabled(mEnablePassword && mEnablePseudo);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Listener which permit to enter a password
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEnablePassword = (s.toString().length() != 0);
                mPlayButton.setEnabled(mEnablePassword && mEnablePseudo);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // When the login button is clicked, the other button appeared
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeVisibility(true);
                mOnLogin = true;
            }
        });

        // When the sign up button is clicked, the other button appeared
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeVisibility(true);
                mOnLogin = false;
            }
        });

        // When the back button is clicked, the other button appeared
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeVisibility(false);
            }
        });


        // When the play button is clicked, all the procedure of login/sign up is processed
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUser.setPseudoPassword(mPseudo.getText().toString(), mPassword.getText().toString());
                System.out.println("***Pseudo : " + mPseudo.getText().toString() + " ***");
                System.out.println("***Mot de passe : " + mPassword.getText().toString() + " ***");

                // Creation of a handler to display the Toast and wait for server's answer
                Handler uiHandler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        uiHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                                if (mUserValidated) {
                                    //creation of the link between mainActivity and ChoiceJourneyActivity
                                    Intent intent = new Intent(MainActivity.this, ChoiceJourneyActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }, 1000);
                    }
                };

                // To enter in debug mode
                if (mUser.debugMode()) {
                    message = "Mode debug activé";
                    mUserValidated = true;
                } else {

                    // Login processing
                    if (mOnLogin) {
                        LoginQuery loginQuery = LoginQuery.builder()
                                .username(mPseudo.getText().toString())
                                .password(mPassword.getText().toString())
                                .build();
                        Apollo.apolloClient.query(loginQuery)
                               .enqueue(new ApolloCall.Callback<LoginQuery.Data>() {
                                @Override
                                public void onResponse(@NotNull Response<LoginQuery.Data> response) {
                                    System.out.println("***"+ response.getData().login());

                                    // Wrong username given, not found in the data base
                                    if (Objects.requireNonNull(response.getData()).login().equals("1")) {
                                        mUserValidated = false;
                                        message = "Erreur de pseudo";
                                        System.out.println("*** Username wrong "+ response.getData().toString());
                                    }
                                    else {

                                        // Wrong password given, don't correspond to the username
                                        if (Objects.requireNonNull(response.getData()).login().equals("2")) {
                                            mUserValidated = false;
                                            message = "Erreur de mot de passe";
                                            System.out.println("*** Password wrong "+ response.getData().toString());
                                        }

                                        // Authentication succeeded
                                        else {
                                            mUserValidated = true;
                                            message = "Authentification réussite";
                                            mUser.setID(response.getData().login());
                                            System.out.println("*** Good "+ response.getData().toString());
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(@NotNull ApolloException e) {
                                    Log.e("Apollo", "Error", e);
                                    mUserValidated = false;
                                }
                            });
                    }

                    // sign up processing
                    else {
                        CreateUserMutation createUserMutation = CreateUserMutation.builder()
                                .username(mPseudo.getText().toString())
                                .password(mPassword.getText().toString())
                                .build();
                        Apollo.apolloClient
                                .mutate(createUserMutation)
                                .enqueue(new ApolloCall.Callback<CreateUserMutation.Data>() {
                                    @Override
                                    public void onResponse(@NotNull Response<CreateUserMutation.Data> response) {

                                        // Username already exist in the data base
                                        if (response.getData().createUser() == null){
                                            message = "Ce pseudo existe déjà";
                                            mUserValidated = false;
                                        }

                                        // Sign up succeeded
                                        else {
                                            message = "Compte créé";
                                            mUserValidated = true;
                                            mUser.setID(response.getData().createUser().toString());
                                        }
                                    }

                                    @Override
                                    public void onFailure(@NotNull ApolloException e) {
                                        Log.e("Failure mutation", e.getMessage(), e);
                                        mUserValidated = false;
                                    }
                                });
                    }
                }
                runnable.run();
            }
        });
    }
}