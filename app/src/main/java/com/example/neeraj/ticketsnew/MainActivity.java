package com.example.neeraj.ticketsnew;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
Toolbar toolbar;
    TextView t;
    DrawerLayout drawer;
    private FirebaseAuth firebaseAuth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View v = navigationView.getHeaderView(0);


        t = (TextView) v.findViewById(R.id.loginbutton);
        FirebaseUser user=firebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            t.setText(user.getDisplayName());
        }
        if(user==null) {
        t.setText("Login!");
            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, register.class));
                    drawer.closeDrawer(GravityCompat.START);
                }
            });
        }


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.l1, new home());
        ft.commit();
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            {FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.l1, new home());
                ft.commit();}
        }
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id==R.id.home){FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.l1, new home());
            ft.commit();}
        else
        if(id==R.id.exit){
            AlertDialog.Builder ab = new AlertDialog.Builder(this);
            ab.setMessage("Are your sure you want to exit..?");
            ab.setPositiveButton("Yes, Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            ab.setNegativeButton("Cancel",null);
            ab.setCancelable(false);
            ab.show();
        }
        else
            if(id==R.id.like){
                View v = getLayoutInflater().inflate(R.layout.ratingbar,null);
                AlertDialog.Builder ab= new AlertDialog.Builder(this);
                ab.setTitle("Give us a Rating :");
                ab.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Thanx for rating!!", Toast.LENGTH_SHORT).show();
                    }
                });
                ab.setNegativeButton("cancel",null);
                ab.setCancelable(false);
                ab.setView(v);
                ab.show();
            }
        else
        if(id==R.id.book){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.l1, new main());
            ft.commit();
        }
        else if(id ==R.id.about){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.l1, new about());
            ft.commit();
        }
        else
            if(id==R.id.settings){
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.l1, new setting_frag());
                ft.commit();
            }
        else if (id==R.id.bm){
                Uri uri = Uri.parse("https://www.imdb.com/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
