package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.*;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonCardRepository;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonSet;
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
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<PokemonSetResponse> call = service.getCardSet();


        call.enqueue(new Callback<PokemonSetResponse>() {
            @Override
            public void onResponse(Call<PokemonSetResponse> call, Response<PokemonSetResponse> response) {

                Log.d("MainActivityDebugger","Dapat yes: " + response.body());
                Log.d("MainActivityDebugger","Dapat yes: " + response);

                final List<id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.PokemonSet> pokemonSetApiData = response.body().getSets();
                Thread downloadThread = new Thread(new Runnable() {
                    public void run() {
                        // Insert Data
                        Log.d("Runnable", "Running syncDb");
                        if (pokemonSetApiData.size() > 0) {
                            for (int index = 0; index < pokemonSetApiData.size(); index++) {
                                int percentage = (index+1) * (100/pokemonSetApiData.size());
//                                publishProgress(percentage);
                                insert(pokemonSetApiData.get(index).toPokemonSetEntity());
                            }
                        }
                    }
                });

                downloadThread.run();

            }

            @Override
            public void onFailure(Call<PokemonSetResponse> call, Throwable t) {
                Log.wtf("Dafuk", "The F ");
                Log.wtf("Dafuk", String.valueOf(call));
                Toast.makeText(application, "Something is wrong. I can feel it.", Toast.LENGTH_SHORT);
            }
        });


        Call<PokemonCardResponse> cardCall = service.getCards();

        Log.d("SebelumCardCall", "SebelumCardCall " + cardCall);
        cardCall.enqueue(new Callback<PokemonCardResponse>() {
            @Override
            public void onResponse(Call<PokemonCardResponse> call, Response<PokemonCardResponse> response) {

                Log.d("cardCall","Dapat yes: " + response.body());
                Log.d("cardCall","Dapat yes: " + response);

                final List<PokemonCard> pokemonCardApiData = response.body().getPokemonCards();
                Thread downloadThread = new Thread(new Runnable() {
                    public void run() {
                        // Insert Data
                        if (pokemonCardApiData.size() > 0) {
                            for (int index = 0; index < pokemonCardApiData.size(); index++) {
                                PokemonCard pokemonCard = pokemonCardApiData.get(index);
                                Log.wtf("PokemonSetViewModel", pokemonCard.getTypes() + "");
                                Log.wtf("PokemonSetViewModel", pokemonCard.getId() + "");
                                Log.wtf("PokemonSetViewModel", pokemonCard.getName() + "");
                                Log.wtf("PokemonSetViewModel", pokemonCard.getSupertype() + "");
                                int percentage = (index+1) * (100/pokemonCardApiData.size());
//                                publishProgress(percentage);
                                if (pokemonCard.getSupertype().equalsIgnoreCase("Pokemon")) {
                                    insertPokemonCard(pokemonCardApiData.get(index).toPokemonCardEntity());
                                }
                            }
                        }
                    }
                });

                downloadThread.run();

            }

            @Override
            public void onFailure(Call<PokemonCardResponse> call, Throwable t) {
                Log.wtf("Dafuk", String.valueOf(call.request()));
                Log.wtf("Dafuk", String.valueOf(call.request().body()));
                Log.wtf("Dafuk", t);
                Toast.makeText(application, "Something is wrong. I can feel it.", Toast.LENGTH_SHORT);
            }
        });

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
