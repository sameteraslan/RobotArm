package com.example.robotarmcontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {


    SeekBar servo0, servo1, servo2, servo3, servo4, servo5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        servo0 = (SeekBar) findViewById(R.id.servo0);
        servo1 = (SeekBar) findViewById(R.id.servo1);
        servo2 = (SeekBar) findViewById(R.id.servo2);
        servo3 = (SeekBar) findViewById(R.id.servo3);
        servo4 = (SeekBar) findViewById(R.id.servo4);
        servo5 = (SeekBar) findViewById(R.id.servo5);

        servo0.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                new SendMessage().execute("0$" + (seekBar.getProgress() + 500) + "$");
            }
        });

        servo1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                new SendMessage().execute("1$" + (seekBar.getProgress() + 700) + "$");
            }
        });

        servo2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                new SendMessage().execute("2$" + (seekBar.getProgress() + 500) + "$");
            }
        });

        servo3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                new SendMessage().execute("3$" + (seekBar.getProgress() + 500) + "$");
            }
        });

        servo4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                new SendMessage().execute("4$" + (seekBar.getProgress() + 500) + "$");
            }
        });

        servo5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //new SendMessage().execute("5$" + (progress + 1360) + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                new SendMessage().execute("5$" + (seekBar.getProgress() + 1360) + "$");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("tıklandıı");
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
        return true;
    }
}
