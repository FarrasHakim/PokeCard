package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.ApiClient;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.ApiInterface;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.PokemonCardResponse;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.PokemonSetResponse;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonCardRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.lang.ref.WeakReference;
import java.util.List;

public class DbSynchronizer extends AsyncTask<Void, Integer, String> {
    private final PokemonCardRepository mPokemonCardRepository;
    Context application;
    private final WeakReference<ProgressDialog> progressDialogWeakReference;

    public DbSynchronizer(PokemonCardRepository repository, Context appContext, ProgressDialog progressDialog) {
        mPokemonCardRepository = repository;
        application = appContext;
        this.progressDialogWeakReference = new WeakReference<ProgressDialog>(progressDialog);
        this.progressDialogWeakReference.get().setMessage(appContext.getString(R.string.syncing));
        this.progressDialogWeakReference.get().show();
    }

    @Override
    protected String doInBackground(Void... voids) {

        final ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<PokemonSetResponse> call = service.getCardSet();

        call.enqueue(new Callback<PokemonSetResponse>() {
            @Override
            public void onResponse(Call<PokemonSetResponse> call, Response<PokemonSetResponse> response) {

                final List<id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.PokemonSet> pokemonSetApiData = response.body().getSets();
                Thread downloadThread = new Thread(new Runnable() {
                    public void run() {
                        // Insert Data
                        if (pokemonSetApiData.size() > 0) {
                            for (int index = 0; index < pokemonSetApiData.size(); index++) {
                                mPokemonCardRepository.insertPokemonSet(pokemonSetApiData.get(index).toPokemonSetEntity());
                            }
                        }
                    }
                });

                downloadThread.run();

            }

            @Override
            public void onFailure(Call<PokemonSetResponse> call, Throwable t) {
                Toast.makeText(application, R.string.retrofit_error, Toast.LENGTH_SHORT).show();
            }
        });

        for (int page = 1; page <= 94; page++) {
            final Call<PokemonCardResponse> cardCall = service.getCards(page);
            cardCall.enqueue(new Callback<PokemonCardResponse>() {
                @Override
                public void onResponse(Call<PokemonCardResponse> call, Response<PokemonCardResponse> response) {
                    final List<id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.PokemonCard> pokemonCardApiData = response.body().getPokemonCards();
                    Thread insertToDBThread = new Thread(new Runnable() {
                        public void run() {
                            // Insert Data
                            if (pokemonCardApiData.size() > 0) {
                                for (int index = 0; index < pokemonCardApiData.size(); index++) {
                                    id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.PokemonCard pokemonCard = pokemonCardApiData.get(index);
                                    if (pokemonCardApiData.get(index).getSupertype().equalsIgnoreCase("Pokémon")) {
                                        mPokemonCardRepository.insertPokemonCard(pokemonCard.toPokemonCardEntity());
                                    }
                                }
                            }
                        }
                    });

                    insertToDBThread.run();
//                    addNotification("PokeCard", application.getString(R.string.sync_done));

                }

                @Override
                public void onFailure(Call<PokemonCardResponse> call, Throwable t) {
                    Toast.makeText(application, R.string.retrofit_error, Toast.LENGTH_SHORT).show();
                }
            });
        }
        return "Sync";
    }

    private void addNotification(String contentTitle, String contentText) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(application);
        mBuilder.setVisibility(1);
        mBuilder.setSmallIcon(R.drawable.ic_wifi);
        mBuilder.setContentTitle(contentTitle);
        mBuilder.setContentText(contentText);

        NotificationManager mNotificationManager = (NotificationManager) application.getSystemService(Context.NOTIFICATION_SERVICE);

        // === Removed some obsoletes
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "Your_channel_id";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());

    }

    protected void onPostExecute(String result) {
        progressDialogWeakReference.get().dismiss();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
