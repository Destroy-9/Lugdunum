package com.example.lugdunum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ImageView mPlayButton;
    private EditText mPseudo;
    private EditText mPassword;
    private boolean enablePseudo = false;
    private boolean enablePassword = false;
    private User mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPseudo = (EditText) findViewById(R.id.editTextTextPseudo);
        mPassword = (EditText) findViewById(R.id.editTextTextPassword);
        mPlayButton = (ImageView) findViewById(R.id.playButton);
        mPlayButton.setEnabled(false);


        mPseudo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enablePseudo = (s.toString().length() != 0);
                mPlayButton.setEnabled(enablePassword && enablePseudo);
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
                enablePassword = (s.toString().length() != 0);
                mPlayButton.setEnabled(enablePassword && enablePseudo);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUser = new User(mPseudo.getText().toString(), mPassword.getText().toString());
                System.out.println("***Pseudo : "+mPseudo.getText().toString()+" ***");
                System.out.println("***Mot de passe : "+mPassword.getText().toString()+" ***");

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
                            }

                            @Override
                            public void onFailure(@NotNull ApolloException e) {
                                Log.e("TAG", e.getMessage(), e);
                            }
                        });
               /* GetUserQuery getUserQuery = GetUserQuery.builder().build();
                Apollo.apolloClient.query(getUserQuery)
                        .enqueue(new ApolloCall.Callback<GetUserQuery.Data>() {
                            @Override
                            public void onResponse(@NotNull Response<GetUserQuery.Data> response) {
                                Log.e("Apollo", "Launch site: " + Objects.requireNonNull(response.getData()).toString());
                            }

                            @Override
                            public void onFailure(@NotNull ApolloException e) {
                                Log.e("Apollo", "Error", e);
                            }
                        });*/

                //creation of the link between mainActivity and ChoiceJourneyActivity
                Intent intent = new Intent(MainActivity.this, ChoiceJourneyActivity.class);
                startActivity(intent);
            }
        });
    }
}