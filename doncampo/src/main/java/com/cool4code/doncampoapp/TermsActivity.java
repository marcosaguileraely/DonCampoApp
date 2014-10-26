package com.cool4code.doncampoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


public class TermsActivity extends ActionBarActivity {
    Button goToSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        goToSearch = (Button) findViewById(R.id.accept_conditions);

        goToSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToSearch = new Intent(TermsActivity.this, SearchActivity.class);
                startActivity(goToSearch);
            }
        });
    }
}
