package com.cool4code.doncampoapp.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.cool4code.doncampoapp.helpers.DB;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by marcosantonioaguilerely on 10/22/14.
 */
public class MyService extends Service{
    private DB db;
    private String URL_WS = "http://placita.azurewebsites.net/";
    private String WS_ACTION_PRICES = "api/Prices" ;
    private static final String PRICES_TABLE = "prices";

    private ArrayList<HashMap> tables;
    Context context = this;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "The new Service was Created", Toast.LENGTH_LONG).show();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //tables = new ArrayList<HashMap>();
        //Log.i("LocalService", "Received start id " + startId + ": " + intent);
        //WebService wsPrices = new WebService(URL_WS, WS_ACTION_PRICES);
        //final HashMap<String, JSONArray> hmPrices = new HashMap();
        //hmPrices.put(PRICES_TABLE, wsPrices.parseJsonText(wsPrices.getJsonText()));
        //tables.add(hmPrices);

        //db = new DB(context, tables);
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

}
