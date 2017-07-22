package fcu.shang.adoptionanimal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

import fcu.shang.adoptionanimal.Animal.Animal;

/**
 * Created by SERS on 2017/7/17.
 */

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{

    private ArrayList<Animal> animalList;
    private Context context;

    String sex_male="♂",sex_female="♀";

    public MyListAdapter(Context context,ArrayList<Animal> animalList){
        this.animalList=animalList;
        this.context=context;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView listBody,listSex,listColor,listShelter,listID;
        public ImageView listImg;
        public LinearLayout listLayout;

        public ViewHolder(View v) {
            super(v);
            listBody=(TextView)v.findViewById(R.id.listBody);
            listSex=(TextView)v.findViewById(R.id.listSex);
            listColor=(TextView)v.findViewById(R.id.listColor);
            listImg=(ImageView)v.findViewById(R.id.listImg);
            listShelter=(TextView)v.findViewById(R.id.listShelter);
            listID=(TextView)v.findViewById(R.id.listID);
            listLayout=(LinearLayout)v.findViewById(R.id.listLayout);
        }
    }

    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclist_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyListAdapter.ViewHolder holder, int position) {
        if(animalList.get(position).getAnimal_kind().equals("狗")){
            holder.listImg.setImageDrawable(context.getResources().getDrawable(R.drawable.dog));
        }else{
            holder.listImg.setImageDrawable(context.getResources().getDrawable(R.drawable.cat));
        }

        holder.listBody.setText(animalList.get(position).getAnimal_bodytype());

        if(animalList.get(position).getAnimal_sex().equals("M")){
            holder.listSex.setText(sex_male);
        }else{
            holder.listSex.setText(sex_female);
        }

        holder.listColor.setText(animalList.get(position).getAnimal_colour());

        holder.listID.setText(String.valueOf(position+1)+".");
        holder.listShelter.setText(animalList.get(position).getShelter_name());

        if(position % 2 == 0){
            holder.listLayout.setBackground(context.getResources().getDrawable(R.color.colorOrange));
        }else{
            holder.listLayout.setBackground(context.getResources().getDrawable(R.color.colorWhite));
        }


    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }
}
