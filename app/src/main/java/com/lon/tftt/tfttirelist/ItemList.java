package com.lon.tftt.tfttirelist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ItemList extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    FirebaseRecyclerOptions<Item> options;
    FirebaseRecyclerAdapter<Item, ItemRecyclerViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar topToolBar=findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView=(RecyclerView)findViewById(R.id.item_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("items/adv");

        displayItems();

    }

    private void displayItems(){
                options =
                        new FirebaseRecyclerOptions.Builder<Item>()
                                .setQuery(databaseReference, new SnapshotParser<Item>() {
                                    @NonNull
                                    @Override
                                    public Item parseSnapshot(@NonNull DataSnapshot snapshot) {
                                        System.out.print(snapshot.getKey());
                                        Item item = new Item();
                                        item.setId(snapshot.getKey());
                                        item.setCombo((String) snapshot.child("combo").getValue());
                                        item.setImg((String) snapshot.child("img").getValue());
                                        return item;
                                    }

                                }).build();

        adapter =
                new FirebaseRecyclerAdapter<Item, ItemRecyclerViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ItemRecyclerViewHolder itemRecyclerViewHolder, int i, @NonNull Item item) {
                        itemRecyclerViewHolder.txt_title.setText(item.getId());
                        Picasso.get().load(item.getImg()).into(itemRecyclerViewHolder.imageView);
                        final ImageView imageView2 = itemRecyclerViewHolder.imageView2;
                        final ImageView imageView3 = itemRecyclerViewHolder.imageView3;

                        String[] combos = item.getCombo().split(",");

                        final DatabaseReference databaseReferenceCombo = firebaseDatabase.getReference("items/basics");

                        databaseReferenceCombo.child(combos[0]).child("img").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.getValue() != null) {
                                    String imgUrl = (String) dataSnapshot.getValue();

                                    Picasso.get().load(imgUrl).into(imageView2);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }

                        });

                        databaseReferenceCombo.child(combos[1]).child("img").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.getValue() != null) {
                                    String imgUrl = (String) dataSnapshot.getValue();

                                    Picasso.get().load(imgUrl).into(imageView3);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }

                        });

                    }
                        @NonNull
                        @Override
                        public ItemRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            View itemView = LayoutInflater.from(getBaseContext()).inflate(R.layout.adv_item, parent, false);
                            return new ItemRecyclerViewHolder(itemView);
                        }
                    };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
                }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
