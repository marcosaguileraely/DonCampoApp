package com.cool4code.doncampoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;

public class FarmerHome extends ActionBarActivity implements OnItemClickListener {

    Button aprende;
    Button inventario;
    Button pedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_home);
        aprende = (Button) findViewById(R.id.farmer_home_aprende);
        inventario = (Button) findViewById(R.id.farmer_home_inventario);
        pedidos = (Button) findViewById(R.id.farmer_home_pedidos);

        pedidos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("tocar", "click en pedidos");
                Intent goToOrder= new Intent(FarmerHome.this, FarmerOrder.class);
                startActivity(goToOrder);
            }
        });

        inventario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("tocar", "click en inventario");
                Intent goToStock= new Intent(FarmerHome.this, FarmerStock.class);
                startActivity(goToStock);
            }
        });

        aprende.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("tocar", "click en aprende");
                Intent goToLearn= new Intent(FarmerHome.this, AprendozActivity.class);
                startActivity(goToLearn);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Toast.makeText(this, "Has seleccionado " + month[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
}

