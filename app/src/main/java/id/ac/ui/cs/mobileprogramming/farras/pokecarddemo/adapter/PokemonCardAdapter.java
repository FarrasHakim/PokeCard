package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.R;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.fragment.PokemonCardFragment;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonCard;

import java.util.List;

public class PokemonCardAdapter extends RecyclerView.Adapter<PokemonCardAdapter.CustomViewHolder> {
    private final List<PokemonCard> pokemonCards;
    private final Context context;
    private final CardListener cardListener;

    public PokemonCardAdapter(Context context, PokemonCardFragment cardListener, List<PokemonCard> pokemonCards) {
        this.pokemonCards = pokemonCards;
        this.context = context;
        this.cardListener = cardListener;
    }

    @NonNull
    @Override
    public PokemonCardAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_row, parent, false);

        return new PokemonCardAdapter.CustomViewHolder(view, this.cardListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonCardAdapter.CustomViewHolder holder, int position) {
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(pokemonCards.get(position).getImageUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.image);
        holder.cardName.setText(pokemonCards.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return pokemonCards != null ? pokemonCards.size() : 0;
    }

    public interface CardListener {
        void onCardListener(int position, boolean addClicked, View view);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        private final PokemonCardAdapter.CardListener cardListener;
        private final ImageView image;
        private final TextView cardName;
        private final ImageView starIcon;
        private final RelativeLayout clickArea;

        public CustomViewHolder(final View itemView, final CardListener cardListener) {
            super(itemView);
            mView = itemView;
            image = mView.findViewById(R.id.coverImage);
            cardName = mView.findViewById(R.id.setName);
            starIcon = mView.findViewById(R.id.starIcon);
            clickArea = mView.findViewById(R.id.card_container);
            this.cardListener = cardListener;

            starIcon.setOnClickListener(this);
            clickArea.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == starIcon.getId()) {
                starIcon.setImageResource(R.drawable.ic_star_filled);
                cardListener.onCardListener(getAdapterPosition(), false, v);
            } else {
                cardListener.onCardListener(getAdapterPosition(), true, itemView);
            }
        }
    }
}
