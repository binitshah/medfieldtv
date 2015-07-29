package com.binitshah.medfieldtv;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.Random;


public class ShowDetailActivity extends ActionBarActivity {

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    String titleHolder;
    String descriptionHolder;
    String timesHolder;
    String thumbnailUrlHolder;
    ArrayList<String> genresHolder;
    android.support.v7.app.ActionBar actionBar;
    String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                titleHolder = null;
                descriptionHolder = null;
                timesHolder = null;
                thumbnailUrlHolder = null;
                genresHolder = null;
            }
            else{
                titleHolder = extras.getString("title");
                thumbnailUrlHolder = extras.getString("thumbnailurl");
                timesHolder = extras.getString("times");
                descriptionHolder = extras.getString("descriptions");
                genresHolder = extras.getStringArrayList("genres");
            }
        } else {
            titleHolder = savedInstanceState.getString("title");
            thumbnailUrlHolder = savedInstanceState.getString("thumbnailurl");
            timesHolder = savedInstanceState.getString("times");
            descriptionHolder = savedInstanceState.getString("descriptions");
            genresHolder = savedInstanceState.getStringArrayList("genres");
        }

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView thumbNail = (NetworkImageView) findViewById(R.id.deetthumbnail);
        TextView title = (TextView) findViewById(R.id.deettitle);
        TextView timing = (TextView) findViewById(R.id.deettimes);
        TextView descriptionofshow = (TextView) findViewById(R.id.deetdescrip);
        color = randomColor();
        FrameLayout layout = (FrameLayout) findViewById(R.id.backgroundofimage);
        layout.setBackgroundColor(Color.parseColor(color));
        TextView genre1 = (TextView) findViewById(R.id.genre1);
        TextView genre2 = (TextView) findViewById(R.id.genre2);
        TextView genre3 = (TextView) findViewById(R.id.genre3);
        ImageView tag3 = (ImageView) findViewById(R.id.tag3);

        changeColor();

        thumbNail.setImageUrl(thumbnailUrlHolder, imageLoader);
        title.setText(titleHolder);
        setTitle(titleHolder);
        timing.setText(timesHolder);
        descriptionofshow.setText(descriptionHolder);

        if(genresHolder.get(0) != null){
            genre1.setText(genresHolder.get(0));
        }
        else{
            genre1.setVisibility(TextView.GONE);
        }

        if(genresHolder.get(1) != null){
            genre2.setText(genresHolder.get(1));
        }
        else{
            genre2.setVisibility(TextView.GONE);
        }

        if(genresHolder.get(genresHolder.size()) != null){
            genre3.setText(genresHolder.get(genresHolder.size()));
        }
        else{
            genre3.setVisibility(TextView.GONE);
            tag3.setVisibility(ImageView.GONE);
        }
    }

    @TargetApi(11)
    public void changeColor(){
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(color)));
    }

    public String randomColor(){
        Random rand = new Random();
        int randnum = rand.nextInt(18);

        if(randnum == 0){
            return "#1abc9c";
        }
        else if(randnum == 1){
            return "#16a085";
        }
        else if(randnum == 2){
            return "#2ecc71";
        }
        else if(randnum == 3){
            return "#27ae60";
        }
        else if(randnum == 4){
            return "#3498db";
        }
        else if(randnum == 5){
            return "#2980b9";
        }
        else if(randnum == 6){
            return "#9b59b6";
        }
        else if(randnum == 7){
            return "#8e44ad";
        }
        else if(randnum == 8){
            return "#34495e";
        }
        else if(randnum == 9){
            return "#2c3e50";
        }
        else if(randnum == 10){
            return "#f1c40f";
        }
        else if(randnum == 11){
            return "#f39c12";
        }
        else if(randnum == 12){
            return "#d35400";
        }
        else if(randnum == 13){
            return "#e67e22";
        }
        else if(randnum == 14){
            return "#e74c3c";
        }
        else if(randnum == 15){
            return "#c0392b";
        }
        else if(randnum == 16){
            return "#7f8c8d";
        }
        else if(randnum == 17){
            return "#bdc3c7";
        }
        else{
            return "#bdc3c7";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
