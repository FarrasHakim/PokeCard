package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonCardResponse {
    @SerializedName("cards")
    private List<PokemonCard> cards;

    public List<PokemonCard> getPokemonCards() {
        return cards;
    }

    public void setPokemonCards(List<PokemonCard> pokemonCards) {
        this.cards = pokemonCards;
    }
}
