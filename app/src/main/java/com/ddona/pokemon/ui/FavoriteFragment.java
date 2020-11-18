package com.ddona.pokemon.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ddona.pokemon.R;
import com.ddona.pokemon.adpater.PokemonAdapter;
import com.ddona.pokemon.model.Pokemon;
import com.ddona.pokemon.viewmodel.PokemonViewModel;
import com.ddona.pokemon.viewmodel.PokemonViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    private static FavoriteFragment INSTANCE;

    public static FavoriteFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FavoriteFragment();
        }
        return INSTANCE;
    }

    private PokemonViewModel viewModel;
    private RecyclerView rvPokemon;
    private PokemonAdapter adapter;
    private List<Pokemon> mPokemons;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon, container, false);
        viewModel = new ViewModelProvider(requireActivity(), new PokemonViewModelFactory(requireActivity().getApplication()))
                .get(PokemonViewModel.class);
        mPokemons = new ArrayList<>();
        adapter = new PokemonAdapter(mPokemons);
        rvPokemon = view.findViewById(R.id.rv_pokemon);
        rvPokemon.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvPokemon.setAdapter(adapter);
        viewModel.getLocalPokemons().observe(getViewLifecycleOwner(), pokemons -> {
            mPokemons.clear();
            mPokemons.addAll(pokemons);
            adapter.notifyDataSetChanged();
        });
        return view;
    }
}