package com.claudebernard.projetbf21.control;

import android.util.Log;

import com.claudebernard.projetbf21.comm.ApiClient;
import com.claudebernard.projetbf21.comm.ApiInterface;
import com.claudebernard.projetbf21.model.FoodPlan;
import com.claudebernard.projetbf21.model.PlanDays;
import com.claudebernard.projetbf21.model.PlanMeals;
import com.claudebernard.projetbf21.model.ResponseServer;
import com.claudebernard.projetbf21.model.ResponseServerArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanDaysControl {

    private ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
    private List<PlanDays> plansDays;
    private PlanDays planDays;
    private boolean isCorrect;

//    public List<PlanDays> getAllDataByPlan(FoodPlan foodPlan) {
//
//        Call<ResponseServerArray> call = apiInterface.findAllPlanDaysByFoodPlan(foodPlan.get_idFoodPlan());
//
//        call.enqueue(new Callback<ResponseServerArray>() {
//            @Override
//            public void onResponse(Call<ResponseServerArray> call, Response<ResponseServerArray> response) {
//                Log.i("PlanDays Control", "Success - getAllDataByPlan");
//                if(response.body().toString() != ""){
//                    GsonBuilder gsonBuilder = new GsonBuilder();
//
//                    gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
//                    gsonBuilder.registerTypeAdapter(Date.class, new DataDeserializer());
//
//                    Gson gson = gsonBuilder.create();
//
//                    String string = new Gson().toJson(response.body().getMeta());
//
//                    Type listType = new TypeToken<ArrayList<FoodPlan>>() {}.getType();
//                    plansDays = gson.fromJson(string, listType);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseServerArray> call, Throwable t) {
//                Log.e("PlanDays Control", "Error - getAllDataByPlan");
//                plansDays = null;
//            }
//        });
//
//        return plansDays;
//    }

    public PlanDays getPlanDayById(FoodPlan foodPlan, PlanDays object){

        StringBuilder sb = new StringBuilder("/foodPlan/");
        sb.append(foodPlan.get_idFoodPlan());
        sb.append("?idPlanDay=");
        sb.append(object.get_idPlanDay());

        Call<ResponseServer> call = apiInterface.findPlanDayById(sb.toString());

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("PlanDays Control", "Success - getPlanDayById");
                if(response.code() == 200){
                    String string = new Gson().toJson(response.body().getMeta());
                    planDays = new Gson().fromJson(string, PlanDays.class);
                } else {
                    planDays = null;
                }
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("PlanDays Control", "Error - getPlanDayById");
                planDays = null;
            }
        });

        return planDays;
    }

    public boolean saveMealToDay(FoodPlan foodPlan, PlanDays planDays, PlanMeals planMeals) {

        Call<ResponseServer> call = apiInterface.saveMealToDay(foodPlan.get_idFoodPlan(), planDays.get_idPlanDay(), planMeals);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("PlanDays Control", "Success - saveMealToDay");
                if(response.code() == 202) {
                    isCorrect = true;
                } else {
                    isCorrect = false;
                }
            }
            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("PlanDays Control", "Error - saveMealToDay");
                isCorrect = false;
            }
        });
        return isCorrect;
    }

    public boolean deleteMealFromDay(FoodPlan foodPlan, PlanDays object, PlanMeals planMeals){

        StringBuilder sb = new StringBuilder("/foodPlan/");
        sb.append(foodPlan.get_idFoodPlan());
        sb.append("/planDay/");
        sb.append(object.get_idPlanDay());
        sb.append("/planMeal/list?idPlanMeal=");
        sb.append(planMeals.get_idPlanMeal());

        Call<ResponseServer> call = apiInterface.deleteMealFromDay(sb.toString());

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("PlanDays Control", "Success - deleteMealFromDay");
                if(response.code() == 202) {
                    isCorrect = true;
                } else {
                    isCorrect = false;
                }
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("PlanDays Control", "Error - deleteMealFromDay");
                isCorrect = false;
            }
        });

        return isCorrect;
    }
}
