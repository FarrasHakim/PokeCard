package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button buttonView;
    private static final String TEXT_STATE = "currentText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);;
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

        if (isWifiConnected()) {
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the TextView
        outState.putString(TEXT_STATE,
                buttonView.getText().toString());
    }

    public void syncDb(View view) {
        Log.wtf("DAFUQ", view.getClass().getCanonicalName());
        buttonView.setText("Syncing...");
        buttonView.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        new DbSynchronizer(buttonView, progressBar).execute();
    }


    private boolean isWifiConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        boolean isMetered = connMgr.isActiveNetworkMetered();
        return (networkInfo != null && networkInfo.isConnected() && isMetered);
    }
}
