package com.example.robotarmcontroller;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SendMessage extends AsyncTask<String, Void, Void> {
    private Exception exception;

    @Override
    protected Void doInBackground(String... strings) {
        try {
            try {

                PrintWriter outToServer = new PrintWriter(new OutputStreamWriter(Settings.socket.getOutputStream()));
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
