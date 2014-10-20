package com.cool4code.doncampoapp;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
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
    ProgressDialog mProgressDialog;

    private String URL_WS = "http://placita.azurewebsites.net/";
    private String WS_ACTION = "Token";
    private Integer codeResponse;

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_security);

        login = (Button) findViewById(R.id.farmer_login_button);
        noAccount = (Button) findViewById(R.id.farmer_login_button_singup);
        farmer_login_dni = (EditText) findViewById(R.id.farmer_login_dni);
        farmer_login_pass = (EditText) findViewById(R.id.farmer_login_pass);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((!farmer_login_dni.equals("")) && !farmer_login_pass.equals("")){
                    new AsyncWS().execute();
                }else{
                    Toast.makeText(context, "¡Ingrese usuario y clave para acceder!", Toast.LENGTH_SHORT).show();
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

    private class AsyncWS extends AsyncTask<Void, Void, Void> {
        private Integer codeResponse;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(ClientSecurityActivity.this);
            mProgressDialog.setTitle("Agronegocios");
            mProgressDialog.setMessage("Validando información. Espere...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        protected Void doInBackground(Void... params) {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
            nameValuePairs.add(new BasicNameValuePair("grant_type", "password"));
            nameValuePairs.add(new BasicNameValuePair("username", farmer_login_dni.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("password", farmer_login_pass.getText().toString()));

            WebService AuthUser = new WebService(URL_WS , WS_ACTION);
            Integer code = AuthUser.WSPostAuth(nameValuePairs);
            codeResponse = code;
            return null;
        }

        protected void onPostExecute(Void result) {
            Log.d("CodeResponse", "-->"+codeResponse);
            if(codeResponse == 200){
                mProgressDialog.dismiss();
                Toast.makeText(context, "¡Acceso exitoso!", Toast.LENGTH_SHORT).show();
                Intent goToLifeClient= new Intent(ClientSecurityActivity.this, MainActivity.class);
                startActivity(goToLifeClient);
            }else {
                mProgressDialog.dismiss();
                Toast toast = Toast.makeText(context,"Agronegocios no ha podido validar el acceso. Intentalo nuevamente.", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
    }
}
