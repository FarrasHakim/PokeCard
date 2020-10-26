package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonCardResponse {
    @SerializedName("cards")
    private List<PokemonCard> pokemonCards;

    public List<PokemonCard> getPokemonCards() {
        return pokemonCards;
    }

    public void setPokemonCards(List<PokemonCard> pokemonCards) {
        this.pokemonCards = pokemonCards;
    }
}
