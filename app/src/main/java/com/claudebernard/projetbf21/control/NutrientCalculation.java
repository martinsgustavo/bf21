package com.claudebernard.projetbf21.control;

import com.claudebernard.projetbf21.model.Client;

public class NutrientCalculation {

    private final int FAT_CAL_GRAM = 9;
    private final int CARBO_CAL_GRAM = 4;
    private final int PROTEIN_CAL_GRAM = 4;
    private final double KG_TO_POUNDS = 2.2;
    private final double IDEAL_FAT_CALORIES = 0.15;


    //=====
    public Double proteinCalorieDay(Client client){
        double clientWeightInPounds = client.get_weight() * KG_TO_POUNDS;
        return client.getClientProteinRequirement().getTax() * clientWeightInPounds * PROTEIN_CAL_GRAM;
    }


    //=====
    public Double proteinGramDay(Double proteinCalorieDay){
        return proteinCalorieDay / PROTEIN_CAL_GRAM;
    }


    //=====
    public Double fatCalorieDay(Double totalDailyCalories){
        return IDEAL_FAT_CALORIES * totalDailyCalories;
    }


    //=====
    public Double fatGramDay(Double fatCalorieDay){
        return fatCalorieDay / FAT_CAL_GRAM;
    }


    //=====
    public Double carbohydratesCalorieDay(Double proteinCalorieDay, Double fatCalorieDay, Double totalDailyCalories){
        return totalDailyCalories - proteinCalorieDay - fatCalorieDay;
    }


    //=====
    public Double carbohydratesGramDay(Double carbohydratesCalorieDay){
        return carbohydratesCalorieDay / CARBO_CAL_GRAM;
    }

}
