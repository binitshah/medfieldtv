package com.binitshah.medfieldtv;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Show> showItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<Show> tshowItems) {
        this.activity = activity;
        this.showItems = tshowItems;
    }

    @Override
    public int getCount() {
        return showItems.size();
    }

    @Override
    public Object getItem(int location) {
        return showItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView timing = (TextView) convertView.findViewById(R.id.timeduration);
        TextView descriptionofshow = (TextView) convertView.findViewById(R.id.descriptionofshows);

        // getting movie data for the row
        Show s = showItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(s.getThumbnailUrl(), imageLoader);

        // title
        title.setText(s.getTitle());

        // times
        timing.setText(s.getTimes());

        // descriptions
        descriptionofshow.setText(s.getDescription());

        if(s.isCurrentShow()){
            convertView.setBackgroundColor(Color.parseColor("#f1c40f"));
        }
        else{
            convertView.setBackgroundColor(0x00000000);
        }

        return convertView;
    }

}