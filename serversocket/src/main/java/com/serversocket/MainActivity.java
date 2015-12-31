package com.serversocket;

import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mTvMessage = ((TextView) findViewById(R.id.tv_message));

        LocalServerSocket serverSocket = null;
        LocalSocket socket = null;
        try {
            serverSocket = new LocalServerSocket("Domain");
            socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            byte [] bytes = new byte[1024];
            int length = 0;
            Toast.makeText(this, " connected success ", Toast.LENGTH_SHORT).show();
            while( ( length = inputStream.read(bytes)) != -1){
                Toast.makeText(this, length + "", Toast.LENGTH_SHORT).show();
                mTvMessage.append(String.valueOf(inputStream.read(bytes, 0, length)));
                mTvMessage.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if(serverSocket != null){
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
