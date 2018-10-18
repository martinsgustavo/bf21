package com.claudebernard.projetbf21.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.model.FoodPlan;

import java.util.ArrayList;


public class AdapterCardPlan extends BaseAdapter {

    public static final String EXTRA_MESSAGE_HOME = "com.claudebernard.projetbf21.HOME";
    public Context _context;
    public Activity _activity;
    public ArrayList<FoodPlan> _listPlans;
    public int _numberMeal = 1;


    //=====
    public AdapterCardPlan(Activity a,Context c, ArrayList<FoodPlan> plans){
        this._activity = a;
        this._context = c;
        this._listPlans = plans;
    }


    //=====
    @Override
    public int getCount() {

        return _listPlans.size();
    }


    //=====
    @Override
    public Object getItem(int position) {

        return _listPlans.get(position);
    }


    //=====
    @Override
    public long getItemId(int position) {

        return position;
    }


    //=====
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        final FoodPlan _plan = (FoodPlan) this.getItem(position);

        if (position == 0 || position % 7 == 0) {
            view = LayoutInflater.from(_context).inflate(R.layout.card_plan_day, viewGroup, false);

        } else {
            view = LayoutInflater.from(_context).inflate(R.layout.card_plan_plate, viewGroup, false);
            TextView _title = (TextView)  view.findViewById(R.id._title_repas);
            _title.setText("Repas "+_numberMeal);

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(_context,ActivityPlan.class);
//                String message = String.valueOf(_client.get_name());
//                intent.putExtra(EXTRA_MESSAGE_HOME, message);
//                _context.startActivity(intent);
//            }
//        });



            if (_numberMeal == 6){
                _numberMeal = 1;

            } else {
                _numberMeal++;
            }
        }

        return view;
    }
}
