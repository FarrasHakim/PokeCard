package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.fragment;

import android.util.Log;
import android.widget.Toast;
import androidx.fragment.app.FragmentFactory;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.Navigation;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonCardViewModel;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.R;

public class PokemonCardFragment extends Fragment {
    private final String bundleSetKey = "setName";
    private PokemonCardViewModel mViewModel;
    private String setName;

    public static PokemonCardFragment newInstance() {
        return new PokemonCardFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.wtf(bundleSetKey, String.valueOf(getArguments()));
        Log.wtf(bundleSetKey, String.valueOf(getArguments().get(bundleSetKey)));
        getArguments().get(setName);
        if (getArguments().get(bundleSetKey) != null) {
            this.setName = getArguments().get(bundleSetKey).toString();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PokemonCardViewModel.class);

        // TODO: Use the ViewModel
    }

}
