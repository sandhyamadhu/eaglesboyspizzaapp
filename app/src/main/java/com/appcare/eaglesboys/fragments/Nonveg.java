package com.appcare.eaglesboys.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.appcare.eaglesboys.R;


public class Nonveg extends Fragment {
ListView listview;
    public Nonveg() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nonveg, container, false);
        ListView listview =(ListView)view.findViewById(R.id.list);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getActivity(), R.layout.nonveglist);
        listview.setAdapter(adapter);
        return view;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//            inflater.inflate(R.menu.menu_chat_fragment, menu);
//            super.onCreateOptionsMenu(menu, inflater);
//    }

}
