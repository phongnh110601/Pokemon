package com.ddona.pokemon.db;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ddona.pokemon.model.Pokemon;

@Database(entities = {Pokemon.class}, version = 1, exportSchema = false)
public abstract class PokemonDatabase extends RoomDatabase {
    public abstract PokemonDao pokemonDao();

    public static PokemonDatabase getInstance(Application application){
        return Room.databaseBuilder(application.getApplicationContext(), PokemonDatabase.class, "pokemon.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
}
