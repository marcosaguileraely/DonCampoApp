package com.cool4code.doncampoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
        //buttons objects
        login                   = (Button) findViewById(R.id.farmer_login_button);
        noAccount               = (Button) findViewById(R.id.farmer_login_button_singup);
        //setActionClickListener
        noAccount.setOnClickListener(this);
        login.setOnClickListener(this);
    }
    /*@Override
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
    }*/
    @Override
    public void onClick(View v) {
        if (v.getId()==findViewById(R.id.farmer_login_button).getId()){
            Log.d("Action", "--> ingresar");
            if(!"".equals(farmer_login_dni) && !"".equals(farmer_login_pass)){
                Toast.makeText(context, "¡Validación correcta.!", Toast.LENGTH_LONG).show();
                Intent goToLifeClient= new Intent(ClientSecurityActivity.this, MainActivity.class);
                startActivity(goToLifeClient);
            }else{
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
            }
        }
        if (v.getId()==findViewById(R.id.farmer_login_button_singup).getId()){
            Log.d("Action", "--> ¿No tienes cuenta?");
            Intent goToSignUp= new Intent(ClientSecurityActivity.this, SingUp.class);
            startActivity(goToSignUp);
        }
    }
}
