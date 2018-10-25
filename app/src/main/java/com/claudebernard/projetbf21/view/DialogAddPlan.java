package com.claudebernard.projetbf21.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.model.Client;
import com.claudebernard.projetbf21.model.Coach;
import com.claudebernard.projetbf21.model.FoodPlan;
import com.claudebernard.projetbf21.model.PlanDays;
import com.claudebernard.projetbf21.model.PlanMeals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DialogAddPlan extends Dialog {

    public Context  _context;
    public Activity _activity;
    public ImageButton _btn1, _btn2;
    public EditText _inputNamePlan, _inputPerStartPlan, _inputPerEndPlan;
    public DatePickerDialog datePickerDialog;
    public Calendar calendar = Calendar.getInstance();
    public int year = calendar.get(Calendar.YEAR);
    public int month = calendar.get(Calendar.MONTH);
    public int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    public Client _client;
    public Coach _coach;
    public Date dtStart;
    public Date dtFin;


    //=====
    public DialogAddPlan(Activity a, Context c, Client client, Coach coach) {
        super(a);
        this._activity = a;
        this._context = c;
        this._client = client;
        this._coach = coach;
    }


    //=====
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_add_plan);
        setCanceledOnTouchOutside(false);

        loadInfoDialog();
        loadActionButtons();
    }


    //====
    public void loadInfoDialog() {

        _inputNamePlan = (EditText) findViewById(R.id._inputNamePlan);
        _inputPerStartPlan = (EditText) findViewById(R.id._inputPerStartPlan);
        _inputPerEndPlan = (EditText) findViewById(R.id._inputPerEndPlan);

        _btn1 = (ImageButton) findViewById(R.id._btn_1);
        _btn2 = (ImageButton) findViewById(R.id._btn_2);

        _inputNamePlan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { if (!hasFocus) { hideKeyboard(v); } }});

        _inputPerStartPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(_context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                _inputPerStartPlan.setText(year + "/" + month + "/" + day);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        _inputPerEndPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(_context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                _inputPerEndPlan.setText(year + "/" + month + "/" + day);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });
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
                    getDataDialog();
                    dismiss();
                }
            }
        });
    }


    //=====
    public void getDataDialog() {

        FoodPlan _plan = new FoodPlan();

        _plan.set_namePlan(_inputNamePlan.getText().toString());
        _plan.set_client(_client);
        _plan.set_coach(_coach);
        _plan.set_planDays(getDates());

        System.out.println("coloca ponto de debug nessa linha");
        // chamar para gravar
    }


    //=====
    private ArrayList<PlanDays> getDates() {

        ArrayList<PlanDays> listDatePlanDay = new ArrayList<PlanDays>();
        ArrayList<PlanMeals> listPlanMeals = new ArrayList<PlanMeals>();

        for (int x = 0; x < 6; x++) {
            PlanMeals planMeals = new PlanMeals();
            planMeals.set_name("Repas " + (x +1));
            planMeals.set_order(x + 1);
            listPlanMeals.add(planMeals);
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(dtStart);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(dtFin);

        while(!cal1.after(cal2)) {
            final PlanDays pd = new PlanDays();
            pd.set_day(cal1.getTime());
            pd.set_planMeals(listPlanMeals);
            listDatePlanDay.add(pd);
            cal1.add(Calendar.DATE, 1);
        }
        return listDatePlanDay;
    }

    //=====
    public boolean validationForm(){

        String message = "";

        if (!_inputNamePlan.getText().toString().equals("")  && _inputNamePlan.getText().length() <= 45){
            if (!_inputPerStartPlan.getText().toString().equals("")){
                if (!_inputPerEndPlan.getText().toString().equals("")){
                    try {
                        dtStart = dateFormat.parse(_inputPerStartPlan.getText().toString());
                        dtFin   = dateFormat.parse(_inputPerEndPlan.getText().toString());

                        if (dtFin.after(dtStart)){
                            return true;

                        } else {
                            message += "La date de fin doit être postérieure à la date de début.\n";

                        }

                    } catch (ParseException e) {
                        e.printStackTrace();

                    }

                } else {
                    message += "Le champ Fin doivent être remplis.\n";
                }
            } else {
                message += "Le champ Début doivent être remplis.\n";
            }
        } else {
            message += "Le champ Nom du Plan doit être remplis et le maximum sont 45 caractères.\n";
        }

        alertForm(message);

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