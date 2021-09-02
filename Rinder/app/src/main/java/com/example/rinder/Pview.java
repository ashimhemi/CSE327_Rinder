package com.example.rinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Pview extends AppCompatActivity {

    private String mpostkey=null;
    private String mname=null;
    private String mgender=null;
    private String msub=null;

    private TextView textView1,textView2,textView3;
    private Button button;
    private boolean isPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pview);


        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.bc));
        }//status bar or the time bar at the top

        //hooks
        textView1=(TextView)findViewById(R.id.namiset);
        textView2=(TextView)findViewById(R.id.genderset);
        textView3=(TextView)findViewById(R.id.subset);
        button=(Button)findViewById(R.id.connectbtn);

        //here is my profile uid for current user
       // final String Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mpostkey=getIntent().getExtras().getString("Package_id");
       // Toast.makeText(getApplicationContext(),mpostkey,Toast.LENGTH_SHORT).show();

        mname=getIntent().getExtras().getString("name");
       // Toast.makeText(getApplicationContext(),mname,Toast.LENGTH_SHORT).show();
        mgender=getIntent().getExtras().getString("gender");
        msub=getIntent().getExtras().getString("subject");
        textView1.setText(mname);
        textView2.setText(mgender);
        textView3.setText(msub);
        button.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                final String rname=mname;
                final String rgender=mgender;
                final String rsub =msub;
                final String key=mpostkey;
                Request request = new Request(key,rname,rgender,rsub);
                FirebaseDatabase.getInstance().getReference("request")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(mpostkey).setValue(request);

                Toast.makeText(getApplicationContext(),"Request Sent!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),findstudyp.class));
                finish();

            }
        });

    }
}