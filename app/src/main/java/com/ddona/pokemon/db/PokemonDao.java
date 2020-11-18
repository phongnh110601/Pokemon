package com.ddona.pokemon.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ddona.pokemon.model.Pokemon;

import java.util.List;

@Dao
public interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPokemon(Pokemon pokemon);

    @Query("SELECT * FROM pokemon")
    LiveData<List<Pokemon>> getAllPokemons();

    @Query("DELETE FROM pokemon where id = :id")
    void deletePokemon(int id);
}
