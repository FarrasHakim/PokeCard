package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PokemonCardRepository {
    private PokemonCardDao mPokemonCardDao;
    private PokemonSetDao mPokemonSetDao;
    private LiveData<List<PokemonCard>> mAllPokemonCards;
    private LiveData<List<PokemonSet>> mAllPokemonSets;

    public PokemonCardRepository(Application application) {
        PokemonRoomDatabase db = PokemonRoomDatabase.getDatabase(application);
        mPokemonCardDao = db.pokemonCardDao();
        mPokemonSetDao = db.pokemonSetDao();
        mAllPokemonCards = mPokemonCardDao.getAllPokemonCards();
        mAllPokemonSets = mPokemonSetDao.getAllSets();
    }

    LiveData<List<PokemonCard>> getAllPokemonCards() {
        return mAllPokemonCards;
    }

    LiveData<List<PokemonCard>> getPokemonCardsBySetName(String setName) {
        return mPokemonCardDao.getPokemonCardsBySet(setName);
    }

    LiveData<PokemonCard> getPokemonCardById(String id) {
        return mPokemonCardDao.getPokemonCardById(id);
    }

    public LiveData<List<PokemonSet>> getAllPokemonSets() { return mAllPokemonSets; }

    public void insertPokemonSet(PokemonSet pokemonSet) {
        new insertAsyncTask(mPokemonSetDao).execute(pokemonSet);
    }

    private static class insertAsyncTask extends AsyncTask<PokemonSet, Void, Void> {

        private PokemonSetDao mAsyncTaskDao;

        insertAsyncTask(PokemonSetDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PokemonSet... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}


