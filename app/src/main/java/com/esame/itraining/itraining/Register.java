package com.esame.itraining.itraining;

import android.content.Intent;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static java.lang.Thread.sleep;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private Button btnRegistrati;
    private EditText editEmail;
    private EditText editPassword;
    private TextView textLogin;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);

        btnRegistrati= (Button) findViewById(R.id.btnRegistrati);

        editEmail= (EditText) findViewById(R.id.editEmail);

        editPassword= (EditText) findViewById(R.id.editPassword);

        textLogin= (TextView) findViewById(R.id.textLogin);

        btnRegistrati.setOnClickListener(this);
        textLogin.setOnClickListener(this);
    }

    private void  Registrazione(){
        String email=editEmail.getText().toString().trim();
        String password= editPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Inserisci la tua email", Toast.LENGTH_SHORT).show();

            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Inserisci la password", Toast.LENGTH_SHORT).show();

            return;
        }

        progressDialog.setMessage("Registrazione in corso");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            //l'utente è registrato
                            Toast.makeText(Register.this, "Registrazione completata", Toast.LENGTH_SHORT).show();
                            try {
                                sleep(3000);
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                finish();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                        else
                        {
                            Toast.makeText(Register.this, "Registrazione fallita", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    @Override
    public void onClick(View v) {

        if(v==btnRegistrati)
        {
            Registrazione();
        }
        if(v==textLogin)
        {
            startActivity(new Intent(this,Login.class));
        }
    }
}
