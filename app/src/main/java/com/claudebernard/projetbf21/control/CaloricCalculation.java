package com.claudebernard.projetbf21.control;

import com.claudebernard.projetbf21.model.Client;

public class CaloricCalculation {

    private final int FIXED_NUMBER_STEP1 = 24;
    private final double MULTIPLY_NUMBER_MAN = 1.0;
    private final double MULTIPLY_NUMBER_WOMAN = 0.9;

    private final double FIXED_NUMBER_STEP2_RANGE1 = 1.0;
    private final double FIXED_NUMBER_STEP2_RANGE2 = 0.95;
    private final double FIXED_NUMBER_STEP2_RANGE3 = 0.90;
    private final double FIXED_NUMBER_STEP2_RANGE4 = 0.85;

    public Double findBasilMetabolicRate(Client client){
        Double bmr;
        Double step1;

        if(client.get_gender() == 'M'){
            step1 = MULTIPLY_NUMBER_MAN * client.get_weight() * FIXED_NUMBER_STEP1;
        } else {
            step1 = MULTIPLY_NUMBER_WOMAN * client.get_weight() * FIXED_NUMBER_STEP1;
        }

        if(client.get_gender() == 'M') {
            if (client.get_bodyFatPercentage() >= 10 && client.get_bodyFatPercentage() <= 14) {
                bmr = step1 * FIXED_NUMBER_STEP2_RANGE1;
            } else if (client.get_bodyFatPercentage() > 14 && client.get_bodyFatPercentage() <= 20) {
                bmr = step1 * FIXED_NUMBER_STEP2_RANGE2;
            } else if (client.get_bodyFatPercentage() > 20 && client.get_bodyFatPercentage() <= 28) {
                bmr = step1 * FIXED_NUMBER_STEP2_RANGE3;
            } else if (client.get_bodyFatPercentage() > 28) {
                bmr = step1 * FIXED_NUMBER_STEP2_RANGE4;
            } else {
                bmr = null;
            }
        } else {
            if (client.get_bodyFatPercentage() >= 14 && client.get_bodyFatPercentage() <= 18) {
                bmr = step1 * FIXED_NUMBER_STEP2_RANGE1;
            } else if (client.get_bodyFatPercentage() > 18 && client.get_bodyFatPercentage() <= 28) {
                bmr = step1 * FIXED_NUMBER_STEP2_RANGE2;
            } else if (client.get_bodyFatPercentage() > 28 && client.get_bodyFatPercentage() <= 38) {
                bmr = step1 * FIXED_NUMBER_STEP2_RANGE3;
            } else if (client.get_bodyFatPercentage() > 38) {
                bmr = step1 * FIXED_NUMBER_STEP2_RANGE4;
            } else {
                bmr = null;
            }
        }
        return bmr;
    }

    public Double totalDailyCalories (Client client){
        return client.get_bmr() * client.getClientactivityLevel().getTax();
    }
}
