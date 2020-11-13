package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.fragment;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonCardViewModel;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.R;

public class PokemonCardFragment extends Fragment {

    private PokemonCardViewModel mViewModel;
    private String setName;

    public static PokemonCardFragment newInstance() {
        return new PokemonCardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pokemon_card_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PokemonCardViewModel.class);
        // TODO: Use the ViewModel
    }

}
