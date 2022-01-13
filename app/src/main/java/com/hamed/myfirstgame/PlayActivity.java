package com.hamed.myfirstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity {

    Button btnOnOff, btnFindOthers, btnSendMessage;
    ListView listView;
    TextView readMessage;
    EditText writeMessage;
    WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_play);

        btnOnOff = (Button) findViewById(R.id.wifi);
        btnFindOthers = (Button) findViewById(R.id.findPlayers);
        btnSendMessage = (Button) findViewById(R.id.send);
        listView = (ListView) findViewById(R.id.peerListView);
        readMessage = (TextView) findViewById(R.id.highScore);
        writeMessage = (EditText) findViewById(R.id.editText);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        btnOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wifiManager.isWifiEnabled()) {
                    wifiManager.setWifiEnabled(false);
                } else {
                    wifiManager.setWifiEnabled(true);
                }
            }
        });

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlayActivity.this, GameActivity.class));
            }
        });
    }
}