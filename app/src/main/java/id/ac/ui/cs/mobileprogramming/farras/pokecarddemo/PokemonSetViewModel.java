package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonCardRepository;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonSet;

import java.util.List;

public class PokemonSetViewModel extends AndroidViewModel {
    private PokemonCardRepository mRepository;
    private LiveData<List<PokemonSet>> mAllSets;

    public PokemonSetViewModel(@NonNull Application application) {
        super(application);
        mRepository = new PokemonCardRepository(application);
        mAllSets = mRepository.getAllPokemonSets();
    }

    public LiveData<List<PokemonSet>> getAllSets() {
        return mAllSets;
    }

    public void insert(PokemonSet set) {
        mRepository.insertPokemonSet(set);
    }
}
