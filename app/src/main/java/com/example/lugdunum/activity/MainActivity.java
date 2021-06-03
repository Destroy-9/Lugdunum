package com.example.lugdunum.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.lugdunum.GetUserQuery;
import com.example.lugdunum.R;
import com.example.lugdunum.User;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ImageView mPlayButton;
    private ImageView mLoginButton;
    private ImageView mSignInButton;
    private EditText mPseudo;
    private EditText mPassword;
    private boolean mEnablePseudo = false;
    private boolean mEnablePassword = false;
    private User mUser;
    private boolean mOnLogin = false;
    private boolean mCreateNewUser = false;
    private boolean mUserValidated = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPseudo = (EditText) findViewById(R.id.editTextTextPseudo);
        mPassword = (EditText) findViewById(R.id.editTextTextPassword);
        mLoginButton = (ImageView) findViewById(R.id.loginButton);
        mSignInButton = (ImageView) findViewById(R.id.signInButton);
        mPlayButton = (ImageView) findViewById(R.id.playButton);
        mUser = (User) getApplicationContext(); // To have mUser accessible to every activity
        mPlayButton.setEnabled(false);


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

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginButton.setVisibility(View.INVISIBLE);
                mSignInButton.setVisibility(View.INVISIBLE);
                mPseudo.setVisibility(View.VISIBLE);
                mPassword.setVisibility(View.VISIBLE);
                mPlayButton.setVisibility(View.VISIBLE);
                mOnLogin = true;
            }
        });


        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginButton.setVisibility(View.INVISIBLE);
                mSignInButton.setVisibility(View.INVISIBLE);
                mPseudo.setVisibility(View.VISIBLE);
                mPassword.setVisibility(View.VISIBLE);
                mPlayButton.setVisibility(View.VISIBLE);
                mOnLogin = false;
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUser.setAll(mPseudo.getText().toString(), mPassword.getText().toString());
                System.out.println("***Pseudo : "+mPseudo.getText().toString()+" ***");
                System.out.println("***Mot de passe : "+mPassword.getText().toString()+" ***");

                // To enter in debug mode
                if (mUser.debugMode()){
                    Toast.makeText(MainActivity.this, "Mode debug activé", Toast.LENGTH_LONG).show();
                }
                /*elif (mOnLogin) {
                    GetUserQuery getUserQuery = GetUserQuery.builder().build();
                    Apollo.apolloClient.query(getUserQuery)
                        .enqueue(new ApolloCall.Callback<GetUserQuery.Data>() {
                            @Override
                            public void onResponse(@NotNull Response<GetUserQuery.Data> response) {
                                Log.e("Apollo", "response: " + Objects.requireNonNull(response.data()).toString());
                                //if response.date().username == username
                                //check mdp
                                mUserValidated = true;
                                //else  mUserValidated = false;
                            }

                            @Override
                            public void onFailure(@NotNull ApolloException e) {
                                Log.e("Apollo", "Error", e);
                                mUserValidated = false;
                            }
                        });
                }
                else {
                    GetUserQuery getUserQuery = GetUserQuery.builder().build();
                    Apollo.apolloClient.query(getUserQuery)
                            .enqueue(new ApolloCall.Callback<GetUserQuery.Data>() {
                                @Override
                                public void onResponse(@NotNull Response<GetUserQuery.Data> response) {
                                    Log.e("Apollo", "response: " + Objects.requireNonNull(response.data()).toString());
                                    //if username is not in database
                                    mCreateNewUser = true;
                                    //else    mCreateNewUser = false;
                                }

                                @Override
                                public void onFailure(@NotNull ApolloException e) {
                                    Log.e("Apollo", "Error", e);
                                    mCreateNewUser = false;
                                }
                            });
                    if (mCreateNewUser) {
                        CreateUserMutation createUserMutation = CreateUserMutation.builder()
                                .username(mPseudo.getText().toString())
                                .password(mPassword.getText().toString())
                                .build();
                        Apollo.apolloClient
                                .mutate(createUserMutation)
                                .enqueue(new ApolloCall.Callback<CreateUserMutation.Data>() {
                                    @Override
                                    public void onResponse(@NotNull Response<CreateUserMutation.Data> response) {
                                        Log.i("TAG", response.toString());
                                        mUserValidated = true;
                                    }

                                    @Override
                                    public void onFailure(@NotNull ApolloException e) {
                                        Log.e("TAG", e.getMessage(), e);
                                        mUserValidated = false;
                                    }
                                });
                    }
                }

                 */
             //   if (mUserValidated) {
                    //creation of the link between mainActivity and ChoiceJourneyActivity
                    Intent intent = new Intent(MainActivity.this, ChoiceJourneyActivity.class);
                    startActivity(intent);
              //  }
            }
        });
    }
}