package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.service.CounterService;

public class CounterFragment extends Fragment {
    TextView counterTextView;
    Button buttonStart, buttonStop, buttonReset;

    public static CounterFragment newInstance(String param1, String param2) {
        CounterFragment fragment = new CounterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_counter, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        counterTextView = getView().findViewById(R.id.counter_text);
        buttonStart = getView().findViewById(R.id.startBUtton);
        buttonReset = getView().findViewById(R.id.resetButton);
        buttonStop = getView().findViewById(R.id.stopButton);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().startService(new Intent(getActivity(), CounterService.class));
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().stopService(new Intent(getActivity(), CounterService.class));
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().stopService(new Intent(getActivity(), CounterService.class));
                counterTextView.setText("0");
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().registerReceiver(br, new IntentFilter(CounterService.COUNTER_BR));
    }

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateUI(intent);
        }
    };



    private void updateUI(Intent intent) {
        if (intent.getExtras() != null) {
            String counter = intent.getStringExtra("counter");
            counterTextView.setText(counter);
        }
    }
}
