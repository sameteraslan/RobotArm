package com.example.myapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorAdditionalInfo;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zerokol.views.JoystickView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements SensorEventListener{



    Button btn;
    TextView xVal, yVal, zVal;
    SensorManager sensorManager;
    Sensor gyroscope;
    int gyroValues[] = new int[]{0,0,0};
    private static final float NS2S = 1.0f / 1000000000.0f;
    private final float[] deltaRotationVector = new float[4];
    private float timestamp;
    private JoystickView joystick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        joystick = (JoystickView) findViewById(R.id.joystickView);
        xVal = (TextView) findViewById(R.id.xVal);
        yVal = (TextView) findViewById(R.id.yVal);
        zVal = (TextView) findViewById(R.id.zVal);
        btn = (Button) findViewById(R.id.button);

        joystick.setOnJoystickMoveListener(new JoystickView.OnJoystickMoveListener() {

            @Override
            public void onValueChanged(int angle, int power, int direction) {
                // TODO Auto-generated method stub
                //angleTextView.setText(" " + String.valueOf(angle) + "°");
                //powerTextView.setText(" " + String.valueOf(power) + "%");

            }
        }, JoystickView.DEFAULT_LOOP_INTERVAL);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);



        if (gyroscope != null) {
            sensorManager.registerListener((SensorEventListener) MainActivity.this, gyroscope, SensorManager.SENSOR_DELAY_GAME);

            Log.i("", "Sensor dinleniyor..");
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "{'phonetype':'N95','cat':'WP'}";
                System.out.println("tiklandi");
                new SendMessage().execute(s);
            }
        });


    }


    @Override
    public void onSensorChanged(final SensorEvent event) {
        /*
        Sensor sensor = event.sensor;
        if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {





            //new Thread(new Wait(event)).start();
            //xVal.setText(gyroValues[0]);
            //yVal.setText(gyroValues[1]);
            //zVal.setText(gyroValues[2]);
            System.out.println("Current Time" + (System.currentTimeMillis() - event.timestamp * 0.000001));
            System.out.println("Timestamp Nano: " + event.timestamp);
            System.out.println("Timestamp Second: " + event.timestamp * Math.pow(10, -9));
            xVal.setText("x = " + (int) (event.values[0] * event.timestamp * Math.pow(10, -9)));
            yVal.setText("y = " + (int) (event.values[1] * 57 * event.timestamp * Math.pow(10, -9)));
            zVal.setText("z = " + (int) (event.values[2] * 57.2957795 * event.timestamp * Math.pow(10, -9)));



            if (Math.abs(gyroValues[0] - (int) (event.values[0] * 57.2957795)) > 10) {
                gyroValues[0] = (int) (event.values[0] * 57.2957795);
                Log.i("xVal:", xVal.getText().toString());
            }
            if (Math.abs(gyroValues[1] - (int) (event.values[1] * 57.2957795)) > 10) {
                gyroValues[1] = (int) (event.values[1] * 57.2957795);
                Log.i("yVal:", yVal.getText().toString());
            }
            if (Math.abs(gyroValues[2] - (int) (event.values[2] * 57.2957795)) > 10) {
                gyroValues[2] = (int) (event.values[2] * 57.2957795);
                Log.i("zVal:", zVal.getText().toString());
            }





            //gyroValues[1] = (int) (event.values[1] * 57.2957795);
            //gyroValues[2] = (int) (event.values[2] * 57.2957795);



            //new SendMessage().execute(xVal.getText().toString());
        }
    */
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
