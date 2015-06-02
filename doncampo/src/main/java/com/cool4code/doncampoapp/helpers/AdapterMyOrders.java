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
 * Created by marcosantonioaguilerely on 11/9/14.
 */
<<<<<<< HEAD
public class AdapterMyOrders extends ArrayAdapter<MyPurchasesModel> {
    private final Context context;
    private final ArrayList<MyPurchasesModel> myPurchasesArrayList;

    public AdapterMyOrders(Context context, ArrayList<MyPurchasesModel> myPurchasesArrayList) {
        super(context, R.layout.my_purchases_listview, myPurchasesArrayList);
=======
public class AdapterMyOrders extends ArrayAdapter<MyOrdersModel> {
    private final Context context;
    private final ArrayList<MyOrdersModel> myPurchasesArrayList;

    public AdapterMyOrders(Context context, ArrayList<MyOrdersModel> myPurchasesArrayList) {
        super(context, R.layout.my_orders_listview, myPurchasesArrayList);
>>>>>>> MyOrders
        this.context = context;
        this.myPurchasesArrayList = myPurchasesArrayList;
    }

    public int getCount() {
        if (myPurchasesArrayList != null)
            return myPurchasesArrayList.size();
        return 0;
    }

<<<<<<< HEAD
    public MyPurchasesModel getItem(int position) {
=======
    public MyOrdersModel getItem(int position) {
>>>>>>> MyOrders
        if (myPurchasesArrayList != null)
            return myPurchasesArrayList.get(position);
        return null;
    }

    public long getItemId(int position) {
        if (myPurchasesArrayList != null)
            return myPurchasesArrayList.get(position).getId_Order();
        return 0;
    }

    public ArrayList getAllData(int position){
<<<<<<< HEAD
        MyPurchasesModel myPurchasesModel= myPurchasesArrayList.get(position);
        ArrayList<String> arrayData = new ArrayList<String>();
        String Id = Integer.toString(myPurchasesModel.getId_Order());
        String Product_Name = myPurchasesModel.getProduct_Name();
        String Unit_Name = myPurchasesModel.getUnit_Name();
        String Qty = Integer.toString(myPurchasesModel.getStock_Qty());

        /*formating local currency*/
        double Price = myPurchasesModel.getPricePerUnit();
=======
        MyOrdersModel myOrdersModel= myPurchasesArrayList.get(position);
        ArrayList<String> arrayData = new ArrayList<String>();
        String Id = Integer.toString(myOrdersModel.getId_Order());
        String Product_Name = myOrdersModel.getProduct_Name();
        String Unit_Name = myOrdersModel.getUnit_Name();
        String Qty = Integer.toString(myOrdersModel.getStock_Qty());

        /*formating local currency*/
        double Price = myOrdersModel.getPricePerUnit();
>>>>>>> MyOrders
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();

        Locale COP = new Locale("es", "CO");
        NumberFormat copFormat = NumberFormat.getCurrencyInstance(COP);
        Log.d("COPFormat", "==>" + copFormat.format(Price));
        String priceCOPString = copFormat.format(Price);

<<<<<<< HEAD
        String ExpiresAt = myPurchasesModel.getExpiresAt();
        String Address = myPurchasesModel.getAddress() + ", " + myPurchasesModel.getGeo_State() + ", " + myPurchasesModel.getCountry();
        String Farmer = myPurchasesModel.getName();
        String Email = myPurchasesModel.getEmail();
=======
        String ExpiresAt = myOrdersModel.getExpiresAt();
        String Address = myOrdersModel.getAddress() + ", " + myOrdersModel.getGeo_State() + ", " + myOrdersModel.getCountry();
        String Farmer = myOrdersModel.getName();
        String Email = myOrdersModel.getEmail();
        String Phone = myOrdersModel.getPhone();
>>>>>>> MyOrders

        arrayData.add(Id);
        arrayData.add(Product_Name);
        arrayData.add(Unit_Name);
        arrayData.add(Qty);
        arrayData.add(priceCOPString);
        arrayData.add(ExpiresAt);
        arrayData.add(Address);
        arrayData.add(Farmer);
        arrayData.add(Email);
<<<<<<< HEAD
=======
        arrayData.add(Phone);
>>>>>>> MyOrders

        return  arrayData;
    }

    @Override
    public boolean isEnabled(int position){
        return true;
    }

    //@Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
<<<<<<< HEAD
        View rowView = inflater.inflate(R.layout.my_purchases_listview, parent, false);

        // 3. Get the two text view from the rowView
        TextView product_name = (TextView) rowView.findViewById(R.id.purchase_productname);
        TextView farmer = (TextView) rowView.findViewById(R.id.purchase_farmer);
        TextView price = (TextView) rowView.findViewById(R.id.purchase_priceunit);
        TextView unit = (TextView) rowView.findViewById(R.id.purchase_unit);
        TextView qty = (TextView) rowView.findViewById(R.id.purchase_qty);
        TextView expiresAt = (TextView) rowView.findViewById(R.id.purchase_expiresat);
        TextView address = (TextView) rowView.findViewById(R.id.purchase_address);

        MyPurchasesModel myPurchasesModel = myPurchasesArrayList.get(position);

        /*formating local currency*/
        int qtyValue = myPurchasesModel.getStock_Qty();
        String expires = myPurchasesModel.getExpiresAt();
        double priceValue = myPurchasesModel.getPricePerUnit();
=======
        View rowView = inflater.inflate(R.layout.my_orders_listview, parent, false);

        // 3. Get the two text view from the rowView
        TextView product_name = (TextView) rowView.findViewById(R.id.orders_productname);
        TextView farmer = (TextView) rowView.findViewById(R.id.orders_farmer);
        TextView price = (TextView) rowView.findViewById(R.id.orders_priceunit);
        TextView unit = (TextView) rowView.findViewById(R.id.orders_unit);
        TextView qty = (TextView) rowView.findViewById(R.id.orders_qty);
        TextView expiresAt = (TextView) rowView.findViewById(R.id.orders_expiresat);
        TextView address = (TextView) rowView.findViewById(R.id.orders_address);

        MyOrdersModel myOrdersModel = myPurchasesArrayList.get(position);

        /*formating local currency*/
        int qtyValue = myOrdersModel.getStock_Qty();
        String comprado = myOrdersModel.getExpiresAt();
        double priceValue = myOrdersModel.getPricePerUnit();
>>>>>>> MyOrders
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();

        Locale COP = new Locale("es", "CO");
        NumberFormat copFormat = NumberFormat.getCurrencyInstance(COP);
        Log.d("COPFormat", "==>" + copFormat.format(priceValue));
        String priceCOPString = copFormat.format(priceValue);
        String qtyString = Integer.toString(qtyValue);
        String qtyComplete = "Cantidad: " + qtyString;
<<<<<<< HEAD
        String expiresComplete = "Vence: "+ expires;

        /*concat address to view*/
        String town = myPurchasesModel.getTown();
        String state = myPurchasesModel.getGeo_State();
        String concatAddress = town + ", " + state;

        // 4. Set the text for textView
        product_name.setText(myPurchasesModel.getProduct_Name());
        farmer.setText(myPurchasesModel.getName());
        price.setText(priceCOPString);
        unit.setText(myPurchasesModel.getUnit_Name());
        qty.setText(qtyComplete);
        expiresAt.setText(expiresComplete);
=======
        String compradoComplete = "Fecha: "+ comprado;

        /*concat address to view*/
        String town = myOrdersModel.getTown();
        String state = myOrdersModel.getGeo_State();
        String concatAddress = town + ", " + state;

        // 4. Set the text for textView
        product_name.setText(myOrdersModel.getProduct_Name());
        farmer.setText(myOrdersModel.getName());
        price.setText(priceCOPString);
        unit.setText(myOrdersModel.getUnit_Name());
        qty.setText(qtyComplete);
        expiresAt.setText(compradoComplete);
>>>>>>> MyOrders
        address.setText(concatAddress);
        // 5. return rowView
        return rowView;
    }
<<<<<<< HEAD


=======
>>>>>>> MyOrders
}
