package com.claudebernard.projetbf21.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.control.FoodPlanControl;
import com.claudebernard.projetbf21.model.FoodPlan;

import java.util.ArrayList;

public class DialogChangePlan extends Dialog {

    private Context _context;
    private Activity _activity;
    private ImageButton _btn1, _btn2;
    private Spinner _spListPlansClient;
    private ArrayAdapter<String> _adapterPlansClient;
    public ArrayList<FoodPlan> _foodPlansClient;


    //=====
    public DialogChangePlan(Activity a, Context c) {
        super(a);
        this._activity = a;
        this._context = c;
    }

    //=====
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        _foodPlansClient = FoodPlanControl._foodPlansClient;

        setContentView(R.layout.dialog_change_plan);
        setCanceledOnTouchOutside(false);

        loadInfoDialog();
        loadActionButtons();
    }

    //====
    public void loadInfoDialog() {

        _btn1 = (ImageButton) findViewById(R.id._btn_1);
        _btn2 = (ImageButton) findViewById(R.id._btn_2);

        _spListPlansClient = (Spinner) findViewById(R.id._inputSpListPlan);

        ArrayList<String> listPlan = new ArrayList<String>();

        for (int x = 0; x < _foodPlansClient.size(); x++) {
            listPlan.add(_foodPlansClient.get(x).get_namePlan());
        }

        _adapterPlansClient = new ArrayAdapter<String>(_context,android.R.layout.simple_spinner_item, listPlan);
        _adapterPlansClient.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        _spListPlansClient.setAdapter(_adapterPlansClient);
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
                ActivityPlan._foodPlanSelect = _foodPlansClient.get(_spListPlansClient.getSelectedItemPosition());
                ActivityPlan.loadHeaderPlan();
                ActivityPlan.loadGridPlans();
                dismiss();
            }
        });
    }
}