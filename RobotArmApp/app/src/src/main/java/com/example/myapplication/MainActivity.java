package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import io.github.controlwear.virtual.joystick.android.JoystickView;


public class MainActivity extends AppCompatActivity {

    //EditText txt;
    TextView txt,txt1, txt2, txt3, txt4, txt5,txt6,txt7;
    SeekBar seekBar1, seekBar2;
    Button btn;
    String message = "";
    String mList[] = new String[]{"0$100","1$100","2$100","3$100","4$100","5$100"};
    int joyStick1Eski[] = new int[]{0, 0};
    int joyStick2Eski[] = new int[]{0, 0};

    void sendMessage(int motorNo, int degree) {
        mList[motorNo] = motorNo  + "$" + degree;
        String m = "";
        for (int i = 0; i < mList.length - 1; i++) {
            m += mList[i] + "/";
        }
        m += mList[mList.length - 1];
        new SendMessage().execute(m);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar1 = (SeekBar) findViewById(R.id.seekBar);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        txt =  (TextView) findViewById(R.id.txt);
        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView) findViewById(R.id.txt3);
        txt4 = (TextView) findViewById(R.id.txt4);
        txt5 = (TextView) findViewById(R.id.txt5);
        txt6 = (TextView) findViewById(R.id.txt6);
        txt7 = (TextView) findViewById(R.id.txt7);
//        btn = (Button) findViewById(R.id.button);


        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sendMessage(5, progress);
                System.out.println("progress: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        JoystickView joystick = (JoystickView) findViewById(R.id.joystick);
       // joystick.setAutoReCenterButton(false);
        joystick.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                // do whatever you want
                double x = Math.cos(Math.toRadians(angle))*strength + 100;
                double y = Math.sin(Math.toRadians(angle))*strength + 100;

                if(angle==90 || angle==270){
                    x=100;
                }else if (angle == 180){
                    y=100;
                }
                txt1.setText("angle: " + angle);
                txt4.setText("str: " + strength);
                txt5.setText("x : " + (x));
                //System.out.println(Math.toRadians(angle));

                txt2.setText("y : " + (y));
                if (Math.abs(joyStick1Eski[0] - x) > 10) {
                    joyStick1Eski[0] = (int)x;
                    sendMessage(0, (int) x);
                }

                if (Math.abs(joyStick1Eski[1] - y) > 10) {
                    joyStick1Eski[1] = (int) y;
                    sendMessage(3, (int)y);
                }

            }
        });

    /*    btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SendMessage().execute(txt.getText().toString());
            }
        });*/
        JoystickView joystick2 = (JoystickView) findViewById(R.id.joystick2);
       // joystick2.setAutoReCenterButton(false);

        joystick2.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                // do whatever you want
                double x = Math.cos(Math.toRadians(angle))*strength + 100;
                double y = Math.sin(Math.toRadians(angle))*strength + 100;
                if(angle==90 || angle==270){
                    x=100;
                }else if (angle == 180){
                    y=100;
                }
                txt.setText("angle: " + angle);
                //  txt2.setText("strength: " + strength);
                txt7.setText("x : " + (x));
                //System.out.println(Math.toRadians(angle));

                txt6.setText("y : " + (y));
                txt3.setText("str  : " + strength);

                if (Math.abs(joyStick2Eski[0] - x) > 10) {
                    joyStick2Eski[0] = (int)x;
                    sendMessage(4, (int) x);
                }

                if (Math.abs(joyStick2Eski[1] - y) > 10) {
                    joyStick2Eski[1] = (int) y;
                    sendMessage(0, (int)y);
                }

            }
        });



    }
}
