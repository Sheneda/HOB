package com.example.hob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class JoinActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mRootRef;

    private EditText username,fullname,email, password;
    private TextView loginUser;
    private Button btnJoin;

    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        username = findViewById(R.id.id_join_username);
        fullname = findViewById(R.id.id_join_fullname);
        email = findViewById(R.id.id_join_email);
        password = findViewById(R.id.id_join_password);
        btnJoin = findViewById(R.id.id_join_joinButton);
        loginUser = findViewById(R.id.id_join_loginUser);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        pd= new ProgressDialog(this);


        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JoinActivity.this, LoginActivity.class));
            }
        });

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtUsername = username.getText().toString();
                String txtName = fullname.getText().toString();
                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();


                if(TextUtils.isEmpty(txtUsername) || TextUtils.isEmpty(txtName) || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)){
                    Toast.makeText(JoinActivity.this,"Enter Credentials", Toast.LENGTH_SHORT).show();
                }
                else if (txtPassword.length()<6){
                    Toast.makeText(JoinActivity.this,"Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                }
                else{
                    RegisterUser(txtUsername,txtName,txtEmail,txtPassword);
                }

            }
        });
    }

    private void RegisterUser(String username, String name, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                HashMap<String,Object> map = new HashMap<>();
                map.put("name",name);
                map.put("email",email);
                map.put("username",username);
                map.put("id",mAuth.getCurrentUser().getUid());

                mRootRef.child("User").child(mAuth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(JoinActivity.this,"Update the profile" + "for better experience", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(JoinActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(JoinActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}