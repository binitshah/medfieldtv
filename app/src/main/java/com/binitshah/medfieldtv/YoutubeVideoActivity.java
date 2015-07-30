package com.binitshah.medfieldtv;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    static private final String DEVELOPER_KEY = "AIzaSyCI_3M_dseMFegeWmxUEILNok6fWD5SBU8";
    YouTubePlayerView youTubeView;
    static private String VIDEO = "4SK0cUNMnMM";
    String titleHolder;
    String descriptionHolder;
    String pubTimesHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_video);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                titleHolder = null;
                descriptionHolder = null;
                pubTimesHolder = null;
            } else {
                titleHolder = extras.getString("title");
                pubTimesHolder = extras.getString("times");
                descriptionHolder = extras.getString("descriptions");
                VIDEO = extras.getString("id");
            }
        } else {
            titleHolder = savedInstanceState.getString("title");
            pubTimesHolder = savedInstanceState.getString("times");
            descriptionHolder = savedInstanceState.getString("descriptions");
            VIDEO = savedInstanceState.getString("id");
        }

        youTubeView = (YouTubePlayerView)
                findViewById(R.id.youtube_view);
        youTubeView.initialize(DEVELOPER_KEY, this);

        TextView title = (TextView) findViewById(R.id.vidTitle);
        TextView publishedTime = (TextView) findViewById(R.id.vidDate);
        TextView description = (TextView) findViewById(R.id.vidDescription);

        title.setText(titleHolder);
        publishedTime.setText(pubTimesHolder);
        description.setText(descriptionHolder);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error) {
        Toast.makeText(this, "Oh no! " + error.toString(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        player.loadVideo(VIDEO);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_youtube_video, menu);
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
