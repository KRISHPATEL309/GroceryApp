package com.krish.practices.groceryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.krish.practices.groceryapp.Login_Activity;
import com.krish.practices.groceryapp.R;
import com.krish.practices.groceryapp.UserHelperClass;

import org.w3c.dom.Text;

import java.lang.reflect.Member;
import java.util.jar.Attributes;

public class Signup_Activity extends AppCompatActivity {
    EditText name,mobile_num,emailRegister,passwordRegister,cpassword;
    Button next_btnR;
    TextView Already;
    FirebaseAuth fAuth;
    UserHelperClass userHelperClass;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        userHelperClass = new UserHelperClass();

        mobile_num = findViewById(R.id.Mobile_num);
        emailRegister = findViewById(R.id.EmailRegister);
        name = findViewById(R.id.Name);
        passwordRegister = findViewById(R.id.PasswordRegister);
        cpassword = findViewById(R.id.Cpassword);
        next_btnR=findViewById(R.id.next_btnR);
        Already=findViewById(R.id.Already);
        fAuth = FirebaseAuth.getInstance();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("User");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    i=(int)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                ///
            }
        });


        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),Login_Activity.class));
        }
        Already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login_Activity.class));
            }
        });

        next_btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Name = name.getText().toString().trim();

                String Mobilenumber = mobile_num.getText().toString().trim();
                String Email = emailRegister.getText().toString().trim();
                String Password = passwordRegister.getText().toString().trim();
                String Confirmpasword = cpassword.getText().toString().trim();

                if(Name.isEmpty())
                {
                    name.setError("Name is required");
                    return;
                }

                if(Mobilenumber.isEmpty())
                {
                    mobile_num.setError("Mobile is required");
                    return;
                }
                if(Email.isEmpty())
                {
                    emailRegister.setError("Email is required");
                    return;
                }
                if(Password.isEmpty())
                {
                    passwordRegister.setError("Password is required");
                    return;
                }
                if(Password.length()<6){
                    passwordRegister.setError("Password must be more than 6 characters");
                }

                if(Confirmpasword.isEmpty())
                {
                    cpassword.setError("Confirm Password is required");
                    return;
                }
                if(!Password.equals(Confirmpasword))
                {
                    cpassword.setError("Password do not match");
                    return;
                }
                // data is validated
                fAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String uid = task.getResult().getUser().getUid();
                            userHelperClass = new UserHelperClass(uid,Name,Mobilenumber,Email,Password);
                            reference.child(uid).setValue(userHelperClass);

                            Toast.makeText(Signup_Activity.this, "User Created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Login_Activity.class));
                        }
                        else
                        {
                            Toast.makeText(Signup_Activity.this,"Error !!"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}