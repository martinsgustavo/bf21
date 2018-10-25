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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.control.CaloricCalculation;
import com.claudebernard.projetbf21.control.ClientControl;
import com.claudebernard.projetbf21.model.Client;
import com.claudebernard.projetbf21.model.ClientActivityLevel;
import com.claudebernard.projetbf21.model.ClientGoal;
import com.claudebernard.projetbf21.model.ClientProteinRequirement;

public class DialogClient extends Dialog {

    private static Activity _activity;
    private ImageButton _btn1, _btn2, _btn3;
    private EditText _nameClient, _ageClient, _eMailClient, _phoneNumberClient, _heightClient, _weightClient, _bodyFatPercentageClient, _tdceClient, _bmrClient;
    private TextView _titleCard;
    private RadioGroup _radioSexGroup;
    private RadioButton _radioSexBtnMale, _radioSexBtnFemale;
    private Spinner _spClientGoal, _spActLevel, _spProteinReq;
    private String _option;
    private int _idClient;
    private Client _client;
    private Context _context;
    private boolean _retDialogYesNo;
    private ClientControl _clientControl = new ClientControl();
    private ArrayAdapter<CharSequence> _adapterClientGoal, _adapterActLevel, _adapterProteinReq;


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
        _tdceClient = (EditText) findViewById(R.id._inputTDCE);
        _bmrClient = (EditText) findViewById(R.id._inputBMR);
        _radioSexGroup = (RadioGroup) findViewById(R.id._radioSex);
        _radioSexBtnMale = (RadioButton) findViewById(R.id._radioMale);
        _radioSexBtnFemale = (RadioButton) findViewById(R.id._radioFemale);

        _spClientGoal = (Spinner) findViewById(R.id._inputSpClientGoal);
        _spActLevel   = (Spinner) findViewById(R.id._inputSpActLevel);
        _spProteinReq = (Spinner) findViewById(R.id._inputSpProteinReq);

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

        _adapterClientGoal = ArrayAdapter.createFromResource(_context, R.array.spClientGoal, android.R.layout.simple_spinner_item);
        _adapterClientGoal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spClientGoal.setAdapter(_adapterClientGoal);

        _adapterActLevel = ArrayAdapter.createFromResource(_context, R.array.spActLevel, android.R.layout.simple_spinner_item);
        _adapterActLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spActLevel.setAdapter(_adapterActLevel);

