package com.example.se2einzelphase;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

//Peter Söllnbauer, 11904589

public class NetworkThread extends Thread {

    String matNo;
    String result;

    NetworkThread(String matNo) {
        this.matNo = matNo;
    }

    public void run() {
        try {
            Socket client = new Socket("se2-isys.aau.at", 53212);
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out.writeBytes(matNo + '\n');

            result = in.readLine();

            client.close();

        } catch (Exception e) {
            result = "An error occured";
        }
    }
}
