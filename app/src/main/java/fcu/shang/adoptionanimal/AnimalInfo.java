package fcu.shang.adoptionanimal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by SERS on 2017/7/13.
 */

public class AnimalInfo{

    RequestQueue requestQueue;
    Handler handler;
    String url="http://data.coa.gov.tw/Service/OpenData/AnimalOpenData.aspx";

    public AnimalInfo(Handler handler, Context context) {
        this.handler=handler;
        requestQueue= Volley.newRequestQueue(context);
    }

    public AnimalInfo(){

    }

    public void getInfo(){
        StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Message message=new Message();
                message.what=1;
                Bundle bundle=new Bundle();
                bundle.putString(MainActivity.GSON,response.toString());
                message.setData(bundle);
                handler.sendMessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(MainActivity.GSON,error.toString());

            }
        });
        requestQueue.add(stringRequest);
    }




}
