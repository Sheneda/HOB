package com.example.hob;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    //Initialize variables
    ImageButton btnLocation, btnAbout, btnShop, btnBible, btnOffering, btnAnnouncements;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment


        btnLocation = myView.findViewById(R.id.id_main_location);
        btnAbout = myView.findViewById(R.id.id_main_aboutUs);
        btnShop = myView.findViewById(R.id.id_main_shop);
        btnBible = myView.findViewById(R.id.id_main_bible);
        btnAnnouncements = myView.findViewById(R.id.id_main_announcements);
        btnOffering = myView.findViewById(R.id.id_main_offering);

        btnAbout.setOnClickListener(this);
        btnLocation.setOnClickListener(this);
        btnShop.setOnClickListener(this);
        btnBible.setOnClickListener(this);
        btnAnnouncements.setOnClickListener(this);
        btnOffering.setOnClickListener(this);


        return myView;
    }

    @Override
    public void onClick(View v) {
        //TODO: onClick listeners for the buttons in home.
        switch (v.getId()){
            case (R.id.id_main_location):
                Toast.makeText(getActivity(),"You Clicked location ", Toast.LENGTH_SHORT).show();
                LocationFragment locationFragment = new LocationFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.id_frame_layout,locationFragment,locationFragment.getTag()).commit();
                break;
            case (R.id.id_main_aboutUs):
                Toast.makeText(getActivity(),"You Clicked about ", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.id_main_offering):
                Toast.makeText(getActivity(),"Join", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                 startActivity(intent);
            default:
                break;
        }

    }

}