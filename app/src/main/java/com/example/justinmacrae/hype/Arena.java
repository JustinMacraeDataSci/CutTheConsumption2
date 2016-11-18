package com.example.justinmacrae.hype;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.MotionEvent;
import android.view.View;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import jmac.example.justinmacrae.hype.R;

public class Arena extends AppCompatActivity {
TextView txt_endure,txt_hp,txt_boss,count;
    int endurance=0,hp=3,bosshp=3223,clown_hit=-30;
    String str_endure;
    Random r = new Random();
    ImageButton sword,sword_r,swordmid;
    Boolean flag=true,clown=true;
    ImageView enemy,attackred,rightred,evil_clown,midred;
    private float x1,x2,y1,y2;
    static final int MIN_DISTANCE = 150;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena);
        txt_endure= (TextView) this.findViewById(R.id.txt_endure);
        txt_hp= (TextView) this.findViewById(R.id.txt_hp);
        txt_boss= (TextView) this.findViewById(R.id.txt_bosshp);
        count= (TextView) this.findViewById(R.id.countdown);
        evil_clown= (ImageView) this.findViewById(R.id.evilclown);
        sword= (ImageButton) this.findViewById(R.id.sword);
        sword_r= (ImageButton) this.findViewById(R.id.swordright);
        enemy= (ImageView) this.findViewById(R.id.enemy);
        attackred= (ImageView) this.findViewById(R.id.attackred);
        rightred= (ImageView) this.findViewById(R.id.rightred);
        swordmid=(ImageButton) this.findViewById(R.id.swordmid);
        midred=(ImageView) this.findViewById(R.id.midred);
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);






        Intent in=getIntent();
        Bundle bundle = in.getExtras();
        endurance=bundle.getInt("endure");
        str_endure=Integer.toString(endurance);
        txt_endure.setText(str_endure);
        txt_hp.setText(Integer.toString(hp));
        txt_boss.setText(Integer.toString(bosshp));

        evil_clown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            clown_hit+=1;
                bosshp=bosshp-10;

                if (clown == true) {
                    clown=false;
                    new CountDownTimer(5000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            count.setText("seconds remaining: " + millisUntilFinished / 1000);
                            //here you can have your logic to set text to edittext
                        }

                        public void onFinish() {
                            hp = hp+ clown_hit;
                            if (hp <=0) {
                                AlertDialog alertDialog = new AlertDialog.Builder(Arena.this).create();
                                alertDialog.setTitle("Alert");
                                alertDialog.setMessage("You lose keep using less water!");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                Intent intent = new Intent(Arena.this, Title.class);
                                                startActivity(intent);
                                            }
                                        });
                                alertDialog.show();

                            }
                            txt_hp.setText(Integer.toString(hp));
                            count.setText("");
                            enemy.setVisibility(View.VISIBLE);
                            evil_clown.setVisibility(View.INVISIBLE);
                        }

                    }.start();
                }
            }
        });

        sword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           sword.setVisibility(View.INVISIBLE);
                sword_r.setVisibility((View.INVISIBLE));
                swordmid.setVisibility(View.VISIBLE);
            }

        });



        sword_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sword_r.setVisibility(View.INVISIBLE);
                sword.setVisibility((View.VISIBLE));
                swordmid.setVisibility(View.INVISIBLE);
            }

        });

        swordmid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sword.setVisibility(View.INVISIBLE);
                sword_r.setVisibility((View.VISIBLE));
                swordmid.setVisibility(View.INVISIBLE);
            }

        });

    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1= event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                float deltaX = x2 - x1;
                float deltay= y2-y1;

                if (Math.abs(deltay) > MIN_DISTANCE)
                {
                    if(y1>y2) {
                        if  (sword.getVisibility() == View.VISIBLE && attackred.getVisibility() == View.VISIBLE)  {
                            if (count.getText().toString().equals("seconds remaining: 1")) {
                                if(flag==true) {
                                    Toast.makeText(this, "Parried!", Toast.LENGTH_SHORT).show();
                                }
                                if(flag==true) {
                                    hp = hp + 1;
                                    flag=false;
                                }
                            }


                        }
                        else if (sword_r.getVisibility() == View.VISIBLE && rightred.getVisibility() == View.VISIBLE) {
                            if (count.getText().toString().equals("seconds remaining: 1")) {
                                if(flag==true) {
                                    Toast.makeText(this, "Parried!", Toast.LENGTH_SHORT).show();

                                }
                                if(flag==true) {
                                    hp = hp + 1;
                                    flag=false;
                                }
                            }
                        }
                        else if (midred.getVisibility() == View.VISIBLE && swordmid.getVisibility() == View.VISIBLE) {
                            if (count.getText().toString().equals("seconds remaining: 1")) {
                                if(flag==true) {
                                    Toast.makeText(this, "Parried!", Toast.LENGTH_SHORT).show();

                                }
                                if(flag==true) {
                                    hp = hp + 1;
                                    flag=false;
                                }
                            }
                        }



/*
                        if (endurance>3) {
                            endurance = endurance - 3;
                            str_endure=Integer.toString(endurance);
                            txt_endure.setText(str_endure);
                        }
                        if (endurance<3) {
                            endurance=0;
                            str_endure=Integer.toString(endurance);
                            txt_endure.setText(str_endure);
                        }
  */                      x2=0;
                        x1=0;

                    }
                }
                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    // Left to Right swipe action
                    if (x2 > x1) {
                        //  Toast.makeText(this, "Left to Right!", Toast.LENGTH_SHORT).show ();

                        if (enemy.getVisibility() == View.VISIBLE) {
                            if (endurance > 0) {
                                bosshp = bosshp - 30;
                            } else {
                                bosshp = bosshp - 10;
                            }

                            txt_boss.setText(Integer.toString(bosshp));
                            //Toast.makeText(this, "Left to Right! Hit! ", Toast.LENGTH_SHORT).show ();
                            if (2600 > bosshp && bosshp > 2500) {
                                enemy.setVisibility(View.INVISIBLE);
                                evil_clown.setVisibility(View.VISIBLE);
                            }
                            if (1600 > bosshp && bosshp > 1500) {
                                enemy.setVisibility(View.INVISIBLE);
                                evil_clown.setVisibility(View.VISIBLE);
                            }
                            int i1 = r.nextInt(5 - 1) + 1;


                            if (i1 == 1) {
                                enemy.setVisibility(View.VISIBLE);
                                attackred.setVisibility(View.INVISIBLE);
                                rightred.setVisibility(View.INVISIBLE);
                            } else if (i1 == 2) {
                                flag = true;
                                enemy.setVisibility(View.INVISIBLE);
                                attackred.setVisibility(View.VISIBLE);
                                rightred.setVisibility(View.INVISIBLE);

                                new CountDownTimer(2000, 1000) {

                                    public void onTick(long millisUntilFinished) {
                                        count.setText("seconds remaining: " + millisUntilFinished / 1000);
                                        //here you can have your logic to set text to edittext
                                    }

                                    public void onFinish() {
                                        hp = hp - 1;
                                        if (hp == 0) {
                                            Intent intent = new Intent(Arena.this, Title.class);
                                            startActivity(intent);
                                        }
                                        txt_hp.setText(Integer.toString(hp));
                                        count.setText("");
                                        enemy.setVisibility(View.VISIBLE);
                                        attackred.setVisibility(View.INVISIBLE);
                                    }

                                }.start();

                            }
                            else if(i1==4) {

                                    flag = true;
                                    enemy.setVisibility(View.INVISIBLE);
                                    attackred.setVisibility(View.INVISIBLE);
                                    rightred.setVisibility(View.INVISIBLE);
                                    midred.setVisibility(View.VISIBLE);

                                    new CountDownTimer(2000, 1000) {

                                        public void onTick(long millisUntilFinished) {
                                            count.setText("seconds remaining: " + millisUntilFinished / 1000);
                                            //here you can have your logic to set text to edittext
                                        }

                                        public void onFinish() {
                                            hp = hp - 1;
                                            if (hp <= 0) {
                                                AlertDialog alertDialog = new AlertDialog.Builder(Arena.this).create();
                                                alertDialog.setTitle("Alert");
                                                alertDialog.setMessage("You lose keep using less water!");
                                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.dismiss();
                                                                Intent intent = new Intent(Arena.this, Title.class);
                                                                startActivity(intent);
                                                            }
                                                        });
                                                alertDialog.show();
                                            }
                                            txt_hp.setText(Integer.toString(hp));
                                            count.setText("");
                                            enemy.setVisibility(View.VISIBLE);
                                            midred.setVisibility(View.INVISIBLE);
                                        }

                                    }.start();
                            }
                            else {
                                flag = true;
                                enemy.setVisibility(View.INVISIBLE);
                                attackred.setVisibility(View.INVISIBLE);
                                rightred.setVisibility(View.VISIBLE);

                                new CountDownTimer(2000, 1000) {

                                    public void onTick(long millisUntilFinished) {
                                        count.setText("seconds remaining: " + millisUntilFinished / 1000);
                                        //here you can have your logic to set text to edittext
                                    }

                                    public void onFinish() {
                                        hp = hp - 1;
                                        if (hp <= 0) {
                                            AlertDialog alertDialog = new AlertDialog.Builder(Arena.this).create();
                                            alertDialog.setTitle("Alert");
                                            alertDialog.setMessage("You lose keep using less water!");
                                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            dialog.dismiss();
                                                            Intent intent = new Intent(Arena.this, Title.class);
                                                            startActivity(intent);
                                                        }
                                                    });
                                            alertDialog.show();
                                        }
                                        txt_hp.setText(Integer.toString(hp));
                                        count.setText("");
                                        enemy.setVisibility(View.VISIBLE);
                                        rightred.setVisibility(View.INVISIBLE);
                                    }

                                }.start();
                            }
                            if (endurance >= 3) {
                                endurance = endurance - 3;
                                str_endure = Integer.toString(endurance);
                                txt_endure.setText(str_endure);

                            }
                            if (endurance < 3) {
                                endurance = 0;
                                str_endure = Integer.toString(endurance);
                                txt_endure.setText(str_endure);
                            }

                        }



                    }
                    // Right to left swipe action
                    else if(x1 > x2)
                    {
                        //Toast.makeText(this, "Right to Left!", Toast.LENGTH_SHORT).show ();

                        if (enemy.getVisibility() == View.VISIBLE) {

                            if(endurance>0) {
                                bosshp=bosshp-30;
                            }
                            else {
                                bosshp = bosshp - 10;
                            }
                            if(2600>bosshp && bosshp>2500){
                                enemy.setVisibility(View.INVISIBLE);
                                evil_clown.setVisibility(View.VISIBLE);
                            }
                            if (1600 > bosshp && bosshp > 1500) {
                                enemy.setVisibility(View.INVISIBLE);
                                evil_clown.setVisibility(View.VISIBLE);
                            }
                            txt_boss.setText(Integer.toString(bosshp));
                            //Toast.makeText(this, "Right to Left! Hit!", Toast.LENGTH_SHORT).show ();
                            int i1 = r.nextInt(5 - 1) + 1;


                            if (i1==1) {
                                enemy.setVisibility(View.VISIBLE);
                                attackred.setVisibility(View.INVISIBLE);
                                rightred.setVisibility(View.INVISIBLE);
                            }
                            else if (i1==2) {
                                flag=true;
                                enemy.setVisibility(View.INVISIBLE);
                                attackred.setVisibility(View.VISIBLE);
                                rightred.setVisibility(View.INVISIBLE);

                                new CountDownTimer(2000, 1000) {

                                    public void onTick(long millisUntilFinished) {
                                        count.setText("seconds remaining: " + millisUntilFinished / 1000);
                                        //here you can have your logic to set text to edittext
                                    }

                                    public void onFinish() {
                                        hp=hp-1;
                                        if(hp==0) {
                                            AlertDialog alertDialog = new AlertDialog.Builder(Arena.this).create();
                                            alertDialog.setTitle("Alert");
                                            alertDialog.setMessage("You lose keep using less water!");
                                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            dialog.dismiss();
                                                            Intent intent = new Intent(Arena.this, Title.class);
                                                            startActivity(intent);
                                                        }
                                                    });
                                            alertDialog.show();
                                        }
                                        txt_hp.setText(Integer.toString(hp));
                                        count.setText("");
                                        enemy.setVisibility(View.VISIBLE);
                                        attackred.setVisibility(View.INVISIBLE);
                                    }

                                }.start();
                            }
                            else if (i1==4) {
                                flag=true;
                                enemy.setVisibility(View.INVISIBLE);
                                attackred.setVisibility(View.INVISIBLE);
                                rightred.setVisibility(View.INVISIBLE);
                                midred.setVisibility(View.VISIBLE);

                                new CountDownTimer(2000, 1000) {

                                    public void onTick(long millisUntilFinished) {
                                        count.setText("seconds remaining: " + millisUntilFinished / 1000);
                                        //here you can have your logic to set text to edittext
                                    }

                                    public void onFinish() {
                                        hp=hp-1;
                                        if(hp==0) {
                                            AlertDialog alertDialog = new AlertDialog.Builder(Arena.this).create();
                                            alertDialog.setTitle("Alert");
                                            alertDialog.setMessage("You lose keep using less water!");
                                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            dialog.dismiss();
                                                            Intent intent = new Intent(Arena.this, Title.class);
                                                            startActivity(intent);
                                                        }
                                                    });
                                            alertDialog.show();
                                        }
                                        txt_hp.setText(Integer.toString(hp));
                                        count.setText("");
                                        enemy.setVisibility(View.VISIBLE);
                                        midred.setVisibility(View.INVISIBLE);
                                    }

                                }.start();
                            }
                            else {
                                flag=true;
                                enemy.setVisibility(View.INVISIBLE);
                                attackred.setVisibility(View.INVISIBLE);
                                rightred.setVisibility(View.VISIBLE);

                                new CountDownTimer(2000, 1000) {

                                    public void onTick(long millisUntilFinished) {
                                        count.setText("seconds remaining: " + millisUntilFinished / 1000);
                                        //here you can have your logic to set text to edittext
                                    }

                                    public void onFinish() {
                                        hp=hp-1;
                                        if (hp==0) {
                                            Intent intent = new Intent(Arena.this, Title.class);
                                            startActivity(intent);
                                        }
                                        txt_hp.setText(Integer.toString(hp));
                                        count.setText("");
                                        enemy.setVisibility(View.VISIBLE);
                                        rightred.setVisibility(View.INVISIBLE);
                                    }

                                }.start();
                            }



                            if (endurance >= 3) {
                                endurance = endurance - 3;
                                str_endure = Integer.toString(endurance);
                                txt_endure.setText(str_endure);

                            }
                            if (endurance < 3) {
                                endurance = 0;
                                str_endure = Integer.toString(endurance);
                                txt_endure.setText(str_endure);





                        }


                            }
                        }
                    }


                else
                {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return super.onTouchEvent(event);
    }
//Boss Changes Randomly and Attacks



}
