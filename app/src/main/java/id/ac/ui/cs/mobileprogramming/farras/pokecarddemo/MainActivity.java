package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.ApiClient;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.ApiInterface;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.PokemonSet;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api.PokemonSetResponse;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonRoomDatabase;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model.PokemonSetDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button buttonView;
    private static final String TEXT_STATE = "currentText";
    ProgressDialog progressDialog;
    private PokemonSetViewModel pokemonSetViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pokemonSetViewModel = ViewModelProviders.of(this).get(PokemonSetViewModel.class);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);;
        progressDialog = new ProgressDialog(this);
        progressBar.setVisibility(View.INVISIBLE);
        buttonView = (Button) findViewById(R.id.syncButton);
        if(savedInstanceState!=null){
            buttonView.setText(savedInstanceState.getString(TEXT_STATE));
        }
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_sets, R.id.navigation_favorites, R.id.navigation_deck)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the TextView
        outState.putString(TEXT_STATE,
                buttonView.getText().toString());
    }

    public void syncDb(View view) {
        progressDialog.setMessage("Syncing....");
        progressDialog.show();
        Log.wtf("DAFUQ", view.getClass().getCanonicalName());
        buttonView.setText("Syncing...");
        buttonView.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
//        new DbSynchronizer(buttonView, progressBar, this).execute();
        pokemonSetViewModel.syncDb();
        Log.wtf("DAFUQ", "After sync db");
        progressDialog.dismiss();
        buttonView.setText("Sync");
        buttonView.setEnabled(true);
        progressBar.setVisibility(View.INVISIBLE);
    }

}

