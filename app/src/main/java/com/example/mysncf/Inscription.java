package com.example.mysncf;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Inscription extends AppCompatActivity implements View.OnClickListener {
    private EditText txtNom, txtPrenom, txtEmail;

    private Spinner spAge, spFrequence;

    private Button btParticiper;

    private String rer, email, nom, prenom;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inscription);


        this.txtNom = (EditText) findViewById(R.id.idNom);
        this.txtPrenom = (EditText) findViewById(R.id.idPrenom);
        this.txtEmail = (EditText) findViewById(R.id.idEmail);
        this.btParticiper = (Button) findViewById(R.id.idParticiper);
        this.spAge = (Spinner) findViewById(R.id.idAge);
        this.spFrequence = (Spinner) findViewById(R.id.idFrequence);

        //onn recupere le nom du rer
        this.rer = getIntent().getStringExtra("rer");

        //remplir les spinners
        ArrayList<String> lesAges = new ArrayList<String>();
        lesAges.add("6 à 18ans");
        lesAges.add("19 à 35");
        lesAges.add("36 à 64");
        lesAges.add("65 et +");
        ArrayAdapter unAdapteurAge = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lesAges);
        this.spAge.setAdapter(unAdapteurAge);

        ArrayList<String> lesFrequences = new ArrayList<String>();
        lesFrequences.add("Journalière");
        lesFrequences.add("Hebdomadaire");
        lesFrequences.add("Mensuelle");
        lesFrequences.add("Annuelle");
        ArrayAdapter unAdapteurFrequence = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lesFrequences);
        this.spFrequence.setAdapter(unAdapteurFrequence);

        this.btParticiper.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        if (v.getId() == R.id.idParticiper) {
            Intent unIntent = new Intent(this, Page1.class);
            this.email = this.txtEmail.getText().toString();
            this.nom = this.txtNom.getText().toString();
            this.prenom = this.txtPrenom.getText().toString();
            String age = this.spAge.getSelectedItem().toString();
            String frequence = this.spFrequence.getSelectedItem().toString();

            //enregistrer le Participant
            Participant unParticipant = new Participant(nom, prenom, email, age, frequence);
            //on ajoute ce participant à l'enquete qu'il a choisit
            SNCF.getEnquete(this.rer).ajouterParticipant(unParticipant);



            //on va faire passer le rer et l'email
            unIntent.putExtra("rer", this.rer);
            unIntent.putExtra("email", this.email);
            Toast.makeText(this, "Bienvenue " + this.nom + " " + this.prenom, Toast.LENGTH_SHORT).show();
            this.startActivity(unIntent);


        }

    }
}