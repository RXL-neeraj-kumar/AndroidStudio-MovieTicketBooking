package com.example.neeraj.ticketsnew;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.PriorityQueue;


public class login extends AppCompatActivity implements View.OnClickListener {

    private Button btLogin;
    private EditText etLoginEmail, etLoginPass;
    private TextView tvSignUp,t;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            //start profile activity directly
            finish();
            startActivity(new Intent(this,home.class));
        }
        progressDialog=new ProgressDialog(this);
        btLogin=findViewById(R.id.btLogin);
        etLoginEmail= findViewById(R.id.etLoginEmail);
        etLoginPass= findViewById(R.id.etLoginPass);
        tvSignUp= findViewById(R.id.tvSignUp);
        //t = (TextView) MainActivity.


        btLogin.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);
    }

    private void userLogin(){
        String email=etLoginEmail.getText().toString().trim();
        String password=etLoginPass.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show();
            return; //stopping the function from executing further

        }

        if(TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        //if validations are ok we will show a progress bar
        progressDialog.setMessage("Logging User...");
        progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                //Toast.makeText(login.this, "Log In Successfull !!", Toast.LENGTH_SHORT).show();
                                //finish();
                                //startActivity(new Intent(login.this, home.class));
                                checkIfEmailVerified();
                            } else {
                                Toast.makeText(login.this, "Log In Failed. Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


    }


    @Override
    public void onClick(View view) {
        if(view==btLogin){
            userLogin();
        }
        if(view==tvSignUp){
            finish();
            startActivity(new Intent(this,register.class));
        }
    }
    private void checkIfEmailVerified()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified())
        {
            // user is verified, so you can finish this activity or send user to activity which you want
            Toast.makeText(login.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(login.this,MainActivity.class));

        }
        else
        {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            Toast.makeText(this, "Email Not verified", Toast.LENGTH_SHORT).show();
           // FirebaseAuth.getInstance().signOut();
            //Intent intent=getIntent();
            //finish();
            //startActivity(intent);

            //restart this activity

        }
    }

}
