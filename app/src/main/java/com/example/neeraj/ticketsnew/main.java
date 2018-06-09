package com.example.neeraj.ticketsnew;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;




public class main extends Fragment {
    String movies[] = {"Baaghi 2","Blackmail","October","Raid","Rampage"};
    String actors[] = {"Tiger Shroff","Irrfan Khan","Varun Dhawan","Ajay Devgn","Dwayne Johnson"};
    int imageids[] = {R.drawable.baagi,R.drawable.blackmail,R.drawable.october,R.drawable.raid,R.drawable.rampage};
    String imdblink[] = {"https://www.imdb.com/title/tt6843812/?ref_=nv_sr_1",
            "https://www.imdb.com/title/tt6972140/?ref_=nv_sr_2",
            "https://www.imdb.com/title/tt7700730/?ref_=nv_sr_1",
            "https://www.imdb.com/title/tt7363076/?ref_=nv_sr_3",
            "https://www.imdb.com/title/tt2231461/?ref_=nv_sr_1"};

    View v;
    ListView l;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.main, container, false);

        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    l = (ListView) v.findViewById(R.id.movie_list);
    movieadapter adapter = new movieadapter(getContext());
        l.setAdapter(adapter);
    }
    class movieadapter extends ArrayAdapter<String> {
        public movieadapter(@NonNull Context context) {
            super(context,R.layout.mylayout,R.id.moviename,movies);
        }
        class myholder{
            TextView moviea;
            TextView actora;
            Button ba;
            ImageView ia;

            public myholder(View v) {
                this.moviea = (TextView) v.findViewById(R.id.moviename);
                this.actora = (TextView) v.findViewById(R.id.directorname);
                this.ba = (Button) v.findViewById(R.id.book);
                this.ia = (ImageView) v.findViewById(R.id.imageView);
            }
        }
        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row=convertView;
            myholder holder = null;
            if(row==null){
            LayoutInflater l = getActivity().getLayoutInflater();
            row = l.inflate(R.layout.mylayout,parent,false);
                holder = new myholder(row);
                row.setTag(holder);
            }
            else{
                holder = (myholder) row.getTag();
            }
            holder.moviea.setText(movies[position]);



            holder.actora.setText(actors[position]);
            holder.ia.setImageResource(imageids[position]);
            holder.ia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent();
                    i.setAction("android.intent.action.VIEW");
                    i.setData(Uri.parse(imdblink[position]));
                    startActivity(i);
                }
            });
            holder.ba.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sp = getActivity().getSharedPreferences("db",0);
                    SharedPreferences.Editor et = sp.edit();

                    et.putString("movie",movies[position]);
                    et.commit();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.l1,new fragtwo());
                    ft.commit();

                }
            });
            return row;
        }

    }

}
