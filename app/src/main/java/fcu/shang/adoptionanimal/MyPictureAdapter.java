package fcu.shang.adoptionanimal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import fcu.shang.adoptionanimal.Animal.Animal;
import fcu.shang.adoptionanimal.Animal.AnimalInfo;

/**
 * Created by SERS on 2017/7/14.
 */

public class MyPictureAdapter extends RecyclerView.Adapter<MyPictureAdapter.ViewHolder>{

    private ArrayList<Animal> animalList;
    private AnimalInfo animalInfo;


    public MyPictureAdapter(AnimalInfo animalInfo,ArrayList<Animal> animalList) {       //之後應該會有其他建構直,用在篩選過後的
        this.animalInfo=animalInfo;
        this.animalList=animalList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public NetworkImageView mNetworkImageView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView)v.findViewById(R.id.info_text);

            mNetworkImageView=(NetworkImageView)v.findViewById(R.id.img);
            mNetworkImageView.setDefaultImageResId(R.drawable.animal);
            mNetworkImageView.setErrorImageResId(R.drawable.failed_image);
        }
    }

    @Override
    public MyPictureAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyPictureAdapter.ViewHolder holder, int position) {
        holder.mTextView.setText(animalList.get(position).getAnimal_kind());
        //holder.mNetworkImageView.setImageUrl(animalList.get(position).getAlbum_file(),imageLoader);
        animalInfo.setImage(holder.mNetworkImageView,animalList.get(position));

        //Log.d("mNetworkImageView",holder.mNetworkImageView.getHeight()+" "+holder.mNetworkImageView.getWidth());
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

}
