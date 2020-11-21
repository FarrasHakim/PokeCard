package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PokemonCardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PokemonCard pokemonCard);

    @Query("SELECT * from pokemon_card_table ORDER BY id ASC")
    LiveData<List<PokemonCard>> getAllPokemonCards();

    @Query("SELECT * FROM pokemon_card_table WHERE id = :cardId")
    LiveData<PokemonCard> getPokemonCardById(String cardId);

    @Query("SELECT * from pokemon_card_table WHERE `set` = :setName ORDER BY id ASC")
    LiveData<List<PokemonCard>> getPokemonCardsBySet(String setName);

    @Query("SELECT * from pokemon_card_table WHERE `is_favorite` = 1 ORDER BY id ASC")
    LiveData<List<PokemonCard>> getFavoriteCards();

    @Query("UPDATE pokemon_card_table SET is_favorite=1 WHERE id = :cardId")
    void addCardToFavorites(String cardId);

    @Query("UPDATE pokemon_card_table SET is_favorite=0 WHERE id = :cardId")
    void removeCardFromFavorites(String cardId);
}
