package com.claudebernard.projetbf21.control;

import android.util.Log;

import com.claudebernard.projetbf21.comm.ApiClient;
import com.claudebernard.projetbf21.comm.ApiInterface;
import com.claudebernard.projetbf21.model.Food;
import com.claudebernard.projetbf21.model.FoodPlan;
import com.claudebernard.projetbf21.model.PlanDays;
import com.claudebernard.projetbf21.model.PlanMeals;
import com.claudebernard.projetbf21.model.ResponseServer;
import com.claudebernard.projetbf21.view.DialogModifyMeal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanMealsControl {

    private ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
    private boolean isCorrect;


    //=====
    public boolean saveFoodToMeal(final FoodPlan foodPlan, PlanDays planDays, PlanMeals object, final List<Food> listFood, String foodSelected){

            for (int index = 0; index < listFood.size(); index++) {
                if (listFood.get(index).get_name().equals(foodSelected)){

                    Call<ResponseServer> call = apiInterface.saveFoodToMeal(foodPlan.get_idFoodPlan(), planDays.get_idPlanDay(), object.get_idPlanMeal(), listFood.get(index));

                    call.enqueue(new Callback<ResponseServer>() {
                        @Override
                        public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                            Log.i("PlanMeals Control", "Success - saveFoodToMeal");
                            if (response.code() == 202) {
                                isCorrect = true;
                                DialogModifyMeal._countSave = DialogModifyMeal._countSave + 1;
                                DialogModifyMeal.saveFoodInMeat();
                            } else {
                                isCorrect = false;
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseServer> call, Throwable t) {
                            Log.e("PlanMeals Control", "Error - saveFoodToMeal");
                            isCorrect = false;
                        }
                    });
                    break;
                }
            }
        return isCorrect;
    }


    //=====
    public boolean deletePlanMealById(FoodPlan foodPlan, PlanDays planDays, PlanMeals object){

        StringBuilder sb = new StringBuilder("/foodPlan/");
        sb.append(foodPlan.get_idFoodPlan());
        sb.append("/planDay/");
        sb.append(planDays.get_idPlanDay());
        sb.append("?idPlanMeal=");
        sb.append(object.get_idPlanMeal());

        Call<ResponseServer> call = apiInterface.deletePlanMealById(sb.toString());

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("PlanMeals Control", "Success - deletePlanMealById");
                if(response.code() == 202) {
                    isCorrect = true;
                } else {
                    isCorrect = false;
                }
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("PlanMeals Control", "Error - deletePlanMealById");
                isCorrect = false;
            }
        });
        return isCorrect;
    }


    //=====
    public boolean deleteFoodFromMeal(final FoodPlan foodPlan, PlanDays planDays, PlanMeals object, final List<Food> listFood, String foodSelected){

        for (int index = 0; index < listFood.size(); index++) {
            if (listFood.get(index).get_name().equals(foodSelected)) {

                StringBuilder sb = new StringBuilder("/foodPlan/");
                sb.append(foodPlan.get_idFoodPlan());
                sb.append("/planDay/");
                sb.append(planDays.get_idPlanDay());
                sb.append("/planMeal/");
                sb.append(object.get_idPlanMeal());
                sb.append("/food/list?idFood=");
                sb.append(listFood.get(index).get_id());

                Call<ResponseServer> call = apiInterface.deleteFoodFromMeal(sb.toString());

                call.enqueue(new Callback<ResponseServer>() {
                    @Override
                    public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                        Log.i("PlanMeals Control", "Success - deleteFoodFromMeal");
                        if (response.code()==200 || response.code() == 202) {
                            isCorrect = true;
                            DialogModifyMeal.saveFoodInMeat();
                        } else {
                            isCorrect = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseServer> call, Throwable t) {
                        Log.e("PlanMeals Control", "Error - deleteFoodFromMeal");
                        isCorrect = false;
                    }
                });
                break;
            }
        }
        return isCorrect;
    }
}
