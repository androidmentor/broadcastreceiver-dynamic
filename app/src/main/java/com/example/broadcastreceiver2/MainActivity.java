package com.example.broadcastreceiver2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String MY_BROADCAST_MSG = "com.example.broadcastreceiver2.message";
    private BroadcastReceiver mReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRegister = (Button) findViewById(R.id.btn_register_broadcast);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerBroadcastReceiver();
            }
        });

        Button btnUnregister = (Button) findViewById(R.id.btn_unregister_broadcast);
        btnUnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unregisterBroadcastReceiver();
            }
        });

        Button btnSend = (Button) findViewById(R.id.btn_send_broadcast);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MY_BROADCAST_MSG);
                sendBroadcast(intent);
            }
        });

    }


    private void registerBroadcastReceiver() {
        if (mReceiver != null) {
            return;
        }

        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MY_BROADCAST_MSG);
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals(MY_BROADCAST_MSG)) {
                    Toast.makeText(getApplicationContext(), "Receive intent: " + action, Toast.LENGTH_SHORT).show();
                }
            }
        };
        registerReceiver(mReceiver, intentFilter);
        Toast.makeText(getApplicationContext(), "Registered receiver", Toast.LENGTH_SHORT).show();
    }

    private void unregisterBroadcastReceiver() {
        if (mReceiver == null) {
            return;
        }

        unregisterReceiver(mReceiver);
        mReceiver = null;
        Toast.makeText(getApplicationContext(), "Unregistered receiver", Toast.LENGTH_SHORT).show();
    }
}
