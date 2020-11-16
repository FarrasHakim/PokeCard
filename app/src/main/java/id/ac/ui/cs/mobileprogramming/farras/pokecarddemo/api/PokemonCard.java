package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api;

import android.util.Log;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonCard {
    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("nationalPokedexNumber")
    String nationalPokedexNumber;
    @SerializedName("imageUrl")
    String imageUrl;
    @SerializedName("imageUrlHiRes")
    String imageUrlHiRes;
    @SerializedName("types")
    List<String> types;
    @SerializedName("supertype")
    String supertype;
    @SerializedName("subtype")
    String subtype;
    @SerializedName("number")
    String number;
    @SerializedName("series")
    String series;
    @SerializedName("set")
    String set;
    @SerializedName("setCode")
    String setCode;

    public id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonCard toPokemonCardEntity() {
        String typesStr = "";
        Log.d("CardApi", name);
        if (types != null) {
            for (String type: types) {
                typesStr += type + ",";
            }
        } else {
            typesStr = "None";
        }
        return new id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonCard(
                id,
                name,
                nationalPokedexNumber,
                imageUrl,
                imageUrlHiRes,
                typesStr,
                supertype,
                subtype,
                number,
                series,
                set,
                setCode
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationalPokedexNumber() {
        return nationalPokedexNumber;
    }

    public void setNationalPokedexNumber(String nationalPokedexNumber) {
        this.nationalPokedexNumber = nationalPokedexNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrlHiRes() {
        return imageUrlHiRes;
    }

    public void setImageUrlHiRes(String imageUrlHiRes) {
        this.imageUrlHiRes = imageUrlHiRes;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getSupertype() {
        return supertype;
    }

    public void setSupertype(String supertype) {
        this.supertype = supertype;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getSetCode() {
        return setCode;
    }

    public void setSetCode(String setCode) {
        this.setCode = setCode;
    }
}