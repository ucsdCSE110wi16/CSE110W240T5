package com.xu.shawn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class PreferenceActivity extends AppCompatActivity implements View.OnClickListener{

    private SeekBar s1;
    private SeekBar s2;
    private SeekBar s3;
    private SeekBar s4;
    private SeekBar s5;
    private SeekBar s6;
    private SeekBar s7;
    private SeekBar s8;
    private SeekBar s9;
    private SeekBar s10;
    private SeekBar s11;
    private SeekBar s12;

    private Button btnGoChooseOne;

//    private TextView textView;

    private int seekProgress1;
    private int seekProgress2;
    private int seekProgress3;
    private int seekProgress4;
    private int seekProgress5;
    private int seekProgress6;
    private int seekProgress7;
    private int seekProgress8;
    private int seekProgress9;
    private int seekProgress10;
    private int seekProgress11;
    private int seekProgress12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        s1 = (SeekBar)findViewById(R.id.sB1);
        s2 = (SeekBar)findViewById(R.id.sB2);
        s3 = (SeekBar)findViewById(R.id.sB3);
        s4 = (SeekBar)findViewById(R.id.sB4);
        s5 = (SeekBar)findViewById(R.id.sB5);
        s6 = (SeekBar)findViewById(R.id.sB6);
        s7 = (SeekBar)findViewById(R.id.sB7);
        s8 = (SeekBar)findViewById(R.id.sB8);
        s9 = (SeekBar)findViewById(R.id.sB9);
        s10 = (SeekBar)findViewById(R.id.sB10);
        s11 = (SeekBar)findViewById(R.id.sB11);
        s12 = (SeekBar)findViewById(R.id.sB12);

        btnGoChooseOne = (Button)findViewById(R.id.btnSubmit);
        btnGoChooseOne.setOnClickListener(this);

//        textView = (TextView)findViewById(R.id.textShow);

        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekProgress1 = seekBar.getProgress();

                if (seekProgress1 < 13) {
                    seekBar.setProgress(0);
                    seekProgress1 = seekBar.getProgress();
                } else if (seekProgress1 >= 13 && seekProgress1 < 38) {
                    seekBar.setProgress(25);
                    seekProgress1 = seekBar.getProgress();
                } else if (seekProgress1 >= 38 && seekProgress1 < 63) {
                    seekBar.setProgress(50);
                    seekProgress1 = seekBar.getProgress();
                } else if (seekProgress1 >= 63 && seekProgress1 < 88) {
                    seekBar.setProgress(75);
                    seekProgress1 = seekBar.getProgress();
                } else if (seekProgress1 >= 88) {
                    seekBar.setProgress(100);
                    seekProgress1 = seekBar.getProgress();
                }

//                textView.setText("Now is " + seekProgress1);
            }
        });

        s2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekProgress2 = seekBar.getProgress();

                if (seekProgress2 < 13) {
                    seekBar.setProgress(0);
                    seekProgress2= seekBar.getProgress();
                } else if (seekProgress2 >= 13 && seekProgress2 < 38) {
                    seekBar.setProgress(25);
                    seekProgress2 = seekBar.getProgress();
                } else if (seekProgress2 >= 38 && seekProgress2 < 63) {
                    seekBar.setProgress(50);
                    seekProgress2 = seekBar.getProgress();
                } else if (seekProgress2 >= 63 && seekProgress2 < 88) {
                    seekBar.setProgress(75);
                    seekProgress2 = seekBar.getProgress();
                } else if (seekProgress2 >= 88) {
                    seekBar.setProgress(100);
                    seekProgress2 = seekBar.getProgress();
                }
            }
        });

        s3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekProgress3 = seekBar.getProgress();

                if (seekProgress3 < 13) {
                    seekBar.setProgress(0);
                    seekProgress3 = seekBar.getProgress();
                } else if (seekProgress3 >= 13 && seekProgress3 < 38) {
                    seekBar.setProgress(25);
                    seekProgress3 = seekBar.getProgress();
                } else if (seekProgress3 >= 38 && seekProgress3 < 63) {
                    seekBar.setProgress(50);
                    seekProgress3 = seekBar.getProgress();
                } else if (seekProgress3 >= 63 && seekProgress3 < 88) {
                    seekBar.setProgress(75);
                    seekProgress3 = seekBar.getProgress();
                } else if (seekProgress3 >= 88) {
                    seekBar.setProgress(100);
                    seekProgress3 = seekBar.getProgress();
                }
            }
        });

        s4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekProgress4 = seekBar.getProgress();

                if (seekProgress4 < 13) {
                    seekBar.setProgress(0);
                    seekProgress4 = seekBar.getProgress();
                } else if (seekProgress4 >= 13 && seekProgress4 < 38) {
                    seekBar.setProgress(25);
                    seekProgress4 = seekBar.getProgress();
                } else if (seekProgress4 >= 38 && seekProgress4 < 63) {
                    seekBar.setProgress(50);
                    seekProgress4 = seekBar.getProgress();
                } else if (seekProgress4 >= 63 && seekProgress4 < 88) {
                    seekBar.setProgress(75);
                    seekProgress4 = seekBar.getProgress();
                } else if (seekProgress4 >= 88) {
                    seekBar.setProgress(100);
                    seekProgress4 = seekBar.getProgress();
                }
            }
        });

        s5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekProgress5 = seekBar.getProgress();

                if (seekProgress5 < 13) {
                    seekBar.setProgress(0);
                    seekProgress5 = seekBar.getProgress();
                } else if (seekProgress5 >= 13 && seekProgress5 < 38) {
                    seekBar.setProgress(25);
                    seekProgress5 = seekBar.getProgress();
                } else if (seekProgress5 >= 38 && seekProgress5 < 63) {
                    seekBar.setProgress(50);
                    seekProgress5 = seekBar.getProgress();
                } else if (seekProgress5 >= 63 && seekProgress5 < 88) {
                    seekBar.setProgress(75);
                    seekProgress5 = seekBar.getProgress();
                } else if (seekProgress5 >= 88) {
                    seekBar.setProgress(100);
                    seekProgress5 = seekBar.getProgress();
                }
            }
        });

        s6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekProgress6 = seekBar.getProgress();

                if (seekProgress6 < 13) {
                    seekBar.setProgress(0);
                    seekProgress6 = seekBar.getProgress();
                } else if (seekProgress6 >= 13 && seekProgress6 < 38) {
                    seekBar.setProgress(25);
                    seekProgress6 = seekBar.getProgress();
                } else if (seekProgress6 >= 38 && seekProgress6 < 63) {
                    seekBar.setProgress(50);
                    seekProgress6 = seekBar.getProgress();
                } else if (seekProgress6 >= 63 && seekProgress6 < 88) {
                    seekBar.setProgress(75);
                    seekProgress6 = seekBar.getProgress();
                } else if (seekProgress6 >= 88) {
                    seekBar.setProgress(100);
                    seekProgress6 = seekBar.getProgress();
                }
            }
        });

        s7.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekProgress7 = seekBar.getProgress();

                if (seekProgress7 < 13) {
                    seekBar.setProgress(0);
                    seekProgress7 = seekBar.getProgress();
                } else if (seekProgress7 >= 13 && seekProgress7 < 38) {
                    seekBar.setProgress(25);
                    seekProgress7 = seekBar.getProgress();
                } else if (seekProgress7 >= 38 && seekProgress7 < 63) {
                    seekBar.setProgress(50);
                    seekProgress7 = seekBar.getProgress();
                } else if (seekProgress7 >= 63 && seekProgress7 < 88) {
                    seekBar.setProgress(75);
                    seekProgress7 = seekBar.getProgress();
                } else if (seekProgress7 >= 88) {
                    seekBar.setProgress(100);
                    seekProgress7 = seekBar.getProgress();
                }
            }
        });

        s8.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekProgress8 = seekBar.getProgress();

                if (seekProgress8 < 13) {
                    seekBar.setProgress(0);
                    seekProgress8 = seekBar.getProgress();
                } else if (seekProgress8 >= 13 && seekProgress8 < 38) {
                    seekBar.setProgress(25);
                    seekProgress8 = seekBar.getProgress();
                } else if (seekProgress8 >= 38 && seekProgress8 < 63) {
                    seekBar.setProgress(50);
                    seekProgress8 = seekBar.getProgress();
                } else if (seekProgress8 >= 63 && seekProgress8 < 88) {
                    seekBar.setProgress(75);
                    seekProgress8 = seekBar.getProgress();
                } else if (seekProgress8 >= 88) {
                    seekBar.setProgress(100);
                    seekProgress8 = seekBar.getProgress();
                }
            }
        });

        s9.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekProgress9 = seekBar.getProgress();

                if (seekProgress9 < 13) {
                    seekBar.setProgress(0);
                    seekProgress9 = seekBar.getProgress();
                } else if (seekProgress9 >= 13 && seekProgress9 < 38) {
                    seekBar.setProgress(25);
                    seekProgress9 = seekBar.getProgress();
                } else if (seekProgress9 >= 38 && seekProgress9 < 63) {
                    seekBar.setProgress(50);
                    seekProgress9 = seekBar.getProgress();
                } else if (seekProgress9 >= 63 && seekProgress9 < 88) {
                    seekBar.setProgress(75);
                    seekProgress9 = seekBar.getProgress();
                } else if (seekProgress9 >= 88) {
                    seekBar.setProgress(100);
                    seekProgress9 = seekBar.getProgress();
                }
            }
        });

        s10.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekProgress10 = seekBar.getProgress();

                if (seekProgress10 < 13) {
                    seekBar.setProgress(0);
                    seekProgress10 = seekBar.getProgress();
                } else if (seekProgress10 >= 13 && seekProgress10 < 38) {
                    seekBar.setProgress(25);
                    seekProgress10 = seekBar.getProgress();
                } else if (seekProgress10 >= 38 && seekProgress10 < 63) {
                    seekBar.setProgress(50);
                    seekProgress10 = seekBar.getProgress();
                } else if (seekProgress10 >= 63 && seekProgress10 < 88) {
                    seekBar.setProgress(75);
                    seekProgress10 = seekBar.getProgress();
                } else if (seekProgress10 >= 88) {
                    seekBar.setProgress(100);
                    seekProgress10 = seekBar.getProgress();
                }
            }
        });

        s11.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekProgress11 = seekBar.getProgress();

                if (seekProgress11 < 13) {
                    seekBar.setProgress(0);
                    seekProgress11 = seekBar.getProgress();
                } else if (seekProgress11 >= 13 && seekProgress11 < 38) {
                    seekBar.setProgress(25);
                    seekProgress11 = seekBar.getProgress();
                } else if (seekProgress11 >= 38 && seekProgress11 < 63) {
                    seekBar.setProgress(50);
                    seekProgress11 = seekBar.getProgress();
                } else if (seekProgress11 >= 63 && seekProgress11 < 88) {
                    seekBar.setProgress(75);
                    seekProgress11 = seekBar.getProgress();
                } else if (seekProgress11 >= 88) {
                    seekBar.setProgress(100);
                    seekProgress11 = seekBar.getProgress();
                }
            }
        });

        s12.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekProgress12 = seekBar.getProgress();

                if (seekProgress12 < 13) {
                    seekBar.setProgress(0);
                    seekProgress12 = seekBar.getProgress();
                } else if (seekProgress12 >= 13 && seekProgress12 < 38) {
                    seekBar.setProgress(25);
                    seekProgress12 = seekBar.getProgress();
                } else if (seekProgress12 >= 38 && seekProgress12 < 63) {
                    seekBar.setProgress(50);
                    seekProgress12 = seekBar.getProgress();
                } else if (seekProgress12 >= 63 && seekProgress12 < 88) {
                    seekBar.setProgress(75);
                    seekProgress12 = seekBar.getProgress();
                } else if (seekProgress12 >= 88) {
                    seekBar.setProgress(100);
                    seekProgress12 = seekBar.getProgress();
                }
            }
        });

























//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ChooseOneActivity.class);
        int []values = {seekProgress1, seekProgress2, seekProgress3, seekProgress4,
                seekProgress5,seekProgress6,seekProgress7,seekProgress8,seekProgress9,
                seekProgress10, seekProgress11, seekProgress12};
        intent.putExtra("values", values);
        startActivity(intent);
    }



}
