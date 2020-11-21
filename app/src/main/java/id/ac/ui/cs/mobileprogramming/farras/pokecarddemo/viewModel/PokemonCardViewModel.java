package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.viewModel;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonCard;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonCardRepository;

import java.util.List;

public class PokemonCardViewModel extends AndroidViewModel {
    private final PokemonCardRepository mRepository;
    private final LiveData<List<PokemonCard>> mAllCards;
    Context application;

    public PokemonCardViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = new PokemonCardRepository(application);
        this.mAllCards = mRepository.getAllPokemonCards();
        this.application = application;
    }

    public LiveData<List<PokemonCard>> getAllCards() {
        return mAllCards;
    }

    public LiveData<List<PokemonCard>> getCardsBySetName(String setName) {
        return mRepository.getPokemonCardsBySetName(setName);
    }

    public LiveData<List<PokemonCard>> getFavoriteCards() {
        return mRepository.getFavoriteCards();
    }

    public void addCardToFavorites(String cardId) {
        final String pokemonCardId = cardId;
        Thread updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                mRepository.addCardToFavorites(pokemonCardId);
            }
        });
        updateThread.run();
    }

    public void removeCardFromFavorites(String id) {
        mRepository.removeCardFromFavorites(id);
    }
}
