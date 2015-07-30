package com.binitshah.medfieldtv;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class onlinevideo extends Fragment {

    //the tag for all error logs
    private static final String TAG = onlinevideo.class.getSimpleName();

    private ProgressDialog pDialog;
    private List<Show> showList = new ArrayList<Show>();
    private ListView listView;
    private CustomListAdapterVid adapter;

    public onlinevideo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_onlinevideo, container, false);;


        listView = (ListView) v.findViewById(R.id.onlinelist);
        adapter = new CustomListAdapterVid(getActivity(), showList);
        listView.setAdapter(adapter);

        if(showList.size() < 9) {
            callJson(urlChanger());
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent youtubePlayer = new Intent(getActivity(), YoutubeVideoActivity.class);
                Show sh = showList.get(position);
                youtubePlayer.putExtra("title", sh.getVideoTitle());
                youtubePlayer.putExtra("times", sh.getPublishedAtTime());
                youtubePlayer.putExtra("descriptions", sh.getVideoDescription());
                youtubePlayer.putExtra("id", sh.getVideoID());
                startActivity(youtubePlayer);
            }
        });

        return v;
    }
    public void callJson(String url){
        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // Creating volley request obj
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                hidePDialog();
                JSONArray arr;

                try {
                    arr = response.getJSONArray("items");
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        Show show = new Show();
                        show.setVideoTitle(obj.getJSONObject("snippet").getString("title"));
                        show.setVideoThumbnailURL(obj.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("medium").getString("url"));
                        Log.e(TAG, "" + show.getVideoThumbnailURL());
                        show.setVideoDescription(obj.getJSONObject("snippet").getString("description"));
                        show.setVideoID(obj.getJSONObject("id").getString("videoId"));
                        show.setPublishedAtTime(obj.getJSONObject("snippet").getString("publishedAt"));

                        // adding program to shows array
                        showList.add(show);
                    }
                }
                catch (JSONException e){
                    Log.e(TAG, "onResponse couldn't parse the array item");
                    e.printStackTrace();
                }

                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "There was an error. My apologies.", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    public String urlChanger(){
        String base = "https://www.googleapis.com/youtube/v3/search?";
        String devKey = "AIzaSyCI_3M_dseMFegeWmxUEILNok6fWD5SBU8";
        String channelID = "UChGvbXwZfpeIazcSi1myVsg";
        String order = "date";
        String numResults = "20";

        String finished = base + "key=" + devKey + "&channelId=" + channelID + "&part=snippet,id&order=" + order + "&maxResults=" + numResults;
        Log.e(TAG, "" + finished);
        return finished;
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


}
