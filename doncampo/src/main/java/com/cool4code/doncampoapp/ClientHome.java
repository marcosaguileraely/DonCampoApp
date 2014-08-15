package com.cool4code.doncampoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ClientHome extends ActionBarActivity implements OnItemClickListener{
    final Context context = this;
    Button aprende;
    Button ofertas;
    Button pedidos;
    ListView lview;
    private final static String month[] = {"P01 - PAPA PASTUSA............$13.000 ARR", "S01 - SANDIA............$13.000 KG",
            "AY01 - AHUYAMA............$800 KG", "B01 - BERENJENA............$13.000 ARR",
            "PH01 - PEPINO COHOMBRO............$1.500 KG", "P01 - ACELGA............$2.000 ARR",
            "P01 - PEPINO COMUN............$1.500 KG","P01 - Papa pastusa............$13.000 ARR"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);
        aprende= (Button) findViewById(R.id.client_home_aprende);
        ofertas= (Button) findViewById(R.id.client_home_ofertas);
        pedidos= (Button) findViewById(R.id.client_home_pedidos);
        lview = (ListView) findViewById(R.id.listView1);
        lview.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,month));
        lview.setOnItemClickListener(this);

        aprende.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("comprador", "click en aprende");
                Intent goToLearn= new Intent(ClientHome.this, AprendozActivity.class);
                startActivity(goToLearn);
            }
        });

        ofertas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("comprador", "click en ofertas");
                Intent goToStock= new Intent(ClientHome.this, FarmerStock.class);
                startActivity(goToStock);
                //Toast.makeText(context, "¡Próxima implementación!", Toast.LENGTH_LONG).show();
            }
        });

        pedidos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("comprador", "click en pedidos");
                Intent goToOrder= new Intent(ClientHome.this, FarmerOrder.class);
                startActivity(goToOrder);
                //Toast.makeText(context, "¡Próxima implementación!", Toast.LENGTH_LONG).show();
            }
        });
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.client_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Has seleccionado " + month[position], Toast.LENGTH_SHORT).show();
    }
}
