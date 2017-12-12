package com.example.nicolas.mybeer.fr.if26.loic.nicolas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import com.example.nicolas.mybeer.MainActivity;
import com.example.nicolas.mybeer.R;
import com.example.nicolas.mybeer.fr.if26.loic.nicolas.model.DataBaseDepense;

public class DisplayBeer extends AppCompatActivity {

    private TextView nameBeer;
    //On instancie la BDD
    DataBaseDepense myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_beer);
        myDb = new DataBaseDepense(this);
        nameBeer = (TextView) findViewById(R.id.nameBeer);

        Intent i= getIntent();

        if(i.getIntExtra(MainActivity.ID_BEER, 0) != 0)
        {
            String j = myDb.;
            nameBeer.setText(j);
        }


    }

}
