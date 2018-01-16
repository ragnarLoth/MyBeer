package com.example.nicolas.mybeer.fr.if26.loic.nicolas.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolas.mybeer.R;
import com.example.nicolas.mybeer.fr.if26.loic.nicolas.Controller.BiereController;
import com.example.nicolas.mybeer.fr.if26.loic.nicolas.Utils.DividerItemDecoration;
import com.example.nicolas.mybeer.fr.if26.loic.nicolas.Model.Biere;
import com.example.nicolas.mybeer.fr.if26.loic.nicolas.Utils.DataBaseOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    public static final String DEFAULT = "null";
    private MyAdapter adapter = new MyAdapter();
    private DataBaseOpenHelper myDb;
    private BiereController controller;
    public  static final String ID_BEER = "ID_BEER";
    private Context context;
    private RecyclerView rv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.MyBeer));
        context = this;
        //dire bonjour à l'utilisateur
        getInfo();
        //on construit la recycleView
        rv = (RecyclerView) findViewById(R.id.list);
        //on positionne élément en ligne
        rv.setLayoutManager(new LinearLayoutManager(context));
        //Permet de séparer chaque biere par une barre horizontale
        rv.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        //adapteur: objet affichant le contenu
        rv.setAdapter(adapter);
        myDb = new DataBaseOpenHelper(context);
        controller = new BiereController(myDb);
        //on affiche la liste de bière
        afficherBiere(myDb.COL_2);


    }

    /**
     * Get the users info to store into sharedpreferences
     */
    public void getInfo() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
        String nom = sharedPreferences.getString("nom", DEFAULT);
        String prenom = sharedPreferences.getString("prenom", DEFAULT);
        //si l'utilisateur ne s est jamais connecté
        if (nom.equals(DEFAULT) || prenom.equals(DEFAULT)) {
           alertReturn();
        }else{
            Toast.makeText(this, "Bonjour " + prenom +" " +
                   nom, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Return som info about the user
     */
    public void alertReturn(){
        // get prompts.xml view
        final LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompt, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
        final EditText nomText = promptsView.findViewById(R.id.nom);
        final EditText prenomText = promptsView.findViewById(R.id.prenom);
        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                               String prenom = prenomText.getText().toString();
                                String nom = nomText.getText().toString();
                                final SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("prenom", prenom);
                                editor.putString("nom", nom);
                                editor.commit();
                                Toast.makeText(context, "Bonjour " + prenom + " " + nom, Toast.LENGTH_SHORT).show();
                            }
                        })
                .show();
    }


    /**
     * Affiche la toolbar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Permet de récupérer les cliques sur les boutons du menu
    */
     public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //trie par degré
            case R.id.degreSort:
                afficherBiere(DataBaseOpenHelper.COL_3 + " DESC");
                return true;
            //trie par note
            case R.id.noteSort:
                afficherBiere(DataBaseOpenHelper.COL_4 + " DESC");
                return true;
            //trie par ordre alphabétique
            case R.id.alphabeticSort:
                afficherBiere(DataBaseOpenHelper.COL_2);
                return true;
            //trie par date d ajout
            case R.id.sortId:
                afficherBiere(DataBaseOpenHelper.COL_1);
                return true;
            //ajouter une biere
            case R.id.action_add:
                Toast.makeText(this,"Ajouter une bière", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, AddBeer.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Display all beers, by orderby
     * @param orderby
     */
    public void afficherBiere(String orderby) {
        adapter.getList().clear();
        ArrayList<Biere> beers = controller.getAllData(orderby);
        adapter.change(beers);
    }

    @Override
    protected void onResume() {
        super.onResume();
        afficherBiere(myDb.COL_2);
    }

    /**
     * Adapter to display RecyclerView
     */
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        //List contenant les bières
        private ArrayList<Biere> list = new ArrayList(Arrays.asList());

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

        public void change(ArrayList<Biere> beers) {
            list = beers;
            notifyDataSetChanged();
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
                        Intent i = new Intent(MainActivity.this, DisplayBeer.class);
                        int idBeer = currentPair.getId();
                        Log.d("biere", idBeer+"");
                        i.putExtra(ID_BEER, idBeer);
                        startActivity(i);
                    }
                });
            }

            //affiche les données de la pair fourni
            public void display(Biere pair) {
                currentPair = pair;
                nom.setText(pair.getNom());
                degre.setText(pair.getDegre()+"");
                note.setText(pair.getNote()+"");
            }
        }

    }


}
