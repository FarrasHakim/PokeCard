package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.*;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonCardRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Arrays;
import java.util.List;

public class DbSynchronizer extends AsyncTask<Void, Integer, String> {
    //    private WeakReference<Button> buttonView;
//    private WeakReference<ProgressBar> progressBar;
    private final PokemonCardRepository mPokemonCardRepository;
    Context application;
    private List<PokemonSet> pokemonSetApiData;

    public DbSynchronizer(PokemonCardRepository repository, Context appContext) {
        mPokemonCardRepository = repository;
        application = appContext;
    }

    @Override
    protected String doInBackground(Void... voids) {

        final ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<PokemonSetResponse> call = service.getCardSet();

        call.enqueue(new Callback<PokemonSetResponse>() {
            @Override
            public void onResponse(Call<PokemonSetResponse> call, Response<PokemonSetResponse> response) {

                Log.d("MainActivityDebugger", "Dapat yes: " + response.body());
                Log.d("MainActivityDebugger", "Dapat yes: " + response);

                final List<id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.PokemonSet> pokemonSetApiData = response.body().getSets();
                Thread downloadThread = new Thread(new Runnable() {
                    public void run() {
                        // Insert Data
                        Log.d("Runnable", "Running syncDb");
                        if (pokemonSetApiData.size() > 0) {
                            for (int index = 0; index < pokemonSetApiData.size(); index++) {
                                int percentage = (index + 1) * (100 / pokemonSetApiData.size());
//                                publishProgress(percentage);
                                mPokemonCardRepository.insertPokemonSet(pokemonSetApiData.get(index).toPokemonSetEntity());
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

        for (int page = 1; page <= 94; page++) {
            final Call<PokemonCardResponse> cardCall = service.getCards(page);
            cardCall.enqueue(new Callback<PokemonCardResponse>() {
                @Override
                public void onResponse(Call<PokemonCardResponse> call, Response<PokemonCardResponse> response) {

                    Log.d("cardCall", "Dapat yes: " + response.body());
                    Log.d("cardCall", "Dapat yes: " + response);

                    final List<id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.PokemonCard> pokemonCardApiData = response.body().getPokemonCards();
                    Thread insertToDBThread = new Thread(new Runnable() {
                        public void run() {
                            // Insert Data
                            if (pokemonCardApiData.size() > 0) {
                                Log.d("CardCall", pokemonCardApiData.size() + "");
                                for (int index = 0; index < pokemonCardApiData.size(); index++) {
                                    id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.PokemonCard pokemonCard = pokemonCardApiData.get(index);
//                                Log.wtf("PokemonSetViewModel", pokemonCard.getTypes() + "");
//                                Log.wtf("PokemonSetViewModel", pokemonCard.getId() + "");
//                                Log.wtf("PokemonSetViewModel", pokemonCard.getSupertype() + "");
                                    int percentage = (index + 1) * (100 / pokemonCardApiData.size());
//                                publishProgress(percentage);
                                    if (pokemonCardApiData.get(index).getSupertype().equalsIgnoreCase("Pokémon")) {
                                        mPokemonCardRepository.insertPokemonCard(pokemonCardApiData.get(index).toPokemonCardEntity());
                                    }
                                }
                            }
                        }
                    });

                    insertToDBThread.run();

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


        return "Sync";
    }

    public void syncDb(List<PokemonSet> pokemonSets) {
    }

    protected void onPostExecute(String result) {
        Log.d("onPostExecute", String.valueOf(pokemonSetApiData));
//        buttonView.get().setText(result);
//        buttonView.get().setEnabled(true);
//        progressBar.get().setProgress(0);
//        progressBar.get().setVisibility(View.INVISIBLE);
        Log.wtf("onPostExecute", result);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.wtf("onProgressUpdate", Arrays.toString(values) + "");
//        progressBar.get().setProgress(values[0]);
    }
}
