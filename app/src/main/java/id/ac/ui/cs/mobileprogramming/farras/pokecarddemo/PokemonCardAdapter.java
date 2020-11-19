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
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.fragment.PokemonCardFragment;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.fragment.PokemonSetFragment;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonCard;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonSet;
import org.w3c.dom.Text;

import java.util.List;

public class PokemonCardAdapter extends RecyclerView.Adapter<PokemonCardAdapter.CustomViewHolder> {
    private List<PokemonCard> pokemonCards;
    private Context context;
    private CardListener cardListener;

    public PokemonCardAdapter(Context context, PokemonCardFragment cardListener , List<PokemonCard> pokemonCards) {
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

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        private PokemonCardAdapter.CardListener cardListener;
        private ImageView image;
        private TextView cardName;
        private ImageView starIcon;
        private RelativeLayout clickArea;

        public CustomViewHolder(final View itemView,final CardListener cardListener) {
            super(itemView);
            mView = itemView;
            image = mView.findViewById(R.id.coverImage);
            cardName = mView.findViewById(R.id.setName);
            starIcon = mView.findViewById(R.id.starIcon);
            clickArea = mView.findViewById(R.id.card_container);
            this.cardListener = cardListener;

            starIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (starIcon.getTag() == null || R.drawable.ic_star_void_dark == (Integer) starIcon.getTag()) {
                        starIcon.setImageResource(R.drawable.ic_star_filled);
                        starIcon.setTag(R.drawable.ic_star_filled);
                    } else {
                        starIcon.setImageResource(R.drawable.ic_star_void_dark);
                        starIcon.setTag(R.drawable.ic_star_void_dark);
                    }
                }
            });

            clickArea.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardListener.onCardListener(getAdapterPosition(), true, v);
                }
            });

        }
    }

    public interface CardListener{
        void onCardListener(int position, boolean addClicked, View view);
    }
}
