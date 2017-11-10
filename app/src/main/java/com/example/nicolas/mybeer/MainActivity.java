package com.example.nicolas.mybeer;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolas.mybeer.fr.if26.loic.nicolas.model.Biere;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    private MyAdapter adapter = new MyAdapter();
    DataBaseDepense myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.MyBeer));
        //on construit la liste view
        final RecyclerView rv = (RecyclerView) findViewById(R.id.list);
        //positionne élément en ligne
        rv.setLayoutManager(new LinearLayoutManager(this));
        //adapteur: objet affichant le contenu
        rv.setAdapter(adapter);
        myDb = new DataBaseDepense(this);
        afficherBiere();
    }

    //Affiche la toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Permet de récupérer les cliques sur les boutons
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
            /* DO EDIT */
                return true;
            case R.id.action_add:
                Toast.makeText(this,"Ajouter une bière", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, AddBeer.class);
                startActivity(intent);
                return true;
            case R.id.action_remove:
            /* DO DELETE */
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void afficherBiere(){
        adapter.getList().clear();
        Cursor resultat =  myDb.getAllData();
        while (resultat.moveToNext()){
            Biere newBiere = new Biere(resultat.getString(1), resultat.getString(2), resultat.getString(3));
            adapter.getList().add(newBiere);
        }
    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


        private ArrayList<Biere> list = new ArrayList(Arrays.asList(
//                new Biere("Heineken", "8", "17"),
//                new Biere("Despe", "4", "14")
        ));

        //savoir le nombre d'élément dans la liste
        @Override
        public int getItemCount() {
            return list.size();
        }

        //créer la vue d'une cellule de la liste
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //créer un layout depuis le XML
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            //récuperer le layout
            View view = inflater.inflate(R.layout.list_cell, parent, false);
            return new MyViewHolder(view);
        }

        //afficher les données dans une cellule
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Biere pair = (Biere) list.get(position);
            holder.display(pair);
        }

        public ArrayList<Biere> getList() {
            return list;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            private final TextView nom;
            private final TextView degre;
            private final TextView note;
            private Biere currentPair;

            public MyViewHolder(final View itemView) {
                super(itemView);
                nom = itemView.findViewById(R.id.nom);
                degre = itemView.findViewById(R.id.degre);
                note = itemView.findViewById(R.id.note);

                //afficher texte de la description en cliquant
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new AlertDialog.Builder(itemView.getContext())
                                .setTitle(currentPair.getLabel())
                                .setMessage(currentPair.getPrix() + " le: " + currentPair.getDate())
                                .show();
                    }
                });
            }

            //affiche les données de la pair fourni
            public void display(Biere pair) {

                currentPair = pair;
                nom.setText(pair.getLabel());
                degre.setText(pair.getPrix());
                note.setText(pair.getDate());
            }
        }

    }


}
