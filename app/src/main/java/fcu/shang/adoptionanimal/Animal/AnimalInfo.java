package fcu.shang.adoptionanimal.Animal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
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

    public void getInfo(){                      //解析資料
        StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson=new Gson();                        //變數可以缺少,不一定都要有
                Type arrayAnimal=new TypeToken<List<Animal>>(){}.getType();
                animalList=gson.fromJson(response,arrayAnimal);
                for(int i=0;i<animalList.size();i++)
                    animalList.get(i).setTag(i);

                Message message=new Message();
                message.what=1;
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

    public void setImage(NetworkImageView networkImageView,Animal animal){   //設置圖片模式的圖片
        networkImageView.setImageUrl(animal.getAlbum_file(),imageLoader);
    }

    public void opneFullInfo(int position){            //切換到FULL INFO
        Message message=new Message();
        message.what=2;
        message.arg1=position;
        handler.sendMessage(message);
    }

    public ArrayList<Animal> pickAnimal(String adoptionPick,String dogcatPick){          //Spinner篩選功能
        if(adoptionPick.equals("全部") && dogcatPick.equals("全部")){
            return animalList;
        }else if(adoptionPick.equals("全部") && !dogcatPick.equals("全部")){
            return search("",dogcatPick);
        }else if(!adoptionPick.equals("全部") && dogcatPick.equals("全部")){
            return search(adoptionPick,"");
        }else{
            return search(adoptionPick,dogcatPick);
        }
    }

    private ArrayList<Animal> search(String adoptionPick,String dogcatPick){
        ArrayList<Animal> list=new ArrayList<>();
        if(adoptionPick.equals("")){                        //全部收容所   挑貓狗
            for(int i=0;i<animalList.size();i++){
                if(animalList.get(i).getAnimal_kind().equals(dogcatPick))
                    list.add(animalList.get(i));
            }
        }else if(dogcatPick.equals("")){                      //全部貓狗   挑收容所
            for(int i=0;i<animalList.size();i++){
                if(animalList.get(i).getShelter_name().equals(adoptionPick))
                    list.add(animalList.get(i));
            }
        }else{
            for(int i=0;i<animalList.size();i++){             //兩個皆有篩選
                if(animalList.get(i).getAnimal_kind().equals(dogcatPick)
                        && animalList.get(i).getShelter_name().equals(adoptionPick))
                    list.add(animalList.get(i));
            }
        }
        return list;
    }

    public void sharePhoto(Animal animal){
        DownloadImgTask downloadImgTask=new DownloadImgTask();
        downloadImgTask.execute(animal.getAlbum_file());
    }


    class DownloadImgTask extends AsyncTask<String,Void,Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {

            String url=params[0];
            Bitmap bitmap=null;
            try{
                InputStream inputStream=new URL(url).openStream();
                bitmap= BitmapFactory.decodeStream(inputStream);
            }catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            Message message=new Message();
            message.what=3;
            message.obj=bitmap;
            handler.sendMessage(message);
        }
    }

}

