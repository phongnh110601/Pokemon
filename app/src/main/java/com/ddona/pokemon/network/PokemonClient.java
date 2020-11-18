package com.ddona.pokemon.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonClient {
    private static Retrofit INSTANCE;
    private PokemonClient(){

    }
    public static Retrofit getInstance(String url){
        if (INSTANCE == null){
            INSTANCE = new Retrofit.Builder().baseUrl(url)
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return INSTANCE;
    }
}
