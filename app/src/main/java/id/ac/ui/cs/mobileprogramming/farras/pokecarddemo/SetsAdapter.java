package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.PokemonSet;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.fragment.PokemonSetFragmentDirections;

import java.util.List;

public class SetsAdapter extends RecyclerView.Adapter<SetsAdapter.CustomViewHolder>{
    private List<PokemonSet> pokemonSets;
    private Context context;

    public SetsAdapter(Context context, List<PokemonSet> pokemonSets) {
        this.pokemonSets = pokemonSets;
        this.context = context;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        final SetsAdapter setsAdapter;
        private ImageView logoImage;
        private TextView setName;
        private ImageView starIcon;
        private RelativeLayout clickArea;

        CustomViewHolder(View itemView, SetsAdapter adapter) {
            super(itemView);
            Log.d("AdapterDebugger", "Constructor View Holder");
            mView = itemView;
            logoImage = mView.findViewById(R.id.coverImage);
            setName = mView.findViewById(R.id.setName);
            starIcon = mView.findViewById(R.id.starIcon);
            clickArea = mView.findViewById(R.id.set_container);
            this.setsAdapter = adapter;

            starIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("SetAdapter", "Star Icon Pressed");
                    Log.d("SetAdapter", "Drawable: " + R.drawable.ic_star_void_dark);
                    Log.d("SetAdapter", "Star Icon Tag: " + starIcon.getTag());
                    Log.d("SetAdapter", String.valueOf((Integer) R.drawable.ic_star_void_dark == starIcon.getTag()));
                    if (starIcon.getTag() == null || R.drawable.ic_star_void_dark == (Integer) starIcon.getTag()) {
                        Log.d("SetAdapter", "If");
                        starIcon.setImageResource(R.drawable.ic_star_filled);
                        starIcon.setTag(R.drawable.ic_star_filled);
                    } else {
                        Log.d("SetAdapter", "Else");
                        starIcon.setImageResource(R.drawable.ic_star_void_dark);
                        starIcon.setTag(R.drawable.ic_star_void_dark);
                    }
                }
            });

            clickArea.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Adapter", setName.getText().toString());
                    Toast.makeText(context, setName.getText(), Toast.LENGTH_SHORT).show();

                    NavDirections action = PokemonSetFragmentDirections.actionNavigationSetsToNavigationCards();
                    Navigation.findNavController(v).navigate(action);
                }
            });

        }
    }

    @NonNull
    @Override
    public SetsAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("RecyclerView", "CreateViewHolder");
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.set_row, parent, false);

        return new CustomViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull SetsAdapter.CustomViewHolder holder, int position) {
        Log.d("RecyclerView", "BindViewHolder");
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(pokemonSets.get(position).getLogoUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.logoImage);
        holder.setName.setText(pokemonSets.get(position).getName());
        Log.d("RecyclerView", pokemonSets.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return pokemonSets.size();
    }

}
