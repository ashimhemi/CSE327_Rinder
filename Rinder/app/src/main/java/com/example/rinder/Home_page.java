package com.example.rinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.nio.channels.AlreadyBoundException;

public class Home_page extends AppCompatActivity implements View.OnClickListener {


    private CardView profile,studyPartner,shop,joinTutor,findTutor,support,aboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.black2));
        }//status bar or the time bar at the top

        profile=(CardView) findViewById(R.id.profile);
        studyPartner=(CardView)findViewById(R.id.studyPartner);
        shop=(CardView) findViewById(R.id.shop);
        joinTutor=(CardView)findViewById(R.id.joinTutor);
        findTutor=(CardView)findViewById(R.id.findTutor);
        support=(CardView)findViewById(R.id.support);
        aboutUs=(CardView)findViewById(R.id.aboutUs);

        profile.setOnClickListener(this);
        studyPartner.setOnClickListener(this);
        shop.setOnClickListener(this);
        joinTutor.setOnClickListener(this);
        findTutor.setOnClickListener(this);
        support.setOnClickListener(this);
        aboutUs.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent i;
        switch (v.getId())
        {
            case R.id.profile:
                i = new Intent(this,Profile.class);
                startActivity(i);
                break;
            case R.id.studyPartner:
                i = new Intent(this,Studypartner.class);
                startActivity(i);
                break;
            case R.id.shop:
                i = new Intent(this,Store.class);
                startActivity(i);
                break;

            case R.id.joinTutor:
                i = new Intent(this,JoinTutor.class);
                startActivity(i);
                break;
            case R.id.findTutor:
                i = new Intent(this,FindTutor.class);
                startActivity(i);
                break;

            case R.id.aboutUs:
                i = new Intent(this,AboutUs.class);
                startActivity(i);
                break;
        }

    }
}