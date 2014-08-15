package com.cool4code.doncampoapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by cool4code team on 7/8/14.
 * Paola Vanegas
 * Alejandro Zarate Orjuela
 * David Almeciga
 * Marcos A. Aguilera Ely
 */

public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    ImageView farmers;
    ImageView clients;
    ArrayAdapter<String> listAdapter;
    ListView list;
    String[] rank;
    String[] country;
    String[] population;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        farmers= (ImageView) findViewById(R.id.home_img_farmer);
        clients= (ImageView) findViewById(R.id.home_img_client);

        farmers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("tocar", "click en boton farmer");
                Intent goToFarmer= new Intent(MainActivity.this, FarmerHome.class);
                startActivity(goToFarmer);
            }
        });

        clients.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("tocar", "click en boton client");
                Intent goToClient= new Intent(MainActivity.this, ClientHome.class);
                startActivity(goToClient);
            }
        });

        if (android.os.Build.VERSION.SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                String text= "@Ministerio_TIC @MinAgricultura @IncoderColombia y #DONCAMPO en la #Agroton, soluciones innovadoras para el Agro Colombiano.";
                Log.d("acerca-de", "Ha presionado redes sociales");
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(shareIntent, "Share using"));
                shareIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml("<p>This is the text that will be shared.</p>"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
    }
}
