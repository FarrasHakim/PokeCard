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
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.PokemonSetsAdapter;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonSet;

import java.util.List;

public class PokemonSetFragment extends Fragment implements PokemonSetsAdapter.CardListener {
    private PokemonSetsAdapter adapter;
    private RecyclerView recyclerView;
    private PokemonSetViewModel mPokemonSetViewModel;
    ProgressDialog progressDialog;

    public PokemonSetFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SetFragment", "onCreate");
        mPokemonSetViewModel = ViewModelProviders.of(this).get(PokemonSetViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("SetFragment", "OnResume");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d("SetFragment", "ViewStateResrotered");
        mPokemonSetViewModel.getAllSets().observe(getViewLifecycleOwner(), new Observer<List<PokemonSet>>() {
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
        adapter = new PokemonSetsAdapter(getContext(),this,pokemonSets);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCardListener(int position, boolean addClicked, View view) {
        PokemonSet theSet = adapter.getPokemonSetAt(position);
        String toastText = "Card " + position + "clicked" + "\n"
                + "Set code :" + theSet.getId() + "\n"
                + "Set Name : " + theSet.getName() + "\n"
                + "Total cards:  " + theSet.getTotalCards();
        Toast.makeText(getContext(), toastText, Toast.LENGTH_LONG).show();
    }
}
