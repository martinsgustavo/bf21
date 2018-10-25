package com.claudebernard.projetbf21.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.model.FoodPlan;
import com.claudebernard.projetbf21.model.PlanDays;
import com.claudebernard.projetbf21.model.PlanMeals;

import java.util.ArrayList;
import java.util.Calendar;


public class AdapterCardPlan extends BaseAdapter {

    public  Context _context;
    public  Activity _activity;
    public  FoodPlan _listPlans;
    public  int _numberMeal = 1;
    public  TextView _cardDayWeek;
    public  TextView _cardDayDay;
    public  TextView _cardDayMonth;
    public  ListView _listRepas;
    private int positionDay;
    private int positionMeat;


    //=====
    public AdapterCardPlan(Activity a,Context c, FoodPlan plans){
        this._activity = a;
        this._context = c;
        this._listPlans = plans;
    }


    //=====
    @Override
    public int getCount() {

        if (_listPlans.get_planDays() == null) {
            return 0;
        }

        return _listPlans.get_planDays().size() * 7;
    }


    //=====
    @Override
    public Object getItem(int position) {

        return _listPlans.get_planDays().get(position);
    }


    //=====
    @Override
    public long getItemId(int position) {

        return position;
    }


    //=====
    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

            if (position == 0 || position % 7 == 0) {
                view = LayoutInflater.from(_context).inflate(R.layout.card_plan_day, viewGroup, false);
                loadInfoCardDay(view, position);

            } else {
                view = LayoutInflater.from(_context).inflate(R.layout.card_plan_meat, viewGroup, false);
                TextView _title = (TextView) view.findViewById(R.id._title_repas);
                _title.setText("Repas " + _numberMeal);
                loadInfoCardMeat(view, position);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (position <= 6) {
                            positionDay  = 0;
                            positionMeat = position - 1;

                        } else {
                            positionDay  = position / 7;
                            positionMeat = (position % 7) - 1;
                        }

                        PlanDays planDays   = _listPlans.get_planDays().get(positionDay);
                        PlanMeals planMeals = _listPlans.get_planDays().get(positionDay).get_planMeals().get(positionMeat);

                        DialogModifyMeal dmm = new DialogModifyMeal(_activity,_context, _listPlans, planDays, planMeals);
                        dmm.show();
                    }
                });
            }
        return view;
    }


    //=====
    public void loadInfoCardDay(View view, int position){

        _cardDayWeek  = (TextView) view.findViewById(R.id._card_day_1);
        _cardDayDay   = (TextView) view.findViewById(R.id._card_day_2);
        _cardDayMonth = (TextView) view.findViewById(R.id._card_day_3);

        Calendar cal = Calendar.getInstance();
        if (position <= 6)
            cal.setTime(_listPlans.get_planDays().get(0).get_day());
        else {
            cal.setTime(_listPlans.get_planDays().get(position / 7).get_day());
        }

        switch (cal.get(Calendar.DAY_OF_WEEK)){
            case 1 :
                _cardDayWeek.setText("Dim");
                break;
            case 2 :
                _cardDayWeek.setText("Lun");
                break;
            case 3 :
                _cardDayWeek.setText("Mar");
                break;
            case 4 :
                _cardDayWeek.setText("Mer");
                break;
            case 5 :
                _cardDayWeek.setText("Jeu");
                break;
            case 6 :
                _cardDayWeek.setText("Ven");
                break;
            case 7 :
                _cardDayWeek.setText("Sam");
                break;
            default:
                break;
        }

        _cardDayDay.setText(String.valueOf(cal.get(Calendar.DATE)));

        switch (cal.get(Calendar.MONTH)+1){
            case 1 :
                _cardDayMonth.setText("Jan");
                break;
            case 2 :
                _cardDayMonth.setText("Fév");
                break;
            case 3 :
                _cardDayMonth.setText("Mars");
                break;
            case 4 :
                _cardDayMonth.setText("Avril");
                break;
            case 5 :
                _cardDayMonth.setText("Mai");
                break;
            case 6 :
                _cardDayMonth.setText("Juin");
                break;
            case 7 :
                _cardDayMonth.setText("Juil");
                break;
            case 8 :
                _cardDayMonth.setText("Août");
                break;
            case 9 :
                _cardDayMonth.setText("Sept");
                break;
            case 10 :
                _cardDayMonth.setText("Oct");
                break;
            case 11 :
                _cardDayMonth.setText("Nov");
                break;
            case 12 :
                _cardDayMonth.setText("Déc");
                break;
            default:
                break;
        }
    }


    //=====
    public void loadInfoCardMeat(View view, int position){

        _listRepas = (ListView) view.findViewById(R.id._repas_items);

        ArrayList<String> _listMeat = new ArrayList<String>();

        if (position <= 6) {
            positionDay  = 0;
            positionMeat = position - 1;

        } else {
            positionDay  = position / 7;
            positionMeat = (position % 7) - 1;
        }

        if (_listPlans.get_planDays().get(positionDay).get_planMeals() != null){

           for(int x = 0; x < _listPlans.get_planDays().get(positionDay).get_planMeals().size(); x++) {
                if (_listPlans.get_planDays().get(positionDay).get_planMeals().get(x).get_order() == positionMeat + 1){
                    if (_listPlans.get_planDays().get(positionDay).get_planMeals().get(x).get_food() != null) {
                        for(int j = 0; j < _listPlans.get_planDays().get(positionDay).get_planMeals().get(x).get_food().size(); j++) {
                            _listMeat.add(_listPlans.get_planDays().get(positionDay).get_planMeals().get(x).get_food().get(j).get_name() + " - "
                                    + _listPlans.get_planDays().get(positionDay).get_planMeals().get(x).get_food().get(j).get_portionSize() + "g");
                        }
                    }
                    if (_listMeat.size() > 0) {
                        ArrayAdapter<String> list = new ArrayAdapter<String>(_context, R.layout.list_item, R.id._itemList, _listMeat);
                        _listRepas.setAdapter(list);
                    }
                }
           }
        }

        if (_numberMeal == 6) {
            _numberMeal = 1;
        } else {
            _numberMeal++;
        }
    }
}
