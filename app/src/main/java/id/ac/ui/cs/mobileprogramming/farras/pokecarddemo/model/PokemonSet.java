package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

    public PokemonSet(String id, String name, String symbolUrl, String logoUrl, String series, Integer totalCards) {
        this.id = id;
        this.name = name;
        this.symbolUrl = symbolUrl;
        this.logoUrl = logoUrl;
        this.series = series;
        this.totalCards = totalCards;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbolUrl() {
        return symbolUrl;
    }

    public void setSymbolUrl(String symbolUrl) {
        this.symbolUrl = symbolUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Integer getTotalCards() {
        return totalCards;
    }

    public void setTotalCards(Integer totalCards) {
        this.totalCards = totalCards;
    }
}
