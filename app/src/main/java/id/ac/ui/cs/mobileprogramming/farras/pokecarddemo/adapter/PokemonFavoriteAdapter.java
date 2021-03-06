package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.adapter;

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
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.R;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.fragment.FavoriteCardFragment;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonCard;

import java.util.List;

public class PokemonFavoriteAdapter extends RecyclerView.Adapter<PokemonFavoriteAdapter.CustomViewHolder> {
    List<PokemonCard> favoriteCards;
    Context context;
    CardListener cardListener;

    public PokemonFavoriteAdapter(Context context, FavoriteCardFragment cardListener, List<PokemonCard> pokemonCards) {
        this.context = context;
        favoriteCards = pokemonCards;
        this.cardListener = cardListener;
    }

    @NonNull
    @Override
    public PokemonFavoriteAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.favorite_row, parent, false);

        return new CustomViewHolder(view, cardListener);
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

    public interface CardListener {
        void onCardListener(int position, boolean addClicked, View view);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView cardName;
        private final ImageView deleteIcon;
        private final CardListener cardListener;

        public CustomViewHolder(@NonNull View itemView, final CardListener cardListener) {
            super(itemView);
            image = itemView.findViewById(R.id.coverImage);
            cardName = itemView.findViewById(R.id.cardName);
            deleteIcon = itemView.findViewById(R.id.deleteIcon);
            this.cardListener = cardListener;

            deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardListener.onCardListener(getAdapterPosition(), true, v);
                }
            });
        }
    }
}
