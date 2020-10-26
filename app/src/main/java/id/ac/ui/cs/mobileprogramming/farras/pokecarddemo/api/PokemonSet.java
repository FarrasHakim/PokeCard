package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api;

import com.google.gson.annotations.SerializedName;

public class PokemonSet {
    @SerializedName("name")
    String name;
    @SerializedName("symbolUrl")
    String symbolUrl;
    @SerializedName("logoUrl")
    String logoUrl;
    @SerializedName("series")
    String series;
    @SerializedName("totalCards")
    Integer totalCards;

    public PokemonSet(String name, String symbolUrl, String logoUrl, String series, Integer totalCards) {
        this.name = name;
        this.symbolUrl = symbolUrl;
        this.logoUrl = logoUrl;
        this.series = series;
        this.totalCards = totalCards;
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
