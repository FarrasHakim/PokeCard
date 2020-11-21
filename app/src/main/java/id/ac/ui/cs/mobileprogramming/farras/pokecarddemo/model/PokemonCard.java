package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pokemon_card_table")
public class PokemonCard implements Parcelable {
    public static final Creator<PokemonCard> CREATOR = new Creator<PokemonCard>() {
        @Override
        public PokemonCard createFromParcel(Parcel in) {
            return new PokemonCard(in);
        }

        @Override
        public PokemonCard[] newArray(int size) {
            return new PokemonCard[size];
        }
    };
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
    @ColumnInfo(name = "is_favorite")
    Boolean isFavorite;

    protected PokemonCard(Parcel in) {
        id = in.readString();
        name = in.readString();
        nationalPokedexNumber = in.readString();
        imageUrl = in.readString();
        imageUrlHiRes = in.readString();
        types = in.readString();
        supertype = in.readString();
        subtype = in.readString();
        number = in.readString();
        series = in.readString();
        set = in.readString();
        setCode = in.readString();
    }

    public PokemonCard(@NonNull String id, String name, String nationalPokedexNumber, String imageUrl, String imageUrlHiRes, String types, String supertype, String subtype, String number, String series, String set, String setCode, Boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.nationalPokedexNumber = nationalPokedexNumber;
        this.imageUrl = imageUrl;
        this.imageUrlHiRes = imageUrlHiRes;
        this.types = types;
        this.supertype = supertype;
        this.subtype = subtype;
        this.number = number;
        this.series = series;
        this.set = set;
        this.setCode = setCode;
        this.isFavorite = isFavorite;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
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

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.nationalPokedexNumber);
        dest.writeString(this.imageUrl);
        dest.writeString(this.imageUrlHiRes);
        dest.writeString(this.types);
        dest.writeString(this.supertype);
        dest.writeString(this.subtype);
        dest.writeString(this.number);
        dest.writeString(this.series);
        dest.writeString(this.set);
        dest.writeString(this.setCode);
    }
}
