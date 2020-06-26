package com.qntm.wificonnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.qntm.qnwificonnection.Qnwificonnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Qnwificonnection qnwificonnection = new Qnwificonnection(this);
        qnwificonnection.Connect("Hotsport","","3300145370","7602");
        //Log.d("status",status);
    }
}
