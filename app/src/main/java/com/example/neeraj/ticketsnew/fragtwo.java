package com.example.neeraj.ticketsnew;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class fragtwo extends Fragment{
    String data[]= {"Satyam Cinemplaxes","PVR Director Cut","PVR Vikaspuri","PVR Pacific","Suraj Cinema","Movie Time","PVR Ambience"};
    String set;
    Spinner spin;
    TextView tv,amountsel;
    TextView timere,ticketse;
    View v;
    View v2;
    Button b1,b2,b3,b4,bookid;
    LinearLayout place;
    LinearLayout timee;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fragtwo,container,false);
        timere = (TextView) v.findViewById(R.id.timer);
        timere.setVisibility(View.GONE);
        //buttons
        b1 = (Button) v.findViewById(R.id.one);
        b2 = (Button) v.findViewById(R.id.two);
        b3 = (Button) v.findViewById(R.id.three);
        b4 = (Button) v.findViewById(R.id.four);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {timere.setText("9:00 AM");timere.setVisibility(View.VISIBLE);}
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {timere.setText("12:30PM");timere.setVisibility(View.VISIBLE);}
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {timere.setText("3:10 PM");timere.setVisibility(View.VISIBLE);}
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {timere.setText("7:40 PM");timere.setVisibility(View.VISIBLE);}
        });



        return v;
    }


    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv = (TextView) v.findViewById(R.id.tv8);
        TextView tw = (TextView) v.findViewById(R.id.movieid);
        SharedPreferences sp = getActivity().getSharedPreferences("db",0);
        tw.setText(sp.getString("movie",null));
        place = (LinearLayout) v.findViewById(R.id.placelayout);
        place.setVisibility(View.GONE);


        timee = (LinearLayout) v.findViewById(R.id.timelayout);
        timee.setVisibility(View.GONE);

        spin= (Spinner)v.findViewById(R.id.spinner);
        ArrayAdapter<String> a= new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,data);
        spin.setAdapter(a);





tv.setOnClickListener(new View.OnClickListener() {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        Calendar c = Calendar.getInstance();
        int dd = c.get(Calendar.DAY_OF_MONTH);
        int mm = c.get(Calendar.MONTH);
        int yy = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DAY_OF_WEEK);
        String date = dd + "/" + mm + "/" + yy;
        DatePickerDialog dp = new DatePickerDialog(
               getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override

            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String a = null;
                switch (i1){
                    case 1 : a = "Jan";break;case 2 : a = "Feb";break;
                    case 3 : a = "Mar";break;case 4 : a = "Apr";break;
                    case 5 : a = "May";break;case 6 : a = "Jun";break;
                    case 7 : a = "Jul";break;case 8 : a = "Aug";break;
                    case 9 : a = "Sep";break;case 10 : a = "Oct";break;
                    case 11 : a = "Nov";break;case 12 : a = "Dec";break;
                }
                set = a+" "+i2+","+i;
                tv.setText(set);
                place.setVisibility(View.VISIBLE);
                timee.setVisibility(View.VISIBLE);
            }
        }, yy, mm, dd);
        dp.show();
    }
});
        bookid = (Button) v.findViewById(R.id.bookid);
        v2 = getActivity().getLayoutInflater().inflate(R.layout.ticketdailog,null);
        Button a1,a2,a3,a4,a5;
        a1 = (Button) v2.findViewById(R.id.a1);
        a2 = (Button) v2.findViewById(R.id.a2);
        a3 = (Button) v2.findViewById(R.id.a3);
        a4 = (Button) v2.findViewById(R.id.a4);
        a5 = (Button) v2.findViewById(R.id.a5);
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketse.setText("1");
                amountsel.setText("140");
            }
        });
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketse.setText("2");
                amountsel.setText("280");
            }
        });
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketse.setText("3");
                amountsel.setText("420");
            }
        });
        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketse.setText("4");
                amountsel.setText("560");
            }
        });
        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketse.setText("5");
                amountsel.setText("700");
            }
        });
        bookid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ticket Dailog
                AlertDialog.Builder ab= new AlertDialog.Builder(getContext());
                ab.setTitle("Select No. of Tickets:");


                ticketse = (TextView) v2.findViewById(R.id.ticketsel);
                amountsel = (TextView) v2.findViewById(R.id.amountsel);


                ab.setView(v2);
                ab.setPositiveButton("PROCEED", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder bb = new AlertDialog.Builder(getContext());
                        String s = "Your Ticket is Booked :\n\n"+"Date : "+set+"\n"+"Place : "+spin.getSelectedItem().toString()+"\nTiming: "+timere.getText().toString()+"\nTotal Tickets :"+ ticketse.getText().toString()+"\nAmount Paid: "+amountsel.getText().toString();
                        bb.setMessage(s);
                        bb.show();

                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.l1, new home());
                        ft.commit();
                    }
                });
                ab.setCancelable(false);
                ab.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder ab = new AlertDialog.Builder(getContext());
                        ab.setMessage("Transaction Failed ! Please Retry ");
                        ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.l1, new main());
                                ft.commit();
                            }
                        });
                        ab.show();
                    }
                });
                ab.show();
            }
        });
    }
}