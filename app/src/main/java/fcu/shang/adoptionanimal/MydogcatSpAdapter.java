package fcu.shang.adoptionanimal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fcu.shang.adoptionanimal.Animal.Animal;

/**
 * Created by Shang on 2017/7/19.
 */

public class MydogcatSpAdapter extends BaseAdapter{

    private Context context;
    private String[] animals;
    private LayoutInflater layoutInflater;

    public MydogcatSpAdapter(Context context,String[] animals) {
        this.context=context;
        this.animals=animals;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return animals.length;
    }

    @Override
    public Object getItem(int position) {
        return animals[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=layoutInflater.inflate(R.layout.dogcatsp_layout,null);
        TextView dogcatTv=(TextView)convertView.findViewById(R.id.dogcatTv);
        ImageView dogcatImg=(ImageView)convertView.findViewById(R.id.dogcatImg);

        dogcatTv.setText(animals[position]);
        switch (position){
            case 0:
                dogcatImg.setImageDrawable(context.getResources().getDrawable(R.drawable.all));
                break;
            case 1:
                dogcatImg.setImageDrawable(context.getResources().getDrawable(R.drawable.dog));
                break;
            case 2:
                dogcatImg.setImageDrawable(context.getResources().getDrawable(R.drawable.cat));
                break;
        }



        return convertView;
    }
}
