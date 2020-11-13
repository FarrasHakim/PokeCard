package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Random;

public class DbSynchronizer extends AsyncTask<Void, Integer, String> {
    private WeakReference<Button> buttonView;
    private WeakReference<ProgressBar> progressBar;


    DbSynchronizer(Button button, ProgressBar progressBar) {
        buttonView = new WeakReference<>(button);
        this.progressBar = new WeakReference<>(progressBar);
    }

    @Override
    protected String doInBackground(Void... voids) {
        // Generate a random number between 0 and 10
        Random r = new Random();
        int n = r.nextInt(11);

        // Make the task take long enough that we have
        // time to rotate the phone while it is running
        int s = n * 200;

        for (int count = 1; count <= 10; count ++) {
            // Sleep for the random amount of time
            try {
                Thread.sleep(s);
                publishProgress(count*10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        // Return a String result
        return "Sync";
    }

    protected void onPostExecute(String result) {
        buttonView.get().setText(result);
        buttonView.get().setEnabled(true);
        progressBar.get().setProgress(0);
        progressBar.get().setVisibility(View.INVISIBLE);
        Log.wtf("onPostExecute", result);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.wtf("onProgressUpdate", Arrays.toString(values) + "");
        progressBar.get().setProgress(values[0]);
    }
}
