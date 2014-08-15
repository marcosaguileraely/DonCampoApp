package com.cool4code.doncampoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cool4code team on 7/8/14.
 * Paola Vanegas
 * Alejandro Zarate Orjuela
 * David Almeciga
 * Marcos A. Aguilera Ely
 */

public class SearchActivity extends ActionBarActivity {
    private CharSequence mTitle;
    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    search_products_adapter adapter;
    private List<model_products> productsList = null;
    EditText search_input;
    Button ingreso;
    Button registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_input= (EditText) findViewById(R.id.search_product);
        ingreso= (Button) findViewById(R.id.search_home_ingresar);
        registro= (Button) findViewById(R.id.search_home_registro);

        ingreso.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("tocar", "click en INGRESO");
                Intent goToSecurity= new Intent(SearchActivity.this, ClientSecurityActivity.class);
                startActivity(goToSecurity);
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tocar", "click en REGISTRO");
                Intent goToSecurity= new Intent(SearchActivity.this, ClientSecurityActivity.class);
                startActivity(goToSecurity);
            }
        });

        search_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                String searchData=  search_input.getText().toString();
                Log.d("search", "->"+searchData);
                if(!s.equals("")){
                    new RemoteDataTask().execute();
                    productsList = new ArrayList<model_products>();
                    try {
                        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Product");
                        query.whereEqualTo("name",searchData);
                        //query.orderByAscending("name");
                        ob = query.find();
                        for (ParseObject products : ob) {
                            model_products map = new model_products();
                            map.setCode((String) products.get("code"));
                            map.setName((String) products.get("name"));
                            map.setCreatedAt((String) products.get("createdAt"));
                            productsList.add(map);
                        }
                    } catch (com.parse.ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        /*Parse.com code*/
        Parse.initialize(this, "Hy4HilhJuJCkZFCNLVZMSmZB6Zihvg1pbASEBqAz", "LnWKpocESxfmvRLskZ27lg20Y4AnC982Eh02DEZk");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Product");
        query.whereEqualTo("name", "Yuca");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> prodList, ParseException e) {
                if (e == null) {
                    Log.d("list", "Retrieved " + prodList.size() + " products");
                    Log.d("list", "Retrieved " + prodList);
                } else {
                    Log.d("list", "Error: " + e.getMessage());
                }
            }
        });
        /*ends Parse.com <code>*/
        new RemoteDataTask().execute();
    }

    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(SearchActivity.this);
            mProgressDialog.setTitle("AgroNegocios");
            mProgressDialog.setMessage("Consultando datos...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            productsList = new ArrayList<model_products>();
            try {
                // Locate the class table named "Products" in Parse.com
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Product");
                //query.whereEqualTo("name", "Yuca");
                //query.orderByAscending("ranknum");
                ob = query.find();
                for (ParseObject products : ob) {
                    model_products map = new model_products();
                    map.setCode((String) products.get("code"));
                    map.setName((String) products.get("name"));
                    map.setCreatedAt((String) products.get("createdAt"));
                    productsList.add(map);
                }
            } catch (com.parse.ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            listview = (ListView) findViewById(R.id.search_productListView);
            adapter = new search_products_adapter(SearchActivity.this, productsList);
            listview.setAdapter(adapter);
            mProgressDialog.dismiss();
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
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
}
