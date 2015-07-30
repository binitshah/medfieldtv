package com.binitshah.medfieldtv;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class CustomListAdapterVid extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Show> showItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapterVid(Activity activity, List<Show> tshowItems) {
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
    public View getView(int position, View convertView1, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView1 == null)
            convertView1 = inflater.inflate(R.layout.vidlist_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView thumbNail = (NetworkImageView) convertView1
                .findViewById(R.id.vidthumbnail);
        TextView title = (TextView) convertView1.findViewById(R.id.vidtitle);
        TextView pubTime = (TextView) convertView1.findViewById(R.id.vidpublished);
        TextView descriptionofshow = (TextView) convertView1.findViewById(R.id.viddescriptionofshows);

        // getting movie data for the row
        Show s = showItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(s.getVideoThumbnailURL(), imageLoader);

        // title
        title.setText(s.getVideoTitle());


        // times
        pubTime.setText(s.getPublishedAtTime());


        // descriptions
        descriptionofshow.setText(s.getVideoDescription());

        return convertView1;
    }

}