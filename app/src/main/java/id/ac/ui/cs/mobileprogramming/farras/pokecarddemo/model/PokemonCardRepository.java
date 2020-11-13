package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PokemonCardRepository {
    private PokemonCardDao mPokemonCardDao;
    private LiveData<List<PokemonCard>> mAllPokemonCards;

    public PokemonCardRepository(Application application) {
        PokemonRoomDatabase db = PokemonRoomDatabase.getDatabase(application);
        mPokemonCardDao = db.pokemonCardDao();
        mAllPokemonCards = mPokemonCardDao.getAllPokemonCards();
    }

    LiveData<List<PokemonCard>> getAllPokemonCards() {
        return mAllPokemonCards;
    }

    LiveData<PokemonCard> getPokemonCardById(String id) {
        return mPokemonCardDao.getPokemonCardById(id);
    }
}
