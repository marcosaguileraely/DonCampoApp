package com.cool4code.doncampoapp.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cool4code.doncampoapp.R;

import java.util.ArrayList;

/**
 * Created by marcosantonioaguilerely on 11/1/14.
 */
public class AdapterMyStock {

    private final Context context;
    private final ArrayList<MyStockModel> myStockArrayList;

    public AdapterMyStock(Context context, ArrayList<MyStockModel> myStockArrayList) {
       // super(context, R.layout.my_stock_listview, myStockArrayList);
        this.context = context;
        this.myStockArrayList = myStockArrayList;
    }

    //@Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.product_listview, parent, false);

        // 3. Get the two text view from the rowView
        TextView nameList = (TextView) rowView.findViewById(R.id.product_nameBox);
        TextView locationList = (TextView) rowView.findViewById(R.id.product_location);
        TextView unitList = (TextView) rowView.findViewById(R.id.mystock_unit);
        TextView avgPriceList = (TextView) rowView.findViewById(R.id.product_avg);

        MyStockModel myStockModel = (MyStockModel) myStockArrayList.get(position);

        //Currency COP Convertion
        //int avgValue = myStockModel.getPriceAvgPerUnit();
        //NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
        //Log.d("CURRENCY", "==>" + defaultFormat.format(avgValue));

        //Locale COP = new Locale("es", "CO");
        //NumberFormat copFormat = NumberFormat.getCurrencyInstance(COP);
        //Log.d("COPFormat", "===>"+ copFormat.format(avgValue));
        //String copString = copFormat.format(avgValue);

        // 4. Set the text for textView
        //nameList.setText(pricesModel.getProduct_Name());
        //locationList.setText(pricesModel.getLocation());
        //unitList.setText(pricesModel.getUnit_Name());
        //avgPriceList.setText(copString);

        // 5. return rowView
        return rowView;
    }


}
