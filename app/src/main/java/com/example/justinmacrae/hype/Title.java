package com.example.justinmacrae.hype;

import android.content.Intent;

import android.os.Handler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;

import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;


import java.sql.Array;

import jmac.example.justinmacrae.hype.R;

public class Title extends AppCompatActivity {
    TextView txt_time,txt_gal,txt_stam,nightday;
    Button record,arena,instruction;
    Intent in,in2;
    Bundle bundle,bundle2;
    long start=0,long_min,const_stam=100;
    Double doub_gal,endurance;
    CharSequence min;
    String str_min,str_min2,str_endurance,original_time;
    String[] parts;
    int int_end;
    boolean nightmulti;


    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - start;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            txt_time.setText(String.format("%d:%02d", minutes, seconds));

            timerHandler.postDelayed(this, 500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        txt_time = (TextView) this.findViewById(R.id.txt_time);
        txt_gal = (TextView) this.findViewById(R.id.gallonsused);
        txt_stam = (TextView) this.findViewById(R.id.stamina);
        nightday = (TextView) this.findViewById(R.id.textView7);
        record = (Button) this.findViewById(R.id.startrecord);
        arena= (Button) this.findViewById(R.id.toarena);
        instruction= (Button) this.findViewById(R.id.instructions);
        int_end=0;
        final int[] time_count = {0};

        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);



        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
        if (hour>=22 || hour<6) {
            nightday.setText("Night");
            nightmulti=true;
        }
        else{
            nightday.setText("Day");
            nightmulti=false;
        }

        arena.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         in = new Intent(Title.this,Arena.class);
                                         bundle = new Bundle();
                                         bundle.putInt("endure",int_end);
                                         in.putExtras(bundle);
                                         startActivity(in);
                                     }

                                 });
        instruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(Title.this,Instruction.class);
                startActivity(in);
            }

        });

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (record.getText().equals("stop")) {
                    timerHandler.removeCallbacks(timerRunnable);
                    record.setText("start");

                    min=txt_time.getText();
                    str_min=min.toString();
                    original_time=min.toString();
                    parts = str_min.split("\\:"); // String array, each element is text between dots
                    str_min2=parts[1];
                    long_min=Long.parseLong(str_min2);
                    str_min2= parts[0];
                    long_min=Long.parseLong(str_min2);
                    doub_gal=long_min*2.1;
                    str_min=Double.toString(doub_gal);
                    txt_gal.setText(str_min+" gallons!");

                    if (long_min<5) {
                        endurance=10.0;
                    }
                    else {
                        endurance=const_stam-doub_gal;
                    }


                    if (nightmulti==true){
                        endurance=endurance*1.5;
                    }
                    int_end=endurance.intValue();
                    str_endurance=Double.toString(int_end);
                    txt_stam.setText(str_endurance);

                      in = new Intent(Title.this,Arena.class);
                     bundle = new Bundle();
                    bundle.putInt("endure",int_end);
                    in.putExtras(bundle);




                } else {
                    start = System.currentTimeMillis();
                    timerHandler.postDelayed(timerRunnable, 0);
                    record.setText("stop");
                    Toast.makeText(Title.this, "Have a good shower!", Toast.LENGTH_SHORT).show();


            }
        };


        });
}
    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
        Button record = (Button)findViewById(R.id.startrecord);
        record.setText("start");
    }


}




