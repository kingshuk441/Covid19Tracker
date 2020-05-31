package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.accounts.Account;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;

   private RecyclerView recyclerView;
   private RecyclerView.Adapter adapter;
   TextView mc,mr,ma,md,lastTv;
   private RecyclerView.LayoutManager layoutManager;

    ArrayList <ExampleItem> exampleList =new ArrayList<>();
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // toJson() obj to json
        // fromJson() json to obj
        ma=findViewById(R.id.activeTv);
        mc=findViewById(R.id.confirmedTv);
        mr=findViewById(R.id.recoveredTv);
        md=findViewById(R.id.deceasedTv);
        lastTv=findViewById(R.id.lastUpdatedTv);



        requestQueue = Volley.newRequestQueue(this);
        sendAPIRequest();


    }

    private void sendAPIRequest(){
        String url="https://api.covid19india.org/data.json";

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray stateWise=response.getJSONArray("statewise");
                    JSONObject state= stateWise.getJSONObject(0);
                    ma.setText(state.getString("active"));
                    mc.setText(state.getString("confirmed"));
                    md.setText(state.getString("deaths"));
                    mr.setText(state.getString("recovered"));
                    updateDate(state.getString("lastupdatedtime"));


                    for(int i=1;i<stateWise.length();i++){
                         state= stateWise.getJSONObject(i);
                        String ac,st,c,r,d;
                        ac=state.getString("active");
                        c=state.getString("confirmed");
                        d=state.getString("deaths");
                        r=state.getString("recovered");
                        st=state.getString("state");


                        exampleList.add(new ExampleItem(ac,st,c,d,r));


                        recyclerView=findViewById(R.id.list);
                        layoutManager=new LinearLayoutManager(MainActivity.this);
                        adapter=new ExampleAdapter(exampleList);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                    }


                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              Toast.makeText(MainActivity.this,"No Data",Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(request);

    }
    public void updateDate(String ustr){

        int hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)-12;
        int minutes = Calendar.getInstance().get(Calendar.MINUTE);
        int seconds = Calendar.getInstance().get(Calendar.SECOND);
       int uh,um,us;
       String Update=ustr.substring(11);
      uh= Integer.parseInt(Update.substring(0,2));
       um= Integer.parseInt(Update.substring(3,5));
       us= Integer.parseInt(Update.substring(6,8));
       hours=hours*60;
       uh=uh*60;
       int m=um-minutes;
    int h=uh-hours;
    m=m+h;
m=m%60;
String s=lastTv.getText().toString();
       lastTv.setText(s+"\n"+m+" Min Ago.");
        //22:25:03







    }
}