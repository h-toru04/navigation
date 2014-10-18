package jp.co.ly.navigation.adapter;

import java.util.List;

import jp.co.ly.navigation.entity.Destination;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class DestinationAdapter extends ArrayAdapter<Destination> {

    /** リソースID */
    private int mResourceId;
    
    public DestinationAdapter(Context context, int resource, List<Destination> objects) {
        super(context, resource, objects);
        mResourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        View view = convertView;
        Holder holder = new Holder();
        
        final Destination destination = (Destination) getItem(position);
        
        if (view != null) {
            holder = (Holder) view.getTag();
        } else {
            view = LayoutInflater.from(this.getContext()).inflate(mResourceId, null);
            holder.nameText = (TextView) view.findViewById(android.R.id.text1);
            view.setTag(holder);
        }
        
        holder.nameText.setText(destination.getName());
        
        
        return view;
    }
    
    private class Holder {
        TextView nameText;
    }
    

}
