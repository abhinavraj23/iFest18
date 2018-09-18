package com.ieee.daiict.ifest18;


import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ContactFragment extends Fragment {

    List<String[]> names;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        names = new ArrayList<String[]>();
        names.add(new String[]{"Sanjoli Singh Chauhan","PR & Marketting","+91 7600816346"});
        names.add(new String[]{"Jainil Pariya","PR & Marketting","+91 9558021183"});
        names.add(new String[]{"Darshak Patel","PR & Marketting","+91 9904939083"});
        names.add(new String[]{"Vishvesh Panchal","Sponsorship","+91 9429023152"});
        names.add(new String[]{"Saumya Aghera","Sponsorship","+91 9428356244"});
        names.add(new String[]{"Abhi Ratnaman","Sponsorship","+91 7574842044"});
        names.add(new String[]{"Shail Kothari","Design","+91 7990291975"});
        names.add(new String[]{"Yashvi Shah","Design","+91 9725035934"});
        names.add(new String[]{"Shivani Bhandari","Production","+91 7016101851"});
        names.add(new String[]{"Rahil Patel","Production","+91 9375962477"});

        ContactAdapter adapter = new ContactAdapter(this.getActivity(), names);

        ListView listView = (ListView) getView().findViewById(R.id.contactList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+names.get(position)[2]));
                startActivity(i);
            }
        });
    }
}
