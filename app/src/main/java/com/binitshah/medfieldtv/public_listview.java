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

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class public_listview extends Fragment {

    private static final String TAG = public_listview.class.getSimpleName();

    private static final String url = "http://binitshah.com/public_list.json";
    private static final String urlold = "http://binitshah.com/public_list_old.json";
    private ProgressDialog pDialog;
    private List<Show> showList = new ArrayList<Show>();
    private ListView listView;
    private TextView textView;
    private CustomListAdapter adapter;
    SharedPreferences sharedPref;
    String provider;

    public public_listview() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_public_listview, container, false);

        listView = (ListView) v.findViewById(R.id.plist);
        adapter = new CustomListAdapter(getActivity(), showList);
        listView.setAdapter(adapter);

        textView = (TextView) v.findViewById(R.id.channelnumber);
        setChannelNum();

        if(showList.size() < 9) {
            pDialog = new ProgressDialog(getActivity());
            // Showing progress dialog before making http request
            pDialog.setMessage("Loading...");
            pDialog.show();

            // Creating volley request obj
            JsonArrayRequest showReq = new JsonArrayRequest(urlold,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d(TAG, response.toString());
                            hidePDialog();

                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);
                                    Show show = new Show();
                                    show.setTitle(obj.getString("title"));
                                    show.setThumbnailUrl(obj.getString("image"));
                                    show.setDuration(obj.getString("duration"));
                                    show.setDescription(obj.getString("description"));

                                    // Genre is json array
                                    JSONArray genreArry = obj.getJSONArray("genre");
                                    ArrayList<String> genre = new ArrayList<String>();
                                    for (int j = 0; j < genreArry.length(); j++) {
                                        genre.add((String) genreArry.get(j));
                                    }
                                    show.setGenre(genre);

                                    // adding program to shows array
                                    showList.add(show);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            // notifying list adapter about data changes
                            // so that it renders the list view with updated date
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

            JsonArrayRequest showReqnew = new JsonArrayRequest(url,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d(TAG, response.toString());
                            hidePDialog();

                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);
                                    Show show = new Show();
                                    show.setTitle(obj.getString("title"));
                                    show.setThumbnailUrl(obj.getString("image"));
                                    show.setDuration(obj.getString("duration"));
                                    show.setDescription(obj.getString("description"));

                                    // Genre is json array
                                    JSONArray genreArry = obj.getJSONArray("genre");
                                    ArrayList<String> genre = new ArrayList<String>();
                                    for (int j = 0; j < genreArry.length(); j++) {
                                        genre.add((String) genreArry.get(j));
                                    }
                                    show.setGenre(genre);

                                    // adding program to shows array
                                    showList.add(show);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            // notifying list adapter about data changes
                            // so that it renders the list view with updated data
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
            listView.setSelection(2);
            AppController.getInstance().addToRequestQueue(showReqnew);
        }

        /*for(int i = 0; i < showList.size(); i++){
            Show temps = showList.get(i);
            String time = temps.getTimes();
            String[] tokens = time.split(" ");
            SimpleDateFormat parser = new SimpleDateFormat("hh:mm a");
            try{
                Date startTime = parser.parse(tokens[0]);
                Date endTime = parser.parse(tokens[2]);
                Date now = new Date();
                String noww = parser.format(now);
                Date realnow = parser.parse(noww);

                if(realnow.after(startTime) && realnow.before(endTime)){
                    listView.setSelection(i);
                }
            }
            catch(ParseException e){
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Error" + e, Toast.LENGTH_LONG);
                toast.show();
            }
        }*/

        return v;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    public void setChannelNum(){
        sharedPref = getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        provider = sharedPref.getString("networkProvider", "Neither");

        if(provider.equals("Comcast")){
            textView.setText("Channel 8");
        }
        else if(provider.equals("Verizon")){
            textView.setText("Channel 47");
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
