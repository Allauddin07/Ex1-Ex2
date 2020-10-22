package com.allauddin.myvolleyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String server_url= "https://dog.ceo/api/breeds/image/random";
    Button btn;
    ImageView img;
    private RequestQueue RQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RQueue= Volley.newRequestQueue(this.getApplicationContext());
        btn=(Button)findViewById(R.id.btn);
        img = (ImageView)findViewById(R.id.img);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volley();
            }


        });
    }
    private void volley(){
        Response.Listener ResponseL=new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response",response.toString());
                try {
                    server_url = response.get("message").toString();
                    Picasso.get().load(server_url).into( img);

                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "Boom somthing went wrong", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        };
        Response.ErrorListener ErrorL = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("err",error.toString());
                Toast.makeText(MainActivity.this, "Boom somthing went wrong", Toast.LENGTH_SHORT).show();

            }
        };
        JsonObjectRequest JOReq = new JsonObjectRequest(Request.Method.GET, server_url, null, ResponseL, ErrorL);
        RQueue.add(JOReq);
    }
}