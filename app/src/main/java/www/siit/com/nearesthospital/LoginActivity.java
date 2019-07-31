package www.siit.com.nearesthospital;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    Toolbar toolbar;
    ProgressBar progressBar;
    EditText userEmail;
    EditText userPass;
    Button userLogin, createAccount;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        toolbar = findViewById(R.id.toolbar2);
        progressBar = findViewById(R.id.progressBar);
        userEmail = findViewById(R.id.etUserEmail);
        userPass = findViewById(R.id.etUserPass);
        userLogin = findViewById(R.id.btnUserLogin);
        createAccount=findViewById(R.id.btnCreateAccount);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LoginActivity.this,CreateAccount.class);
                startActivity(intent);

            }
        });

        toolbar.setTitle("Login");

        firebaseAuth = FirebaseAuth.getInstance();
        userLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnUserLogin:
                //-----user Registration------
                userSignInProcess();
                break;
        }
    }

    private void userSignInProcess() {

        String email=userEmail.getText().toString().trim();
        String password=userPass.getText().toString().trim();



        if (email.isEmpty()){
            userEmail.setError("Enter Your Email Please");
            userEmail.requestFocus();
            return;
        }


        if (password.isEmpty()){
            userPass.setError("Enter Your Password Please");
            userPass.requestFocus();
            return;
        }

        //-----------if every thing is Ok---------------

        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.signInWithEmailAndPassword(userEmail.getText().toString(),
                userPass.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()){
                            if(firebaseAuth.getCurrentUser().isEmailVerified()){
                                startActivity(new Intent(LoginActivity.this, Main_ActitvityF.class));
                            }else{
                                Toast.makeText(LoginActivity.this, "Please verify your email address"
                                        , Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this, task.getException().getMessage()
                                    , Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }
}
