package com.cool4code.doncampoapp.helpers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cool4code.doncampoapp.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by marcosantonioaguilerely on 11/5/14.
 */
public class AdapterMarket extends ArrayAdapter<MarketModel>{
    private final Context context;
    private final ArrayList<MarketModel> marketArrayList;

    public AdapterMarket(Context context, ArrayList<MarketModel> myStockArrayList) {
        super(context, R.layout.my_stock_listview, myStockArrayList);
        this.context = context;
        this.marketArrayList = myStockArrayList;
    }

    //@Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.market_listview, parent, false);

        // 3. Get the two text view from the rowView
        TextView product_name = (TextView) rowView.findViewById(R.id.market_productname);
        TextView farmer = (TextView) rowView.findViewById(R.id.market_farmer);
        TextView price = (TextView) rowView.findViewById(R.id.market_priceunit);
        TextView unit = (TextView) rowView.findViewById(R.id.market_unit);
        TextView qty = (TextView) rowView.findViewById(R.id.market_qty);
        TextView expiresAt = (TextView) rowView.findViewById(R.id.market_expiresat);
        TextView address = (TextView) rowView.findViewById(R.id.market_address);

        MarketModel marketModel = marketArrayList.get(position);

        /*formating local currency*/
        int qtyValue = marketModel.getQty();
        String expires = marketModel.getExpiresAt();
        int priceValue = marketModel.getPricePerUnit();
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();

        Locale COP = new Locale("es", "CO");
        NumberFormat copFormat = NumberFormat.getCurrencyInstance(COP);
        Log.d("COPFormat", "==>" + copFormat.format(priceValue));
        String priceCOPString = copFormat.format(priceValue);
        String qtyString = Integer.toString(qtyValue);
        String qtyComplete = "Cantidad: " + qtyString;
        String expiresComplete = "Vence: "+ expires;

        /*concat address to view*/
        String town = marketModel.getGeoPoint_Town();
        String state = marketModel.getGeoPoint_State();
        String concatAddress = town + ", " + state;

        // 4. Set the text for textView
        product_name.setText(marketModel.getProduct_Name());
        farmer.setText(marketModel.getUser_Name());
        price.setText(priceCOPString);
        unit.setText(marketModel.getUnit_Name());
        qty.setText(qtyComplete);
        expiresAt.setText(expiresComplete);
        address.setText(concatAddress);
        // 5. return rowView
        return rowView;
    }
}
