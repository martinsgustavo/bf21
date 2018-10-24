package com.claudebernard.projetbf21.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.control.FoodControl;
import com.claudebernard.projetbf21.control.FoodPlanControl;
import com.claudebernard.projetbf21.control.PlanMealsControl;
import com.claudebernard.projetbf21.model.Food;
import com.claudebernard.projetbf21.model.FoodPlan;
import com.claudebernard.projetbf21.model.PlanDays;
import com.claudebernard.projetbf21.model.PlanMeals;

import java.util.ArrayList;
import java.util.List;

public class DialogModifyMeal  extends Dialog {

    private static Context _context;
    private static Activity _activity;
    private ImageButton _btn1, _btn2;
    private static ListView _list_checkable;
    private static ArrayAdapter<String> _adapterFoods;
    private FoodControl _foodControl = new FoodControl();
    public  static ArrayList<Food> _listFood;
    private static ArrayList<String> _selectedItems;
    private static ArrayList<String> _selectedItemsOrig;
    private static PlanMealsControl _planMealsControl = new PlanMealsControl();
    private static FoodPlan _listPlans;
    private static PlanDays _planDays;
    private static PlanMeals _planMeals;
    private static int _countSave;


    //=====
    public DialogModifyMeal(Activity a, Context c, FoodPlan listPlans, PlanDays planDays, PlanMeals planMeals) {
        super(a);
        this._activity = a;
        this._context = c;
        this._listPlans = listPlans;
        this._planDays = planDays;
        this._planMeals = planMeals;
    }

    //=====
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_modify_meal);
        setCanceledOnTouchOutside(false);

        _btn1 = (ImageButton) findViewById(R.id._btn_1);
        _btn2 = (ImageButton) findViewById(R.id._btn_2);
        _list_checkable = (ListView) findViewById(R.id._list_checkable);
        _list_checkable.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        _foodControl.getDataAll("DialogModifyMeal");
        loadActionButtons();
    }



    //====
    public static void loadInfoDialog(ArrayList<Food> foods) {

        ArrayList<String> listFoodName = new ArrayList<>();
        _selectedItems = new ArrayList<>();
        _listFood = foods;

        for (int x = 0; x < _listFood.size(); x++) {
            listFoodName.add(_listFood.get(x).get_name());
        }

        _adapterFoods = new ArrayAdapter<String>(_context,android.R.layout.simple_list_item_checked, listFoodName);
        _list_checkable.setAdapter(_adapterFoods);
        _list_checkable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = ((TextView)view).getText().toString();
                if(_selectedItems.contains(itemSelected)){
                    _selectedItems.remove(itemSelected);
                } else {
                    _selectedItems.add(itemSelected);
                }
            }
        });

        if (_planMeals.get_food() != null){
            for(int x = 0; x < _planMeals.get_food().size(); x++){
                for (int y = 0; y < listFoodName.size(); y++) {
                   if (_planMeals.get_food().get(x).get_name().equals(listFoodName.get(y))){
                       _selectedItems.add(_planMeals.get_food().get(x).get_name());
                       _list_checkable.setItemChecked(y, true);
                   }
                }
            }
            _selectedItemsOrig = _selectedItems;
        }
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

                if (_selectedItems.size() > 0){
                    _countSave = 0;
                    saveFoodInMeat();
                }
                dismiss();
            }
        });
    }


    //=====
    public static void saveFoodInMeat(){

        if (_countSave < _selectedItems.size()){
            _planMealsControl.saveFoodToMeal(_listPlans, _planDays, _planMeals, _listFood, _selectedItems.get(_countSave));
            _countSave++;

        } else {
            new FoodPlanControl().getDataAllClient(_listPlans.get_client(),"refresh");
        }
    }


    //=====
    public static void refreshGrid(List<FoodPlan> list){
        List<FoodPlan> foodPlansClient = list;

        for (int y = 0; y < foodPlansClient.size(); y++) {
            if (foodPlansClient.get(y).get_idFoodPlan() == _listPlans.get_idFoodPlan()) {
                ActivityPlan._foodPlansClient = foodPlansClient;
                ActivityPlan._foodPlanSelect = foodPlansClient.get(y);
                ActivityPlan.loadGridPlans();
                break;
            }
        }
    }
}