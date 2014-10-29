package com.cool4code.doncampoapp;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.cool4code.doncampoapp.helpers.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;


public class NewStockForm extends ActionBarActivity {
    private String URL_WS = "http://placita.azurewebsites.net/";
    private String WS_ACTION_UNITS = "api/Units";
    private String WS_ACTION_PRODUCTS = "api/Products";
    ProgressDialog mProgressDialog;
    Context context = this;

    File dbplacita =new File("/data/data/com.cool4code.doncampoapp/databases/placitadb");
    private String Token = "02jZ21hvGcETSVfdXBQWYZbiVh0zNDLjUgfhV2SSag780Qq_ss7pAq8JXGBBuHp3BeGUL14fMJMGqBrbqrb7dHyTLJ87F4mQ6n2QqnWIzKaw_GyJbBJmHAiLEdNQMjHEMwhhhKDz5o8sfiSSRgJw_inPcxYa33qmrsPLr0n9hrn_i0FsFe0e7fLqgoTAXMN826mP7622QZFVXuvm8Hmp0EQ7xN5XJePRM0pI1DGDPImcJbmyi7UV1ihEvCZn6DUohbHL-TokHAVnsKRbZWI5NDc4Es-noNxXv0Byo1_41LJsyCYTQ9yB2ehGV4JniP-Ko7oFHP6cwQ21XOb5oVd6vDWYkl849aWS_f24p2LhGcYRnrTg0O_RiEUEYTAWvjfXnnyWn5QOpqxwWkCl55PjRTNSKXt6fKOHUolLkDaL2-rvAk3L5wEJx5GGCrYl4LQriUsxil-p3vION7vW7WHd1EXxBZKdzZe3_t-IBX1ZGHA";

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_stock_form);

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

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        new RemoteDataTask().execute();
    }

    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setTitle("Agronegocios");
            mProgressDialog.setMessage("¡Preparando semillas y abono!. Esto se realizará solo una vez. Espere...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            File db =new File("/data/data/com.cool4code.doncampoapp/databases/placitadb");
            Boolean existsUnit = existUnitTable();
            Boolean existsProduct = existProductTable();

            //Caso #1 : Descargando Unidades
            if(existsUnit == true){
                /*nothing to do here*/
                Log.d("->", "unit Exists");
            }else{
                WebService getUnits = new WebService(URL_WS , WS_ACTION_UNITS);
                String units = getUnits.WSGetUnits(Token);
                JSONArray jsonarray = null;
                try {
                    jsonarray = new JSONArray(units);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("==>", "JsonObj : " + jsonarray);

                if(db.exists()){
                    SQLiteDatabase mydb = getBaseContext().openOrCreateDatabase("placitadb", SQLiteDatabase.OPEN_READWRITE, null);
                    mydb.execSQL("CREATE TABLE IF NOT EXISTS " + "units" + "(Code VARCHAR, Name VARCHAR);");
                    Log.d("===>", "Lenght: " + jsonarray.length());
                    try {
                        for (int i=0; i <= jsonarray.length()-1; i++) {
                            Log.d("//i", " i : " + i);
                            JSONObject obj = jsonarray.getJSONObject(i);
                            String Code = obj.getString("Code");
                            String Name = obj.getString("Name");
                            mydb.execSQL("INSERT INTO units"+"(Code, Name)"+
                                    "VALUES ('"+Code+"','"+Name+"');");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mydb.close();
                }else{
                    Log.d("->", "db Exists");
                }
            }

            //Caso #2 : Descargando Productos
            if(existsProduct == true){
                /*nothing to do here*/
                Log.d("->", "product Exists");
            }else{
                WebService getProducts = new WebService(URL_WS , WS_ACTION_PRODUCTS);
                String products = getProducts.WSGetUnits(Token);
                JSONArray jsonarrayproducts = null;
                try {
                    jsonarrayproducts = new JSONArray(products);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("==>", "JsonArrayProducts : " + jsonarrayproducts);

                if(db.exists()){
                    SQLiteDatabase mydb = getBaseContext().openOrCreateDatabase("placitadb", SQLiteDatabase.OPEN_READWRITE, null);
                    mydb.execSQL("CREATE TABLE IF NOT EXISTS " + "products" + "(Code VARCHAR, Name VARCHAR);");
                    Log.d("===>", "Lenght: " + jsonarrayproducts.length());
                    try {
                        for (int i=0; i <= jsonarrayproducts.length()-1; i++) {
                            Log.d("//i", " i : " + i);
                            JSONObject obj = jsonarrayproducts.getJSONObject(i);
                            String Code = obj.getString("Code");
                            String Name = obj.getString("Name");
                            mydb.execSQL("INSERT INTO products"+"(Code, Name)"+
                                    "VALUES ('"+Code+"','"+Name+"');");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mydb.close();
                }else{
                    Log.d("->", "db Exists");
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mProgressDialog.hide();
            if(dbplacita.exists()){
                Log.d("->", "Existe db");
            }else{
                Toast.makeText(NewStockForm.this, "¡Hora de la siembra!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public Boolean existUnitTable(){
        SQLiteDatabase mDatabase = openOrCreateDatabase("placitadb", SQLiteDatabase.CREATE_IF_NECESSARY,null);
        Cursor c = null;
        boolean tableExists = false;
        try{
            c = mDatabase.query("units", null,
                    null, null, null, null, null);
            tableExists = true;
        }
        catch (Exception e) {
            Log.d("checkingTable", "Units : "+" doesn't exist :(((");
        }
        return tableExists;
    }

    public Boolean existProductTable(){
        SQLiteDatabase mDatabase = openOrCreateDatabase("placitadb", SQLiteDatabase.CREATE_IF_NECESSARY,null);
        Cursor c = null;
        boolean tableExists = false;
        try{
            c = mDatabase.query("products", null,
                    null, null, null, null, null);
            tableExists = true;
        }
        catch (Exception e) {
            Log.d("checkingTable", "Products : "+" doesn't exist :(((");
        }
        return tableExists;
    }

}
