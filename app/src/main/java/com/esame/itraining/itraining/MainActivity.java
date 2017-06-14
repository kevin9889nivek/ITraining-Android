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

//import com.android.colorpicker.ColorPickerDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

import static android.R.attr.numColumns;
import static android.R.attr.tag;


public class MainActivity extends AppCompatActivity {


    //public final static String PREF_IP = "PREF_IP_ADDRESS";
    //public final static String PREF_PORT = "PREF_PORT_NUMBER";
    private EditText editTextIPAddress, editTextPortNumber;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);


    }


    public void buttonOnClick(View v) {
// do something when the button is clicked

        try{
// Creating new socket connection to the IP (first parameter) and its opened port (second parameter)
            EditText mEditTocchi= (EditText) findViewById(R.id.editTocchi);
            int tocchi=Integer.parseInt(mEditTocchi.getText().toString());

            //EditText mEditPorta= (EditText) findViewById(R.id.name2);
            //int porta= Integer.parseInt(mEditPorta.getText().toString());

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
            char crgbred= (char) rgbred;
            char crgbgreen= (char) green;
            char crgbblue= (char) blue;
            int idistanza= (int) distanza;
            char cdistanza= (char) distanza;
            //EditText mEditSSID= (EditText) findViewById(R.id.nameSsid);
            //String ssid=mEditSSID.getText().toString();

            //EditText mEditPassword= (EditText) findViewById(R.id.namePassword);
            //String password=mEditPassword.getText().toString();

            // get the pin number
            //String parameterValue = "";
            // get the ip address
            //String ipAddress = editTextIPAddress.getText().toString().trim();
            // get the port number
            //String portNumber = editTextPortNumber.getText().toString().trim();


            // save the IP address and port for the next time the app is used
            //editor.putString(PREF_IP,ipAddress); // set the ip address value to save
            //editor.putString(PREF_PORT,portNumber); // set the port number to save
            //editor.commit(); // save the IP and PORT


            Socket s = new Socket("192.168.4.1", 3000);

            // Initialize output stream to write message to the socket stream

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            Context context = getApplicationContext();
            CharSequence text = "Inviato";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

// Write message to stream
            out.write(crgbred);
// Flush the data from the stream to indicate end of message
            out.flush();
            out.write(crgbgreen);
            out.flush();
            out.write(crgbblue);
            out.flush();
            out.write(cdistanza);
            out.flush();
            out.write(tocchi);
            out.flush();
// Close the output stream
            out.close();
// Close the socket connection
            s.close();
        }
        catch(Exception ex){
        // Handle exceptions
            //Toast.makeText(this, "Si è verificata un'eccezione", Toast.LENGTH_SHORT).show();
        }
    }


}
