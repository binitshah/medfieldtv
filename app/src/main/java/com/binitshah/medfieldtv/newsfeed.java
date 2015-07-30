package com.binitshah.medfieldtv;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class newsfeed extends Fragment {


    public newsfeed() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Intent trialDetail = new Intent(getActivity(), ShowDetailActivity.class);
        startActivity(trialDetail);

        return inflater.inflate(R.layout.fragment_newsfeed, container, false);
    }


}
