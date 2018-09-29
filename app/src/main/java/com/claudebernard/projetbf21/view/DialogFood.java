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
import com.claudebernard.projetbf21.control.FoodControl;
import com.claudebernard.projetbf21.model.Food;

public class DialogFood extends Dialog {

    private Activity _activity;
    private ImageButton _btn1, _btn2, _btn3;
    private EditText _nameFood, _brandFood, _portionFood, _calorieFood, _proteinFood, _lipidsFood, _glycidesFood, _fibreFood, _glycemicIndexFood;
    private TextView _titleCard;
    private String _option;
    private Food _food;
    private int _idFood;
    private Context _context;
    private boolean _retDialogYesNo;


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
        _calorieFood = (EditText) findViewById(R.id._inputCalorie);
        _proteinFood = (EditText) findViewById(R.id._inputProteine);
        _lipidsFood = (EditText) findViewById(R.id._inputLipide);
        _glycidesFood = (EditText) findViewById(R.id._inputGlucide);
        _fibreFood = (EditText) findViewById(R.id._inputFibre);
        _glycemicIndexFood = (EditText) findViewById(R.id._inputIndGlycemique);

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

        _calorieFood.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _proteinFood.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _lipidsFood.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _glycidesFood.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _fibreFood.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
                        ActivityFood.loadGridFoods();
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
                        ActivityFood.loadGridFoods();
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

        Food _food = new Food();

        _food.set_name(_nameFood.getText().toString());
        _food.set_brand(_brandFood.getText().toString());
        _food.set_portionSize(_portionFood.getText().toString());


        if (_option.equals("modify")) {
            _food.set_id(_idFood);
            ret = FoodControl.modifyFood(_food);

        } else if (_option.equals("add")) {
            ret = FoodControl.addFood(_food);

        } else {
            dialogYesNo("Vous êtes sure de supprimer ce Aliment ?");

        }

        return ret;
    }


    //====
    public void loadOptionView() {

        _idFood = _food.get_id();

        _titleCard.setText("Informations sur l aliment");
        _nameFood.setText(_food.get_name());
        _brandFood.setText(_food.get_brand());
        _portionFood.setText(_food.get_portionSize());

        _nameFood.setEnabled(false);
        _brandFood.setEnabled(false);
        _portionFood.setEnabled(false);
        _calorieFood.setEnabled(false);
        _proteinFood.setEnabled(false);
        _lipidsFood.setEnabled(false);
        _glycidesFood.setEnabled(false);
        _fibreFood.setEnabled(false);
        _glycemicIndexFood.setEnabled(false);
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
        _calorieFood.setEnabled(true);
        _proteinFood.setEnabled(true);
        _lipidsFood.setEnabled(true);
        _glycidesFood.setEnabled(true);
        _fibreFood.setEnabled(true);
        _glycemicIndexFood.setEnabled(true);

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
                        ActivityFood.loadGridFoods();
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
                _food.set_id(_idFood);
                _retDialogYesNo = FoodControl.removeFood(_food);
                dismiss();
                ActivityFood.loadGridFoods();
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

        if (!_nameFood.getText().toString().equals("")){
            if (!_brandFood.getText().toString().equals("")){
                if (!_portionFood.getText().toString().equals("")){
                    if (!_calorieFood.getText().toString().equals("")){
                        if (!_proteinFood.getText().toString().equals("")){
                            if (!_lipidsFood.getText().toString().equals("")){
                                if (!_glycidesFood.getText().toString().equals("")){
                                    if (!_fibreFood.getText().toString().equals("")){
                                        if (!_glycemicIndexFood.getText().toString().equals("")){

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