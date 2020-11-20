package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonCard;

import java.util.List;

public class PokemonFavoriteAdapter extends RecyclerView.Adapter<PokemonFavoriteAdapter.CustomViewHolder> {
    List<PokemonCard> favoriteCards;
    Context context;

    public PokemonFavoriteAdapter(Context context, List<PokemonCard> pokemonCards) {
        this.context = context;
        favoriteCards = pokemonCards;
    }

    @NonNull
    @Override
    public PokemonFavoriteAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.set_row, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(favoriteCards.get(position).getImageUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.image);
        holder.cardName.setText(favoriteCards.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return favoriteCards != null ? favoriteCards.size() : 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView cardName;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.coverImage);
            cardName = itemView.findViewById(R.id.setName);
        }
    }
}
