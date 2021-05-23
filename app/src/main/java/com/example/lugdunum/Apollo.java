package com.example.lugdunum;

import com.apollographql.apollo.ApolloClient;


public class Apollo {
    // First, create an `ApolloClient`
    // Replace the serverUrl with your GraphQL endpoint
    public static ApolloClient apolloClient = ApolloClient.builder()
            .serverUrl("https://localhost:3000/graphql/endpoint")
            .build();
}
