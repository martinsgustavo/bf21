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
import com.claudebernard.projetbf21.control.CoachControl;
import com.claudebernard.projetbf21.model.Coach;

public class DialogCoach extends Dialog {

    private Activity _activity;
    private ImageButton _btn1, _btn2, _btn3;
    private EditText _nameCoach, _eMailCoach, _phoneCoach, _loginCoach, _passwordCoach;
    private TextView _titleCard;
    private String _option;
    private int _idCoach;
    private Coach _coach;
    private Context _context;
    private boolean _retDialogYesNo;
    private CoachControl _coachControl = new CoachControl();

    //=====
    public DialogCoach(Activity a, Context c, String opt, Coach coach) {
        super(a);
        this._activity = a;
        this._option = opt;
        this._coach = coach;
        this._context = c;
    }

    //=====
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_coach);
        setCanceledOnTouchOutside(false);

        loadInfoDialog();
        loadActionButtons();
    }

    public void loadInfoDialog() {

        _titleCard = (TextView) findViewById(R.id._title);
        _nameCoach = (EditText) findViewById(R.id._inputName);
        _eMailCoach = (EditText) findViewById(R.id._inputEmail);
        _phoneCoach = (EditText) findViewById(R.id._inputPhoneNumber);
        _loginCoach = (EditText) findViewById(R.id._inputLogin);
        _passwordCoach = (EditText) findViewById(R.id._inputPassword);

        _btn1 = (ImageButton) findViewById(R.id._btn_1);
        _btn2 = (ImageButton) findViewById(R.id._btn_2);
        _btn3 = (ImageButton) findViewById(R.id._btn_3);


        _nameCoach.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _eMailCoach.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _phoneCoach.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _loginCoach.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _passwordCoach.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
                    }
                }
            }
        });

        _btn3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (_option.equals("add") && validationForm()) {
                    if (getDataDialog()) {
                        dismiss();
                    }

                } else if (_option.equals("view")) {
                    loadOptionModify();
                }
            }
        });
    }


    //=====
    public boolean getDataDialog() {

        boolean ret = false;

            if (_option.equals("view")) {

            Coach _coach = new Coach();

            _coach.set_name(_nameCoach.getText().toString());
            _coach.set_eMail(_eMailCoach.getText().toString());
            _coach.set_phone(_phoneCoach.getText().toString());
            _coach.set_login(_loginCoach.getText().toString());
            _coach.set_password(_passwordCoach.getText().toString());


            if (_option.equals("modify")) {
                _coach.set_id(_idCoach);
                ret = _coachControl.editData(_coach);

            } else if (_option.equals("add")) {
                ret = _coachControl.saveData(_coach);

            }
        } else {
            dialogYesNo("Vous êtes sure de supprimer ce coach ?");

        }

        return ret;
    }


    //====
    public void loadOptionView() {

        _idCoach = _coach.get_id();

        _titleCard.setText("Informations sur le coach");
        _nameCoach.setText(_coach.get_name());
        _eMailCoach.setText(_coach.get_eMail());
        _phoneCoach.setText(_coach.get_phone());
        _loginCoach.setText(_coach.get_login());
        _passwordCoach.setText(_coach.get_password());

        _nameCoach.setEnabled(false);
        _eMailCoach.setEnabled(false);
        _phoneCoach.setEnabled(false);
        _loginCoach.setEnabled(false);
        _passwordCoach.setEnabled(false);
    }


    //====
    public void loadOptionAdd() {

        _titleCard.setText("Ajouter - Nouveau Coach");

        _btn1.setVisibility(View.GONE);
        _btn2.setBackgroundResource(R.drawable.icon_cancel);
        _btn3.setBackgroundResource(R.drawable.icon_ok);
    }


    //====
    public void loadOptionModify() {

        _titleCard.setText("Modifier - Les informations du coach");

        _nameCoach.setEnabled(true);
        _eMailCoach.setEnabled(true);
        _phoneCoach.setEnabled(true);
        _loginCoach.setEnabled(true);
        _passwordCoach.setEnabled(true);

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
                    }
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
                _coach.set_id(_idCoach);
                _retDialogYesNo = _coachControl.deleteData(_coach);
                dismiss();
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

        String _message;

        if (!_nameCoach.getText().toString().equals("") && _nameCoach.getText().length() <= 45){
            if (!_eMailCoach.getText().toString().equals("") && _eMailCoach.getText().length() <= 45){
                if (!_phoneCoach.getText().toString().equals("") && _phoneCoach.getText().length() <= 45){
                    if (!_loginCoach.getText().toString().equals("") && _loginCoach.getText().length() <= 45){
                        if (!_passwordCoach.getText().toString().equals("") && _passwordCoach.getText().length() <= 45){

                            return true;

                        } else {
                            _message = "Le champ Mot de passe doit être remplis et le maximum sont 45 caractères.\n";
                        }
                    } else {
                        _message = "Le champ Login doit être remplis et le maximum sont 45 caractères.\n";
                    }
                } else {
                    _message = "Le champ Téléphone doit être remplis et le maximum sont 45 caractères.\n";
                }
            } else {
                _message = "Le champ Courriel doit être remplis et le maximum sont 45 caractères.\n";
            }
        } else {
            _message = "Le champ Nom doit être remplis et le maximum sont 45 caractères.\n";
        }

        alertForm(_message);

        return false;
    }


    //=====
    public void alertForm(String message){
        AlertDialog alertDialog = new AlertDialog.Builder(_context).create();
        alertDialog.setTitle("Alerte !");
        alertDialog.setMessage(message);

        alertDialog.show();

    }

    //=====
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) _activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
