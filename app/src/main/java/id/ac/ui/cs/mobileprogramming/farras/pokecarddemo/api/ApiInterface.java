package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ApiInterface {

    @GET("sets")
    Call<PokemonSetResponse> getCardSet();


    @GET("cards")
    Call<List<PokemonCard>> getCards();


    @GET("cards/xy7-54")
    Call<PokemonCard> getCardById();
}
