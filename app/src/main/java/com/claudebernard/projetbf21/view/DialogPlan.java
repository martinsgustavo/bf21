package com.claudebernard.projetbf21.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.control.FoodPlanControl;
import com.claudebernard.projetbf21.model.Plan;

public class DialogPlan extends Dialog {

    private Activity _activity;
    private ImageButton _btn1, _btn2;
    private EditText _inputNamePlan, _inputObjectivePlan, _inputProteinPlan, _inputLipidePlan, _inputGlycidesPlan, _inputPerStartPlan, _inputPerEndPlan;
    private TextView _titlePlan;
    private String _namePlan;
    private Plan _plan;
    private Context _context;
    private boolean _retDialogYesNo;

    FoodPlanControl foodPlanControl;


    //=====
    public DialogPlan(Activity a, Context c, Plan plan) {
        super(a);
        this._activity = a;
        this._plan = plan;
        this._context = c;
    }

    //=====
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_plan);
        setCanceledOnTouchOutside(false);

        loadInfoDialog();
        loadActionButtons();
    }

    //====
    public void loadInfoDialog() {

        _titlePlan = (TextView) findViewById(R.id._titlePlan);
        _inputNamePlan = (EditText) findViewById(R.id._inputNamePlan);
        _inputObjectivePlan = (EditText) findViewById(R.id._inputObjectivePlan);
        _inputProteinPlan = (EditText) findViewById(R.id._inputProteinPlan);
        _inputLipidePlan = (EditText) findViewById(R.id._inputLipidePlan);
        _inputGlycidesPlan = (EditText) findViewById(R.id._inputGlycidesPlan);
        _inputPerStartPlan = (EditText) findViewById(R.id._inputPerStartPlan);
        _inputPerEndPlan = (EditText) findViewById(R.id._inputPerEndPlan);

        _btn1 = (ImageButton) findViewById(R.id._btn_1);
        _btn2 = (ImageButton) findViewById(R.id._btn_2);

        _titlePlan.setText("Ajouter - Nouveau Plan");

        _inputNamePlan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _inputObjectivePlan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _inputProteinPlan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _inputLipidePlan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _inputGlycidesPlan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _inputPerStartPlan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _inputPerEndPlan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});
    }

    //=====
    public void loadActionButtons() {

        _btn1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                    dismiss();
            }
        });

        _btn2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (validationForm()) {
                    if (getDataDialog()) {
                        dismiss();
                        ActivityPlan.loadGridPlans();
                    }
                } else {
                    alertForm();
                }
            }
        });
    }


    //=====
    public boolean getDataDialog() {

        Plan _plan = new Plan();

        _plan.set_client("");
        _plan.set_name(_inputNamePlan.getText().toString());
        _plan.set_objective(_inputObjectivePlan.getText().toString());
        _plan.set_protein(_inputProteinPlan.getText().toString());
        _plan.set_lipids(_inputLipidePlan.getText().toString());
        _plan.set_glycides(_inputGlycidesPlan.getText().toString());
        _plan.set_periodStart(_inputPerStartPlan.getText().toString());
        _plan.set_periodEnd(_inputPerEndPlan.getText().toString());

        return foodPlanControl.saveData(_plan);
    }


//    //=====
//    public boolean dialogYesNo(String message) {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(_context);
//
//        builder.setMessage(message).setPositiveButton("Oui", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                _plan.set_name(_nameClient);
//                _retDialogYesNo = ClientControl.removeClient(_plan);
//                dismiss();
//                ActivityClient.loadGridClients();
//            }
//        }).setNegativeButton("Non", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                _retDialogYesNo = false;
//            }
//        }).create();
//
//        AlertDialog alert = builder.create();
//        alert.show();
//
//        return _retDialogYesNo;
//    }


    //=====
    public boolean validationForm(){

        if (!_inputNamePlan.getText().toString().equals("")){
            if (!_inputObjectivePlan.getText().toString().equals("")){
                if (!_inputProteinPlan.getText().toString().equals("")){
                    if (!_inputLipidePlan.getText().toString().equals("")){
                        if (!_inputGlycidesPlan.getText().toString().equals("")){
                            if (!_inputPerStartPlan.getText().toString().equals("")){
                                if (!_inputPerEndPlan.getText().toString().equals("")){

                                            return true;
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