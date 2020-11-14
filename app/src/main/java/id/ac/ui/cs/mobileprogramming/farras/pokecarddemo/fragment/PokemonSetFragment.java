package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.PokemonSetViewModel;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.R;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.SetsAdapter;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.ApiClient;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.ApiInterface;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.PokemonSetResponse;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonSet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class PokemonSetFragment extends Fragment {
    private SetsAdapter adapter;
    private RecyclerView recyclerView;
    private PokemonSetViewModel mPokemonSetViewModel;
    ProgressDialog progressDialog;

    public PokemonSetFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPokemonSetViewModel = ViewModelProviders.of(this).get(PokemonSetViewModel.class);
        mPokemonSetViewModel.getAllSets().observe(this, new Observer<List<PokemonSet>>() {
            @Override
            public void onChanged(@Nullable final List<PokemonSet> sets) {
                // Update the cached copy of the words in the adapter.
                loadPokemonSetUI(sets);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.pokemon_set_fragment, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void loadPokemonSetUI(List<PokemonSet> pokemonSets) {
        recyclerView = getView().findViewById(R.id.customRecyclerView);
        adapter = new SetsAdapter(getActivity(),pokemonSets);
//        adapter.setPokemonSet(pokemonSets);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
