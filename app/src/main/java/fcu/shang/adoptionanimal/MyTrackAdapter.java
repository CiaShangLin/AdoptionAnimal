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
    String sex_male="♂",sex_female="♀";

    public MyTrackAdapter(AnimalInfo animalInfo, ArrayList<Animal> animalList,SharedPreferences sp) {
        this.animalInfo=animalInfo;
        this.animalList=animalList;
        this.sp=sp;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView track_id,track_body,track_shelter;
        NetworkImageView track_img;

        public ViewHolder(View itemView) {
            super(itemView);
            track_id=(TextView)itemView.findViewById(R.id.track_id);
            track_body=(TextView)itemView.findViewById(R.id.track_body);
            track_shelter=(TextView)itemView.findViewById(R.id.track_shelter);
            track_img=(NetworkImageView)itemView.findViewById(R.id.track_img);
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
        holder.track_id.setText(animalList.get(position).getAnimal_id());
        StringBuffer sb=new StringBuffer("");
        if(animalList.get(position).getAnimal_bodytype().equals("BIG")){
            sb.append("大型");
        }else if(animalList.get(position).getAnimal_bodytype().equals("MINI")){
            sb.append("迷你");
        }else if (animalList.get(position).getAnimal_bodytype().equals("MEDIUM")){
            sb.append("中型");
        }else if(animalList.get(position).getAnimal_bodytype().equals("SMALL")){
            sb.append("小型");
        }
        sb.append("   "+animalList.get(position).getAnimal_colour());
        if(animalList.get(position).getAnimal_sex().equals("M")){
            sb.append("   "+sex_male);
        }else{
            sb.append("   "+sex_female);
        }

        holder.track_body.setText(sb.toString());
        holder.track_shelter.setText(animalList.get(position).getShelter_name());
        animalInfo.setImage(holder.track_img,animalList.get(position));

        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    @Override
    public void onClick(View v) {
        animalInfo.opneFullInfo(animalList.get((int)v.getTag()).getTag());
    }
}
