package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.fragment;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.R;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonCard;

import java.net.URL;

public class PokemonDetailFragment extends Fragment {
    PokemonCard pokemonCard;
    private ImageView pokemonImage;
    private TextView cardDetail;

    public PokemonDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().getParcelable("pokemon") != null) {
            pokemonCard = getArguments().getParcelable("pokemon");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        pokemonImage = getView().findViewById(R.id.cardImage);
        cardDetail = getView().findViewById(R.id.cardDetailText);

        String detailText = "Id : " + pokemonCard.getId() + "\n"
                + "Card Number: " + pokemonCard.getNumber() + "\n"
                + "Name : " + pokemonCard.getName() + "\n"
                + "National Pokedex Number:  " + pokemonCard.getNationalPokedexNumber() + "\n"
                + "Series: " + pokemonCard.getSeries() + "\n"
                + "Types: " + pokemonCard.getTypes() + "\n"
                + "Supertype: " + pokemonCard.getSupertype() + "\n"
                + "Subtype: " + pokemonCard.getSubtype() + "\n"
                + "Set: " + pokemonCard.getSet();
        cardDetail.setText(detailText);

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getActivity().getString(R.string.syncing));
        progressDialog.show();
        Log.d("DetailFragment", String.valueOf(progressDialog.isShowing()));
        if (progressDialog.isShowing()) {
            AsyncTask<String, String, Bitmap> maybeImageBitmap = new setImage(pokemonCard.getImageUrl(), progressDialog).execute(pokemonCard.getImageUrlHiRes());
            try {
                pokemonImage.setImageBitmap(maybeImageBitmap.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    private static class setImage extends AsyncTask<String, String, Bitmap> {

        private final String urlString;
        private final ProgressDialog progressDialog;

        setImage(String urlString, ProgressDialog progressDialog) {
            this.urlString = urlString;
            this.progressDialog = progressDialog;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bmp = null;
            try {
                URL url = new URL(this.urlString);
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                return bmp;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bmp;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            this.progressDialog.dismiss();
        }
    }
}
