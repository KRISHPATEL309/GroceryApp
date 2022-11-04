package com.krish.practices.groceryapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Popular_item_Activity extends AppCompatActivity implements RecyclerviewInterface{
    RecyclerView recyclerView;
    ArrayList<User_Fruit> userArrayList;
    Adapter adapter;
    FirebaseFirestore ff;
    ProgressDialog progressDialog;
    ImageView back_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_item);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        back_icon = findViewById(R.id.back_icon);
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Popular_item_Activity.this,HomeFragment.class);
                startActivity(intent);
            }
        });
        ff = FirebaseFirestore.getInstance();
        userArrayList = new ArrayList<User_Fruit>();
        adapter = new Adapter(Popular_item_Activity.this,userArrayList,this);

        recyclerView.setAdapter(adapter);

        EventChangelistener();
    }

    private void EventChangelistener() {
        ff.collection("popular_item").orderBy("Fruit_Name", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error!=null){
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("Firestore Error",error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                userArrayList.add(dc.getDocument().toObject(User_Fruit.class));
                            }
                            adapter.notifyDataSetChanged();
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(Popular_item_Activity.this,Info_Activity.class);

        intent.putExtra("Fruit_Name",userArrayList.get(position).getFruit_Name());
        intent.putExtra("Price",userArrayList.get(position).getPrice());
        intent.putExtra("Quantity",userArrayList.get(position).getQuantity());
        intent.putExtra("Image",userArrayList.get(position).getImage());

        startActivity(intent);
    }

    public ColorSpace.Model get(int position) {
        return null;
    }
}