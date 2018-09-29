package com.claudebernard.projetbf21.control;

import com.claudebernard.projetbf21.model.Coach;
import com.claudebernard.projetbf21.model.Food;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FoodControl {

    private static ArrayList<Food> _listFoods = new ArrayList<>();


    //=====
    public static ArrayList getDataFoods() {

        Collections.sort(_listFoods, new Comparator<Food>() {
            public int compare(Food c1, Food c2) {
                return c1.get_name().compareTo(c2.get_name());
            }
        });

        return _listFoods;
    }


    //=====
    public static boolean addFood(Food food) {
        _listFoods.add(food);

        return true;
    }


    //=====
    public static boolean modifyFood(Food food) {

        for (int i = 0; i < _listFoods.size(); i++) {

            if (food.get_id() == _listFoods.get(i).get_id()){
                _listFoods.get(i).set_name(food.get_name());
                _listFoods.get(i).set_brand(food.get_brand());
                _listFoods.get(i).set_portionSize(food.get_portionSize());

                return true;
            }
        }
        return false;
    }


    //=====
    public static boolean removeFood(Food food) {

        for (int i = 0; i < _listFoods.size(); i++) {

            if (food.get_id() == _listFoods.get(i).get_id()){
                _listFoods.remove(i);

                return true;
            }
        }
        return false;
    }
}
