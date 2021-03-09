package com.example.se2einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button send = (Button) findViewById(R.id.buttonSend);
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startThread();
            }
        });

        Button calculate = (Button) findViewById(R.id.buttonCalculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSorted();
            }
        });
    }

    private void showSorted() {
        TextView result = findViewById(R.id.textViewresult);
        EditText input = findViewById(R.id.editTextMatNo);
        String matNo = input.getText().toString();

        String withoutPrime = removePrimeNums(matNo);
        String sorted = sort(withoutPrime);

        result.setText(sorted);

    }

    private String sort(String withoutPrime) {

        char tempArray[] = withoutPrime.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public static String
    removePrimeNums(String matNo) {
        StringBuilder sb = new StringBuilder(matNo);

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '2' || sb.charAt(i) == '3' || sb.charAt(i) == '5' || sb.charAt(i) == '7') {
                sb.deleteCharAt(i);
            }
        }
        return sb.toString();
    }

    public void startThread() {
        TextView result = findViewById(R.id.textViewresult);
        EditText input = findViewById(R.id.editTextMatNo);
        String matNo = input.getText().toString();

        NetworkThread n = new NetworkThread(matNo);
        n.start();

        try {
            n.join();
        } catch (Exception e) {
            result.setText("An error occured");
        }

        result.setText(n.result);

    }
}