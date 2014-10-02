package com.cool4code.doncampoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cool4code.doncampoapp.helpers.WebService;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import java.util.ArrayList;
import java.util.List;


public class ClientSecurityActivity extends ActionBarActivity{
    EditText farmer_login_dni;
    EditText farmer_login_pass;
    Button login;
    Button noAccount;
    final Context context = this;

    private String URL_WS = "http://placita.azurewebsites.net/";
    private String WS_ACTION = "Token";
    private Integer codeResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_security);

        login = (Button) findViewById(R.id.farmer_login_button);
        noAccount = (Button) findViewById(R.id.farmer_login_button_singup);
        farmer_login_dni = (EditText) findViewById(R.id.farmer_login_dni);
        farmer_login_pass = (EditText) findViewById(R.id.farmer_login_pass);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
                nameValuePairs.add(new BasicNameValuePair("grant_type", "password"));
                nameValuePairs.add(new BasicNameValuePair("username", farmer_login_dni.getText().toString()));
                nameValuePairs.add(new BasicNameValuePair("password", farmer_login_pass.getText().toString()));

                WebService AuthUser = new WebService(URL_WS , WS_ACTION);
                Integer code = AuthUser.WSPostAuth(nameValuePairs);
                codeResponse = code;
                Log.d("CodeResponse", "-->"+codeResponse);
                if(code == 200){
                    Toast.makeText(context, "¡Acceso exitoso!", Toast.LENGTH_SHORT).show();
                    Intent goToLifeClient= new Intent(ClientSecurityActivity.this, MainActivity.class);
                    startActivity(goToLifeClient);
                }else {
                    Toast.makeText(context, "Ups! Usuario y/o clave invalido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Action", "--> ¿No tienes cuenta?");
                Intent goToSignUp= new Intent(ClientSecurityActivity.this, SingUp.class);
                startActivity(goToSignUp);
            }
        });
    }
}
