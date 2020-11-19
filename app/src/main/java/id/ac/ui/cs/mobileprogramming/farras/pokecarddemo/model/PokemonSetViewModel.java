package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.DbSynchronizer;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class PokemonSetViewModel extends AndroidViewModel {
    private PokemonCardRepository mRepository;
    private LiveData<List<PokemonSet>> mAllSets;
    Context application;

    public PokemonSetViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        mRepository = new PokemonCardRepository(application);
        mAllSets = mRepository.getAllPokemonSets();
    }

    public void syncDb() {
        Log.d("PokemonSetViewModel", "SyncDB");
        if (isWifiConnected()) {
            new DbSynchronizer(mRepository, application).execute();
        } else {
            Toast.makeText(application, "No Wifi Connection", Toast.LENGTH_SHORT).show();
        }
    }

    public LiveData<List<PokemonSet>> getAllSets() {
        return mAllSets;
    }

    public void insert(PokemonSet set) {
        mRepository.insertPokemonSet(set);
    }

    public void insertPokemonCard(id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonCard card) {mRepository.insertPokemonCard(card);}

    private boolean isWifiConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        boolean isMetered = connMgr.isActiveNetworkMetered();
        return (networkInfo != null && networkInfo.isConnected() && isMetered);
    }
}
