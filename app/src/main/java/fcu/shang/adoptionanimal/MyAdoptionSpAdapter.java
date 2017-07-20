package fcu.shang.adoptionanimal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by SERS on 2017/7/20.
 */

public class MyAdoptionSpAdapter extends BaseAdapter{
    private String[] shelterName;
    private Context context;
    LayoutInflater layoutInflater;

    public MyAdoptionSpAdapter(Context context,String[] shelterName) {
        this.context=context;
        this.shelterName = shelterName;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return shelterName.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=layoutInflater.inflate(R.layout.adoptionsp_layout,null);
        TextView adoptionTv=(TextView)convertView.findViewById(R.id.adoptionTv);
        adoptionTv.setText(shelterName[position]);
        return convertView;
    }

}
