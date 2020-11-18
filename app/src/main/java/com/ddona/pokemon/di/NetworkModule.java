package com.ddona.pokemon.di;


import com.ddona.pokemon.network.PokemonService;
import com.ddona.pokemon.util.Const;

import javax.inject.Singleton;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
@InstallIn(ApplicationComponent.class)
public class NetworkModule {

    @Singleton
    public static Retrofit provideRetrofit(){
        return new Retrofit.Builder().baseUrl(Const.BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static PokemonService providePokemonService(Retrofit retrofit){
        return retrofit.create(PokemonService.class);
    }
}
