package com.cool4code.doncampoapp.helpers;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by COOL4CODE TEAM @cool4code on 9/28/14.
 * Indeed DonCampo Team
 * David Alméciga @dagrinchi
 * Marcos Aguilera @marcode_ely
 */
public class WebService {
    boolean resul = true;

    private String URL;
    private String WS_Method;

    public WebService(String URL, String WS_Method){
        this.URL = URL;
        this.WS_Method= WS_Method;
    }

    public Integer WSPostAction(JSONObject data) {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(URL + WS_Method);
        post.setHeader("content-type", "application/json");
        int statusCode = 0;
        try {
            StringEntity entity = new StringEntity(data.toString());
            post.setEntity(entity);

            HttpResponse response = client.execute(post);
            StatusLine statusLine = response.getStatusLine();
            statusCode = statusLine.getStatusCode();

            String respStr = EntityUtils.toString(response.getEntity());
            Log.d("httpResponse", "-->" + respStr);
            Log.d("httpResponse", "-->" + statusCode);

            if (!respStr.equals("true"))
                resul = false;

        } catch (Exception e) {
            Log.e("HttpResponse-->", "Error " + e);
            resul = false;
        }
        return statusCode;
    }

    public Integer WSPostAuth(List nameValuePairs) {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(URL + WS_Method);
        int statusCode = 0;

        try {
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = client.execute(post);
            StatusLine statusLine = response.getStatusLine();
            statusCode = statusLine.getStatusCode();

            String respStr = EntityUtils.toString(response.getEntity());
            Log.d("httpResponse", "-->" + respStr);
            Log.d("httpResponse", "-->" + statusCode);

        }catch (Exception e){
            Log.e("HttpResponse-->", "Error " + e);
        }
        return statusCode;
    }

    public String getJsonText() {
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(this.URL);
        String jsonText = null;

        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                jsonText = builder.toString();
            } else {
                Log.e(WebService.class.getName(), "Failed to connect!");
            }
        }catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return  jsonText;
    }

    //parsing jsonText to JSONArray
    public JSONArray parseJsonText(String jsonText){
        JSONArray jsonArray = null;
        try {
            jsonArray= new JSONArray(jsonText);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonArray;
    }
}
