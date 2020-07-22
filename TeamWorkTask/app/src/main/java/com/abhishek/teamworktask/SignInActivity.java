package com.abhishek.teamworktask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {
    private EditText userEmailLogin, userPasswordLogin;
    private Button btn_login;
    private TextView signUp_textView;
    private ProgressDialog mDialog;
    //Firebase
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        userEmailLogin = findViewById(R.id.emailLogin);
        userPasswordLogin = findViewById(R.id.passwordLogin);
        btn_login = findViewById(R.id.btn_login);
        signUp_textView = findViewById(R.id.sigup_textView);

        firebaseAuth = FirebaseAuth.getInstance();

        //Checking if the user is currently logged in or not
//        if (firebaseAuth.getCurrentUser()!=null){
//            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
//        }

        mDialog = new ProgressDialog(this);



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userEmailLogin.getText().toString().trim();
                String pass = userPasswordLogin.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    userEmailLogin.setError("Email Required...");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    userPasswordLogin.setError("Password Required...");
                    return;
                }
                mDialog.setMessage("Checking your credentials...");
                mDialog.show();
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Snackbar snackBar = Snackbar .make(findViewById(R.id.linLayoutMain), "Login Successful", Snackbar.LENGTH_LONG) .setAction("SUCCESS", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });
                            snackBar.show();
                            mDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        }else{
                            Snackbar snackBar = Snackbar .make(findViewById(R.id.linLayoutMain), "Login Failed", Snackbar.LENGTH_LONG) .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });
                            snackBar.show();
                            mDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });

        signUp_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this,SignUpActivity.class));
            }
        });
    }
}
