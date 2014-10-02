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
import android.widget.Toast;


public class ClientSecurityActivity extends ActionBarActivity implements OnClickListener {
    EditText farmer_login_dni;
    EditText farmer_login_pass;
    Button login;
    Button noAccount;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_security);

        login                   = (Button) findViewById(R.id.farmer_login_button);
        noAccount               = (Button) findViewById(R.id.farmer_login_button_singup);
        noAccount.setOnClickListener(this);
        login.setOnClickListener(this);
    }

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
