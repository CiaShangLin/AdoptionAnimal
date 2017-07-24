package fcu.shang.adoptionanimal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import fcu.shang.adoptionanimal.Animal.Animal;
import fcu.shang.adoptionanimal.Animal.AnimalInfo;

/**
 * Created by SERS on 2017/7/14.
 */

public class MyPictureAdapter extends RecyclerView.Adapter<MyPictureAdapter.ViewHolder> implements View.OnClickListener{

    private Context context;
    private ArrayList<Animal> animalList;
    private AnimalInfo animalInfo;


    public MyPictureAdapter(Context context,AnimalInfo animalInfo, ArrayList<Animal> animalList) {       //之後應該會有其他建構直,用在篩選過後的
        this.context=context;
        this.animalInfo=animalInfo;
        this.animalList=animalList;

    }


    @Override
    public void onClick(View v) {
        //Toast.makeText(context,v.getTag()+"",Toast.LENGTH_SHORT).show();       //切換成有塞選的　會有問題
        animalInfo.opneFullInfo(animalList.get((int)v.getTag()).getTag());
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView info_sex,info_bodytype;
        public ImageView info_type;
        public NetworkImageView mNetworkImageView;
        public ViewHolder(View v) {
            super(v);
            info_sex = (TextView)v.findViewById(R.id.info_sex);
            info_bodytype= (TextView)v.findViewById(R.id.info_bodytype);
            info_type=(ImageView)v.findViewById(R.id.info_type);

            mNetworkImageView=(NetworkImageView)v.findViewById(R.id.img);
            mNetworkImageView.setDefaultImageResId(R.drawable.animal);
            mNetworkImageView.setErrorImageResId(R.drawable.failed_image);
        }
    }

    @Override
    public MyPictureAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout,parent,false);
        v.setOnClickListener(this);
        ViewHolder viewHolder=new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyPictureAdapter.ViewHolder holder, int position) {
        holder.info_bodytype.setText(animalList.get(position).getAnimal_bodytype());
        if(animalList.get(position).getAnimal_bodytype().equals("")){
            
        }
        if(animalList.get(position).getAnimal_kind().equals("狗")){

        }else{

        }
        holder.info_type.setImageDrawable(context.getResources().getDrawable(R.drawable.cat));
        animalInfo.setImage(holder.mNetworkImageView,animalList.get(position));
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

}
