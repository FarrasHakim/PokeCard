package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.R;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonCardViewModel;

public class FavoriteCardFragment extends Fragment {
    private PokemonCardViewModel mViewModel;

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
//        mViewModel.getCardsBySetName(setName).observe(getViewLifecycleOwner(), new Observer<List<PokemonCard>>() {
//            @Override
//            public void onChanged(@Nullable final List<PokemonCard> cards) {
//                // Update the cached copy of the words in the adapter.
//                Log.wtf("onChangedObserver", "SetName: " + setName);
//                mPokemonCards = cards;
//                loadPokemonCardUI(cards);
//            }
//        });
    }
}
