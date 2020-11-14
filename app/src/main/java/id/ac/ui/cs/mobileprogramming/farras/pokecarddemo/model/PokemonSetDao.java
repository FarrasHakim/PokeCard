package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PokemonSetDao {
    @Insert
    void insert(PokemonSet set);

    @Query("SELECT * from pokemon_set_table ORDER BY id ASC")
    LiveData<List<PokemonSet>> getAllSets();

}
