package com.abhishek.teamworktask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private EditText userEmailRegistration, userPasswordRegistration,nameRegistration,confirmPasswordRegistration;
    private Button btn_registration;
    private TextView login_textView;
    private ProgressDialog mDialog;
    private String email,password,userName,confirmPassword;

    //Firebase....
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initialize();

        firebaseAuth = FirebaseAuth.getInstance();

        mDialog = new ProgressDialog(this);

        btn_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    //upload the data to the firebase
                    String user_email = userEmailRegistration.getText().toString().trim();
                    String user_pass = userPasswordRegistration.getText().toString().trim();

                    mDialog.setMessage("Signing You Up!");
                    mDialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                mDialog.dismiss();
                                sendUserData();
                                Toast.makeText(getApplicationContext(),"SignUp Successful",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(SignUpActivity.this,HomeActivity.class));
                            }else{
                                mDialog.dismiss();
                                Toast.makeText(getApplicationContext(),"SignUp Failed",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

        //onclick for the already have an account
        login_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignInActivity.class));
            }
        });
    }
    private void initialize(){
        userEmailRegistration = findViewById(R.id.emailRegistration);
        userPasswordRegistration = findViewById(R.id.passwordRegistration);
        btn_registration = findViewById(R.id.btn_registration);
        login_textView = findViewById(R.id.login_textView);
        nameRegistration = findViewById(R.id.nameRegistration);
        confirmPasswordRegistration = findViewById(R.id.confirmPasswordRegistration);
    }
    private Boolean validate() {
        Boolean result = false;

         email = userEmailRegistration.getText().toString().trim();
         password = userPasswordRegistration.getText().toString().trim();
         userName = nameRegistration.getText().toString().trim();
         confirmPassword = confirmPasswordRegistration.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty() || userName.isEmpty() || confirmPassword.isEmpty()) {
            Snackbar snackBar = Snackbar .make(findViewById(R.id.linLayoutMain), "Field(s) cannot be empty!", Snackbar.LENGTH_LONG) .setAction("RETRY", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
            snackBar.show();
        }else if (!email.contains("@gmail.com")){
            Snackbar snackBar = Snackbar .make(findViewById(R.id.linLayoutMain), "Not a valid email(Eg. xyz@gmail.com)", Snackbar.LENGTH_LONG) .setAction("RETRY", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
            snackBar.show();
        }else if (!password.equals(confirmPassword)){
            Snackbar snackBar = Snackbar .make(findViewById(R.id.linLayoutMain), "Password and confirm password did not match", Snackbar.LENGTH_LONG) .setAction("RETRY", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
            snackBar.show();
        }else{
            result = true;
        }
          return result;
        }

        private void sendUserData(){
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
            userProfile userProfile = new userProfile(email,password,userName,confirmPassword);
            myRef.setValue(userProfile);
        }
    }