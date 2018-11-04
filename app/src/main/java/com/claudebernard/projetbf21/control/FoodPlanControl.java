package com.claudebernard.projetbf21.control;

import android.util.Log;

import com.claudebernard.projetbf21.comm.ApiClient;
import com.claudebernard.projetbf21.comm.ApiInterface;
import com.claudebernard.projetbf21.model.FoodPlan;
import com.claudebernard.projetbf21.model.FoodPlanBD;
import com.claudebernard.projetbf21.model.PlanDays;
import com.claudebernard.projetbf21.model.PlanDaysBD;
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

public class FoodPlanControl implements GenericControl<FoodPlan>{

    private ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
    private List<FoodPlan> foodPlans;
    private FoodPlan foodPlan;
    private boolean isCorrect;

    @Override
    public List<FoodPlan> getDataAll() {

        Call<ResponseServerArray> call = apiInterface.findAllPlans();

        call.enqueue(new Callback<ResponseServerArray>() {
            @Override
            public void onResponse(Call<ResponseServerArray> call, Response<ResponseServerArray> response) {
                Log.i("FoodPlan Control", "Success - getDataFoodPlans");
                if(response.body().toString() != ""){
                    GsonBuilder gsonBuilder = new GsonBuilder();

                    gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
                    gsonBuilder.registerTypeAdapter(Date.class, new DataDeserializer());

                    Gson gson = gsonBuilder.create();

                    String string = new Gson().toJson(response.body().getMeta());

                    Type listType = new TypeToken<ArrayList<FoodPlan>>() {}.getType();
                    foodPlans = gson.fromJson(string, listType);
                }
            }

            @Override
            public void onFailure(Call<ResponseServerArray> call, Throwable t) {
                Log.e("FoodPlan Control", "Error - getDataFoodPlans");
                foodPlans = null;
            }
        });

        return foodPlans;
    }

    @Override
    public FoodPlan getData(Integer id) {

        StringBuilder sb = new StringBuilder("/foodPlan?idFoodPlan=");
        sb.append(id);

        Call<ResponseServer> call = apiInterface.findPlan(sb.toString());

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("FoodPlan Control", "Success - getDataFoodPlan");
                String string = new Gson().toJson(response.body().getMeta());
                foodPlan = new Gson().fromJson(string, FoodPlan.class);
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("FoodPlan Control", "Error - getDataFoodPlan");
                foodPlan = null;
            }
        });
        return foodPlan;
    }


    public List<FoodPlan> getDataAllClient(Integer id) {

        StringBuilder sb = new StringBuilder("/foodPlan/list?idClient=");
        sb.append(id);

        Call<ResponseServerArray> call = apiInterface.findAllPlansClient(sb.toString());

        call.enqueue(new Callback<ResponseServerArray>() {
            @Override
            public void onResponse(Call<ResponseServerArray> call, Response<ResponseServerArray> response) {

                if(response.body().toString() != "") {
                    GsonBuilder gsonBuilder = new GsonBuilder();

                    gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
                    gsonBuilder.registerTypeAdapter(Date.class, new DataDeserializer());

                    Gson gson = gsonBuilder.create();

                    String string = gson.toJson(response.body().getMeta());

                    Type listType = new TypeToken<ArrayList<FoodPlan>>() {}.getType();
                    foodPlans = gson.fromJson(string, listType);

                }
            }

            @Override
            public void onFailure(Call<ResponseServerArray> call, Throwable t) {
                Log.e("FoodPlan Control", "Error - getDataFoodPlans");
                foodPlans = null;
            }
        });

        return foodPlans;
    }


    @Override
    public boolean saveData(FoodPlan object) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
        gsonBuilder.registerTypeAdapter(Date.class, new DataDeserializer());
        Gson gson = gsonBuilder.create();

        String strJson = gson.toJson(object);
        FoodPlanBD foodPlanBD = gson.fromJson(strJson, FoodPlanBD.class);

        Call<ResponseServer> call = apiInterface.saveFoodPlan(foodPlanBD);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("FoodPlan Control", "Success - saveData");

                if(response.code() == 201) {
                    isCorrect = true;
                } else {
                    isCorrect = false;
                }
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("FoodPlan Control", "Error - saveData");
                isCorrect = false;
            }
        });

        return isCorrect;
    }

    public boolean saveDaysToPlan(Integer id, PlanDays planDays){

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
        gsonBuilder.registerTypeAdapter(Date.class, new DataDeserializer());
        Gson gson = gsonBuilder.create();

        String strJson = gson.toJson(planDays);
        PlanDaysBD planDaysBD = gson.fromJson(strJson, PlanDaysBD.class);

        Call<ResponseServer> call = apiInterface.addDayToPlan(id, planDaysBD);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("FoodPlan Control", "Success - saveData");

                if(response.code() == 202) {
                    isCorrect = true;
                } else {
                    isCorrect = false;
                }
            }
            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("FoodPlan Control", "Error - saveData");
                isCorrect = false;
            }
        });
        return isCorrect;
    }

    @Override
    public boolean editData(FoodPlan object) {

        Call<ResponseServer> call = apiInterface.editFoodPlan(foodPlan);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("FoodPlan Control", "Success - editData");
                if(response.code() == 200) {
                    isCorrect = true;
                } else {
                    isCorrect = false;
                }
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("FoodPlan Control", "Error - editData");
                isCorrect = false;
            }
        });
        return isCorrect;
    }

    @Override
    public boolean deleteData(FoodPlan object) {

        StringBuilder sb = new StringBuilder("/foodPlan?idFoodPlan=");
        sb.append(object.get_idFoodPlan());

        Call<ResponseServer> call = apiInterface.deleteFoodPlan(sb.toString());

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("FoodPlan Control", "Success - deleteData");
                if(response.code() == 202) {
                    isCorrect = true;
                } else {
                    isCorrect = false;
                }
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("FoodPlan Control", "Error - deleteData");
                isCorrect = false;
            }
        });
        return isCorrect;
    }

    public boolean sendPlan(FoodPlan object) {
        StringBuilder sb = new StringBuilder("/foodPlan/email?idFoodPlan=");
        sb.append(object.get_idFoodPlan());

        Call<ResponseServer> call = apiInterface.sendPlanByEmail(sb.toString());

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("FoodPlan Control", "Success - sendPlan");
                if(response.code() == 200) {
                    isCorrect = true;
                } else {
                    isCorrect = false;
                }
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("FoodPlan Control", "Error - sendPlan");
                isCorrect = false;
            }
        });
        return isCorrect;

    }
}
