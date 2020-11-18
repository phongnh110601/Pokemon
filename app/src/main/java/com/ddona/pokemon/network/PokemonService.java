package com.ddona.pokemon.network;

import com.ddona.pokemon.model.PokemonResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonService {

    @GET("pokemon")
    Observable<PokemonResponse> getAllPokemons();
}
