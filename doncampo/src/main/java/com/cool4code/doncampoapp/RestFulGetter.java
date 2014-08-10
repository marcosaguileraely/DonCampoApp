package com.cool4code.doncampoapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;


public class RestFulGetter extends ActionBarActivity implements OnClickListener{
    Button cargar;
    EditText etResponse;
    TextView tvIsConnected;
    private static String url_src = "http://hmkcode.appspot.com/rest/controller/get.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_ful_getter);

        cargar= (Button) findViewById(R.id.boton_cargar_rest);
        etResponse = (EditText) findViewById(R.id.etResponse);
        tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);

        cargar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        new JSONParser_().execute();
        Log.d("clicked", "boton ejecutado");
    }

    private class JSONParser_ extends AsyncTask<String, String, JSONArray> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*ver = (TextView)findViewById(R.id.vers);
            name = (TextView)findViewById(R.id.name);
            api = (TextView)findViewById(R.id.api);*/
            Log.d("paso 1", "---->1");
            pDialog = new ProgressDialog(RestFulGetter.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONArray doInBackground(String... args) {
            JSONParser_ jParser = new JSONParser_();
            // Getting JSON from URL
            JSONArray json = jParser.get(0);
            Log.d("paso 2", "--->2--->"+json);
            return json;
        }

        private JSONArray get(int i) {
            return null;
        }

        @Override
        protected void onPostExecute(JSONArray json) {
            pDialog.dismiss();
            Log.d("paso 3", "---->3");
            /*try {
                // Getting JSON Array from URL
                *//*android = json.getJSONArray(TAG_OS);
                for(int i = 0; i < android.length(); i++){
                    JSONObject c = android.getJSONObject(i);*//*


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }*/
        }
    }
}
