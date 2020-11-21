package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PokemonCardRepository {
    private final PokemonCardDao mPokemonCardDao;
    private final PokemonSetDao mPokemonSetDao;
    private final LiveData<List<PokemonCard>> mAllPokemonCards;
    private final LiveData<List<PokemonSet>> mAllPokemonSets;

    public PokemonCardRepository(Application application) {
        PokemonRoomDatabase db = PokemonRoomDatabase.getDatabase(application);
        mPokemonCardDao = db.pokemonCardDao();
        mPokemonSetDao = db.pokemonSetDao();
        mAllPokemonCards = mPokemonCardDao.getAllPokemonCards();
        mAllPokemonSets = mPokemonSetDao.getAllSets();
    }

    public LiveData<List<PokemonCard>> getAllPokemonCards() {
        return mAllPokemonCards;
    }

    public LiveData<List<PokemonCard>> getPokemonCardsBySetName(String setName) {
        Log.d("DAO", setName);
        return mPokemonCardDao.getPokemonCardsBySet(setName);
    }

    LiveData<PokemonCard> getPokemonCardById(String id) {
        return mPokemonCardDao.getPokemonCardById(id);
    }

    public LiveData<List<PokemonSet>> getAllPokemonSets() {
        return mAllPokemonSets;
    }

    public void insertPokemonSet(PokemonSet pokemonSet) {
        new insertSetAsyncTask(mPokemonSetDao).execute(pokemonSet);
    }

    public void insertPokemonCard(PokemonCard pokemonCard) {
        Log.wtf("insertPokemonCardRepository", pokemonCard.getName());
        Log.wtf("insertPokemonCardRepository", pokemonCard.getSet());
        new insertCardAsyncTask(mPokemonCardDao).execute(pokemonCard);
    }

    public LiveData<List<PokemonCard>> getFavoriteCards() {
        return mPokemonCardDao.getFavoriteCards();
    }

    public void addCardToFavorites(String cardId) {
        new updateFavoriteCard(mPokemonCardDao, true).execute(cardId);
    }

    public void removeCardFromFavorites(String id) {
        new updateFavoriteCard(mPokemonCardDao, false).execute(id);
    }

    private static class insertSetAsyncTask extends AsyncTask<PokemonSet, Void, Void> {

        private final PokemonSetDao mAsyncTaskDao;

        insertSetAsyncTask(PokemonSetDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PokemonSet... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertCardAsyncTask extends AsyncTask<PokemonCard, Void, Void> {

        private final PokemonCardDao mAsyncTaskDao;

        insertCardAsyncTask(PokemonCardDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PokemonCard... params) {
            mAsyncTaskDao.insert(params[0]);
            Log.wtf(params[0].getSet(), String.valueOf(params[0].getName()));
            return null;
        }
    }

    private static class updateFavoriteCard extends AsyncTask<String, Void, Void> {

        private final PokemonCardDao mAsyncTaskDao;
        private final Boolean isAddingToFavorites;

        updateFavoriteCard(PokemonCardDao dao, Boolean isAddingToFavorites) {
            mAsyncTaskDao = dao;
            this.isAddingToFavorites = isAddingToFavorites;
        }

        @Override
        protected Void doInBackground(String... strings) {
            if (isAddingToFavorites) {
                mAsyncTaskDao.addCardToFavorites(strings[0]);
            } else {
                mAsyncTaskDao.removeCardFromFavorites(strings[0]);
            }
            return null;
        }
    }
}


