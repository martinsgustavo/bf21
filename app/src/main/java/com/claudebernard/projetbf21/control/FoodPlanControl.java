package com.claudebernard.projetbf21.control;

import android.content.Context;

import com.claudebernard.projetbf21.model.Plan;

import java.util.List;

public class FoodPlanControl implements GenericControl<Plan>{

    @Override
    public List<Plan> getDataAll() {
        return null;
    }

    @Override
    public Plan getData(String option, Integer id) {
        return null;
    }

    @Override
    public boolean saveData(Plan object) {

        return false;
    }

    @Override
    public boolean editData(Plan object) {

        return false;
    }

    @Override
    public boolean deleteData(Plan object) {

        return false;
    }
}
