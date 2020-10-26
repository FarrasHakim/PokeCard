package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.R;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.SetsAdapter;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.ApiClient;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.ApiInterface;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.PokemonSetResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonSetFragment extends Fragment {
    private SetsAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;

    public PokemonSetFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.pokemon_set_fragment, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<PokemonSetResponse> call = service.getCardSet();
        call.enqueue(new Callback<PokemonSetResponse>() {
            @Override
            public void onResponse(Call<PokemonSetResponse> call, Response<PokemonSetResponse> response) {
                progressDialog.dismiss();

                Log.d("MainActivityDebugger","Dapat yes: " + response.body());
                Log.d("MainActivityDebugger","Dapat yes: " + response);
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<PokemonSetResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.wtf("Dafuk", "The F");
                Toast.makeText(getActivity(), "Something is wrong. I can feel it.", Toast.LENGTH_SHORT);
            }
        });
    }

    private void generateDataList(PokemonSetResponse pokemonSetResponse) {
        recyclerView = getView().findViewById(R.id.customRecyclerView);
        adapter = new SetsAdapter(getActivity(),pokemonSetResponse.getSets());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
