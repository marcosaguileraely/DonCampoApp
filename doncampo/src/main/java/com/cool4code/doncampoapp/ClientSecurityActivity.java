package com.cool4code.doncampoapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class ClientSecurityActivity extends ActionBarActivity implements OnClickListener {
    TextView label_login_dni;
    TextView label_login_pass;

    TextView farmer_sign_label_name;
    TextView farmer_sign_label_dni;
    TextView label_sign_pass;
    TextView label_sign_mail;
    TextView label_sign_mobile;

    EditText farmer_login_dni;
    EditText farmer_login_pass;

    EditText farmer_sign_name;
    EditText farmer_sign_dni;

    EditText farmer_sign_pass;
    EditText farmer_sign_mail;
    EditText farmer_sign_mobile;
    EditText farmer_sign_address;
    EditText farmer_sign_state;

    Button login;
    Button noAccount;
    Button register;

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_security);

        //login objects
        label_login_dni         = (TextView) findViewById(R.id.farmer_login_label_dni);
        label_login_pass        = (TextView) findViewById(R.id.farmer_login_label_pass);
        //register objects
        farmer_sign_label_name  = (TextView) findViewById(R.id.farmer_sign_label_name);
        farmer_sign_label_dni   = (TextView) findViewById(R.id.farmer_sign_label_dni);
        label_sign_pass         = (TextView) findViewById(R.id.farmer_sign_label_pass);
        label_sign_mail         = (TextView) findViewById(R.id.farmer_sign_label_mail);
        label_sign_mobile       = (TextView) findViewById(R.id.farmer_sign_label_mobile);
        //login objects
        farmer_login_dni        = (EditText) findViewById(R.id.farmer_login_dni);
        farmer_login_pass       = (EditText) findViewById(R.id.farmer_login_pass);
        //register objects
        farmer_sign_name        = (EditText) findViewById(R.id.farmer_sign_name);
        farmer_sign_dni         = (EditText) findViewById(R.id.farmer_sign_dni);
        farmer_sign_pass        = (EditText) findViewById(R.id.farmer_sign_pass);
        farmer_sign_mail        = (EditText) findViewById(R.id.farmer_sign_mail);
        farmer_sign_mobile      = (EditText) findViewById(R.id.farmer_sign_mobile);
        farmer_sign_address     = (EditText) findViewById(R.id.farmer_sign_address);
        farmer_sign_state       = (EditText) findViewById(R.id.farmer_sign_state);
        //buttons objects
        login                   = (Button) findViewById(R.id.farmer_login_button);
        noAccount               = (Button) findViewById(R.id.farmer_login_button_singup);
        register                = (Button) findViewById(R.id.farmer_sign_button_singup);

        farmer_sign_label_name.setVisibility(View.INVISIBLE);
        farmer_sign_label_dni.setVisibility(View.INVISIBLE);
        label_sign_pass.setVisibility(View.INVISIBLE);
        label_sign_mail.setVisibility(View.INVISIBLE);
        label_sign_mobile.setVisibility(View.INVISIBLE);

        farmer_sign_name.setVisibility(View.INVISIBLE);
        farmer_sign_dni.setVisibility(View.INVISIBLE);
        farmer_sign_pass.setVisibility(View.INVISIBLE);
        farmer_sign_mail.setVisibility(View.INVISIBLE);
        farmer_sign_mobile.setVisibility(View.INVISIBLE);
        farmer_sign_address.setVisibility(View.INVISIBLE);
        farmer_sign_state.setVisibility(View.INVISIBLE);

        register.setVisibility(View.INVISIBLE);

        register.setOnClickListener(this);
        noAccount.setOnClickListener(this);
        login.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.client_security, menu);
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
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==findViewById(R.id.farmer_login_button).getId()){
            Log.d("Action", "--> ingresar");
            if(farmer_login_dni.equals("") && farmer_login_pass.equals("")){
                Toast.makeText(context, "¡Error en el ingreso de datos!", Toast.LENGTH_LONG).show();
                Intent goToLifeFarmer= new Intent();
            }else{
                Toast.makeText(context, "Validación correcta.", Toast.LENGTH_LONG).show();
            }

        }
        if (v.getId()==findViewById(R.id.farmer_login_button_singup).getId()){
            Log.d("Action", "--> ¿No tienes cuenta?");
            farmer_sign_label_name.setVisibility(View.VISIBLE);
            farmer_sign_label_dni.setVisibility(View.VISIBLE);
            label_sign_pass.setVisibility(View.VISIBLE);
            label_sign_mail.setVisibility(View.VISIBLE);
            label_sign_mobile.setVisibility(View.VISIBLE);

            farmer_sign_name.setVisibility(View.VISIBLE);
            farmer_sign_dni.setVisibility(View.VISIBLE);
            farmer_sign_pass.setVisibility(View.VISIBLE);
            farmer_sign_mail.setVisibility(View.VISIBLE);
            farmer_sign_mobile.setVisibility(View.VISIBLE);
            farmer_sign_address.setVisibility(View.VISIBLE);
            farmer_sign_state.setVisibility(View.VISIBLE);

            register.setVisibility(View.VISIBLE);
            login.setVisibility(View.INVISIBLE);
            noAccount.setVisibility(View.INVISIBLE);

            label_login_dni.setVisibility(View.INVISIBLE);
            label_login_pass.setVisibility(View.INVISIBLE);
            farmer_login_dni.setVisibility(View.INVISIBLE);
            farmer_login_pass.setVisibility(View.INVISIBLE);


        }
        if (v.getId()==findViewById(R.id.farmer_sign_button_singup).getId()){
            Log.d("Action", "--> Registrarme");
            farmer_sign_label_name.setVisibility(View.INVISIBLE);
            farmer_sign_label_dni.setVisibility(View.INVISIBLE);
            label_sign_pass.setVisibility(View.INVISIBLE);
            label_sign_mail.setVisibility(View.INVISIBLE);
            label_sign_mobile.setVisibility(View.INVISIBLE);

            farmer_sign_name.setVisibility(View.INVISIBLE);
            farmer_sign_dni.setVisibility(View.INVISIBLE);
            farmer_sign_pass.setVisibility(View.INVISIBLE);
            farmer_sign_mail.setVisibility(View.INVISIBLE);
            farmer_sign_mobile.setVisibility(View.INVISIBLE);
            farmer_sign_address.setVisibility(View.INVISIBLE);
            farmer_sign_state.setVisibility(View.INVISIBLE);

            register.setVisibility(View.INVISIBLE);

            label_login_dni.setVisibility(View.VISIBLE);
            label_login_pass.setVisibility(View.VISIBLE);
            farmer_login_dni.setVisibility(View.VISIBLE);
            farmer_login_pass.setVisibility(View.VISIBLE);

            login.setVisibility(View.VISIBLE);
            noAccount.setVisibility(View.VISIBLE);
            //Toast.makeText(context, "Registro creado exitosamente", Toast.LENGTH_LONG).show();
        }
    }
}
