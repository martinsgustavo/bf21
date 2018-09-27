package com.claudebernard.projetbf21.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.control.ClientControl;
import com.claudebernard.projetbf21.model.Client;
import com.claudebernard.projetbf21.model.ClientGoal;

public class DialogClient extends Dialog {

    private Activity _activity;
    private Button _yes, _no;
    private EditText _firstName, _lastName, _address, _eMail, _phone, _height, _weight, _tdee;
    private String option;
    private Client _client;
    private Context _c;

    //=====
    public DialogClient(Activity a, Context c, String opt, Client client) {
        super(a);
        this._activity = a;
        this.option = opt;
        this._client = client;
        this._c = c;
    }

    //=====
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_client);
        loadClientModel();

        this.setCanceledOnTouchOutside(false);
        loadButtonYesNo();

        if(option.equals("modify")){

            _firstName.setText(_client.get_name());
            _lastName.setText(_client.get_name());
            _address.setText(_client.get_eMail());
            _eMail.setText(_client.get_eMail());
            _phone.setText(_client.get_phoneNumber());
            _height.setText(String.valueOf(_client.get_height()));
            _weight.setText(String.valueOf(_client.get_weight()));
            _tdee.setText(_client.get_tdee());

            _yes.setText("Modifier");
        }
    }

    //====
    public void loadClientModel(){

        _firstName = (EditText)findViewById(R.id._inputFirstName);
        _lastName  = (EditText)findViewById(R.id._inputLastName);
        _address   = (EditText)findViewById(R.id._inputAddress);
        _eMail     = (EditText)findViewById(R.id._inputEmail);
        _phone     = (EditText)findViewById(R.id._inputTelephone);
        _height    = (EditText)findViewById(R.id._inputHeight);
        _weight    = (EditText)findViewById(R.id._inputWeight);
        _tdee      = (EditText)findViewById(R.id._inputTDEE);

        _firstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v);} }});

        _lastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v);} }});

        _address.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v);} }});

        _eMail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v);} }});

        _phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v);} }});

        _height.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v);} }});

        _weight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v);} }});

        _tdee.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v);} }});

    }

    //=====
    public void loadButtonYesNo(){
        _yes = (Button) findViewById(R.id.btn_yes);
        _no  = (Button) findViewById(R.id.btn_no);

        _yes.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Client client = new Client();
                ClientGoal clientGoal = new ClientGoal();

                clientGoal.setIdClientGoal(1);
                clientGoal.setGoal("TESTE");

                client.set_name(_firstName.getText().toString());
                client.set_age(18);
                client.set_eMail(_eMail.getText().toString());
                client.set_phoneNumber(_phone.getText().toString());
                client.set_height(Double.parseDouble(_height.getText().toString()));
                client.set_weight(Double.parseDouble(_weight.getText().toString()));
                client.set_tdee(_tdee.getText().toString());
                client.setClientGoal(clientGoal);

                ClientControl clientControl = new ClientControl();

                clientControl.saveClient(_c, client);

                dismiss();
            }
        });

        _no.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                dismiss();
            }
        });
    }

    //=====
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) _activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
