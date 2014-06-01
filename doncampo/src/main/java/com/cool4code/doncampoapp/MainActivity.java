package com.cool4code.doncampoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    ImageView farmers;
    ImageView clients;
    Button leer;
    ArrayAdapter<String> listAdapter;
    ListView list;
    ListViewAdapter adapter;
    String[] rank;
    String[] country;
    String[] population;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        farmers= (ImageView) findViewById(R.id.home_img_farmer);
        clients= (ImageView) findViewById(R.id.home_img_client);
        leer= (Button) findViewById(R.id.home_bot_leer);
        leer.setVisibility(View.INVISIBLE);
        leer.setOnClickListener(this);


        farmers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("tocar", "click en boton farmer");
                Intent goToFarmer= new Intent(MainActivity.this, FarmerSecurityActivity.class);
                startActivity(goToFarmer);
            }
        });

        clients.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("tocar", "click en boton client");
                Intent goToClient= new Intent(MainActivity.this, ClientSecurityActivity.class);
                startActivity(goToClient);
            }
        });

        if (android.os.Build.VERSION.SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        /*// Generate sample data into string arrays
        rank = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

        country = new String[] { "China", "India", "United States",
                "Indonesia", "Brazil", "Pakistan", "Nigeria", "Bangladesh",
                "Russia", "Japan" };

        population = new String[] { "1,354,040,000", "1,210,193,422",
                "315,761,000", "237,641,326", "193,946,886", "182,912,000",
                "170,901,000", "152,518,015", "143,369,806", "127,360,000" };

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.home_list_preciosList);

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(MainActivity.this, rank, country, population);
        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        // Capture ListView item click*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*// Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);*/

        switch (item.getItemId()) {
            case R.id.action_search:
                String text= "@Ministerio_TIC y #DONCAMPO en la #Agroton, soluciones innovadoras para el Agro Colombiano.";
                Log.d("acerca-de", "Ha presionado redes sociales");
                Intent shareIntent = new Intent(Intent.ACTION_SEND);

                //shareIntent.setType("plain/text");
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
        Log.d("click", "boton leer funciona!");
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet del =
                new HttpGet("http://cool4code.co/doncampo/prices.json");

        del.setHeader("content-type", "application/json");

        try
        {
            HttpResponse resp = httpClient.execute(del);
            String respStr = EntityUtils.toString(resp.getEntity());

            JSONArray jsonarray = new JSONArray(respStr);
            Log.d("array", "->"+ jsonarray);
            Log.d("array", "->"+ jsonarray.length());
            for(int i=0; i <= jsonarray.length(); i++) {
                /*String id       = jsonarray.getString("id");
                String code    = jsonobject.getString("code");
                String name  = jsonobject.getString("name");
                String description = jsonobject.getString("description");
                String price    = jsonobject.getString("price");
                Log.d("details", "->"+ id + " - "+ name);*/
            }
        }catch(Exception ex){
            Log.e("ServicioRest","Error!", ex);
        }
    }
}
