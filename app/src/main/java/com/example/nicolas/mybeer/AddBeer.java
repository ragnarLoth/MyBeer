package com.example.nicolas.mybeer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddBeer extends AppCompatActivity {
    DataBaseDepense myDb;
    EditText nom, degre, note;
    Button btnAddData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beer);
        myDb = new DataBaseDepense(this);

        nom = (EditText) findViewById(R.id.nom);
        degre = (EditText) findViewById(R.id.degre);
        note = (EditText) findViewById(R.id.note);
        btnAddData = (Button) findViewById(R.id.addButton);
        AddData();
    }

    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        int noteEntier = Integer.parseInt(note.toString());
//                        int degreNombre = Integer.parseInt(degre.toString());
//                        if (noteEntier >= 0 && noteEntier <= 20 && degreNombre >=
//                                0 && degreNombre < 50) {
                            boolean isInserted = myDb.insertData(nom.getText().toString(),
                                    degre.getText().toString(),
                                    note.getText().toString());

                            if (isInserted == true) {
                                Toast.makeText(AddBeer.this, "Bière ajoutée", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(AddBeer.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                            }
//                        } else {
//                            Toast.makeText(AddBeer.this, "Mauvaise données", Toast.LENGTH_LONG).show();
//                        }

                    }
                }
        );
    }

}