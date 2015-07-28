package com.binitshah.medfieldtv;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class intro_5 extends Fragment {

    SharedPreferences sharedPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_intro_5, container, false);

        ImageButton verizonButton = (ImageButton) v.findViewById(R.id.verizonbtn);
        verizonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPrefVerizon();
            }
        });

        ImageButton comcastButton = (ImageButton) v.findViewById(R.id.comcastbtn);
        comcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPrefComcast();
            }
        });

        sharedPref = getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        return v;
    }

    public void setPrefVerizon(){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("networkProvider","Verizon");
        editor.apply();

        getActivity().finish();
    }

    public void setPrefComcast(){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("networkProvider","Comcast");
        editor.apply();

        getActivity().finish();
    }

    public static intro_5 newInstance(String text) {

        intro_5 f = new intro_5();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}