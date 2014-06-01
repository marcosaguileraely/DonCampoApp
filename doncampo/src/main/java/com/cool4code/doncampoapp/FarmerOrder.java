package com.cool4code.doncampoapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


public class FarmerOrder extends ActionBarActivity implements OnItemClickListener {
    ListView lview;
    private final static String orders[] = {
            "P001 - JUAN DANIEL\nPAPA SABANERA\nRequerido: 10 ARR\nKr 13 #24-56 CHIA, CUND\n320 550 4567",
            "P002 - CARLOS DANIEL ALOHA\nYUCA COMUN\nRequerido: 120 KG\nCll 170 #7a El cordero dorado, BOGOTÁ\n312 456 2343",
            "P003 - MARCOS ELY\nACELGA\nRequerido: 120 ARR\nVereda Los bajos, Choconta, CUND\n321 440 5676",
            "P004 - AMALIA SANDERO\nGRANDILLA\nRequerido: 25 ARR\nVereda Camellito, Choconta, CUND\n314 830 7919"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_order);

        lview = (ListView) findViewById(R.id.listView);
        lview.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,orders));
        lview.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.farmer_order, menu);
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
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Has seleccionado " + orders[position], Toast.LENGTH_SHORT).show();
    }
}
