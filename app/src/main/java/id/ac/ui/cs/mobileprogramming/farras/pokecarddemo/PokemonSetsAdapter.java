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
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.fragment.PokemonSetFragment;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonSet;

import java.util.List;

public class PokemonSetsAdapter extends RecyclerView.Adapter<PokemonSetsAdapter.CustomViewHolder> {
    private final Context context;
    private final CardListener cardListener;
    private List<PokemonSet> pokemonSets;

    public PokemonSetsAdapter(Context appContext, PokemonSetFragment cardListener, List<PokemonSet> pokemonSets) {
        this.pokemonSets = pokemonSets;
        this.cardListener = cardListener;
        this.context = appContext;
    }

    @NonNull
    @Override
    public PokemonSetsAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.set_row, parent, false);

        return new CustomViewHolder(view, this.cardListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonSetsAdapter.CustomViewHolder holder, int position) {
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(pokemonSets.get(position).getLogoUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.logoImage);
        holder.setName.setText(pokemonSets.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return pokemonSets != null ? pokemonSets.size() : 0;
    }

    public void setPokemonSet(List<PokemonSet> pokemonSets) {
        this.pokemonSets = pokemonSets;
        notifyDataSetChanged();
    }

    public PokemonSet getPokemonSetAt(int position) {
        return pokemonSets.get(position);
    }


    public interface CardListener {
        void onCardListener(int position, boolean addClicked, View view);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private final ImageView logoImage;
        private final TextView setName;
        private final RelativeLayout clickArea;
        private final CardListener cardListener;

        CustomViewHolder(final View itemView, final CardListener cardListener) {
            super(itemView);
            mView = itemView;
            logoImage = mView.findViewById(R.id.coverImage);
            setName = mView.findViewById(R.id.setName);
            clickArea = mView.findViewById(R.id.set_container);
            this.cardListener = cardListener;

            clickArea.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardListener.onCardListener(getAdapterPosition(), true, itemView);
                }
            });

        }
    }

}
