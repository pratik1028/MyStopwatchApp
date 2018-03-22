package com.example.application.mystopwatch;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

public class activity_stopwatch extends Activity {
    private  int seconds=0;
    private boolean running;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_stopwatch);
        if(savedInstanceState!=null){
            seconds=savedInstanceState.getInt("second");
            running=savedInstanceState.getBoolean("running");
        }
        runTimer();

    }
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);
    }
    public void onClickStart(View view){
        running=true;

    }
    public void onClickStop(View view){
        running=false;

    }
    public void onClickReset(View view){
        running=false;
        seconds=0;

    }
    public void onClickLap(View view){

        running=true;
        final TextView timeView1=(TextView)findViewById(R.id.time_view1);

                int hours=seconds/3600;
                int minutes=(seconds%3600)/60;
                int secs=seconds%60;
                String time=String.format("%d:%02d:%02d",hours,minutes,secs);
                timeView1.setText(time);

        seconds=0;
    }
    private void runTimer(){
        final TextView timeView=(TextView)findViewById(R.id.time_view);
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds/3600;
                int minutes=(seconds%3600)/60;
                int secs=seconds%60;
                String time=String.format("%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });


    }
}
