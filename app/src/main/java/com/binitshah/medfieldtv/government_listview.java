package com.binitshah.medfieldtv;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class government_listview extends Fragment {

    //the tag for all error logs
    private static final String TAG = government_listview.class.getSimpleName();

    //url of the current jsonstring
    private ProgressDialog pDialog;
    private List<Show> showList = new ArrayList<Show>();
    private ListView listView;
    private TextView textView;
    private CustomListAdapter adapter;
    SharedPreferences sharedPref;
    String provider;
    boolean downfirst = false;
    int current = 1;
    int howManyTimesRun = 0;

    public government_listview() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_government_listview, container, false);

        listView = (ListView) v.findViewById(R.id.glist);
        adapter = new CustomListAdapter(getActivity(), showList);
        listView.setAdapter(adapter);

        textView = (TextView) v.findViewById(R.id.channelnumber2);
        setChannelNum();

        if(showList.size() < 9) {
            callJson(urlChanger(), true);
        }

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount){
                if(firstVisibleItem == 1){
                    downfirst = true;
                }

                if(firstVisibleItem == 0 && downfirst){
                    callJson(urlChanger(), false);
                    downfirst = false;
                }
            }
        });

        return v;
    }


    public void callJson(String url, final Boolean firstTime){
        if(firstTime) {
            pDialog = new ProgressDialog(getActivity());
            // Showing progress dialog before making http request
            pDialog.setMessage("Loading...");
            pDialog.show();
        }

        // Creating volley request obj
        JsonArrayRequest showReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        if(firstTime) {
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);
                                    Show show = new Show();
                                    show.setTitle(obj.getString("title"));
                                    show.setThumbnailUrl(obj.getString("image"));
                                    show.setStartandEndTime(obj.getString("starttime"), obj.getString("endtime"));
                                    show.setDescription(obj.getString("description"));

                                    // Genre is json array
                                    JSONArray genreArry = obj.getJSONArray("genre");
                                    ArrayList<String> genre = new ArrayList<String>();
                                    for (int j = 0; j < genreArry.length(); j++) {
                                        genre.add((String) genreArry.get(j));
                                    }
                                    show.setGenre(genre);

                                    // adding program to shows array

                                    if(show.isCurrentShow()){
                                        current = i;
                                    }
                                    showList.add(show);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            adapter.notifyDataSetChanged();
                            listView.setSelection(current);
                        }
                        else{
                            int index = listView.getFirstVisiblePosition() + response.length();
                            View v = listView.getChildAt(0);
                            int top = (v == null) ? 0 : (v.getTop() - listView.getPaddingTop());

                            for (int i = response.length(); i >= 0; i--) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);
                                    Show show = new Show();
                                    show.setTitle(obj.getString("title"));
                                    show.setThumbnailUrl(obj.getString("image"));
                                    show.setStartandEndTime(obj.getString("starttime"), obj.getString("endtime"));
                                    show.setDescription(obj.getString("description"));

                                    // Genre is json array
                                    JSONArray genreArry = obj.getJSONArray("genre");
                                    ArrayList<String> genre = new ArrayList<String>();
                                    for (int j = 0; j < genreArry.length(); j++) {
                                        genre.add((String) genreArry.get(j));
                                    }
                                    show.setGenre(genre);

                                    // adding program to shows array
                                    showList.add(0, show);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            // notifying list adapter about data changes
                            // so that it renders the list view with updated data
                            adapter.notifyDataSetChanged();
                            listView.setSelectionFromTop(index, top);
                        }
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
        AppController.getInstance().addToRequestQueue(showReq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    public String urlChanger(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1 * howManyTimesRun);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String url = "http://binitshah.com/a/" + format.format(c.getTime()) + ".json";
        howManyTimesRun++;
        Log.e(TAG, url);
        return url;
    }

    public void setChannelNum(){
        sharedPref = getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        provider = sharedPref.getString("networkProvider", "Neither");

        if(provider.equals("Comcast")){
            textView.setText("Channel 22");
        }
        else if(provider.equals("Verizon")){
            textView.setText("Channel 45");
        }
        else{
            textView.setVisibility(TextView.GONE);
        }
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

}
