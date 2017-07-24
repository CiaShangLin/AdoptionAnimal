package fcu.shang.adoptionanimal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

import fcu.shang.adoptionanimal.Animal.Animal;
import fcu.shang.adoptionanimal.Animal.AnimalInfo;

/**
 * Created by SERS on 2017/7/17.
 */

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> implements View.OnClickListener{

    private ArrayList<Animal> animalList;
    private Context context;
    private AnimalInfo animalInfo;

    String sex_male="♂",sex_female="♀";

    public MyListAdapter(Context context,AnimalInfo animalInfo,ArrayList<Animal> animalList){
        this.animalList=animalList;
        this.context=context;
        this.animalInfo=animalInfo;
    }

    @Override
    public void onClick(View v) {
        Log.d("TAG",(int)v.getTag()+"");
        animalInfo.opneFullInfo(animalList.get((int)v.getTag()).getTag());
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
        v.setOnClickListener(this);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyListAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(position);

        if(animalList.get(position).getAnimal_kind().equals("狗")){
            holder.listImg.setImageDrawable(context.getResources().getDrawable(R.drawable.dog));
        }else{
            holder.listImg.setImageDrawable(context.getResources().getDrawable(R.drawable.cat));
        }

        if(animalList.get(position).getAnimal_bodytype().equals("BIG")){
            holder.listBody.setText("大型");
        }else if(animalList.get(position).getAnimal_bodytype().equals("MINI")){
            holder.listBody.setText("迷你");
        }else if (animalList.get(position).getAnimal_bodytype().equals("MEDIUM")){
            holder.listBody.setText("中型");
        }else if(animalList.get(position).getAnimal_bodytype().equals("SMALL")){
            holder.listBody.setText("小型");
        }

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
