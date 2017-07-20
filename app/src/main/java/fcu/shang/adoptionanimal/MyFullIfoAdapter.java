package fcu.shang.adoptionanimal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by Shang on 2017/7/20.
 */

public class MyFullIfoAdapter extends RecyclerView.Adapter<MyFullIfoAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View v) {

            super(v);
            TextView id=(TextView)v.findViewById(R.id.full_id);

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

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
