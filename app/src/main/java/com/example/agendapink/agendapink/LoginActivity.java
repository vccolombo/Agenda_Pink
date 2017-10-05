package com.example.agendapink.agendapink;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout usernameTextInputLayout;
    TextInputEditText usernameEditText;
    TextInputLayout passwordTextInputLayout;
    TextInputEditText passwordEditText;
    Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_login);

        usernameTextInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout_username);
        usernameEditText = (TextInputEditText) findViewById(R.id.edit_text_username);
        passwordTextInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout_password);
        passwordEditText = (TextInputEditText) findViewById(R.id.edit_text_password);
        buttonLogin = (Button) findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(usernameEditText.getText().toString()) || TextUtils.isEmpty(passwordEditText.getText().toString()))
                {
                    if (TextUtils.isEmpty(usernameEditText.getText().toString())) {
                        usernameTextInputLayout.setErrorEnabled(true);
                        usernameTextInputLayout.setError(getString(R.string.invalid_username));

                    }

                    if (TextUtils.isEmpty(passwordEditText.getText().toString())) {
                        passwordTextInputLayout.setErrorEnabled(true);
                        passwordTextInputLayout.setError(getString(R.string.invalid_password));

                    }
                    return;
                }

                Intent intent = new Intent(LoginActivity.this, ContactActivity.class);
                startActivity(intent);

                finish();
            }
        });

        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                usernameTextInputLayout.setErrorEnabled(false);
                usernameTextInputLayout.setError("");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passwordTextInputLayout.setErrorEnabled(false);
                passwordTextInputLayout.setError("");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void onMoonClick(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";

        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();

    }
}
