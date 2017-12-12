package com.example.nicolas.mybeer.fr.if26.loic.nicolas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.nicolas.mybeer.MainActivity;
import com.example.nicolas.mybeer.R;
import com.example.nicolas.mybeer.fr.if26.loic.nicolas.model.Biere;
import com.example.nicolas.mybeer.fr.if26.loic.nicolas.model.DataBaseDepense;

public class DisplayBeer extends AppCompatActivity {

    private TextView nameBeer;
    //On instancie la BDD
    DataBaseDepense myDb;
    private Biere beer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_beer);
        myDb = new DataBaseDepense(this);
        nameBeer = (TextView) findViewById(R.id.nameBeer);
        Intent i = getIntent();
        beer = myDb.getBiere(i.getIntExtra(MainActivity.ID_BEER, 0));
        nameBeer.setText(beer.getNom());


    }

}
