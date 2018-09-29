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
import com.claudebernard.projetbf21.model.Food;

public class DialogFood extends Dialog {

    private Activity _activity;
    private Button _yes, _no;
    private EditText _name, _portion, _calorie, _protein, _lipids, _glycides, _fibre, _glycemicIndex;
    private String option;
    private Food _food;

    //=====
    public DialogFood(Activity a, String opt, Food food) {
        super(a);
        this._activity = a;
        this.option = opt;
        this._food = food;
    }

    //=====
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_food);
        loadFoodModel();

        this.setCanceledOnTouchOutside(false);
        loadButtonYesNo();

        if(option.equals("modify") || option.equals("view")){

            _name.setText(_food.get_name());
//            _portion.setText(_food.get_portion());
//            _calorie.setText(_food.get_calorie());
//            _protein.setText(_food.get_protein());
//            _lipids.setText(_food.get_lipids());
//            _glycides.setText(_food.get_glycides());
//            _fibre.setText(_food.get_fibre());
//            _glycemicIndex.setText(_food.get_glycemicIndex());

            if(option.equals("modify")) {

                _yes.setText("Modifier");

            } else {

                _name.setFocusable(false);
                _portion.setFocusable(false);
                _calorie.setFocusable(false);
                _protein.setFocusable(false);
                _lipids.setFocusable(false);
                _glycides.setFocusable(false);
                _fibre.setFocusable(false);
                _glycemicIndex.setFocusable(false);
                _yes.setVisibility(View.GONE);
                _no.setVisibility(View.GONE);

                LinearLayout _layout_dialog_food = (LinearLayout) findViewById(R.id._layout_dialog_food);

                _layout_dialog_food.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        dismiss();
                    }
                });
            }
        }
    }

    //====
    public void loadFoodModel(){


        _name          = (EditText)findViewById(R.id._inputName);
        _portion       = (EditText)findViewById(R.id._inputPortion);
        _calorie       = (EditText)findViewById(R.id._inputCalorie);
        _protein       = (EditText)findViewById(R.id._inputProteine);
        _lipids        = (EditText)findViewById(R.id._inputLipide);
        _glycides      = (EditText)findViewById(R.id._inputGlucide);
        _fibre         = (EditText)findViewById(R.id._inputFibre);
        _glycemicIndex = (EditText)findViewById(R.id._inputIndGlycemique);

        _name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v);} }});

        _portion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v);} }});

        _calorie.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v);} }});

        _protein.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v);} }});

        _lipids.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v);} }});

        _glycides.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v);} }});

        _fibre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v);} }});

        _glycemicIndex.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
