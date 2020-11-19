package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonSetViewModel;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.R;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.PokemonSetsAdapter;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonSet;

import java.util.List;

public class PokemonSetFragment extends Fragment implements PokemonSetsAdapter.CardListener {
    private final String setName = "setName";
    private PokemonSetsAdapter adapter;
    private RecyclerView recyclerView;
    private PokemonSetViewModel mPokemonSetViewModel;
    ProgressDialog progressDialog;
    private ProgressBar progressBar;
    private Button buttonView;
    private View mView;

    public PokemonSetFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SetFragment", "onCreate");    }

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
        this.mView = root;

        progressBar = (ProgressBar) mView.findViewById(R.id.progressBar);
        progressDialog = new ProgressDialog(getActivity());
        progressBar.setVisibility(View.INVISIBLE);
        buttonView = (Button) mView.findViewById(R.id.syncButton);
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

    public void syncDb(View view) {
        progressDialog.setMessage("Syncing....");
        progressDialog.show();
        Log.wtf("DAFUQ", view.getClass().getCanonicalName());
        buttonView.setText("Syncing...");
        buttonView.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
//        new DbSynchronizer(buttonView, progressBar, this).execute();
        Log.wtf("DAFUQ", "After sync db");
        mPokemonSetViewModel.syncDb();
        progressDialog.dismiss();
        buttonView.setText("Sync");
        buttonView.setEnabled(true);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onCardListener(int position, boolean addClicked, View view) {
        PokemonSet theSet = adapter.getPokemonSetAt(position);
        String toastText = "Card " + position + "clicked" + "\n"
                + "Set code :" + theSet.getId() + "\n"
                + "Set Name : " + theSet.getName() + "\n"
                + "Total cards:  " + theSet.getTotalCards();
        Toast.makeText(getContext(), toastText, Toast.LENGTH_LONG).show();

        Bundle bundle = new Bundle();
        bundle.putString(setName, theSet.getName());
//        NavDirections action = PokemonSetFragmentDirections.actionNavigationSetsToNavigationCards();

        Navigation.findNavController(view).navigate(R.id.action_navigation_sets_to_navigation_cards, bundle);
    }
}
