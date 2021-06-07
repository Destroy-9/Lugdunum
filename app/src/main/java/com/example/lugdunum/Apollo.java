package com.example.lugdunum;

import com.apollographql.apollo.ApolloClient;


public class Apollo {
    // First, create an `ApolloClient`
    // Creation of the link between front & back
    public static ApolloClient apolloClient = ApolloClient.builder()
            .serverUrl("https://lugdunum.herokuapp.com/graphql")
            .build();
}
