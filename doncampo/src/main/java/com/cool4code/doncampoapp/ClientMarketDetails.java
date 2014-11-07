package com.cool4code.doncampoapp;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class ClientMarketDetails extends ActionBarActivity {
    ArrayList<String> detailsMarketArray;
    TextView Product;
    TextView Unit;
    TextView Qty;
    TextView Price;
    TextView Expires;
    TextView Address;
    TextView Farmer;
    TextView Email;
    Button New_Order;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_market_details);

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

        Bundle extras = getIntent().getExtras();
        detailsMarketArray = extras.getStringArrayList("DetailsArray");
        Log.d("//Extra", "//Extra" + detailsMarketArray);

        Product = (TextView) findViewById(R.id.details_right_product);
        Unit = (TextView) findViewById(R.id.details_right_unit);
        Qty = (TextView) findViewById(R.id.details_right_qty);
        Price = (TextView) findViewById(R.id.details_right_price);
        Expires = (TextView) findViewById(R.id.details_right_expires);
        Address = (TextView) findViewById(R.id.details_right_location);
        Farmer = (TextView) findViewById(R.id.details_right_farmer);
        Email = (TextView) findViewById(R.id.details_right_email);
        New_Order = (Button) findViewById(R.id.new_order);

        Product.setText(detailsMarketArray.get(1));
        Unit.setText(detailsMarketArray.get(2));
        Qty.setText(detailsMarketArray.get(3));
        Price.setText(detailsMarketArray.get(4));
        Expires.setText(detailsMarketArray.get(5));
        Address.setText(detailsMarketArray.get(6));
        Farmer.setText(detailsMarketArray.get(7));
        Email.setText(detailsMarketArray.get(8));

        New_Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToBuy = new Intent(ClientMarketDetails.this, ClientBuy.class);
                goToBuy.putExtra("buyDetails", detailsMarketArray);
                startActivity(goToBuy);
            }
        });
    }
}
