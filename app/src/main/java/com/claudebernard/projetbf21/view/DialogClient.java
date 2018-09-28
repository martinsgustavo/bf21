package com.claudebernard.projetbf21.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.control.ClientControl;
import com.claudebernard.projetbf21.model.Client;

public class DialogClient extends Dialog {

    private Activity _activity;
    private ImageButton _btn1, _btn2, _btn3;
    private EditText _nameClient, _ageClient, _eMailClient, _phoneNumberClient, _heightClient, _weightClient, _bodyFatPercentageClient, _idClientGoalClient, _tdeeClient;
    private TextView _titleCard;
    private String _option;
    private int _idClient;
    private Client _client;
    private Context _context;
    private boolean _retDialogYesNo;


    //=====
    public DialogClient(Activity a, Context c, String opt, Client client) {
        super(a);
        this._activity = a;
        this._option = opt;
        this._client = client;
        this._context = c;
    }

    //=====
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_client);
        setCanceledOnTouchOutside(false);

        loadInfoDialog();
        loadActionButtons();
    }

    //====
    public void loadInfoDialog() {

        _titleCard = (TextView) findViewById(R.id._title);
        _nameClient = (EditText) findViewById(R.id._inputName);
        _ageClient = (EditText) findViewById(R.id._inputAge);
        _eMailClient = (EditText) findViewById(R.id._inputEmail);
        _phoneNumberClient = (EditText) findViewById(R.id._inputPhoneNumber);
        _heightClient = (EditText) findViewById(R.id._inputHeight);
        _weightClient = (EditText) findViewById(R.id._inputWeight);
        _bodyFatPercentageClient = (EditText) findViewById(R.id._inputFatPernc);
        _idClientGoalClient = (EditText) findViewById(R.id._inputGoal);
        _tdeeClient = (EditText) findViewById(R.id._inputTDEE);

        _btn1 = (ImageButton) findViewById(R.id._btn_1);
        _btn2 = (ImageButton) findViewById(R.id._btn_2);
        _btn3 = (ImageButton) findViewById(R.id._btn_3);


        _nameClient.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _ageClient.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _eMailClient.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _phoneNumberClient.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _heightClient.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _weightClient.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _bodyFatPercentageClient.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _idClientGoalClient.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _tdeeClient.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        if (_option.equals("view")) {

            loadOptionView();

        } else if (_option.equals("add")) {

            loadOptionAdd();
        }

    }

    //=====
    public void loadActionButtons() {

        _btn1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (_option.equals("view")) {
                    dismiss();
                }
            }
        });

        _btn2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (_option.equals("add")){
                    dismiss();

                } else if (_option.equals("view") && validationForm()) {
                    if (getDataDialog()) {
                        dismiss();
                        ActivityClient.loadGridClients();
                    }
                } else if (_option.equals("view") && !validationForm()){
                    alertForm();
                }
            }
        });

        _btn3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (_option.equals("add") && validationForm()) {
                    if (getDataDialog()) {
                        dismiss();
                        ActivityClient.loadGridClients();
                    }

                } else if (_option.equals("add") && !validationForm()) {
                    alertForm();

                } else if (_option.equals("view")) {
                   loadOptionModify();
                }
            }
        });
    }


    //=====
    public boolean getDataDialog() {

        boolean ret = false;

        Client _client = new Client();

        _client.set_name(_nameClient.getText().toString());
        _client.set_age(Integer.valueOf(_ageClient.getText().toString()));
        _client.set_eMail(_eMailClient.getText().toString());
        _client.set_phoneNumber(_phoneNumberClient.getText().toString());
        _client.set_height(Double.valueOf(_heightClient.getText().toString()));
        _client.set_weight(Double.valueOf(_weightClient.getText().toString()));
        _client.set_bodyFatPercentage(Integer.valueOf(_bodyFatPercentageClient.getText().toString()));
        _client.set_idClientGoal(Integer.valueOf(_idClientGoalClient.getText().toString()));
        _client.set_tdee(_tdeeClient.getText().toString());


        if (_option.equals("modify")) {
            _client.set_id(_idClient);
            ret = ClientControl.modifyClient(_client);

        } else if (_option.equals("add")) {
            ret = ClientControl.addClient(_client);

        } else {
            dialogYesNo("Vous êtes sure de supprimer ce client ?");

        }

        return ret;
    }


    //====
    public void loadOptionView() {

        _idClient = _client.get_id();

        _titleCard.setText("Informations sur le client");
        _nameClient.setText(_client.get_name());
        _ageClient.setText(String.valueOf(_client.get_age()));
        _eMailClient.setText(_client.get_eMail());
        _phoneNumberClient.setText(_client.get_phoneNumber());
        _heightClient.setText(String.valueOf(_client.get_height()));
        _weightClient.setText(String.valueOf(_client.get_weight()));
        _bodyFatPercentageClient.setText(String.valueOf(_client.get_bodyFatPercentage()));
        _idClientGoalClient.setText(String.valueOf(_client.get_idClientGoal()));
        _tdeeClient.setText(_client.get_tdee());

        _nameClient.setEnabled(false);
        _ageClient.setEnabled(false);
        _eMailClient.setEnabled(false);
        _phoneNumberClient.setEnabled(false);
        _heightClient.setEnabled(false);
        _weightClient.setEnabled(false);
        _bodyFatPercentageClient.setEnabled(false);
        _idClientGoalClient.setEnabled(false);
        _tdeeClient.setEnabled(false);
    }


    //====
    public void loadOptionAdd() {

        _titleCard.setText("Ajouter - Nouveau client");

        _btn1.setVisibility(View.GONE);
        _btn2.setBackgroundResource(R.drawable.icon_cancel);
        _btn3.setBackgroundResource(R.drawable.icon_ok);
    }


    //====
    public void loadOptionModify() {

        _titleCard.setText("Modifier - Les informations du client");

        _nameClient.setEnabled(true);
        _ageClient.setEnabled(true);
        _eMailClient.setEnabled(true);
        _phoneNumberClient.setEnabled(true);
        _heightClient.setEnabled(true);
        _weightClient.setEnabled(true);
        _bodyFatPercentageClient.setEnabled(true);
        _idClientGoalClient.setEnabled(true);
        _tdeeClient.setEnabled(true);

        _btn1.setVisibility(View.GONE);
        _btn2.setBackgroundResource(R.drawable.icon_cancel);
        _btn3.setBackgroundResource(R.drawable.icon_ok);

        _btn2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                dismiss();
            }
        });

        _btn3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                _option = "modify";

                if (validationForm()) {
                    if (getDataDialog()) {
                        dismiss();
                        ActivityClient.loadGridClients();
                    }
                } else {
                    alertForm();
                }
            }
        });
    }


    //=====
    public boolean dialogYesNo(String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(_context);

        builder.setMessage(message).setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                _client.set_id(_idClient);
                _retDialogYesNo = ClientControl.removeClient(_client);
                dismiss();
                ActivityClient.loadGridClients();
            }
        }).setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                _retDialogYesNo = false;
            }
        }).create();

        AlertDialog alert = builder.create();
        alert.show();

        return _retDialogYesNo;
    }


    //=====
    public boolean validationForm(){

        if (!_nameClient.getText().toString().equals("")){
            if (!_ageClient.getText().toString().equals("")){
                if (!_eMailClient.getText().toString().equals("")){
                    if (!_phoneNumberClient.getText().toString().equals("")){
                        if (!_heightClient.getText().toString().equals("")){
                            if (!_weightClient.getText().toString().equals("")){
                                if (!_bodyFatPercentageClient.getText().toString().equals("")){
                                    if (!_idClientGoalClient.getText().toString().equals("")){
                                        if (!_tdeeClient.getText().toString().equals("")){

                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }


    //=====
    public void alertForm(){
        AlertDialog alertDialog = new AlertDialog.Builder(_context).create();
        alertDialog.setTitle("Alerte !");
        alertDialog.setMessage("Tous les champs doivent être remplis.");

        alertDialog.show();

    }

    //=====
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) _activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}