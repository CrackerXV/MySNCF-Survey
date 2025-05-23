package com.example.mysncf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Page2 extends AppCompatActivity implements View.OnClickListener {

    private RatingBar rtAvis;
    private EditText commentaire;
    private Button btFin;
    private String rer, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page2);

        this.btFin=(Button) findViewById(R.id.idFin);
        this.rtAvis=(RatingBar) findViewById(R.id.idAvis);
        this.commentaire=(EditText) findViewById(R.id.idComment);

        this.btFin.setOnClickListener(this);

        this.rer = this.getIntent().getStringExtra("rer");
        this.email = this.getIntent().getStringExtra("email");



    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.idFin) {
            //on enregistre les scores de réponses
            int score = 0;

            score = (int) (this.rtAvis.getRating() * this.rtAvis.getNumStars());
            String question="Avis";
            SNCF.getEnquete(this.rer).getLesParticipants(this.email).ajouterReponse(question,score);

            //on enregistre le commentaire
            SNCF.getEnquete(this.rer).getLesParticipants(this.email).setComment(this.commentaire.getText().toString());
            //affecter un score au commantaire
            if (this.commentaire.getText().toString().length()<20){
                score = 8 ;
            } else if (this.commentaire.getText().toString().length()<40) {
                score = 12;
            } else {
                score = 16;
            }
            //on passe à la page suivante
            Intent unIntent = new Intent(this, Fin.class);
            unIntent.putExtra("rer", this.rer);
            unIntent.putExtra("email", this.email);
            this.startActivity(unIntent);
        }
    }
}