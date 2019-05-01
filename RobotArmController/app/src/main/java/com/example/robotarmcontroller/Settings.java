package com.example.robotarmcontroller;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;

public class Settings extends AppCompatActivity {

    static EditText ip, port;
    Button connect, disconnect;
    static Socket socket;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ip = (EditText) findViewById(R.id.txtIP);
        port = (EditText) findViewById(R.id.txtPort);
        context = this;

        connect = (Button) findViewById(R.id.btncon);
        disconnect = (Button) findViewById(R.id.btnDc);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new mySocket().execute(ip.getText().toString(), port.getText().toString());
                Toast.makeText(context, "Connected", Toast.LENGTH_LONG);
            }
        });

        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    socket.close();
                    Toast.makeText(context, "Disconnected", Toast.LENGTH_LONG);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public class mySocket extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... strings) {
            try {
                socket = new Socket(strings[0], Integer.parseInt(strings[1]));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}


