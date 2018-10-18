package com.claudebernard.projetbf21.view;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.comm.ApiClient;
import com.claudebernard.projetbf21.comm.ApiInterface;
import com.claudebernard.projetbf21.control.ValidationLogin;
import com.claudebernard.projetbf21.model.Client;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class ActivityMain extends AppCompatActivity {

    private TextInputLayout _inputLayoutlogin, _inputLayoutPassword;
    private EditText  _inputLogin, _inputPassword;
    private Button    _loginButton;
    private ValidationLogin validationLogin = new ValidationLogin();


    //=====
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _inputLogin          = (EditText)findViewById(R.id._inputLogin);
        _inputPassword       = (EditText)findViewById(R.id._inputPassword);
        _loginButton         = (Button)findViewById(R.id._loginButton);
        _inputLayoutlogin    = (TextInputLayout) findViewById(R.id.input_layout_login);
        _inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);

        controlFocus();
    }


    //=====
    public void controlFocus(){

        _inputLogin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                _inputLayoutPassword.setErrorEnabled(false);

                if (!hasFocus) {
                   hideKeyboard(v);
                }
            }
        });

        _inputPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                _inputLayoutPassword.setErrorEnabled(false);

                if (!hasFocus) {
                   hideKeyboard(v);
                }
            }
        });
    }


    //=====
    public void validationLogin(View view){
        _inputLogin.clearFocus();
        _inputPassword.clearFocus();

        if (validationLogin.accessSystem(this,_inputLogin.getText().toString().trim(),_inputPassword.getText().toString().trim())){
            _inputLayoutlogin.setError(" ");
            _inputLayoutPassword.setError("Nom d'utilisateur ou mot de passe incorrect.");
        }
    }


    //=====
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
