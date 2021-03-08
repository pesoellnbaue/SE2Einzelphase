package com.example.se2einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button send = (Button) findViewById(R.id.buttonSend);
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) { startThread();
            }
        });
    }

    public void startThread() {
        TextView result = findViewById(R.id.textViewresult);
        EditText input = findViewById(R.id.editTextMatNo);
        String matNo = input.getText().toString();

        NetworkThread n = new NetworkThread(matNo);
        n.start();

        try {
            n.join();
        }
        catch (Exception e)
        {
            result.setText("An error occured");
        }

        result.setText(n.result);

        /*try {

            Socket client = new Socket("se2-isys.aau.at", 53212);
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out.writeBytes(matNo);

            result.setText(in.readLine());

            client.close();

        } catch (Exception e) {
            result.setText(in.readLine());
        }*/


    }
}