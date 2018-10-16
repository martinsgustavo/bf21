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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.control.FoodControl;
import com.claudebernard.projetbf21.model.Food;
import com.claudebernard.projetbf21.model.FoodMacros;
import com.claudebernard.projetbf21.model.FoodNutrients;

import java.util.ArrayList;

public class DialogFood extends Dialog {

    private Activity _activity;
    private ImageButton _btn1, _btn2, _btn3;
    private EditText _nameFood, _brandFood, _portionFood, _lipidsFood, _glycidesFood, _proteinFood, _fibreFood, _sugarFood, _sodiumFood, _cholesterolFood, _glycemicIndexFood;
    private CheckBox _macroFat, _macroCarbohydrate, _macroProtein;
    private TextView _titleCard;
    private String _option;
    private Food _food;
    private int _idFood;
    private Context _context;
    private boolean _retDialogYesNo;
    private FoodControl _foodControl = new FoodControl();


    //=====
    public DialogFood(Activity a, Context c, String opt, Food food) {
        super(a);
        this._activity = a;
        this._option = opt;
        this._food = food;
        this._context = c;
    }

    //=====
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_food);
        setCanceledOnTouchOutside(false);

        loadInfoDialog();
        loadActionButtons();
    }

    //====
    public void loadInfoDialog() {

        _titleCard = (TextView) findViewById(R.id._title);
        _nameFood = (EditText) findViewById(R.id._inputName);
        _brandFood = (EditText) findViewById(R.id._inputBrand);
        _portionFood = (EditText) findViewById(R.id._inputPortionSize);
        _lipidsFood = (EditText) findViewById(R.id._inputLipide);
        _glycidesFood = (EditText) findViewById(R.id._inputGlucide);
        _proteinFood = (EditText) findViewById(R.id._inputProteine);
        _fibreFood = (EditText) findViewById(R.id._inputFibre);
        _sugarFood = (EditText) findViewById(R.id._inputSugar);
        _sodiumFood = (EditText) findViewById(R.id._inputSodium);
        _cholesterolFood = (EditText) findViewById(R.id._inputCholesterol);
        _glycemicIndexFood = (EditText) findViewById(R.id._inputIndGlycemique);

        _macroFat = (CheckBox)findViewById(R.id._cbFat);
        _macroCarbohydrate = (CheckBox)findViewById(R.id._cbCarbohydrate);
        _macroProtein = (CheckBox)findViewById(R.id._cbProtein);

        _btn1 = (ImageButton) findViewById(R.id._btn_1);
        _btn2 = (ImageButton) findViewById(R.id._btn_2);
        _btn3 = (ImageButton) findViewById(R.id._btn_3);

        _nameFood.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _brandFood.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _portionFood.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _lipidsFood.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _glycidesFood.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _proteinFood.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _fibreFood.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _sugarFood.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _sodiumFood.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _cholesterolFood.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _glycemicIndexFood.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        ArrayList<FoodNutrients> _listNutrients = new ArrayList<>();
        ArrayList<FoodMacros>    _listMacro = new ArrayList<>();
        Food _food = new Food();
        boolean ret = false;

        if (_option.equals("add")) {
            insertDefaultValues();
        }

        for (int x = 0; x < 8; x++) {

            final FoodNutrients _foodNutrient = new FoodNutrients();

            _foodNutrient.set_id(x+1);

            if (x == 0) {
                _foodNutrient.set_total(Double.valueOf(_lipidsFood.getText().toString()));
            } else if (x == 1){
                _foodNutrient.set_total(Double.valueOf(_glycidesFood.getText().toString()));
            } else if (x == 2){
                _foodNutrient.set_total(Double.valueOf(_proteinFood.getText().toString()));
            } else if (x == 3){
                _foodNutrient.set_total(Double.valueOf(_fibreFood.getText().toString()));
            } else if (x == 4){
                _foodNutrient.set_total(Double.valueOf(_sugarFood.getText().toString()));
            } else if (x == 5){
                _foodNutrient.set_total(Double.valueOf(_sodiumFood.getText().toString()));
            } else if (x == 6){
                _foodNutrient.set_total(Double.valueOf(_cholesterolFood.getText().toString()));
            } else if (x == 7){
                _foodNutrient.set_total(Double.valueOf(_glycemicIndexFood.getText().toString()));
            }

            _listNutrients.add(_foodNutrient);
        }

        for (int y = 0; y < 3; y++) {

            final FoodMacros _foodMacro = new FoodMacros();

            if (y == 0){
                if (_macroFat.isChecked()) {_foodMacro.set_idMacro(1);} else {_foodMacro.set_idMacro(null);}

            } else if (y == 1) {
                if (_macroCarbohydrate.isChecked()) { _foodMacro.set_idMacro(2); } else { _foodMacro.set_idMacro(null);}

            } else if (y == 2) {
                if (_macroProtein.isChecked()) { _foodMacro.set_idMacro(3); } else { _foodMacro.set_idMacro(null);}

            }

            if (_foodMacro.get_idMacro()!= null) {
                _listMacro.add(_foodMacro);
            }
        }

        _food.set_name(_nameFood.getText().toString());
        _food.set_brand(_brandFood.getText().toString());
        _food.set_portionSize(Integer.valueOf(_portionFood.getText().toString()));
        _food.set_foodNutrients(_listNutrients);
        _food.set_foodMacros(_listMacro);


        if (_option.equals("modify")) {
            _food.set_id(_idFood);
            ret = _foodControl.editData(_food);

        } else if (_option.equals("add")) {
            ret = _foodControl.saveData(_food);

        } else {
            dialogYesNo("Vous êtes sure de supprimer ce Aliment ?");

        }

        return ret;
    }


    //====
    public void insertDefaultValues() {

        if (_portionFood.getText().toString().equals("")){ _lipidsFood.setText("0"); }

        if (_lipidsFood.getText().toString().equals("")){ _lipidsFood.setText("0"); }

        if (_glycidesFood.getText().toString().equals("")){ _glycidesFood.setText("0"); }

        if (_proteinFood.getText().toString().equals("")){ _proteinFood.setText("0"); }

        if (_fibreFood.getText().toString().equals("")){ _fibreFood.setText("0"); }

        if (_sugarFood.getText().toString().equals("")){ _sugarFood.setText("0"); }

        if (_sodiumFood.getText().toString().equals("")){ _sodiumFood.setText("0"); }

        if (_cholesterolFood.getText().toString().equals("")){ _cholesterolFood.setText("0"); }

        if (_glycemicIndexFood.getText().toString().equals("")) { _glycemicIndexFood.setText("0"); }
    }


    //====
    public void loadOptionView() {

        boolean _cbFat = false;
        boolean _cbCarbohydrate = false;
        boolean _cbProtein = false;



        _idFood = _food.get_id();

        _titleCard.setText("Informations sur l aliment");
        _nameFood.setText(_food.get_name());
        _brandFood.setText(_food.get_brand());
        _portionFood.setText(String.valueOf(_food.get_portionSize()));

        for (int y = 0; y < _food.get_foodNutrients().size();y++){

            if (_food.get_foodNutrients().get(y).get_id() == 1){
                _lipidsFood.setText(String.valueOf(_food.get_foodNutrients().get(y).get_total()));

            } else if (_food.get_foodNutrients().get(y).get_id() == 2){
                _glycidesFood.setText(String.valueOf(_food.get_foodNutrients().get(y).get_total()));

            } else if (_food.get_foodNutrients().get(y).get_id() == 3){
                _proteinFood.setText(String.valueOf(_food.get_foodNutrients().get(y).get_total()));

            } else if (_food.get_foodNutrients().get(y).get_id() == 4){
                _fibreFood.setText(String.valueOf(_food.get_foodNutrients().get(y).get_total()));

            } else if (_food.get_foodNutrients().get(y).get_id() == 5){
                _sugarFood.setText(String.valueOf(_food.get_foodNutrients().get(y).get_total()));

            } else if (_food.get_foodNutrients().get(y).get_id() == 6){
                _sodiumFood.setText(String.valueOf(_food.get_foodNutrients().get(y).get_total()));

            } else if (_food.get_foodNutrients().get(y).get_id() == 7){
                _cholesterolFood.setText(String.valueOf(_food.get_foodNutrients().get(y).get_total()));

            } else if (_food.get_foodNutrients().get(y).get_id() == 8){
                _glycemicIndexFood.setText(String.valueOf(_food.get_foodNutrients().get(y).get_total()));
            }

        }

        for (int x = 0; x < _food.get_foodMacros().size();x++){

            if (_food.get_foodMacros().get(x).get_macro().equals("Fat")){
                _cbFat = true;

            } else if (_food.get_foodMacros().get(x).get_macro().equals("Carbohydrate")){
                _cbCarbohydrate = true;

            } else if (_food.get_foodMacros().get(x).get_macro().equals("Protein")){
                _cbProtein = true;

            }
        }

        _macroFat.setChecked(_cbFat);
        _macroCarbohydrate.setChecked(_cbCarbohydrate);
        _macroProtein.setChecked(_cbProtein);

        _nameFood.setEnabled(false);
        _brandFood.setEnabled(false);
        _portionFood.setEnabled(false);
        _lipidsFood.setEnabled(false);
        _glycidesFood.setEnabled(false);
        _proteinFood.setEnabled(false);
        _fibreFood.setEnabled(false);
        _sugarFood.setEnabled(false);
        _sodiumFood.setEnabled(false);
        _cholesterolFood.setEnabled(false);
        _glycemicIndexFood.setEnabled(false);
        _macroFat.setEnabled(false);
        _macroCarbohydrate.setEnabled(false);
        _macroProtein.setEnabled(false);
    }


    //====
    public void loadOptionAdd() {

        _titleCard.setText("Ajouter - Nouveau Aliment");

        _btn1.setVisibility(View.GONE);
        _btn2.setBackgroundResource(R.drawable.icon_cancel);
        _btn3.setBackgroundResource(R.drawable.icon_ok);
    }


    //====
    public void loadOptionModify() {

        _titleCard.setText("Modifier - Les informations d aliment");

        _nameFood.setEnabled(true);
        _brandFood.setEnabled(true);
        _portionFood.setEnabled(true);
        _lipidsFood.setEnabled(true);
        _glycidesFood.setEnabled(true);
        _proteinFood.setEnabled(true);
        _fibreFood.setEnabled(true);
        _sugarFood.setEnabled(true);
        _sodiumFood.setEnabled(true);
        _cholesterolFood.setEnabled(true);
        _glycemicIndexFood.setEnabled(true);
        _macroFat.setEnabled(true);
        _macroCarbohydrate.setEnabled(true);
        _macroProtein.setEnabled(true);

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
                _food.set_id(_idFood);
                _retDialogYesNo = _foodControl.deleteData(_food);
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

        if (!_nameFood.getText().toString().equals("") && _nameFood.getText().length() <= 45) {
            if (!_portionFood.getText().toString().equals("") && _portionFood.getText().length() <= 5) {

                return true;

            } else {
                _msg = "Le champ Taille de portion doit être remplis et le maximum sont 5 caractères.\n";
            }
        } else {
            _msg = "Le champ Nom d'aliment doit être remplis et le maximum sont 45 caractères.\n";
        }

        alertForm(_msg);

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