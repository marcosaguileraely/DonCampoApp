package com.cool4code.doncampoapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class FarmerStock extends ActionBarActivity implements OnItemClickListener {
    ListView lview;
    private final static String stock[] = {"#001 - PAPA PASTUSA\nStock: 10 ARR\nVereda Carmelita, Choconta, CUND\nJose Marin Maria",
            "#002 - TOMATE CHERRY\nStock: 120 KG\nVereda Carmelita, Choconta, CUND\nJose Marin Maria",
            "#003 - TOMATE COMUN\nStock: 120 ARR\nVereda Carmelita, Choconta, CUND\nJose Marin Maria",
            "#004 - AHUYAMA\nStock: 25 ARR\nVereda Carmelita, Choconta, CUND\nJose Marin Maria",
            "#005 - BERENJENA\nStock: 25 ARR\nVereda Carmelita, Choconta, CUND\nJose Marin Maria",
            "#006 - PEPINO COHOMBRO\nStock: 25 ARR\nVereda Carmelita, Choconta, CUND\nJose Marin Maria",
            "#007 - ACELGA\nStock: 230 LB\nVereda Carmelita, Choconta, CUND\nJose Marin Maria",
            "#008 - PEPINO COMUN\nStock: 230 LB\nVereda Carmelita, Choconta, CUND\nJose Marin Maria"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_stock);

        lview = (ListView) findViewById(R.id.listView);
        lview.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,stock));
        lview.setOnItemClickListener(this);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.farmer_stock, menu);
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
        Toast.makeText(this, "Has seleccionado " + stock[position], Toast.LENGTH_SHORT).show();
    }
}
