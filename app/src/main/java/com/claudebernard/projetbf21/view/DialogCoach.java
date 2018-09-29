package com.claudebernard.projetbf21.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.model.Coach;

public class DialogCoach extends Dialog {

    private Activity _activity;
    private Button _yes, _no;
    private EditText _firstName, _lastName, _address, _eMail, _phone;
    private String option;
    private Coach _coach;

    //=====
    public DialogCoach(Activity a, String opt, Coach coach) {
        super(a);
        this._activity = a;
        this.option = opt;
        this._coach = coach;
    }

    //=====
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_coach);
        loadClientModel();

        this.setCanceledOnTouchOutside(false);
        loadButtonYesNo();

        if(option.equals("modify") || option.equals("view")){

            _firstName.setText(_coach.getName());
            _lastName.setText(_coach.getName());
//            _address.setText(_coach.get_address());
            _eMail.setText(_coach.get_eMail());
            _phone.setText(_coach.get_phone());

            if(option.equals("modify")) {

                _yes.setText("Modifier");

            } else {
                _firstName.setFocusable(false);
                _lastName.setFocusable(false);
                _address.setFocusable(false);
                _eMail.setFocusable(false);
                _phone.setFocusable(false);
                _yes.setVisibility(View.GONE);
                _no.setVisibility(View.GONE);

                LinearLayout _layout_dialog_coach = (LinearLayout) findViewById(R.id._layout_dialog_coach);

                _layout_dialog_coach.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        dismiss();
                    }
                });
            }
        }
    }

    //====
    public void loadClientModel(){


        _firstName = (EditText)findViewById(R.id._inputFirstName);
        _lastName  = (EditText)findViewById(R.id._inputLastName);
        _address   = (EditText)findViewById(R.id._inputAddress);
        _eMail     = (EditText)findViewById(R.id._inputEmail);
        _phone     = (EditText)findViewById(R.id._inputTelephone);


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
    }

    //=====
    public void loadButtonYesNo(){
        _yes = (Button) findViewById(R.id.btn_yes);
        _no  = (Button) findViewById(R.id.btn_no);

        _yes.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
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
