package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonCard;

import java.util.List;

public class PokemonCardAdapter extends RecyclerView.Adapter<PokemonCardAdapter.CustomViewHolder> {
    private List<PokemonCard> pokemonCards;
    private Context context;

    public PokemonCardAdapter(List<PokemonCard> pokemonCards, Context context, PokemonSetsAdapter.CardListener cardListener) {
        this.pokemonCards = pokemonCards;
        this.context = context;
//        this.cardListener = cardListener;
    }


    @NonNull
    @Override
    public PokemonCardAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonCardAdapter.CustomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
