package com.example.nicolas.mybeer.fr.if26.loic.nicolas.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.nicolas.mybeer.fr.if26.loic.nicolas.Activities.MainActivity;
import com.example.nicolas.mybeer.R;
import com.example.nicolas.mybeer.fr.if26.loic.nicolas.model.Biere;
import com.example.nicolas.mybeer.fr.if26.loic.nicolas.model.DataBaseDepense;

public class DisplayBeer extends AppCompatActivity {

    private TextView nameBeer;
    //On instancie la BDD
    DataBaseDepense myDb;
    private Biere beer;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_beer);
        context = this;
        setTitle("Display a beer");
        myDb = new DataBaseDepense(this);
        nameBeer = (TextView) findViewById(R.id.nameBeer);
        Intent i = getIntent();
        beer = myDb.getBiere(i.getIntExtra(MainActivity.ID_BEER, 0));
        nameBeer.setText(beer.getNom());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView note = (TextView) findViewById(R.id.display_note);
        TextView percentage = (TextView) findViewById(R.id.display_percentage);

        note.setText(beer.getNote() + "/5");
        percentage.setText(beer.getDegre() + "%");

        ImageButton btn_delete = (ImageButton) findViewById(R.id.delete_beer);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete beer")
                        .setMessage("Do you really want to delete " + beer.getNom() + " ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                myDb.deleteData(beer.getId());
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
            }
        });
    }

    // go back
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
