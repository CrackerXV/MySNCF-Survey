package com.example.mysncf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class Page1 extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup rgRegularite, rgProprete ;
    private Button btSuivant;
    private String email, rer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

        this.rgRegularite= (RadioGroup) findViewById(R.id.idRegularite);
        this.rgProprete= (RadioGroup) findViewById(R.id.idProprete);
        this.btSuivant = (Button) findViewById(R.id.idSuivant);

        this.rer = this.getIntent().getStringExtra("rer");
        this.email = this.getIntent().getStringExtra("email");

        this.btSuivant.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.idSuivant){
            //on enregistre les scores de réponses
            int score = 0;
            switch (this.rgRegularite.getCheckedRadioButtonId()){
                case R.id.idRegularite1 : score = 16; break;
                case R.id.idRegularite2 : score = 12; break;
                case R.id.idRegularite3 : score = 8; break;
            }
            String question="regularite";
            SNCF.getEnquete(this.rer).getLesParticipants(this.email).ajouterReponse(question,score);

            switch (this.rgProprete.getCheckedRadioButtonId()){
                case R.id.idProprete1 : score = 16 ; break ;
                case R.id.idProprete2 : score = 12 ; break ;
                case R.id.idProprete3 : score = 8 ; break ;
            }
            question = "proprete";
            SNCF.getEnquete(this.rer).getLesParticipants(this.email).ajouterReponse(question,score);
            //on passe à la page suivante
            Intent unIntent = new Intent(this, Page2.class);
            unIntent.putExtra("rer", this.rer);
            unIntent.putExtra("email", this.email);
            this.startActivity(unIntent);
        }
    }
}