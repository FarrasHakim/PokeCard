package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.PokemonSetsAdapter;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.R;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.fragment.PokemonSetFragment;

import java.util.List;

public class PokemonCardViewModel extends AndroidViewModel {
    private PokemonCardRepository mRepository;
    private LiveData<List<PokemonCard>> mAllSets;
    Context application;

    public PokemonCardViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = new PokemonCardRepository(application);
        this.mAllSets = mRepository.getAllPokemonCards();
        this.application = application;
    }

    public LiveData<List<PokemonCard>> getAllCards() {
        return mAllSets;
    }

    public LiveData<List<PokemonCard>> getCardsBySetName(String setName) {
        return mRepository.getPokemonCardsBySetName(setName);
    }
}
