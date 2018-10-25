package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlanMeals {

    @SerializedName("idPlanMeal")
    @Expose
    private Integer _idPlanMeal;

    @SerializedName("name")
    @Expose
    private String _name;

    @SerializedName("order")
    @Expose
    private Integer _order;

    @SerializedName("foods")
    @Expose
    private List<Food> _food;


    //=====
    public Integer get_idPlanMeal() {
        return _idPlanMeal;
    }

    public void set_idPlanMeal(Integer _idPlanMeal) {
        this._idPlanMeal = _idPlanMeal;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public Integer get_order() {
        return _order;
    }

    public void set_order(Integer _order) {
        this._order = _order;
    }

    public List<Food> get_food() {
        return _food;
    }

    public void set_food(List<Food> _food) {
        this._food = _food;
    }
}
