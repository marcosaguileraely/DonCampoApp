package com.cool4code.doncampoapp;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cool4code.doncampoapp.helpers.DatabaseHandler;
import com.cool4code.doncampoapp.helpers.SpinnerObject;
import com.cool4code.doncampoapp.helpers.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NewStockForm extends ActionBarActivity {
    private String URL_WS = "http://placita.azurewebsites.net/";
    private String WS_ACTION_UNITS = "api/Units";
    private String WS_ACTION_PRODUCTS = "api/Products";
    private String WS_ACTION_STOCK = "api/Stocks";

    ProgressDialog mProgressDialog;
    Context context = this;

    File dbplacita =new File("/data/data/com.cool4code.doncampoapp/databases/placitadb");
    private String GeoUrl    = "https://maps.googleapis.com/maps/";
    private String GeoParams = "api/geocode/json?latlng=";

    Spinner units;
    Spinner products;
    EditText qty;
    EditText price;
    EditText date;
    Button save_data;
    Button date_picker;

    JSONObject jsonObj = new JSONObject();
    String token;
    Integer statusCodeGlobal;

    private int mYear, mMonth, mDay, mHour, mMinute;

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

        units     = (Spinner) findViewById(R.id.unit_spinner);
        products  = (Spinner) findViewById(R.id.product_spinner);
        qty       = (EditText) findViewById(R.id.product_cuantity);
        price     = (EditText) findViewById(R.id.product_price);
        date      = (EditText) findViewById(R.id.expires_date);
        save_data = (Button) findViewById(R.id.save_stock_data);
        date_picker = (Button) findViewById(R.id.date_picker_selector);

        new RemoteDataTask().execute();

        units.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //Object item = parent.getItemAtPosition(pos);
                //String item2 = parent.getItemAtPosition(pos).toString();
                //int unitId = Integer.parseInt(String.valueOf(((SpinnerObject) units.getSelectedItem()).getId()));
                //Toast.makeText(context, "Seleccionado :" + unitId, Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        products.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //Object item = parent.getItemAtPosition(pos);
                //String item2 = parent.getItemAtPosition(pos).toString();
                //int unitId = Integer.parseInt(String.valueOf( ((SpinnerObject) products.getSelectedItem()).getId()) );
                //Toast.makeText(context, "Seleccionado :" + unitId, Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(NewStockForm.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date.setText( (year + "-" + (monthOfYear + 1) + "-" + dayOfMonth) );
                            }
                        }, mYear, mMonth, mDay);
                dpd.show();
            }
        });

        save_data.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int productId = Integer.parseInt(String.valueOf( ((SpinnerObject) products.getSelectedItem()).getId()) );
                int unitId = Integer.parseInt(String.valueOf(((SpinnerObject) units.getSelectedItem()).getId()));
                double dqty = Double.parseDouble(qty.getText().toString());
                double dprice = Double.parseDouble(price.getText().toString());
                String expire_date = date.getText().toString();

                LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                //String provider = lm.getBestProvider(new Criteria(), true);
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();
                Log.d("->", " Lat-> " + latitude + " Long-> " + longitude);
                //i have to change the lat long by automatic changes

                WebService geo = new WebService(GeoUrl, GeoParams);
                ArrayList geoArray = geo.WSGetGeoCode(latitude, longitude);
                String geoString = (String) geoArray.get(0);
                Log.d("Geo", "geo : " + geoString);
                String[] geoStringArray = geoString.trim().split("\\s*,\\s*");
                ArrayList<String> arryLocation = new ArrayList<String>();

                    for(int i=0 ; i<= geoStringArray.length-1 ; i++){
                        Log.d("Geo", "geo : " + geoStringArray[i]);
                        arryLocation.add(geoStringArray[i]);
                    }
                Log.d("String", "String : " + arryLocation);
                String geoAddress = (String) arryLocation.get(0);
                String geoTown    = (String) arryLocation.get(1);
                String geoState   = (String) arryLocation.get(2);
                String geoCountry = (String) arryLocation.get(3);
                Log.d("Address", "Address : " + geoAddress);
                Log.d("Town", "Town : " + geoTown);
                Log.d("State", "State : " + geoState);
                Log.d("Country", "Country : " + geoCountry);

                String table_name = "auth";
                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                token = db.getAuth(table_name);

                try {
                    jsonObj.put("ProductId", productId);
                    jsonObj.put("UnitId", unitId);
                    jsonObj.put("Qty", dqty);
                    jsonObj.put("PricePerUnit", dprice);
                    jsonObj.put("ExpiresAt", expire_date);
                        JSONObject GeoPoint = new JSONObject();
                        GeoPoint.put("Latitude", longitude);
                        GeoPoint.put("Longitude", latitude);
                        GeoPoint.put("Address", geoAddress);
                        GeoPoint.put("Town", "xxx");
                        GeoPoint.put("State", geoState);
                        GeoPoint.put("Country", geoCountry);
                    jsonObj.put("GeoPoint", GeoPoint);
                    Log.d("json", "json : " + jsonObj);

                PostStockAT newExe = new PostStockAT();
                    newExe.execute();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Function to load the spinner data from Unit Table
     **/
    private void loadSpinnerDataUnit() {
        String table_name = "units";
        // database handler
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        // Spinner Drop down elementssss
        List <SpinnerObject> lables = db.getAllLabels(table_name);
        // Creating adapter for spinner
        ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(this,
                    android.R.layout.simple_spinner_item, lables);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        units.setAdapter(dataAdapter);
    }
    /**
     * Function to load the spinner data from Products Table
     **/
    private void loadSpinnerDataProduct() {
        String table_name = "products";
        // database handler
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        // Spinner Drop down elements
        List <SpinnerObject> lables = db.getAllLabels(table_name);
        // Creating adapter for spinner
        ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(this,
                    android.R.layout.simple_spinner_item, lables);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        products.setAdapter(dataAdapter);
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

            String table_name = "auth";
            DatabaseHandler dbh = new DatabaseHandler(getApplicationContext());
            token = dbh.getAuth(table_name);

            //Caso #1 : Descargando Unidades
            if(existsUnit == true){
                /*nothing to do here*/
                Log.d("->", "unit Exists");
            }else{
                WebService getUnits = new WebService(URL_WS , WS_ACTION_UNITS);
                String units = getUnits.WSGetUnits(token);
                JSONArray jsonarray = null;
                try {
                    jsonarray = new JSONArray(units);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("==>", "JsonObj : " + jsonarray);

                if(db.exists()){
                    SQLiteDatabase mydb = getBaseContext().openOrCreateDatabase("placitadb", SQLiteDatabase.OPEN_READWRITE, null);
                    mydb.execSQL("CREATE TABLE IF NOT EXISTS " + "units" + "(Id INT, Code VARCHAR, Name VARCHAR);");
                    Log.d("===>", "Lenght: " + jsonarray.length());
                    try {
                        for (int i=0; i <= jsonarray.length()-1; i++) {
                            Log.d("//i", " i : " + i);
                            JSONObject obj = jsonarray.getJSONObject(i);
                            Integer Id  = obj.getInt("Id");
                            String Code = obj.getString("Code");
                            String Name = obj.getString("Name");
                            mydb.execSQL("INSERT INTO units"+"(Id, Code, Name)"+
                                         "VALUES ('"+Id+"','"+Code+"','"+Name+"');");
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
                String products = getProducts.WSGetUnits(token);
                JSONArray jsonarrayproducts = null;
                try {
                    jsonarrayproducts = new JSONArray(products);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("==>", "JsonArrayProducts : " + jsonarrayproducts);

                if(db.exists()){
                    SQLiteDatabase mydb = getBaseContext().openOrCreateDatabase("placitadb", SQLiteDatabase.OPEN_READWRITE, null);
                    mydb.execSQL("CREATE TABLE IF NOT EXISTS " + "products" + "(Id INT, Code VARCHAR, Name VARCHAR);");
                    Log.d("===>", "Lenght: " + jsonarrayproducts.length());
                    try {
                        for (int i=0; i <= jsonarrayproducts.length()-1; i++) {
                            Log.d("//i", " i : " + i);
                            JSONObject obj = jsonarrayproducts.getJSONObject(i);
                            Integer Id  = obj.getInt("Id");
                            String Code = obj.getString("Code");
                            String Name = obj.getString("Name");
                            mydb.execSQL("INSERT INTO products"+"(Id, Code, Name)"+
                                         "VALUES ('"+Id+"','"+Code+"','"+Name+"');");
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
            loadSpinnerDataUnit();
            loadSpinnerDataProduct();
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

    private class PostStockAT extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setTitle("Agronegocios");
            mProgressDialog.setMessage("¡Preparando productos para la placita!. Esto tomará unos segundos. Espere...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            WebService postStock = new WebService(URL_WS, WS_ACTION_STOCK);
            final Integer statusCode = postStock.WSPostStock(jsonObj, token);
            Log.d("Code", "Code : " + statusCode);
            statusCodeGlobal = statusCode;
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if(statusCodeGlobal == 200 || statusCodeGlobal == 201){
                mProgressDialog.hide();
                Toast.makeText(NewStockForm.this, "¡Inventario creado y puesto en la placita!", Toast.LENGTH_SHORT).show();
                Intent goToStockHome =  new Intent(NewStockForm.this, FarmerStock.class);
                startActivity(goToStockHome);
            }else{
                mProgressDialog.hide();
                Toast.makeText(NewStockForm.this, "¡Algo esta mal!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            double longitudex = location.getLongitude();
            double latitudex  = location.getLatitude();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };*/

}
