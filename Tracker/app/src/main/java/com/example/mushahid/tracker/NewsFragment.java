package com.example.mushahid.tracker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

        ListView myListView;
        List<String> newsList;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
         View fragmentView = inflater.inflate(R.layout.fragment_news, container, false);
         myListView = (ListView) fragmentView.findViewById(R.id.listView);

       newsList = new ArrayList<String>();

        newsList.add("first element");
        newsList.add("second element");
        newsList.add("third element");
        newsList.add("fourth element");

        myListView.setAdapter(new MyAdapter());

        return fragmentView;
    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return newsList.size();
        }

        @Override
        public Object getItem(int position) {
            return newsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
          View rowView = getActivity().getLayoutInflater().inflate(R.layout.row
          ,null);
            TextView txt = (TextView) rowView.findViewById(R.id.a);
            txt.setText(newsList.get(position));
            ImageView img = (ImageView) rowView.findViewById(R.id.img);
            Picasso.with(getActivity()).load("http://i.imgur.com/DvpvklR.png").into(img);

   return rowView;
        }
    }


}
