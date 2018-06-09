package com.example.neeraj.ticketsnew;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import static android.content.ContentValues.TAG;


public class setting_frag extends Fragment {
    EditText upname,uppass;
    Button bname,bpassword,logout;
    private FirebaseAuth firebaseAuth;


    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       v=  inflater.inflate(R.layout.fragment_setting_frag, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        upname=v.findViewById(R.id.upname);
        uppass=v.findViewById(R.id.uppassword);
        bname=v.findViewById(R.id.bname);
        bpassword=v.findViewById(R.id.bpassword);
        logout=v.findViewById(R.id.logout);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseAuth=FirebaseAuth.getInstance();
        bname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (user != null) {
                    String name = upname.getText().toString();
                    if(TextUtils.isEmpty(name)) {
                        //email is empty
                        Toast.makeText(getContext(), "Please enter Name", Toast.LENGTH_SHORT).show();
                        return; //stopping the function from executing further

                    }
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                            .build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "User profile updated.");
                                        Toast.makeText(getContext(), "Display name changed", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getContext(),MainActivity.class));
                                    }
                                }
                            });

                }else
                    Toast.makeText(getContext(), "Please Sign In", Toast.LENGTH_SHORT).show();
            }
        });

        bpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (user != null) {
                    String newPassword = uppass.getText().toString();
                    if(TextUtils.isEmpty(newPassword)) {
                        //email is empty
                        Toast.makeText(getContext(), "Please enter Password", Toast.LENGTH_SHORT).show();
                        return; //stopping the function from executing further

                    }

                    user.updatePassword(newPassword)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "User password updated.");
                                        Toast.makeText(getContext(), "Password Changed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else
                    Toast.makeText(getContext(), "Please Sign In", Toast.LENGTH_SHORT).show();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user != null) {
                    firebaseAuth.getInstance().signOut();
                    Toast.makeText(getContext(), "Successfully Signed Out", Toast.LENGTH_SHORT).show();
                   // FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    //ft.replace(R.id.l1, new home());
                    //ft.commit();
                    startActivity(new Intent(getContext(),MainActivity.class));
                }
                else
                    Toast.makeText(getContext(), "Please Sign In", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
