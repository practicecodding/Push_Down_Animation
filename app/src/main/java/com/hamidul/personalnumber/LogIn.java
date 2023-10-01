package com.hamidul.personalnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.thekhaeng.pushdownanim.PushDownAnim;

public class LogIn extends AppCompatActivity {
    TextInputLayout lPassword;
    EditText edEmail,edPassword;
    Button bLongIn;
    SharedPreferences sharedPreferences;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.log_in);

        lPassword = findViewById(R.id.lPassword);
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        bLongIn = findViewById(R.id.bLogIn);

        PushDownAnim.setPushDownAnimTo(bLongIn)
                        .setScale(PushDownAnim.MODE_SCALE,0.98f);

        edEmail.addTextChangedListener(watcher);
        edPassword.addTextChangedListener(watcher);

        sharedPreferences = getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);

        String email = sharedPreferences.getString("email","Default");
        int password = sharedPreferences.getInt("password",0);

        bLongIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sEmail = edEmail.getText().toString();
                int iPassword = Integer.parseInt(edPassword.getText().toString());

                if (sEmail.equals(email) && iPassword==password){
                    startActivity(new Intent(LogIn.this,List.class));
                } else if (!sEmail.equals(email) && iPassword!=password){
                    toastMessage("Incorrect Email or Password");
                    //Toast.makeText(LogIn.this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                    edEmail.requestFocus();
                } else if (!sEmail.equals(email)) {
                    toastMessage("Incorrect Email");
                    //Toast.makeText(LogIn.this, "Incorrect Email", Toast.LENGTH_SHORT).show();
                    edEmail.requestFocus();
                } else if (iPassword!=password) {
                    toastMessage("Incorrect Password");
                    //Toast.makeText(LogIn.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                    edPassword.requestFocus();
                }

            }
        });


    }//onCreate=====================================================================================

    public void toastMessage (String message){
        if (toast!=null) toast.cancel();
        toast = Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
        toast.show();
    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String email = edEmail.getText().toString();
            String password = edPassword.getText().toString();

            if (password.length()==0){
                lPassword.setHelperText(" ");
            }else if (password.length()<4){
                lPassword.setHelperText("Enter 4-digit PIN");
            }else if (password.length()==4){
                lPassword.setHelperText(" ");
            }
            bLongIn.setEnabled(!email.isEmpty() && !password.isEmpty() && password.length()==4);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}//AppCompatActivity================================================================================