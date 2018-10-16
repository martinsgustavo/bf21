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

    private final double FIXED_NUMBER_STEP3_RANGE1 = 1.3;
    private final double FIXED_NUMBER_STEP3_RANGE2 = 1.55;
    private final double FIXED_NUMBER_STEP3_RANGE3 = 1.65;
    private final double FIXED_NUMBER_STEP3_RANGE4 = 1.8;

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

        switch (client.getClientactivityLevel().get_idDailyActivityLevel()){

            case 1 :
                client.getClientactivityLevel().setTax(FIXED_NUMBER_STEP3_RANGE1);
                break;

            case 2 :
                client.getClientactivityLevel().setTax(FIXED_NUMBER_STEP3_RANGE2);
                break;

            case 3:
                client.getClientactivityLevel().setTax(FIXED_NUMBER_STEP3_RANGE3);
                break;

            case 4 :
                client.getClientactivityLevel().setTax(FIXED_NUMBER_STEP3_RANGE4);
                break;

            default:
                break;
        }

        return client.get_bmr() * client.getClientactivityLevel().getTax();
    }
}
