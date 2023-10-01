package com.hamidul.personalnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {
    EditText edName,edEmail,edPassword;
    Button bSingUp;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        setContentView(R.layout.sign_up);

        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        bSingUp = findViewById(R.id.bSingUp);

        sharedPreferences = getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);
        editor = sharedPreferences.edit();

        flag = sharedPreferences.getBoolean("flag",false);

        if (flag){
            startActivity(new Intent(SignUp.this,LogIn.class));
            finish();
        }

        edName.addTextChangedListener(watcher);
        edEmail.addTextChangedListener(watcher);
        edPassword.addTextChangedListener(watcher);

        bSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sName = edName.getText().toString();
                String sEmail = edEmail.getText().toString();
                int iPassword = Integer.parseInt(edPassword.getText().toString());

                editor.putString("name",sName);
                editor.putString("email",sEmail);
                editor.putInt("password",iPassword);
                editor.putBoolean("flag",true);
                editor.putBoolean("splash",true);
                editor.apply();

                startActivity(new Intent(SignUp.this,List.class));

            }
        });

    }//onCreate=====================================================================================

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String name = edName.getText().toString();
            String email = edEmail.getText().toString();
            String password = edPassword.getText().toString();

            bSingUp.setEnabled(!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && password.length()==4);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}//AppCompatActivity================================================================================