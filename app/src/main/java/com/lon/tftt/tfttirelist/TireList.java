package com.lon.tftt.tfttirelist;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.firebase.ui.database.SnapshotParser;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class TireList extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    FirebaseRecyclerOptions<Post> options;
    FirebaseRecyclerAdapter<Post, MyRecyclerViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* //way to start a new activity
        Intent intent=new Intent(this,Items.class);
        startActivity(intent);
        */
        setContentView(R.layout.activity_tire_list);

        Toolbar topToolBar=findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("pows");

        //call post function
        //call firebase adapter

        displayComment();

        adapter.notifyDataSetChanged();
    }


    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch(itemId) {

            case R.id.items:
                Intent intent=new Intent(this,ItemList.class);
                startActivity(intent);
                return true;
            // manage other entries if you have it ...
        }
        return true;
    }

    //post comm function
    private void PostFunction(){
        //get title
        String title;
        //get content from res
        String content;
       // Post post=new Post(title,content);

        //use push function to create uniqe id in firebase database
       // databaseReference.push().setValue(post);
       //// databaseReference.child("a").setValue(post);
    }

    private void displayComment() {
        options =
                new FirebaseRecyclerOptions.Builder<Post>()
                        .setQuery(databaseReference, new SnapshotParser<Post>() {
                                    @NonNull
                                    @Override
                                    public Post parseSnapshot(@NonNull DataSnapshot snapshot) {
                                        System.out.print(snapshot.getKey());
                                        Post post=new Post();
                                        post.setId(snapshot.getKey());
                                        post.setContent((String)snapshot.child("content").getValue());
                                        post.setTitle((String)snapshot.child("title").getValue());
                                        return post;
                                    }

                        }).build();
                        //.setQuery(databaseReference, Post.class)
                        //.build();
        adapter =
                new FirebaseRecyclerAdapter<Post, MyRecyclerViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull MyRecyclerViewHolder myRecyclerViewHolder, int i, @NonNull Post post) {
                        myRecyclerViewHolder.txt_title.setText(post.getTitle());
                        myRecyclerViewHolder.txt_comment.setText(post.getContent());

                        final ImageView imageView=myRecyclerViewHolder.imageView;
                        final ImageView imageView2=myRecyclerViewHolder.imageView2;
                        final ImageView imageView3=myRecyclerViewHolder.imageView3;
                        final ImageView imageView4=myRecyclerViewHolder.imageView4;
                        final ImageView imageView5=myRecyclerViewHolder.imageView5;
                        final ImageView imageView6=myRecyclerViewHolder.imageView6;
                        final ImageView imageView7=myRecyclerViewHolder.imageView7;
                        final ImageView imageView8=myRecyclerViewHolder.imageView8;
                        //split the champions string into indevidul champions
                        String[] champions=post.getContent().split(",");
                        //convert champions to imgs
                        final DatabaseReference databaseReferenceImg=firebaseDatabase.getReference("champions");

                        databaseReferenceImg.child(champions[0]).child("img").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.getValue()!=null){
                                        String imgUrl=(String)dataSnapshot.getValue();

                                        Picasso.get().load(imgUrl).into(imageView);
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }

                            });

                        if(champions.length>6){
                            databaseReferenceImg.child(champions[6]).child("img").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.getValue()!=null){
                                        String imgUrl=(String)dataSnapshot.getValue();
                                        Picasso.get().load(imgUrl).into(imageView2);
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });
                        }


                        databaseReferenceImg.child(champions[1]).child("img").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.getValue()!=null){
                                    String imgUrl=(String)dataSnapshot.getValue();
                                    Picasso.get().load(imgUrl).into(imageView3);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });

                        databaseReferenceImg.child(champions[2]).child("img").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.getValue()!=null){
                                    String imgUrl=(String)dataSnapshot.getValue();
                                    Picasso.get().load(imgUrl).into(imageView4);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });

                        databaseReferenceImg.child(champions[3]).child("img").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.getValue()!=null){
                                    String imgUrl=(String)dataSnapshot.getValue();
                                    Picasso.get().load(imgUrl).into(imageView5);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });

                        databaseReferenceImg.child(champions[4]).child("img").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.getValue()!=null){
                                    String imgUrl=(String)dataSnapshot.getValue();
                                    Picasso.get().load(imgUrl).into(imageView6);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });

                        databaseReferenceImg.child(champions[5]).child("img").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.getValue()!=null){
                                    String imgUrl=(String)dataSnapshot.getValue();
                                    Picasso.get().load(imgUrl).into(imageView7);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });

                        if(champions.length>7){
                            databaseReferenceImg.child(champions[7]).child("img").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.getValue()!=null){
                                        String imgUrl=(String)dataSnapshot.getValue();
                                        Picasso.get().load(imgUrl).into(imageView8);
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });
                        }


                        //Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/tfttierlist.appspot.com/o/tft%2Fashe.png?alt=media&token=0362ce86-7e52-4129-a268-dca171ecf94a").into(myRecyclerViewHolder.imageView);

                    }

                    @NonNull
                    @Override
                    public MyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View itemView = LayoutInflater.from(getBaseContext()).inflate(R.layout.post_item, parent, false);
                        return new MyRecyclerViewHolder(itemView);
                    }
                };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}
