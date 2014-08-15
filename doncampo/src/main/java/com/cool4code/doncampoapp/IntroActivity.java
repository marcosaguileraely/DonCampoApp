package com.cool4code.doncampoapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.File;
import java.util.List;


public class IntroActivity extends ActionBarActivity implements OnClickListener{
    Button goToHome;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ProgressDialog barProgressDialog;
    private SQLiteDatabase mydb;
    int queryLimit= 1000;
    final Context context = this;
    int entry_value=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        goToHome= (Button) findViewById(R.id.intro_boton_ir_home);
        goToHome.setOnClickListener(this);

        Parse.initialize(this, "hdlW9gwhnKi61559O2MHzL5trUg3lhJxHQ8FFC4f", "DrnFCRWW2fMzv1JD4EN03QAEryW7HqclL4HDy8YK");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

        new RemoteDataTask().execute();
    }
    //executing RemoteDataTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(IntroActivity.this);
            mProgressDialog.setTitle("AgroNegocios");
            mProgressDialog.setMessage("Descargando datos. Esto tomará unos segundos...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            File db =new File("/data/data/com.cool4code.doncampoapp/databases/agronegocios");
            String objectId, fecha_SipsaMensual, nombreMercado_MercadosSem, nombreProducto_ProductosMensuales, nombreUnidad_SipsaUnidadesSemMen, createdAt;
            int codMercado_SipsaMensual, codProducto_SipsaMensual, codUnidad_SipsaMensual, codigoDepartament_MercadosSem, codigoMunicipio_MercadosSem ;
            int precioMaximoMensual_SipsaMensual, precioMinimoMensual_SipsaMensual, precioPromedioMensual_SipsaMensual;
            int size;
            if(db.exists()){
                Log.d("->", "Existe db");
                Log.d("->", "¡NOTHING TO-DO HERE!");
                mProgressDialog.dismiss();
            }else{
                Log.d("->", "No Existe db");
                SQLiteDatabase mydb = getBaseContext().openOrCreateDatabase("agronegocios", SQLiteDatabase.OPEN_READWRITE, null);
                Log.d("->", "billsdb Creada");
                mydb.execSQL("CREATE TABLE IF NOT EXISTS "+ "productos" + "(objectId VARCHAR,codMercado_SipsaMensual INT,codProducto_SipsaMensual INT,codUnidad_SipsaMensual INT,codigoDepartament_MercadosSem INT,codigoMunicipio_MercadosSem INT,nombreMercado_MercadosSem VARCHAR,nombreProducto_ProductosMensuales VARCHAR,nombreUnidad_SipsaUnidadesSemMen VARCHAR,precioMaximoMensual_SipsaMensual INT,precioMinimoMensual_SipsaMensual INT,precioPromedioMensual_SipsaMensual INT,createdAt VARCHAR);");
                Log.d("->", "tabla banknote creada");
                mydb.execSQL("CREATE TABLE IF NOT EXISTS "+ "firstime" + "(entry VARCHAR);");
                Log.d("->", "tabla firstime creada");
                mydb.execSQL("INSERT INTO firstime"+"(entry)"+
                             "VALUES ('"+entry_value+"');");
                try {
                    Log.d("MyApp", "Iniciando busqueda! ->");
                    ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Productos");
                    query.setLimit(queryLimit); // limit to at most 10 results
                    ob = query.find();
                    size= ob.size();
                    Log.d("Accion"," total: "+size);
                    for (ParseObject products : ob) {
                        objectId= products.getObjectId();
                        codMercado_SipsaMensual = products.getInt("codMercado_SipsaMensual");
                        codProducto_SipsaMensual = products.getInt("codProducto_SipsaMensual");
                        codUnidad_SipsaMensual = products.getInt("codUnidad_SipsaMensual");
                        codigoDepartament_MercadosSem = products.getInt("codigoDepartament_MercadosSem");
                        codigoMunicipio_MercadosSem = products.getInt("codigoMunicipio_MercadosSem");
                        nombreMercado_MercadosSem = products.getString("nombreMercado_MercadosSem");
                        nombreProducto_ProductosMensuales = products.getString("nombreProducto_ProductosMensuales");
                        nombreUnidad_SipsaUnidadesSemMen = products.getString("nombreUnidad_SipsaUnidadesSemMen");
                        precioMaximoMensual_SipsaMensual = products.getInt("precioMaximoMensual_SipsaMensual");
                        precioMinimoMensual_SipsaMensual = products.getInt("precioMinimoMensual_SipsaMensual");
                        precioPromedioMensual_SipsaMensual = products.getInt("precioPromedioMensual_SipsaMensual");
                        createdAt = products.getString("createdAt");
                        Log.d("Accion","-> "+size+" - "+objectId+" - "+size-- +" - "+codMercado_SipsaMensual);
                        mydb.execSQL("INSERT INTO productos"+"(objectId, codMercado_SipsaMensual, codProducto_SipsaMensual, codUnidad_SipsaMensual, codigoDepartament_MercadosSem, codigoMunicipio_MercadosSem, nombreMercado_MercadosSem, nombreProducto_ProductosMensuales, nombreUnidad_SipsaUnidadesSemMen, precioMaximoMensual_SipsaMensual, precioMinimoMensual_SipsaMensual, precioPromedioMensual_SipsaMensual, createdAt)"+
                                     "VALUES ('"+objectId+"','"+codMercado_SipsaMensual+"','"+codProducto_SipsaMensual+"','"+codUnidad_SipsaMensual+"','"+codigoDepartament_MercadosSem+"','"+codigoMunicipio_MercadosSem+"','"+nombreMercado_MercadosSem+"','"+nombreProducto_ProductosMensuales+"','"+nombreUnidad_SipsaUnidadesSemMen+"','"+precioMaximoMensual_SipsaMensual+"','"+precioMinimoMensual_SipsaMensual+"','"+precioPromedioMensual_SipsaMensual+"','"+createdAt+"');");
                    }
                } catch (ParseException e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            /*SQLiteDatabase mydb = getBaseContext().openOrCreateDatabase("billsdb", SQLiteDatabase.OPEN_READWRITE, null);*/
            mProgressDialog.dismiss();
            /*mydb.execSQL("update main.banknote set serie='' where serie='null'");
            mydb.execSQL("update main.banknote set descripcion='' where descripcion='null'");*/
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.intro, menu);
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
    public void onClick(View v) {
        Intent goToSearch= new Intent(IntroActivity.this, SearchActivity.class);
        startActivity(goToSearch);
    }
}
