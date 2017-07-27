package fcu.shang.adoptionanimal;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import fcu.shang.adoptionanimal.Animal.Animal;
import fcu.shang.adoptionanimal.Animal.AnimalInfo;

/**
 * Created by Shang on 2017/7/27.
 */

public class MyTrackAdapter extends RecyclerView.Adapter<MyTrackAdapter.ViewHolder> implements View.OnClickListener{


    private ArrayList<Animal> animalList;
    private AnimalInfo animalInfo;
    private SharedPreferences sp;


    public MyTrackAdapter(AnimalInfo animalInfo, ArrayList<Animal> animalList,SharedPreferences sp) {
        this.animalInfo=animalInfo;
        this.animalList=animalList;
        this.sp=sp;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
            TextView track_id=(TextView)itemView.findViewById(R.id.track_id);
            TextView track_body=(TextView)itemView.findViewById(R.id.track_body);
            TextView track_shelter=(TextView)itemView.findViewById(R.id.track_shelter);
            NetworkImageView track_img=(NetworkImageView)itemView.findViewById(R.id.track_img);
            track_img.setErrorImageResId(R.drawable.failed_image);
        }
    }

    @Override
    public MyTrackAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyctrack_layout,parent,false);
        v.setOnClickListener(this);
        MyTrackAdapter.ViewHolder viewHolder=new MyTrackAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyTrackAdapter.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    @Override
    public void onClick(View v) {

    }
}
