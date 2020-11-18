package com.ddona.pokemon.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;


import com.ddona.pokemon.R;
import com.ddona.pokemon.adpater.PokemonAdapter;
import com.ddona.pokemon.model.Pokemon;
import com.ddona.pokemon.viewmodel.PokemonViewModel;
import com.ddona.pokemon.viewmodel.PokemonViewModelFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PokemonFragment extends Fragment {

    private static PokemonFragment INSTANCE;
    private PokemonViewModel viewModel;
    private List<Pokemon> mPokemons;
    private PokemonAdapter adapter;


    public static PokemonFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PokemonFragment();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPokemons = new ArrayList<>();
        adapter = new PokemonAdapter(mPokemons);
        viewModel = new ViewModelProvider(requireActivity(), new PokemonViewModelFactory(requireActivity().getApplication())).get(PokemonViewModel.class);
        viewModel.getRemotePokemons().observe(getViewLifecycleOwner(), pokemons -> {
            mPokemons.clear();
            mPokemons.addAll(pokemons);
            adapter.notifyDataSetChanged();
        });
        viewModel.getPokemons();
        View view = inflater.inflate(R.layout.fragment_pokemon, container, false);
        RecyclerView rvPokemon = view.findViewById(R.id.rv_pokemon);
        rvPokemon.setAdapter(adapter);
        setupSwipe(rvPokemon);
        return view;
    }

    private void setupSwipe(RecyclerView rvPokemon) {
         ItemTouchHelper.SimpleCallback callback =
                 new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Pokemon pokemon = mPokemons.get(position);
                viewModel.insertPokemon(pokemon);
                adapter.notifyDataSetChanged();
            }
        };

         ItemTouchHelper helper = new ItemTouchHelper(callback);
         helper.attachToRecyclerView(rvPokemon);
    }
}