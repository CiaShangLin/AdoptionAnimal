package fcu.shang.adoptionanimal.Animal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import fcu.shang.adoptionanimal.MainActivity;

/**
 * Created by SERS on 2017/7/13.
 */

public class AnimalInfo{
    private ArrayList<Animal> animalList;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;
    private Handler handler;
    String url="http://data.coa.gov.tw/Service/OpenData/AnimalOpenData.aspx";

    public AnimalInfo(Handler handler, Context context) {
        this.handler=handler;
        requestQueue= Volley.newRequestQueue(context);
        imageLoader=new ImageLoader(requestQueue,new BitmapCache());
    }

    public ArrayList<Animal> getAnimalList(){    //應該要複製一份過 不是傳參照
        return animalList;
    }


    public void getInfo(){
        StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson=new Gson();                        //變數可以缺少,不一定都要有
                Type arrayAnimal=new TypeToken<List<Animal>>(){}.getType();
                animalList=gson.fromJson(response,arrayAnimal);

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

    public void setImage(NetworkImageView networkImageView,Animal animal){
        networkImageView.setImageUrl(animal.getAlbum_file(),imageLoader);
    }

    public void opneFullInfo(int position){
        Message message=new Message();
        message.what=2;
        message.arg1=position;
        handler.sendMessage(message);

    }
}
