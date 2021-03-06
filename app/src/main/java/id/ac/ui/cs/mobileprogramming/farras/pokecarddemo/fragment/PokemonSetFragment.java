package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.R;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.adapter.PokemonSetsAdapter;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonSet;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.viewModel.PokemonSetViewModel;

import java.util.List;

public class PokemonSetFragment extends Fragment implements PokemonSetsAdapter.CardListener {
    private final String setName = "setName";
    ProgressDialog progressDialog;
    private PokemonSetsAdapter adapter;
    private RecyclerView recyclerView;
    private PokemonSetViewModel mPokemonSetViewModel;
    private Button buttonView;
    private View mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.pokemon_set_fragment, container, false);
        this.mView = root;

        progressDialog = new ProgressDialog(getActivity());
        buttonView = mView.findViewById(R.id.syncButton);
        mPokemonSetViewModel = ViewModelProviders.of(this).get(PokemonSetViewModel.class);
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syncDb(v);
            }
        });

        return root;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        mPokemonSetViewModel.getAllSets().observe(getViewLifecycleOwner(), new Observer<List<PokemonSet>>() {
            @Override
            public void onChanged(@Nullable final List<PokemonSet> sets) {
                // Update the cached copy of the words in the adapter.
                loadPokemonSetUI(sets);
            }
        });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void loadPokemonSetUI(List<PokemonSet> pokemonSets) {
        recyclerView = getView().findViewById(R.id.customRecyclerView);
        adapter = new PokemonSetsAdapter(getContext(), this, pokemonSets);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void syncDb(View view) {
        buttonView.setEnabled(false);
        mPokemonSetViewModel.syncDb(progressDialog);
        buttonView.setEnabled(true);
    }

    @Override
    public void onCardListener(int position, boolean addClicked, View view) {
        PokemonSet theSet = adapter.getPokemonSetAt(position);

        Bundle bundle = new Bundle();
        bundle.putString(setName, theSet.getName());

        Navigation.findNavController(view).navigate(R.id.action_navigation_sets_to_navigation_cards, bundle);
    }
}
