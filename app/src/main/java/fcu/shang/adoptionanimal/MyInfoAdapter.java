package fcu.shang.adoptionanimal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by SERS on 2017/7/14.
 */

public class MyInfoAdapter extends RecyclerView.Adapter<MyInfoAdapter.ViewHolder>{

    private ArrayList<Animal> animalList;
    private Context context;

    public MyInfoAdapter(ArrayList<Animal> animalList, Context context) {
        this.animalList=animalList;
        this.context=context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ImageView mImageView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView)v.findViewById(R.id.info_text);
            mImageView=(ImageView)v.findViewById(R.id.img);

        }
    }

    @Override
    public MyInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyInfoAdapter.ViewHolder holder, final int position) {
        holder.mTextView.setText(animalList.get(position).getAnimal_kind());
        holder.mImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.animal));

    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

}
