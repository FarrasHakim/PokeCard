package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("sets")
    Call<PokemonSetResponse> getCardSet();

    @GET("cards")
    Call<PokemonCardResponse> getCards(@Query("page") int page);

    @GET("cards/xy7-54")
    Call<PokemonCard> getCardById();
}
