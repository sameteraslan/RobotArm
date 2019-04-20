package com.example.myapplication;

import android.app.Application;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.text.format.Formatter;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import static android.content.Context.WIFI_SERVICE;
import static android.support.v4.content.ContextCompat.getSystemService;


public class SendMessage extends AsyncTask<String, Void, Void> {
    private Exception exception;
    @Override
    protected Void doInBackground(String... strings) {
        try {
            try {
                Socket socket = new Socket("172.16.153.30", 8888);
                PrintWriter outToServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                outToServer.print(strings[0]);
                outToServer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            this.exception = e;
            return null;
        }

        return null;
    }
}
