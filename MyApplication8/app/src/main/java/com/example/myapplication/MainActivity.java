package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.widget.AdapterView.*;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

    private Button button;

    private TextView mTextviewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Spinner spinner = findViewById(R.id.spinner); //Spinner bzw Dropdown Liste

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.fuels, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);


        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient(); //File download

                String download_Source_Url = "https://www.youtube.com/"; //Download-URL der eigentlichen XML Datei mit den benötigten Daten, muss richtiger Link sein sonst funktioniert es nicht

                Request request = new Request.Builder()
                        .url(download_Source_Url)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace(); //Wenn server nicht erreichbar oder datei nicht runterladbar, dann gib Fehler aus
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            final  String myResponse = response.body().string();





                        }
                    }
                });
            }
        });

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity2();
            }
        });


        	/*
        	  Schritte die wir ohne die kostenpflichtigen Daten nicht erstellen konnten:
        	  Zerlege XML in einzelne Infos und vergleiche Tankstellen, erst nach Spritart, alle unpassenden werden weggelassen
              Dann Entfernung über die Google maps APi errechnen, diese kann über die Addresse der Tankstellen, aus der XML entnommen, und
              dem vorherig eingegebennen Standort eine Entfernung ausrechnen. Dann werden die einzelnen Entfernungen gegen 10 Kilometer gegengeprüft, alles
              was größer ist, ist zu weit und fliegt damit raus. Die übrig gebliebenen Tankstellen werden am Preis gemessen verglichen bis die billigste gefunden ist.
              Deren Addresse und Kosten pro Liter wird dann auf der nächsten Seite angezeigt.
            */

    }

    //Functions

    //Wandelt Array Index in Wert an dieser Stelle um und lässt ihn anzeigen in der Dropdown Liste, da Werte in Array gespeichert sind
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String spinnertext = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), spinnertext, Toast.LENGTH_SHORT);
    }

    //Ignore
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


    //Springe zu 2. Seite
    public void openactivity2() {
        Intent intent = new Intent(this, Activity_maps.class);
        startActivity(intent);
    }
}