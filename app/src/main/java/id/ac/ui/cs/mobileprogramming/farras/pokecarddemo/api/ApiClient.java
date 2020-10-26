package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api;

import android.util.Log;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "https://api.pokemontcg.io/v1/";
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        Log.d("ApiClientDebugger", "Awal get client");
        if (retrofit==null) {
            Log.d("ApiClientDebugger", "if null");
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        Log.d("ApiClientDebugger", "akhir");
        return retrofit;
    }

}
