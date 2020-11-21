package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.R;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.adapter.PokemonFavoriteAdapter;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonCard;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.viewModel.PokemonCardViewModel;

import java.util.List;

public class FavoriteCardFragment extends Fragment implements PokemonFavoriteAdapter.CardListener {
    private PokemonCardViewModel mViewModel;
    private RecyclerView recyclerView;
    private PokemonFavoriteAdapter adapter;
    private List<PokemonCard> favoriteCards;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PokemonCardViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_favorite_card, container, false);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.getFavoriteCards().observe(getViewLifecycleOwner(), new Observer<List<PokemonCard>>() {
            @Override
            public void onChanged(@Nullable final List<PokemonCard> cards) {
                // Update the cached copy of the words in the adapter.
                favoriteCards = cards;
                loadPokemonCardUI(cards);
            }
        });
    }

    private void loadPokemonCardUI(List<PokemonCard> pokemonCards) {
        recyclerView = getView().findViewById(R.id.favoritesRecyclerView);
        adapter = new PokemonFavoriteAdapter(getContext(), this, pokemonCards);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCardListener(int position, boolean addClicked, View view) {
        // ToDo: Remove Card from favorites
        PokemonCard card = favoriteCards.get(position);
        Log.wtf("onCardListener Favorite", "Card Removed");
        Toast.makeText(getContext(), card.getName() + " removed from favorites.", Toast.LENGTH_LONG).show();
        mViewModel.removeCardFromFavorites(card.getId());
    }
}
