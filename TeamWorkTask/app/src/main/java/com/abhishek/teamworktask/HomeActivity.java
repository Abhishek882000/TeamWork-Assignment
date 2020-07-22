package com.abhishek.teamworktask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        toolbar = findViewById(R.id.myToolBar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.myViewPager);

        //To set the toolbar
       //setSupportActionBar(toolbar);

        setUpViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);


    }

    private void setUpViewPager(ViewPager viewPager){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ContactUs_Fragment(),"Contact Us");
        viewPagerAdapter.addFragment(new Images_Fragment(),"Images");
        viewPagerAdapter.addFragment(new ViewImages_Fragment(),"View Images");
        viewPager.setAdapter(viewPagerAdapter);
    }
}
