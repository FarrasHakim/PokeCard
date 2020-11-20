package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PokemonCardViewModel extends AndroidViewModel {
    private final PokemonCardRepository mRepository;
    private final LiveData<List<PokemonCard>> mAllSets;
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

    public LiveData<List<PokemonCard>> getFavoriteCards() {
        return mRepository.getFavoriteCards();
    }
}
