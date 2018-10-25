package com.claudebernard.projetbf21.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlanDaysBD {

    @SerializedName("idPlanDay")
    @Expose
    private Integer _idPlanDay;

    @SerializedName("day")
    @Expose
    private String _day;

    @SerializedName("planMeals")
    @Expose
    private List<PlanMeals> _planMeals;


    public Integer get_idPlanDay() {
        return _idPlanDay;
    }

    public void set_idPlanDay(Integer _idPlanDay) {
        this._idPlanDay = _idPlanDay;
    }

    public String get_day() { return _day; }

    public void set_day(String _day) { this._day = _day; }

    public List<PlanMeals> get_planMeals() {
        return _planMeals;
    }

    public void set_planMeals(List<PlanMeals> _planMeals) {
        this._planMeals = _planMeals;
    }
}
