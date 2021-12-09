package com.example.hob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {

    //Initialize variables

    //ImageButton btnLocation, btnAbout, btnShop, btnBible, btnOffering, btnAnnouncements;
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variables
        bottomNavigation = findViewById(R.id.id_bottom_navigation);

        //Add menu items to bottom bar
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_feed));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_setting));


        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                //Initialize Fragment
                Fragment fragment = null;

                //Check conditions
                switch (item.getId()){
                    case 1:
                        //If item 1 is clicked; Initialize feed fragment
                        fragment = new FeedFragment();
                        break;
                    case 2:
                        //If item 2 is clicked; Initialize home fragment
                        fragment = new HomeFragment();
                        break;
                    case 3:
                        //If item 3 is clicked; Initialize setting fragment
                        fragment = new SettingFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });


        //This is how you show navigation
        bottomNavigation.setCount(1,"10");
        bottomNavigation.show(2,true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(),"You Clicked " + item.getId(), Toast.LENGTH_SHORT).show();

            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(),"You Reselected " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        //Replace fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.id_frame_layout,fragment)
                .commit();
    }
}