        _adapterProteinReq = ArrayAdapter.createFromResource(_context, R.array.spProteinReq, android.R.layout.simple_spinner_item);
        _adapterProteinReq.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spProteinReq.setAdapter(_adapterProteinReq);

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
                    getDataDialog();

                }
            }
        });

        _btn3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (_option.equals("add") && validationForm()) {
                    getDataDialog();

                } else if (_option.equals("view")) {
                   loadOptionModify();
                }
            }
        });
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
        _tdceClient.setText(String.valueOf(_client.get_tdce()));
        _bmrClient.setText(String.valueOf(_client.get_bmr()));

        if (_client.get_gender() == 'M'){
            _radioSexBtnMale.setChecked(true);
            _radioSexBtnFemale.setChecked(false);
        } else {
            _radioSexBtnMale.setChecked(false);
            _radioSexBtnFemale.setChecked(true);
        }

        if (_client.getClientGoal().get_idClientGoal() != null) {
            int spinnerPosition1 = (int) _adapterClientGoal.getItemId(_client.getClientGoal().get_idClientGoal()-1);
            _spClientGoal.setSelection(spinnerPosition1);
        }

        if (_client.getClientactivityLevel().get_idDailyActivityLevel() != null) {
            int spinnerPosition2 = (int) _adapterActLevel.getItemId(_client.getClientactivityLevel().get_idDailyActivityLevel()-1);
            _spActLevel.setSelection(spinnerPosition2);
        }

        if (_client.getClientProteinRequirement().get_idProteinRequirement() != null) {
            int spinnerPosition3 = (int) _adapterProteinReq.getItemId(_client.getClientProteinRequirement().get_idProteinRequirement()-1);
            _spProteinReq.setSelection(spinnerPosition3);
        }


        _nameClient.setEnabled(false);
        _ageClient.setEnabled(false);
        _eMailClient.setEnabled(false);
        _phoneNumberClient.setEnabled(false);
        _heightClient.setEnabled(false);
        _weightClient.setEnabled(false);
        _bodyFatPercentageClient.setEnabled(false);
        _radioSexBtnMale.setEnabled(false);
        _radioSexBtnFemale.setEnabled(false);
        _spClientGoal.setEnabled(false);
        _spActLevel.setEnabled(false);
        _spProteinReq.setEnabled(false);
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
        _radioSexBtnMale.setEnabled(true);
        _radioSexBtnFemale.setEnabled(true);
        _spClientGoal.setEnabled(true);
        _spActLevel.setEnabled(true);
        _spProteinReq.setEnabled(true);

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
                    getDataDialog();
                }
            }
        });
    }


    //=====
    public void getDataDialog() {

        if (!_option.equals("view")) {

            Client _client = new Client();

            _client.set_name(_nameClient.getText().toString().toUpperCase());
            _client.set_age(Integer.valueOf(_ageClient.getText().toString()));
            _client.set_eMail(_eMailClient.getText().toString());
            _client.set_phoneNumber(_phoneNumberClient.getText().toString());
            _client.set_height(Double.valueOf(_heightClient.getText().toString()));
            _client.set_weight(Double.valueOf(_weightClient.getText().toString()));
            _client.set_bodyFatPercentage(Integer.valueOf(_bodyFatPercentageClient.getText().toString()));
            _client.set_gender('M');

            if (_radioSexBtnFemale.isChecked()) {
                _client.set_gender('F');
            }

            ClientGoal _cGol = new ClientGoal();
            _cGol.set_idClientGoal(_spClientGoal.getSelectedItemPosition() + 1);
            _client.setClientGoal(_cGol);

            ClientActivityLevel _cAL = new ClientActivityLevel();
            _cAL.set_idDailyActivityLevel(_spActLevel.getSelectedItemPosition() + 1);
            _client.setClientactivityLevel(_cAL);

            ClientProteinRequirement _cPR = new ClientProteinRequirement();
            _cPR.set_idProteinRequirement(_spProteinReq.getSelectedItemPosition() + 1);
            _client.setClientProteinRequirement(_cPR);

            CaloricCalculation _cCalculation = new CaloricCalculation();
            _client.set_bmr(_cCalculation.findBasilMetabolicRate(_client));
            _client.set_tdce(_cCalculation.totalDailyCalories(_client));

            if (_option.equals("modify")) {
                _client.set_id(_idClient);
                 _clientControl.editData(_client);

            } else if (_option.equals("add")) {
                _clientControl.saveData(_client);

            }

        } else {
            dialogYesNo("Vous êtes sure de supprimer ce client ?");

        }
    }


    //=====
    public boolean dialogYesNo(String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(_context);

        builder.setMessage(message).setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                _client.set_id(_idClient);
                _retDialogYesNo = _clientControl.deleteData(_client);
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

        String _msg;
        Double _FatPerc;

        if (!_nameClient.getText().toString().equals("") && _nameClient.getText().length() <= 45){
            if (!_ageClient.getText().toString().equals("") && _ageClient.getText().length() <= 3){
                if (!_eMailClient.getText().toString().equals("") && _eMailClient.getText().length() <= 45){
                    if (!_phoneNumberClient.getText().toString().equals("") && _phoneNumberClient.getText().length() <= 45){
                        if (!_heightClient.getText().toString().equals("") && _heightClient.getText().length() <= 7){
                            if (!_weightClient.getText().toString().equals("") && _weightClient.getText().length() <= 7){
                                if (!_bodyFatPercentageClient.getText().toString().equals("") && _bodyFatPercentageClient.getText().length() <= 2){
                                    _FatPerc = Double.valueOf(_bodyFatPercentageClient.getText().toString());
                                    if (_radioSexBtnFemale.isChecked() && _FatPerc >= 14 || _radioSexBtnMale.isChecked() && _FatPerc >= 10){
                                        return true;

                                    } else {
                                        _msg = "Le champ Taux de graisse doit avoir la valeur minimale de:\nHomme -> 10\nFemme -> 14\n";
                                    }
                                } else {
                                    _msg = "Le champ Taux de graisse de passe doit être remplis et le maximum sont 2 caractères.\n";
                                }
                            } else {
                                _msg = "Le champ Poids doit être remplis et le maximum sont 5,2 caractères.\n";
                            }
                        } else {
                            _msg = "Le champ Taille doit être remplis et le maximum sont 5,2 caractères.\n";
                        }
                    } else {
                        _msg = "Le champ Téléphone doit être remplis et le maximum sont 45 caractères.\n";
                    }
                } else {
                    _msg = "Le champ Courriel doit être remplis et le maximum sont 45 caractères.\n";
                }
            } else {
                _msg = "Le champ Âge doit être remplis et le maximum sont 3 caractères.\n";
            }
        } else {
            _msg = "Le champ Nom doit être remplis et le maximum sont 45 caractères.\n";
        }

        alertForm(_msg);

        return false;
    }


    //=====
    public void alertForm(String _msg){
        AlertDialog alertDialog = new AlertDialog.Builder(_context).create();
        alertDialog.setTitle("Alerte !");
        alertDialog.setMessage(_msg);

        alertDialog.show();

    }

    //=====
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) _activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}