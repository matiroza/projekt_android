package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class GridViewContents extends AppCompatActivity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_contents);

        gridView = findViewById(R.id.gridViewM);

        String[] mountainNames = {"Mount Everest", "K2", "Kangchenjunga", "Lhotse","Makalu", "Cho Oyo", "Dhaulagiri", "Manaslu", "Nanga Parbat",
                                        "Annapurna", "Hidden Peak", "Broad Peak", "Gasherbrum II","Shishapangma"};

        String[] mountainHighs = {"8,849", "8,611", "8,586", "8,516","8,485", "8,188", "8,167", "8,163", "8,126",
                "8,091", "8,080", "8,051","8,035", "8,027"};

        int[] images = {
                R.drawable.everest, R.drawable.k2, R.drawable.kangchenjunga, R.drawable.lhotse,
                R.drawable.makalu, R.drawable.cho_oyu, R.drawable.dhaulagiri, R.drawable.mansalu,
                R.drawable.nanga_parbat, R.drawable.annapurna, R.drawable.hidden_peak, R.drawable.broadpeak,
                R.drawable.gasherbrum2, R.drawable.shishapangma
        };

        GridAdapter gridAdapter = new GridAdapter(getApplicationContext(), mountainNames, mountainHighs, images );
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), mountainNames[position] + " -  " + mountainHighs[position] + "m", Toast.LENGTH_SHORT).show();
            }
        });


    }
}