package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pokemon_card_table")
public class PokemonCard {
    @NonNull
    @PrimaryKey()
    @ColumnInfo(name = "id")
    String id;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "national_pokedex_number")
    String nationalPokedexNumber;
    @ColumnInfo(name = "image_url")
    String imageUrl;
    @ColumnInfo(name = "image_url_hi_res")
    String imageUrlHiRes;
    @ColumnInfo(name = "types")
    String types;
    @ColumnInfo(name = "supertype")
    String supertype;
    @ColumnInfo(name = "subtype")
    String subtype;
    @ColumnInfo(name = "number")
    String number;
    @ColumnInfo(name = "series")
    String series;
    @ColumnInfo(name = "set")
    String set;
    @ColumnInfo(name = "set_code")
    String setCode;
}
