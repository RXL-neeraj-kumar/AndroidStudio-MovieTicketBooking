package com.example.neeraj.ticketsnew;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class home extends Fragment {
    View v;

    ImageButton ib4;
    ImageButton ib2;
    ImageButton ib5;
    ImageButton ib7;
    ImageButton ib6;
    ImageButton bm;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_home, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ib7 = (ImageButton) v.findViewById(R.id.button7);
        ib2 = (ImageButton) v.findViewById(R.id.button2);
        ib5 = (ImageButton) v.findViewById(R.id.button5);
        ib4 = (ImageButton) v.findViewById(R.id.button4);
        ib6 = (ImageButton) v.findViewById(R.id.button6);
        bm = (ImageButton) v.findViewById(R.id.bm);
        firebaseAuth=FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        bm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.imdb.com/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        ib6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.l1, new setting_frag());
                ft.commit();
            }
        });
        ib4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.l1, new about());
                ft.commit();
            }
        });
        ib7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(getContext());
                ab.setMessage("Are your sure you want to exit..?");
                ab.setPositiveButton("Yes, Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      getActivity().finish();
                    }
                });
                ab.setNegativeButton("Cancel",null);
                ab.setCancelable(false);
                ab.show();
            }
        });
        if(user!=null) {
            ib2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.l1, new main());
                    ft.commit();
                }
            });
        }
        ib5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View v2 = getActivity().getLayoutInflater().inflate(R.layout.ratingbar,null);
                RatingBar rb;
                AlertDialog.Builder ab= new AlertDialog.Builder(getContext());
                ab.setTitle("Rate us");
                rb=(RatingBar)v2.findViewById(R.id.rb);
                ab.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Thanx for rating!!", Toast.LENGTH_SHORT).show();
                    }
                });
                ab.setNegativeButton("cancel",null);
                ab.setCancelable(false);
                ab.setView(v2);
                ab.show();
            }
        });
    }
}
