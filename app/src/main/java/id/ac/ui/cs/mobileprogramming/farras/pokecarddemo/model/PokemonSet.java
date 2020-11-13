package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "pokemon_set_table")
public class PokemonSet {
    @NonNull
    @PrimaryKey()
    @ColumnInfo(name = "id")
    String id;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "symbolUrl")
    String symbolUrl;
    @ColumnInfo(name = "logoUrl")
    String logoUrl;
    @ColumnInfo(name = "series")
    String series;
    @ColumnInfo(name = "totalCards")
    Integer totalCards;
}
