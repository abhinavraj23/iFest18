package com.ieee.daiict.ifest18;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DayFragment extends Fragment {

    String date;
    FragmentManager fragmentManager;

    public DayFragment(){
    }

    public DayFragment(int day, FragmentManager fragmentManager) {
        if(day==0)
            date = "else";
        else if(day==1)
            date = "05-10-18";
        else if(day==2)
            date = "06-10-18";
        else
            date = "07-10-18";
        this.fragmentManager = fragmentManager;
    }

    List<Event> events;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_day, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        events = new ArrayList<Event>();
        ArrayList<Event> temp = Data.getEventList();
        if(date.equals("else")) {
            for (int i = 0; i < temp.size(); i++) {
                if (!(temp.get(i).getDate().equals("05-10-18") || temp.get(i).getDate().equals("06-10-18") || temp.get(i).getDate().equals("07-10-18")))
                    events.add(temp.get(i));
            }
            ArrayList<Event> temp2 = new ArrayList<Event>();
            for (int i = 0; i < events.size(); i++) {
                if (events.get(i).getDate().equals("18-09-18")) {
                    temp2.add(events.get(i));
                    break;
                }
            }
            for (int i = 0; i < events.size(); i++) {
                if (events.get(i).getDate().equals("03-10-18")) {
                    temp2.add(events.get(i));
                    break;
                }
            }
            temp = new ArrayList<Event>(temp2);
            temp2 = new ArrayList<Event>();
            for(int i=0;i<events.size();i++) {
                if (events.get(i).getDate().equals("25-10-17")) {
                    temp2.add(events.get(i));
                }
            }
            Collections.sort(temp2);
            temp.addAll(temp2);
            events = temp;
        }
        else {
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).getDate().equals(date))
                    events.add(temp.get(i));
            }
            Collections.sort(events);
        }
        EventAdapter adapter = new EventAdapter(this.getActivity(), events);

        ListView listView = (ListView) getView().findViewById(R.id.eventList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = events.get(position).getName();
                if(name.contains("."))
                    name = name.split(" ")[0];
                else
                    name = name.replace(" ","");
                Fragment fragment=new SingleEventFragment(name);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
                transaction.addToBackStack(null);  // this will manage backstack
                transaction.commit();
            }
        });
    }
}
