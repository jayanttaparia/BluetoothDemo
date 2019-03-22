package com.jayanttaparia.bluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter BA;

    public void turnBluetoothOff(View view){

        BA.disable();

        if (BA.isEnabled()){
            Toast.makeText(getApplicationContext(), "Bluetooth could not be disabled", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "Bluetooth turned Off", Toast.LENGTH_SHORT).show();
        }

    }

    public void findDiscoverableDevices(View view){
        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivity(i);
    }

    public void viewPairedDevices(View view){

        Set<BluetoothDevice> set = BA.getBondedDevices();
        ListView pairedDevicesListView = (ListView)findViewById(R.id.pairedDevicesListView);
        ArrayList pairedDevicesArrayList = new ArrayList();

        for (BluetoothDevice bluetoothDevice : set){
            pairedDevicesArrayList.add(bluetoothDevice.getName());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pairedDevicesArrayList);
        pairedDevicesListView.setAdapter(arrayAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(BA.isEnabled()){
            Toast.makeText(getApplicationContext(), "Bluetooth is ON", Toast.LENGTH_SHORT).show();
        }else {
            // this will attempt to turn on the bluetooth
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(intent);

            if (BA.isEnabled()){
                Toast.makeText(getApplicationContext(), "Bluetooth has been turned ON", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
