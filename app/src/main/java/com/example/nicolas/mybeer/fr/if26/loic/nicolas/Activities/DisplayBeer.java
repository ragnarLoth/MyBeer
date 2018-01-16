package com.example.nicolas.mybeer.fr.if26.loic.nicolas.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nicolas.mybeer.R;
import com.example.nicolas.mybeer.fr.if26.loic.nicolas.Controller.BiereController;
import com.example.nicolas.mybeer.fr.if26.loic.nicolas.Model.Biere;
import com.example.nicolas.mybeer.fr.if26.loic.nicolas.Utils.DataBaseOpenHelper;

/**
 * This activity display all the informations about a beer
 */
public class DisplayBeer extends AppCompatActivity {

    private TextView nameBeer, noteBeer, degreBeer, commentaireBeer;
    //On instancie la BDD
    private DataBaseOpenHelper myDb;
    private BiereController controller;
    private Biere beer;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_beer);
        context = this;
        setTitle(getString(R.string.display_beer));
        myDb = new DataBaseOpenHelper(this);
        controller = new BiereController(myDb);
        nameBeer = (TextView) findViewById(R.id.nameBeer);
        Intent i = getIntent();
        beer = controller.getBiere(i.getIntExtra(MainActivity.ID_BEER, 0));
        nameBeer.setText(beer.getNom());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView note = (TextView) findViewById(R.id.display_note);
        TextView percentage = (TextView) findViewById(R.id.display_percentage);

        note.setText(beer.getNote() + "/17");
        percentage.setText(beer.getDegre() + "%");

        ImageButton btn_delete = (ImageButton) findViewById(R.id.delete_beer);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.delete_beer)
                        .setMessage(getString(R.string.sure_to_delete) + beer.getNom() + " ?")
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                controller.deleteData(beer.getId());
                                finish();
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
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
