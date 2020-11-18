package com.ddona.pokemon.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.ddona.pokemon.db.PokemonDao;
import com.ddona.pokemon.db.PokemonDatabase;
import com.ddona.pokemon.model.Pokemon;
import com.ddona.pokemon.model.PokemonResponse;
import com.ddona.pokemon.network.PokemonModule;
import com.ddona.pokemon.network.PokemonService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;

public class PokemonRepository {
    private PokemonDao pokemonDao;
    @Inject
    private PokemonService pokemonService;

    public PokemonRepository(Application application) {
        this.pokemonDao = PokemonDatabase.getInstance(application).pokemonDao();
        this.pokemonService = PokemonModule.getInstance();
    }

    public Observable<PokemonResponse> getRemotePokemons(){
        return pokemonService.getAllPokemons();
    }

    public LiveData<List<Pokemon>> getLocalPokemon(){
        return pokemonDao.getAllPokemons();
    }

    public void insertPokemon(Pokemon pokemon){
        pokemonDao.insertPokemon(pokemon);
    }

    public void deletePokemon(int id){
        pokemonDao.deletePokemon(id);
    }
}
