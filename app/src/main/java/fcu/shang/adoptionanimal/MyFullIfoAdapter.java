package fcu.shang.adoptionanimal;

import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import fcu.shang.adoptionanimal.Animal.Animal;
import fcu.shang.adoptionanimal.Animal.AnimalInfo;

/**
 * Created by Shang on 2017/7/20.
 */

public class MyFullIfoAdapter extends RecyclerView.Adapter<MyFullIfoAdapter.ViewHolder>{

    private ArrayList<Animal> animalList;
    private AnimalInfo animalInfo;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public MyFullIfoAdapter(ArrayList<Animal> animalList,AnimalInfo animalInfo,SharedPreferences sp) {
        this.animalList=animalList;
        this.animalInfo=animalInfo;
        this.sp=sp;
        editor=sp.edit();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageButton full_track,full_facebook;
        public TextView id,place,kind,sex,bodytype,colour,age,sterilization,bacterin,foundplace,status,remark,opendata,closedata,update,createtime,shelterName,cDate,tel;
        public NetworkImageView networkImageView;

        public ViewHolder(View v) {
            super(v);
            full_track=(ImageButton)v.findViewById(R.id.full_track);
            full_facebook=(ImageButton)v.findViewById(R.id.full_facebook);
            networkImageView=(NetworkImageView)v.findViewById(R.id.fullImg);
            networkImageView.setDefaultImageResId(R.drawable.failed_image);
            id=(TextView)v.findViewById(R.id.full_id);
            place=(TextView)v.findViewById(R.id.full_place);
            kind =(TextView)v.findViewById(R.id.full_kind);
            sex=(TextView)v.findViewById(R.id.full_sex);
            bodytype =(TextView)v.findViewById(R.id.full_bodytype);
            colour =(TextView)v.findViewById(R.id.full_colour);
            age=(TextView)v.findViewById(R.id.full_age);
            sterilization=(TextView)v.findViewById(R.id.full_sterilization);
            bacterin=(TextView)v.findViewById(R.id.full_bacterin);
            foundplace=(TextView)v.findViewById(R.id.full_foundplace);
            status=(TextView)v.findViewById(R.id.full_status);
            remark=(TextView)v.findViewById(R.id.full_remark);
            opendata =(TextView)v.findViewById(R.id.full_opendate);
            closedata=(TextView)v.findViewById(R.id.full_closeddate);
            update=(TextView)v.findViewById(R.id.full_update);
            createtime=(TextView)v.findViewById(R.id.full_createtime);
            shelterName=(TextView)v.findViewById(R.id.full_shelter_name);
            cDate=(TextView)v.findViewById(R.id.full_cDate);
            tel=(TextView)v.findViewById(R.id.full_tel);
        }
    }

    @Override
    public MyFullIfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.fullinfo_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyFullIfoAdapter.ViewHolder holder, final int position) {
        animalInfo.setImage(holder.networkImageView,animalList.get(position));            //network圖片

        holder.full_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=sp.getString(animalList.get(position).getAnimal_id(),"");
                if(id.equals("")){     //還沒有加入追蹤
                    holder.full_track.setImageResource(R.drawable.on_track);
                    editor.putString(animalList.get(position).getAnimal_id(),animalList.get(position).getAnimal_id()).commit();
                    Log.d("Shared_off",id);
                }else{
                    holder.full_track.setImageResource(R.drawable.off_track);

                    editor.remove(id).commit();
                    Log.d("Shared_on",id);
                }

            }
        });

        if(sp.getString(animalList.get(position).getAnimal_id(),"").equals("")){
            holder.full_track.setImageResource(R.drawable.off_track);
        }else{
            holder.full_track.setImageResource(R.drawable.on_track);
        }


        holder.full_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.id.setText(animalList.get(position).getAnimal_id());
        holder.place.setText(animalList.get(position).getAnimal_place());
        holder.kind.setText(animalList.get(position).getAnimal_kind());
        holder.sex.setText(animalList.get(position).getAnimal_sex());
        holder.bodytype.setText(animalList.get(position).getAnimal_bodytype());
        holder.colour.setText(animalList.get(position).getAnimal_colour());
        holder.age.setText(animalList.get(position).getAnimal_age());
        holder.sterilization.setText(animalList.get(position).getAnimal_sterilization());
        holder.bacterin.setText(animalList.get(position).getAnimal_bacterin());
        holder.foundplace.setText(animalList.get(position).getAnimal_foundplace());
        holder.status.setText(animalList.get(position).getAnimal_status());
        holder.remark.setText(animalList.get(position).getAnimal_remark());
        holder.opendata.setText(animalList.get(position).getAnimal_opendate());
        holder.closedata.setText(animalList.get(position).getAnimal_closeddate());
        holder.update.setText(animalList.get(position).getAlbum_update());
        holder.createtime.setText(animalList.get(position).getAnimal_createtime());
        holder.shelterName.setText(animalList.get(position).getShelter_name());
        holder.cDate.setText(animalList.get(position).getcDate());
        holder.tel.setText(animalList.get(position).getShelter_tel());
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }
}
