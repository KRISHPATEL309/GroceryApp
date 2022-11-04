package com.krish.practices.groceryapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<User> items;
    FirebaseFirestore ff;
    ProgressDialog progressDialog;
    private FirebaseAuth auth;
    private FirebaseUser user;
    CardView fruit,vegetable;
    TextView viewall2,viewall1;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        fruit=view.findViewById(R.id.fruit);
        vegetable=view.findViewById(R.id.vegetable);
        viewall2=view.findViewById(R.id.viewall2);
        viewall1=view.findViewById(R.id.viewall1);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if (user==null)
        {
            Intent intent = new Intent(getActivity(),Login_Activity.class);
            startActivity(intent);
        }





        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Fruit_Activity.class);
                startActivity(intent);
            }
        });
        vegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Vegetable_Activity.class);
                startActivity(intent);
            }
        });
        viewall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Popular_item_Activity.class);
                startActivity(intent);
            }
        });
        viewall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Categories_Activity.class);
                startActivity(intent);
            }
        });


////        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Fetching Data...");
//        progressDialog.show();
//
//        recyclerView=view.findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        items=new ArrayList<>();
//        progressDialog = new ProgressDialog(getContext());
//
//        recyclerView.setHasFixedSize(true);
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),10);
//        recyclerView.setLayoutManager(gridLayoutManager);
//
//        ff = FirebaseFirestore.getInstance();
//        adapter = new Adapter(getContext(),items, (RecyclerviewInterface) this);
//
//        recyclerView.setAdapter(adapter);
//        EventChangelistener();

//        recyclerView=view.findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        items=new ArrayList<>();
//
//        User obj1=new User("Fruit",R.drawable.fruits);
//        items.add(obj1);
//        User obj2=new User("Fruit",R.drawable.fruits);
//        items.add(obj2);
//        User obj3=new User("Fruit",R.drawable.fruits);
//        items.add(obj3);
//        Data_rest obj2=new Data_rest(R.drawable.logo_splash,"Tulsi_Restaurant","9kg/12p","india");
//        items.add(obj2);
//        Data_rest obj3=new Data_rest(R.drawable.logo_splash,"Tulsi_Restaurant","9kg/12p","india");
//        items.add(obj3);
//        Data_rest obj4=new Data_rest(R.drawable.logo_splash,"Tulsi_Restaurant","9kg/12p","india");
//        items.add(obj4);
//        Data_rest obj5=new Data_rest(R.drawable.logo_splash,"Tulsi_Restaurant","9kg/12p","india");
//        items.add(obj5);

//        recyclerView.setAdapter(new Adapter(items));
        return view;
    }

    private void EventChangelistener() {
        ff.collection("vegetable").orderBy("Vegetable_Name", Query.Direction.ASCENDING)
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
                                items.add(dc.getDocument().toObject(User.class));
                            }
                            adapter.notifyDataSetChanged();
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    }
                });
    }
}