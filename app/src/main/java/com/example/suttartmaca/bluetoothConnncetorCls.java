package com.example.suttartmaca;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class bluetoothConnncetorCls {

    public static class CreateConnectThread extends Thread {
        public CreateConnectThread(BluetoothAdapter bluetoothAdapter, String address) {
            BluetoothDevice bluetoothDevice = bluetoothAdapter.getRemoteDevice(address);
            BluetoothSocket tmp = null;
            try {
                tmp = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(bluetoothDevice.getUuids()[0].getUuid());
            } catch (IOException e) {
                Log.e("ContentValues", "Socket's create() method failed", e);
            }
            MainActivity.mmSocket = tmp;
        }

        public void run() {
            BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
            try {
                MainActivity.mmSocket.connect();
                Log.e("Status", "Device connected");
                MainActivity.handler.obtainMessage(1, 1, -1).sendToTarget();
                MainActivity.connectedThread = new ConnectedThread(MainActivity.mmSocket);
                MainActivity.connectedThread.run();
            } catch (IOException e) {
                try {
                    MainActivity.mmSocket.close();
                    Log.e("Status", "Cannot connect to device");
                    MainActivity.handler.obtainMessage(1, -1, -1).sendToTarget();
                } catch (IOException closeException) {
                    Log.e("ContentValues", "Could not close the client socket", closeException);
                }
            }
        }

        public void cancel() {
            try {
                MainActivity.mmSocket.close();
            } catch (IOException e) {
                Log.e("ContentValues", "Could not close the client socket", e);
            }
        }
    }

    public static class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private final BluetoothSocket mmSocket;

        public ConnectedThread(BluetoothSocket socket) {
            this.mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
            }
            this.mmInStream = tmpIn;
            this.mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[1024];
            int bytes = 0;
            while (true) {
                try {
                    buffer[bytes] = (byte) this.mmInStream.read();
                    if (buffer[bytes] == 120) {
                        MainActivity.handler.obtainMessage(2, new String(buffer, 0, bytes)).sendToTarget();
                        bytes = 0;
                    } else {
                        bytes++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }

        public void write(String input) {
            try {
                this.mmOutStream.write(input.getBytes());
            } catch (IOException e) {
                Log.e("Send Error", "Unable to send message", e);
            }
        }

        public void cancel() {
            try {
                this.mmSocket.close();
            } catch (IOException e) {
            }
        }
    }
}
