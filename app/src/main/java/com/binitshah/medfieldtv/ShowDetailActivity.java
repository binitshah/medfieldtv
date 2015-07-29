package com.binitshah.medfieldtv;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
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

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                titleHolder = null;
                descriptionHolder = null;
                timesHolder = null;
                thumbnailUrlHolder = null;
                genresHolder = null;
            } else {
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

        float[] radii = new float[8];
        for(int i = 0; i < 8; i++) {
            radii[i] = 10;
        }

        if (genresHolder.size() > 0){
            TextView genre1 = (TextView) findViewById(R.id.tag1);
            genre1.setText(genresHolder.get(0));
            ShapeDrawable tagBackground1 = new ShapeDrawable();
            tagBackground1.setShape(new RoundRectShape(radii,null,null));
            String tempColorHolder = genreColor(genresHolder.get(0));
            tagBackground1.getPaint().setColor(Color.parseColor(tempColorHolder));
            tagBackground1.setPadding(5, 5, 5, 5);
            genre1.setBackgroundDrawable(tagBackground1);
        }
        else{
            TextView genre1 = (TextView) findViewById(R.id.tag1);
            genre1.setVisibility(TextView.GONE);
        }

        if(genresHolder.size() > 1) {
            TextView genre2 = (TextView) findViewById(R.id.tag2);
            genre2.setText(genresHolder.get(1));
            ShapeDrawable tagBackground2 = new ShapeDrawable();
            tagBackground2.setShape(new RoundRectShape(radii, null, null));
            String tempColorHolder = genreColor(genresHolder.get(1));
            tagBackground2.getPaint().setColor(Color.parseColor(tempColorHolder));
            tagBackground2.setPadding(5, 5, 5, 5);
            genre2.setBackgroundDrawable(tagBackground2);
        }
        else{
            TextView genre2 = (TextView) findViewById(R.id.tag2);
            genre2.setVisibility(TextView.GONE);
        }

        if(genresHolder.size() > 2) {
            TextView genre3 = (TextView) findViewById(R.id.tag3);
            genre3.setText(genresHolder.get(2));
            ShapeDrawable tagBackground3 = new ShapeDrawable();
            tagBackground3.setShape(new RoundRectShape(radii,null,null));
            String tempColorHolder = genreColor(genresHolder.get(2));
            tagBackground3.getPaint().setColor(Color.parseColor(tempColorHolder));
            tagBackground3.setPadding(5, 5, 5, 5);
            genre3.setBackgroundDrawable(tagBackground3);
        }
        else{
            TextView genre3 = (TextView) findViewById(R.id.tag3);
            genre3.setVisibility(TextView.GONE);
        }

        changeActionBarColor();

        thumbNail.setImageUrl(thumbnailUrlHolder, imageLoader);
        title.setText(titleHolder);
        setTitle(titleHolder);
        timing.setText(timesHolder);
        descriptionofshow.setText(descriptionHolder);
    }

    @TargetApi(11)
    public void changeActionBarColor(){
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
            return "#000000";
        }
    }

    public String genreColor(String genre){
        if(genre.equals("Crime")){
            return "#1abc9c";
        }
        else if(genre.equals("Drama")){
            return "#16a085";
        }
        else if(genre.equals("Action")){
            return "#2ecc71";
        }
        else if(genre.equals("Crime")){
            return "#27ae60";
        }
        else if(genre.equals("Biography")){
            return "#3498db";
        }
        else if(genre.equals("History")){
            return "#2980b9";
        }
        else if(genre.equals("Western")){
            return "#9b59b6";
        }
        else if(genre.equals("Adventure")){
            return "#8e44ad";
        }
        else if(genre.equals("Fantasy")){
            return "#34495e";
        }
        else if(genre.equals("Romance")){
            return "#2c3e50";
        }
        else if(genre.equals("Mystery")){
            return "#f1c40f";
        }
        else if(genre.equals("Sci-Fi")){
            return "#f39c12";
        }
        else if(genre.equals("War")){
            return "#d35400";
        }
        else if(genre.equals("Animation")){
            return "#e67e22";
        }
        else if(genre.equals("Thiller")){
            return "#e74c3c";
        }
        else if(genre.equals("Family")){
            return "#c0392b";
        }
        else if(genre.equals("Comedy")){
            return "#7f8c8d";
        }
        else if(genre.equals("Film-Noir")){
            return "#bdc3c7";
        }
        else{
            return "#000000";
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
