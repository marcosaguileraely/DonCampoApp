package com.cool4code.doncampoapp;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class FarmerStock extends ActionBarActivity implements OnItemClickListener {
    ListView lview;
    Button nuevo_stock;
    final Context context = this;

    private final static String stock[] = {"#001 - PAPA PASTUSA\nStock: 10 ARR\nVereda Carmelita, Choconta, CUND\nJose Marin Maria",
            "#002 - TOMATE CHERRY\nStock: 120 KG\nVereda Carmelita, Choconta, CUND\nJose Marin Maria",
            "#003 - TOMATE COMUN\nStock: 120 ARR\nVereda Carmelita, Choconta, CUND\nJose Marin Maria",
            "#004 - AHUYAMA\nStock: 25 ARR\nVereda Carmelita, Choconta, CUND\nJose Marin Maria",
            "#005 - BERENJENA\nStock: 25 ARR\nVereda Carmelita, Choconta, CUND\nJose Marin Maria",
            "#006 - PEPINO COHOMBRO\nStock: 25 ARR\nVereda Carmelita, Choconta, CUND\nJose Marin Maria",
            "#007 - ACELGA\nStock: 230 LB\nVereda Carmelita, Choconta, CUND\nJose Marin Maria",
            "#008 - PEPINO COMUN\nStock: 230 LB\nVereda Carmelita, Choconta, CUND\nJose Marin Maria"};

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_stock);

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#669900")));
        int titleId;
        int textColor = getResources().getColor(android.R.color.white);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            titleId = getResources().getIdentifier("action_bar_title", "id", "android");
            TextView abTitle = (TextView) findViewById(titleId);
            abTitle.setTextColor(textColor);
        } else {
            TextView abTitle = (TextView) getWindow().findViewById(android.R.id.title);
            abTitle.setTextColor(textColor);
        }

        nuevo_stock = (Button) findViewById(R.id.new_stock);
        lview = (ListView) findViewById(R.id.listView);
        lview.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,stock));
        lview.setOnItemClickListener(this);

        nuevo_stock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToNewStock = new Intent(FarmerStock.this, NewStockForm.class);
                startActivity(goToNewStock);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Has seleccionado " + stock[position], Toast.LENGTH_SHORT).show();
        /*final Dialog dialog = new Dialog(context);
        Log.d("acerca-de", "Ha selecciona un item");
        dialog.setContentView(R.layout.activity_actions_elements);
        dialog.setTitle("Acciones");
        dialog.getWindow().setLayout(800, 600);
        Button     delete_stock= (Button) dialog.findViewById(R.id.details_stock);
        Button     details_stock= (Button) dialog.findViewById(R.id.delete_stock);
        dialog.show();*/

    }
}
