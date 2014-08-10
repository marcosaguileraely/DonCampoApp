package com.cool4code.doncampoapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class IntroActivity extends ActionBarActivity implements OnClickListener{
    Button goToHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        goToHome= (Button) findViewById(R.id.intro_boton_ir_home);
        goToHome.setOnClickListener(this);



        new AsyncTask<Integer, Integer, Boolean>(){
            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute(){
                /*
                 * This is executed on UI thread before doInBackground(). It is
                 * the perfect place to show the progress dialog.
                 */
                progressDialog = ProgressDialog.show(IntroActivity.this, "", "Descargando datos. Unos segundos...");
            }
            @Override
            protected Boolean doInBackground(Integer... params)
            {
                if (params == null){
                    return false;
                }
                try{
                    /*
                     * This is run on a background thread, so we can sleep here
                     * or do whatever we want without blocking UI thread. A more
                     * advanced use would download chunks of fixed size and call
                     * publishProgress();
                     */
                    Thread.sleep(params[0]);
                }
                catch (Exception e){
                    Log.e("tag", e.getMessage());
                    /*
                     * The task failed
                     */
                    return false;
                }
                /*
                 * The task succeeded
                 */
                return true;
            }

            @Override
            protected void onPostExecute(Boolean result){
                progressDialog.dismiss();
                AlertDialog.Builder b = new AlertDialog.Builder(IntroActivity.this);
                b.setTitle(android.R.string.dialog_alert_title);
                if (result){
                    b.setMessage("¡Descarga exitosa!");
                }else{
                    b.setMessage("¡Ups! intenta nuevamente");
                }
                b.setPositiveButton(getString(android.R.string.ok),
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dlg, int arg1){
                                dlg.dismiss();
                            }
                        });
                b.create().show();
            }
        }.execute(5000);

        new Thread(){
            @Override
            public void run(){
                // dismiss the progressdialog
                /*ProgressDialog progressDialog2;
                progressDialog2.dismiss();*/
                Log.d("Dismiss","desparece dialogo");
            }
        }.start();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.intro, menu);
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
        Intent goToSearch= new Intent(IntroActivity.this, SearchActivity.class);
        startActivity(goToSearch);
    }
}
