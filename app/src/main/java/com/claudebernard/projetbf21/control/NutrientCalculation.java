package com.claudebernard.projetbf21.control;

import com.claudebernard.projetbf21.model.Client;

public class NutrientCalculation {

    private final double KG_TO_POUNDS = 2.2;

    private final int PROTEIN_CAL_GRAM = 4;
    private final int CARBO_CAL_GRAM = 4;
    private final int FAT_CAL_GRAM = 9;

    private final double SEDENTARY_ADULT = 0.40;
    private final double ADULT_RECREATIONAL_EXERCISER = 0.75;
    private final double ADULT_COMPETITIVE_ATHLETE = 0.90;
    private final double ADULT_BUILDING_MUSCLE_MASS = 0.90;
    private final double DIETING_ATHLETE = 1.0;
    private final double GROWING_TEENAGE_ATHLETE = 1.0;

    private final double IDEAL_FAT_CALORIES = 0.15;

    public Double proteinCalorieDay(String requirement, Client client){

        double pcd;
        double clientWeightInPounds = client.get_weight() * KG_TO_POUNDS;

        switch (requirement){
            case "Sedentary Adult" :
                pcd = SEDENTARY_ADULT * clientWeightInPounds * PROTEIN_CAL_GRAM;
                break;
            case "Adult Recreational Exerciser" :
                pcd = ADULT_RECREATIONAL_EXERCISER * clientWeightInPounds * PROTEIN_CAL_GRAM;
                break;
            case "Adult Competitive Athlete" :
                pcd = ADULT_COMPETITIVE_ATHLETE * clientWeightInPounds * PROTEIN_CAL_GRAM;
                break;
            case "Adult Building Muscle Mass" :
                pcd = ADULT_BUILDING_MUSCLE_MASS * clientWeightInPounds * PROTEIN_CAL_GRAM;
                break;
            case "Dieting Athlete" :
                pcd = DIETING_ATHLETE * clientWeightInPounds * PROTEIN_CAL_GRAM;
                break;
            case "Growing Teenage Athlete" :
                pcd = GROWING_TEENAGE_ATHLETE * clientWeightInPounds * PROTEIN_CAL_GRAM;
                break;
            default :
                pcd = clientWeightInPounds * PROTEIN_CAL_GRAM;
                break;
        }
        return pcd;
    }

    public Double proteinGramDay(Double proteinCalorieDay){
        return proteinCalorieDay / PROTEIN_CAL_GRAM;
    }

    public Double fatCalorieDay(Double totalDailyCalories){
        return IDEAL_FAT_CALORIES * totalDailyCalories;
    }

    public Double fatGramDay(Double fatCalorieDay){
        return fatCalorieDay / FAT_CAL_GRAM;
    }

    public Double carbohydratesCalorieDay(Double proteinCalorieDay, Double fatCalorieDay, Double totalDailyCalories){
        return totalDailyCalories - proteinCalorieDay - fatCalorieDay;
    }

    public Double carbohydratesGramDay(Double carbohydratesCalorieDay){
        return carbohydratesCalorieDay / CARBO_CAL_GRAM;
    }

}
