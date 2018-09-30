package com.claudebernard.projetbf21.control;

import com.claudebernard.projetbf21.model.Plan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PlanControl {


    private static ArrayList<Plan> _listPlans = new ArrayList<>();


    //=====
    public static ArrayList getDataPlans() {

        insertData();

        Collections.sort(_listPlans, new Comparator<Plan>() {
            public int compare(Plan c1, Plan c2) {
                return c1.get_name().compareTo(c2.get_name());
            }
        });

        return _listPlans;
    }


    //=====
    public static void insertData() {

        for (int index = 0; index < 42; index++) {

            Plan plan = new Plan();

            plan.set_name("Teste - "+ (index+1));

            _listPlans.add(plan);
        }
    }
}