package fcu.shang.adoptionanimal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

/**
 * Created by SERS on 2017/7/17.
 */

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{

    private ArrayList<Animal> animalList;
    private AnimalInfo animalInfo;
    
    String sex_male="♂",female="♀";

    public MyListAdapter(AnimalInfo animalInfo){
        this.animalInfo=animalInfo;
        animalList=animalInfo.getAnimalList();

    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView listBody,listSex;
        public ImageView listImg;

        public ViewHolder(View v) {
            super(v);
            listBody=(TextView)v.findViewById(R.id.listBody);
            listSex=(TextView)v.findViewById(R.id.listSex);
            listImg=(ImageView)v.findViewById(R.id.listImg);
        }
    }

    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
