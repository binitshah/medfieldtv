package com.binitshah.medfieldtv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class tvguide extends Fragment {
    private FragmentTabHost mTabHost;

    public tvguide() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.tvguidecontent);


        mTabHost.addTab(mTabHost.newTabSpec("public").setIndicator("Public"),
                public_listview.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("education").setIndicator("Education"),
                education_listview.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("government").setIndicator("Government"),
                government_listview.class, null);

        return mTabHost;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }
}
