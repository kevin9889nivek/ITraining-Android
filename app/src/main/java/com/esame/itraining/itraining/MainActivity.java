package com.esame.itraining.itraining;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.content.SharedPreferences;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;



public class MainActivity extends AppCompatActivity {


    public final static String PREF_IP = "PREF_IP_ADDRESS";
    public final static String PREF_PORT = "PREF_PORT_NUMBER";
    private EditText editTextIPAddress, editTextPortNumber;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);

        sharedPreferences = getSharedPreferences("HTTP_HELPER_PREFS", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editTextIPAddress = (EditText)findViewById(R.id.name);
        editTextPortNumber = (EditText)findViewById(R.id.name2);


        editTextIPAddress.setText(sharedPreferences.getString(PREF_IP,""));
        editTextPortNumber.setText(sharedPreferences.getString(PREF_PORT,""));
    }


    public void buttonOnClick(View v) {
// do something when the button is clicked

        try{
// Creating new socket connection to the IP (first parameter) and its opened port (second parameter)
            EditText mEditIp= (EditText) findViewById(R.id.name);
            String ip=mEditIp.getText().toString();

            EditText mEditPorta= (EditText) findViewById(R.id.name2);
            int porta= Integer.parseInt(mEditPorta.getText().toString());

            EditText mEditDistanza= (EditText) findViewById(R.id.TextDistanza);
            float distanza= Float.parseFloat(mEditDistanza.getText().toString());

            SeekBar seekbarRed= (SeekBar) findViewById(R.id.seekBarRed);
            double red=seekbarRed.getProgress()*2.55;

            SeekBar seekbarGreen = (SeekBar) findViewById(R.id.seekBarGreen);
            double green=seekbarGreen.getProgress()*2.55;

            SeekBar seekbarBlue= (SeekBar) findViewById(R.id.seekBarBlue);
            double blue=seekbarBlue.getProgress()*2.55;

            int rgbred= (int) red;
            int rgbgreen= (int) green;
            int rgbblue= (int) blue;

            EditText mEditSSID= (EditText) findViewById(R.id.nameSsid);
            String ssid=mEditSSID.getText().toString();

            EditText mEditPassword= (EditText) findViewById(R.id.namePassword);
            String password=mEditPassword.getText().toString();

            // get the pin number
            String parameterValue = "";
            // get the ip address
            String ipAddress = editTextIPAddress.getText().toString().trim();
            // get the port number
            String portNumber = editTextPortNumber.getText().toString().trim();


            // save the IP address and port for the next time the app is used
            editor.putString(PREF_IP,ipAddress); // set the ip address value to save
            editor.putString(PREF_PORT,portNumber); // set the port number to save
            editor.commit(); // save the IP and PORT


            Socket s = new Socket(ip, porta);

// Initialize output stream to write message to the socket stream
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            String outMsg = "";

            outMsg = ssid + password + rgbred + rgbgreen + rgbblue + distanza;

            Context context = getApplicationContext();
            CharSequence text = "Inviato";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

// Write message to stream
            out.write(outMsg);

// Flush the data from the stream to indicate end of message
            out.flush();

// Close the output stream
            out.close();

// Close the socket connection
            s.close();
        }

        catch(Exception ex){
        // Handle exceptions
        }
    }


}
