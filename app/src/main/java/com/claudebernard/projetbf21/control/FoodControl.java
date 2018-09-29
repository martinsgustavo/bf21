package com.claudebernard.projetbf21.control;

import com.claudebernard.projetbf21.model.Food;

import java.util.ArrayList;

public class FoodControl {


    //=====
    public static ArrayList getDataFoods() {

        ArrayList<Food> foods = new ArrayList<>();

        for (int index = 0; index < 30; index++) {

            Food food = new Food();

            food.set_name("Food - ");
            //food.set_portion(String.valueOf(index+1));

            foods.add(food);
        }
        return foods;
    }
}
