package com.cool4code.doncampoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class FarmerHome extends ActionBarActivity implements OnItemClickListener {
    ListView list;
    TextView ver;
    TextView name;
    TextView api;

    ArrayList<HashMap<String, String>> oslist = new ArrayList<HashMap<String, String>>();
    //URL to get JSON Array
    //private static String url = "http://api.learn2crack.com/android/jsonos/";
    //private static String url = "http://cool4code.com/stocks.json";
    private static String url = "http://bit2media.com/android.json";
    //JSON Node Names
    private static final String TAG_OS = "android";
    private static final String TAG_VER = "ver";
    private static final String TAG_NAME = "name";
    private static final String TAG_API = "api";
    JSONArray android = null;
    /*----------------------------------*/
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

       /* lview = (ListView) findViewById(R.id.listView1);
        lview.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,month));
        lview.setOnItemClickListener(this);*/
        new JSONParse().execute();
        Log.d("clicked", "boton ejecutado");

        oslist = new ArrayList<HashMap<String, String>>();
        //Btngetdata = (Button) findViewById(R.id.cargar_datos);
        /*Btngetdata.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new JSONParse().execute();
                Log.d("clicked", "boton ejecutado");
            }
        });*/

        aprende.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("tocar", "click en aprende");
                Intent goToLearn= new Intent(FarmerHome.this, AprendozActivity.class);
                startActivity(goToLearn);
            }
        });

        inventario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("tocar", "click en inventario");
                Intent goToStock= new Intent(FarmerHome.this, FarmerStock.class);
                startActivity(goToStock);
            }
        });

        pedidos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("tocar", "click en pedidos");
                Intent goToOrder= new Intent(FarmerHome.this, FarmerOrder.class);
                startActivity(goToOrder);
            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.farmer_home, menu);
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

    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ver = (TextView)findViewById(R.id.vers);
            name = (TextView)findViewById(R.id.name);
            api = (TextView)findViewById(R.id.api);
            pDialog = new ProgressDialog(FarmerHome.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jParser = new JSONParser();
            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(url);
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
            try {
                // Getting JSON Array from URL
                android = json.getJSONArray(TAG_OS);
                for(int i = 0; i < android.length(); i++){
                    JSONObject c = android.getJSONObject(i);
                    // Storing  JSON item in a Variable
                    String ver = c.getString(TAG_VER);
                    String name = c.getString(TAG_NAME);
                    String api = c.getString(TAG_API);
                    // Adding value HashMap key => value
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(TAG_VER, ver);
                    map.put(TAG_NAME, name);
                    map.put(TAG_API, api);
                    oslist.add(map);
                    list=(ListView)findViewById(R.id.listView1);
                    ListAdapter adapter = new SimpleAdapter(FarmerHome.this, oslist,
                            R.layout.list_v,
                            new String[] { TAG_VER,TAG_NAME, TAG_API }, new int[] {
                            R.id.vers,R.id.name, R.id.api});
                    list.setAdapter(adapter);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            Toast.makeText(FarmerHome.this, "You Clicked at "+oslist.get(+position).get("name"), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this, "Has seleccionado " + month[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
}

