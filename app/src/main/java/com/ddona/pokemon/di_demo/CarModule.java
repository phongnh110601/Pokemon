package com.ddona.pokemon.di_demo;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class CarModule {

    @Provides
    public static Library provideLibrary(){
        return new Library();
    }
}
