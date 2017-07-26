package fcu.shang.adoptionanimal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.TextView;

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

    public MyFullIfoAdapter(ArrayList<Animal> animalList,AnimalInfo animalInfo) {
        this.animalList=animalList;
        this.animalInfo=animalInfo;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageButton full_img,full_facebook;
        public TextView id,place,kind,sex,bodytype,colour,age,sterilization,bacterin,foundplace,status,remark,opendata,closedata,update,createtime,shelterName,cDate,tel;
        public NetworkImageView networkImageView;

        public ViewHolder(View v) {
            super(v);
            full_img=(ImageButton)v.findViewById(R.id.full_track);
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
    public void onBindViewHolder(MyFullIfoAdapter.ViewHolder holder, int position) {
        animalInfo.setImage(holder.networkImageView,animalList.get(position));
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
