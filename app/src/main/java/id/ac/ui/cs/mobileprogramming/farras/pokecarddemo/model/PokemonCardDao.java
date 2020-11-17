package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PokemonCardDao {
    @Insert
    void insert(PokemonCard pokemonCard);

    @Query("SELECT * from pokemon_card_table ORDER BY id ASC")
    LiveData<List<PokemonCard>> getAllPokemonCards();

    @Query("SELECT * FROM pokemon_card_table WHERE id = :cardId")
    LiveData<PokemonCard> getPokemonCardById(String cardId);

    @Query("SELECT * from pokemon_card_table WHERE `set` = :setName ORDER BY id ASC")
    LiveData<List<PokemonCard>> getPokemonCardsBySet(String setName);
}
