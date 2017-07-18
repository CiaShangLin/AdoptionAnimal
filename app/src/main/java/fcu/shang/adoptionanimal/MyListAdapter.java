package fcu.shang.adoptionanimal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by SERS on 2017/7/17.
 */

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{

    private ArrayList<Animal> animalList;
    private AnimalInfo animalInfo;
    private Context context;

    String sex_male="♂",sex_female="♀";

    public MyListAdapter(AnimalInfo animalInfo,Context context){
        this.animalInfo=animalInfo;
        animalList=animalInfo.getAnimalList();
        this.context=context;
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


    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }
}
