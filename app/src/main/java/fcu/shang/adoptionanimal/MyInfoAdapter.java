package fcu.shang.adoptionanimal;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

/**
 * Created by SERS on 2017/7/14.
 */

public class MyInfoAdapter extends RecyclerView.Adapter<MyInfoAdapter.ViewHolder>{

    private ArrayList<Animal> animalList;
    private Context context;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private AnimalInfo animalInfo;


    public MyInfoAdapter(Context context,AnimalInfo animalInfo) {
        //this.animalList=animalList;
        this.context=context;
        //requestQueue= Volley.newRequestQueue(context);
        //imageLoader=new ImageLoader(requestQueue,new BitmapCache());
        this.animalInfo=animalInfo;
        this.animalList=animalInfo.getAnimalList();
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
    public MyInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyInfoAdapter.ViewHolder holder,int position) {
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
