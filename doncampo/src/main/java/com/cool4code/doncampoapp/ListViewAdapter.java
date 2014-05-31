package com.cool4code.doncampoapp;

import android.content.Context;
import android.view.LayoutInflater;

public class ListViewAdapter{

    // Declare Variables
    Context context;
    String[] rank;
    String[] country;
    String[] population;
    LayoutInflater inflater;

    public ListViewAdapter(Context context, String[] rank, String[] country,
                           String[] population) {
        this.context = context;
        this.rank = rank;
        this.country = country;
        this.population = population;
    }

    //@Override
    public int getCount() {
        return rank.length;
    }

    //@Override
    public Object getItem(int position) {
        return null;
    }

    //@Override
    public long getItemId(int position) {
        return 0;
    }


}
