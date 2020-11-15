package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.ApiClient;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.ApiInterface;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.PokemonSet;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.PokemonSetResponse;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonRoomDatabase;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonSetDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;

public class DbSynchronizer extends AsyncTask<Void, Integer, String> {
    private WeakReference<Button> buttonView;
    private WeakReference<ProgressBar> progressBar;
    private final PokemonSetDao mPokemonSetDao;
    private List<PokemonSet> pokemonSetApiData;
    Context application;

    DbSynchronizer(Button button, ProgressBar progressBar, Context appContext) {
        mPokemonSetDao = PokemonRoomDatabase.getDatabase(appContext).pokemonSetDao();
        application = appContext;
        buttonView = new WeakReference<>(button);
        this.progressBar = new WeakReference<>(progressBar);
    }

    @Override
    protected String doInBackground(Void... voids) {

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<PokemonSetResponse> call = service.getCardSet();
        call.enqueue(new Callback<PokemonSetResponse>() {
            @Override
            public void onResponse(Call<PokemonSetResponse> call, Response<PokemonSetResponse> response) {

                Log.d("MainActivityDebugger","Dapat yes: " + response.body());
                Log.d("MainActivityDebugger","Dapat yes: " + response);
                pokemonSetApiData = response.body().getSets();

//                if (pokemonSetApiData.size() > 0) {
//                    for (int index = 0; index < pokemonSetApiData.size(); index++) {
//                        int percentage = (index+1) * (100/pokemonSetApiData.size());
//                        publishProgress(percentage);
//                        mPokemonSetDao.insert(pokemonSetApiData.get(index).toPokemonSetEntity());
//                    }
//                }

//                Runnable startTimer = new Runnable() {
//                    @Override
//                    public void run() {
//                        // Insert Data
//                        Log.d("Runnable", "Running syncDb");
//                        syncDb(pokemonSetApiData);
//                    }
//                };

                Thread downloadThread = new Thread(new Runnable() {
                    public void run() {
                        // Insert Data
                        Log.d("Runnable", "Running syncDb");
                        syncDb(pokemonSetApiData);
                    }
                });

                downloadThread.run();

            }

            @Override
            public void onFailure(Call<PokemonSetResponse> call, Throwable t) {
                Log.wtf("Dafuk", "The F");
                Toast.makeText(application, "Something is wrong. I can feel it.", Toast.LENGTH_SHORT);
            }
        });

        return "Sync";
    }

    public void syncDb (List<PokemonSet> pokemonSets) {
        Log.wtf("SyncDB", String.valueOf(mPokemonSetDao == null));
        Log.wtf("SyncDB", String.valueOf(mPokemonSetDao.getAllSets()));
        Log.wtf("SyncDB", String.valueOf(mPokemonSetDao.getAllSets().getValue()));
        if (mPokemonSetDao.getAllSets().getValue() == null ) {
            for (int index = 0; index < pokemonSets.size(); index++) {
                int percentage = (index+1) * (100/pokemonSets.size());
                publishProgress(percentage);
                mPokemonSetDao.insert(pokemonSets.get(index).toPokemonSetEntity());
            }
        }
    }

    protected void onPostExecute(String result) {
        Log.d("onPostExecute" , String.valueOf(pokemonSetApiData));
        buttonView.get().setText(result);
        buttonView.get().setEnabled(true);
        progressBar.get().setProgress(0);
        progressBar.get().setVisibility(View.INVISIBLE);
        Log.wtf("onPostExecute", result);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.wtf("onProgressUpdate", Arrays.toString(values) + "");
        progressBar.get().setProgress(values[0]);
    }
}
