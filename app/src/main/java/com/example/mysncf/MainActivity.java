package com.example.mysncf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton imgRerA, imgRerB, imgRerC, imgRerD, imgRerE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        this.imgRerA= (ImageButton) findViewById(R.id.idRerA);
        this.imgRerB= (ImageButton) findViewById(R.id.idRerB);
        this.imgRerC= (ImageButton) findViewById(R.id.idRerC);
        this.imgRerD= (ImageButton) findViewById(R.id.idRerD);
        this.imgRerE= (ImageButton) findViewById(R.id.idRerE);

        this.imgRerA.setOnClickListener(this);
        this.imgRerB.setOnClickListener(this);
        this.imgRerC.setOnClickListener(this);
        this.imgRerD.setOnClickListener(this);
        this.imgRerE.setOnClickListener(this);

        //initialisation des enquetes
        SNCF.initialiser();
    }

    @Override
    public void onClick(View v) {
        String rer = "";
        Intent unIntent = new Intent(this, Inscription.class);
        if (v.getId() == R.id.idRerA){
            rer = "rerA";
        } else if (v.getId() == R.id.idRerB) {
            rer = "rerB";
        } else if (v.getId() == R.id.idRerC) {
            rer = "rerC";
        }else if (v.getId() == R.id.idRerD) {
            rer = "rerD";
        }else if (v.getId() == R.id.idRerE) {
            rer = "rerE";
        }
        unIntent.putExtra("rer", rer);
        this.startActivity(unIntent);
    }
}