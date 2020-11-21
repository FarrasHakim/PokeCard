package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.fragment;

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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.R;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.adapter.PokemonCardAdapter;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonCard;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.viewModel.PokemonCardViewModel;

import java.util.List;

public class PokemonCardFragment extends Fragment implements PokemonCardAdapter.CardListener {
    private final String bundleSetKey = "setName";
    private PokemonCardViewModel mViewModel;
    private List<PokemonCard> mPokemonCards;
    private String setName;
    private PokemonCardAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.wtf(bundleSetKey, String.valueOf(getArguments()));
        Log.wtf(bundleSetKey, String.valueOf(getArguments().get(bundleSetKey)));
        getArguments().get(setName);
        if (getArguments().get(bundleSetKey) != null) {
            this.setName = getArguments().get(bundleSetKey).toString();
        }
        mViewModel = ViewModelProviders.of(this).get(PokemonCardViewModel.class);
        Log.wtf("onCreate", "Masuk sini");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.wtf("onActivityCreated", "Masuk sini");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.wtf("onCreateView", "Masuk sini");
        View root = inflater.inflate(R.layout.pokemon_card_fragment, container, false);
        return root;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.wtf("onViewStateRestored", "Masuk sini");
    }

    @Override
    public void onStart() {
        super.onStart();
        if (setName != null) {
            Log.wtf("onCallRepository", "SetName: " + setName);
            mViewModel.getCardsBySetName(setName).observe(getViewLifecycleOwner(), new Observer<List<PokemonCard>>() {
                @Override
                public void onChanged(@Nullable final List<PokemonCard> cards) {
                    // Update the cached copy of the words in the adapter.
                    Log.wtf("onChangedObserver", "SetName: " + setName);
                    mPokemonCards = cards;
                    loadPokemonCardUI(cards);
                }
            });
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.wtf("onViewCreated", "Masuk sini");
    }

    private void loadPokemonCardUI(List<PokemonCard> pokemonCards) {
        recyclerView = getView().findViewById(R.id.cardRecyclerView);
        Log.d("loadPokemonCardUI", "Size: " + pokemonCards.size());
        adapter = new PokemonCardAdapter(getContext(), this, pokemonCards);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCardListener(int position, boolean addClicked, View view) {
        PokemonCard pokemonCard = mPokemonCards.get(position);

        // ClickArea
        if (addClicked) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("pokemon", pokemonCard);

            Navigation.findNavController(view).navigate(R.id.action_navigation_cards_to_navigation_detail, bundle);
        } else {
            // Favorite
            Toast.makeText(getContext(), pokemonCard.getName() + getString(R.string.added_to_favorites), Toast.LENGTH_LONG).show();
            mViewModel.addCardToFavorites(pokemonCard.getId());
        }
    }
}
