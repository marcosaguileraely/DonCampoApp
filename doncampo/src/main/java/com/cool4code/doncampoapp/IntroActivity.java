package com.cool4code.doncampoapp;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cool4code.doncampoapp.helpers.DB;
import com.cool4code.doncampoapp.helpers.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;


public class IntroActivity extends ActionBarActivity{
    private DB db;
    private String URL_WS = "http://placita.azurewebsites.net/";
    private String WS_ACTION_PRICES = "api/Prices" ;
    private static final String PRICES_TABLE = "prices";

    Button goToHome;
    Button Fire;
    ProgressDialog mProgressDialog;
    Context context = this;

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        goToHome = (Button) findViewById(R.id.intro_boton_ir_home);
        //Fire = (Button) findViewById(R.id.Fire);

        new RemoteDataTask().execute();

        goToHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToSearch = new Intent(IntroActivity.this, SearchActivity.class);
                startActivity(goToSearch);
            }
        });
    }

    //executing RemoteDataTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setTitle("AgroNegocios");
            mProgressDialog.setMessage("Descargando productos. Esto tomará unos minutos dependiendo de su conexión. Espere...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            File db =new File("/data/data/com.cool4code.doncampoapp/databases/placitadb");
            int objectId, Id, PriceMaxPerUnit, PriceMinPerUnit, PriceAvgPerUnit;
            String  Product_Code, Product_Name, Unit_Code, Unit_Name, Location, Created, Updated;
            WebService wsPrices = new WebService(URL_WS, WS_ACTION_PRICES);
            JSONArray jsonArray = new JSONArray();
            jsonArray = wsPrices.parseJsonText(wsPrices.getJsonText());
            Log.d("String", "===>" + jsonArray);
            Log.d("lenght", "===>" + jsonArray.length());
                /*if(db.exists()){
                 *   Log.d("->", "Existe db");
                 *   Log.d("->", "¡NOTHING TO-DO HERE!");
                 *}else{*/
                    Log.d("->", "No Existe db");
                    SQLiteDatabase mydb = getBaseContext().openOrCreateDatabase("placitadb", SQLiteDatabase.OPEN_READWRITE, null);
                    //mydb.execSQL("DROP TABLE IF EXISTS prices;");
                    mydb.execSQL("CREATE TABLE IF NOT EXISTS "+ "prices" + "(objectId INT, Id INT, Product_Code VARCHAR, Product_Name VARCHAR, Unit_Code VARCHAR, Unit_Name VARCHAR, PriceMaxPerUnit INT, PriceMinPerUnit INT, PriceAvgPerUnit INT, Location VARCHAR, Created VARCHAR, Updated VARCHAR);");
                    Log.d("->", "tabla productos creada");
                        try {
                            for(int i=0; i <= jsonArray.length()-1; i++){
                                JSONObject obj = jsonArray.getJSONObject(i);
                                objectId = i;
                                Id = obj.getInt("Id");
                                JSONObject Product = obj.getJSONObject("Product");
                                JSONObject Unit = obj.getJSONObject("Unit");
                                Product_Code = Product.getString("Code");
                                Product_Name = Product.getString("Name");
                                Unit_Code = Unit.getString("Code");
                                Unit_Name = Unit.getString("Name");
                                PriceMaxPerUnit = obj.getInt("PriceMaxPerUnit");
                                PriceMinPerUnit = obj.getInt("PriceMinPerUnit");
                                PriceAvgPerUnit = obj.getInt("PriceAvgPerUnit");
                                Location = obj.getString("Location");
                                Created = obj.getString("Created");
                                Updated = obj.getString("Updated");
                                Log.d("Showing", "i = " + i + " - Product_Code = " + Product_Code + " PriceMaxPerUnit " + PriceMaxPerUnit + " Created " + Created);
                                mydb.execSQL("INSERT INTO prices"+"(objectId, Id, Product_Code, Product_Name, Unit_Code, Unit_Name, PriceMaxPerUnit, PriceMinPerUnit, PriceAvgPerUnit, Location, Created, Updated )"+
                                        "VALUES ('"+objectId+"','"+Id+"','"+Product_Code+"','"+Product_Name+"','"+Unit_Code+"','"+Unit_Name+"','"+PriceMaxPerUnit+"','"+PriceMinPerUnit+"','"+PriceAvgPerUnit+"','"+Location+"','"+Created+"','"+Updated+"');");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                //}
            mydb.close();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mProgressDialog.hide();
            Toast.makeText(IntroActivity.this, "¡Productos descargados exitosamente!", Toast.LENGTH_SHORT).show();
        }
    }
}
