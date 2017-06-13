package com.esame.itraining.itraining;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class Login extends AppCompatActivity implements View.OnClickListener{

    private Button btnLogin;
    private EditText editEmail;
    private EditText editPassword;
    private TextView textRegistrati;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog= new ProgressDialog(this);
        firebaseAuth= FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() !=null)
        {
            //l'utente è già loggato
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
        editEmail= (EditText) findViewById(R.id.editEmail);
        editPassword= (EditText) findViewById(R.id.editPassword);
        btnLogin= (Button) findViewById(R.id.btnLogin);
        textRegistrati= (TextView) findViewById(R.id.textRegistrati);

        btnLogin.setOnClickListener(this);
        textRegistrati.setOnClickListener(this);

    }

    private void Login(){
        String email= editEmail.getText().toString().trim();
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

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            //l'utente si è registrato
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {

        if(v==btnLogin)
        {
            Login();

        }
        if(v==textRegistrati)
        {
            startActivity(new Intent(this,Register.class));
        }
    }
}
