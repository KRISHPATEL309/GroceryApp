package com.krish.practices.groceryapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView Name,Email,Mobilenumber,Verification;
    String userid;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Button Resetpassword,Logout,Verifibut,Editprofile;
    ImageView Profilepicture,Trial;
    StorageReference storageReference;


    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Verification=view.findViewById(R.id.verifyemail);
        Verifibut=view.findViewById(R.id.verify);
        Name=view.findViewById(R.id.nameview);
//        Trial=findViewById(R.id.imageView2);
        Email=view.findViewById(R.id.emailview);
        Mobilenumber=view.findViewById(R.id.Mobilenumberview);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        userid=fAuth.getCurrentUser().getUid();
        Resetpassword=view.findViewById(R.id.resetpassword);
        Logout=view.findViewById(R.id.logout);
        Editprofile=view.findViewById(R.id.editprofile);
        Profilepicture=view.findViewById(R.id.profilepicture);
        storageReference= FirebaseStorage.getInstance().getReference();

//        StorageReference profileRef=storageReference.child("User"+fAuth.getCurrentUser().getUid());
//        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                Picasso.get().load(uri).into(Profilepicture);
//            }
//        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(),Splash_Activity.class);
                startActivity(intent);
            }
//
        });
        DocumentReference documentReference=fStore.collection("User").document(userid);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    Mobilenumber.setText(documentSnapshot.getString("mobile_num"));
                    Email.setText(documentSnapshot.getString("emailRegister"));
                    Name.setText(documentSnapshot.getString("name"));
                }
                else{
                    Toast.makeText(getActivity(), "Info not found", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Failed to fetch the data", Toast.LENGTH_SHORT).show();
            }
        });

//        Resetpassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), resetpassword.class));
//            }
//        });

        if (!fAuth.getCurrentUser().isEmailVerified()) {  //if email is not verified
            Verifibut.setVisibility(View.VISIBLE);
            Verification.setVisibility(View.VISIBLE);
        }
        Verifibut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fAuth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getActivity(), "Verification Email Sent", Toast.LENGTH_SHORT).show();
                        Verification.setVisibility(View.GONE);
                        Verifibut.setVisibility(View.GONE);
                    }
                });
            }
        });

//        Editprofile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent i=new Intent(view.getContext(),editprofile.class);
//                i.putExtra("fullname",Name.getText().toString());
//                i.putExtra("email",Email.getText().toString());
//                i.putExtra("phone",Mobilenumber.getText().toString());
//                startActivity(i);
//            }
//        });
        return view;
    }
}