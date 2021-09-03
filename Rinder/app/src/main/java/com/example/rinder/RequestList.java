package com.example.rinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class RequestList extends AppCompatActivity {
    private RecyclerView recyclerView;

    private DatabaseReference mDatabaseP;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list);

        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.black2));
        }//status bar or the time bar at the top

        recyclerView=(RecyclerView)findViewById(R.id.requestrec);

        mAuth=FirebaseAuth.getInstance();

       // mDatabaseP = FirebaseDatabase.getInstance().getReference().child("users");



        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    protected void onStart() {
        super.onStart();
        final String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("request").child(id)
                .limitToLast(50);
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(query, model.class)
                        .build();
        //Recycler for viewing the information of posts from database
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<model, findstudyp.PackageViewHolder>(options) {
            @Override
            public findstudyp.PackageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Used same procedure as the posting options for pulling and setting information from database
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.singlerow, parent, false);

                return new findstudyp.PackageViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(findstudyp.PackageViewHolder holder, int position, model model) {

                String post_key = getRef(position).getKey();
                final String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                holder.setName(model.getName());
                holder.setGender(model.getGender());
                holder.setSub(model.getSubject());


                if(model.getUid() ==userid ){

                    holder.mView.setVisibility(View.INVISIBLE);
                }





                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(PackageOptions.this,post_key,Toast.LENGTH_SHORT).show();

                        Intent SinglePackageIntent = new Intent(RequestList.this,RequestViewProfile.class);
                        SinglePackageIntent.putExtra("Package_id",post_key);
                        SinglePackageIntent.putExtra("name",model.getName());
                        SinglePackageIntent.putExtra("gender",model.getGender());
                        SinglePackageIntent.putExtra("email",model.getEmail());
                        SinglePackageIntent.putExtra("subject",model.getSubject());
                        finish();
                        startActivity(SinglePackageIntent);

                    }
                });

            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }


    public static class PackageViewHolder extends RecyclerView.ViewHolder{

        View mView;


        FirebaseAuth mAuth;



        public PackageViewHolder(View itemView) {
            super(itemView);

            mView=itemView;


            mAuth= FirebaseAuth.getInstance();




        }



        public void setName(String Lname){

            TextView name = (TextView) mView.findViewById(R.id.listname);
            name.setText(Lname);

        }

        public void setGender(String Ggender){

            TextView gender = (TextView) mView.findViewById(R.id.listgender);
            gender.setText(Ggender);

        }


        public void setSub(String Ssub){

            TextView subject = (TextView) mView.findViewById(R.id.listsub);
            subject.setText(Ssub);

        }


    }


    @Override
    protected void onStop() {
        super.onStop();

    }

}