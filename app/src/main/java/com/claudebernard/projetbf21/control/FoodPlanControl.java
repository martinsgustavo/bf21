package com.claudebernard.projetbf21.control;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.claudebernard.projetbf21.comm.ApiClient;
import com.claudebernard.projetbf21.comm.ApiInterface;
import com.claudebernard.projetbf21.model.Client;
import com.claudebernard.projetbf21.model.FoodPlan;
import com.claudebernard.projetbf21.model.PlanDays;
import com.claudebernard.projetbf21.model.ResponseServer;
import com.claudebernard.projetbf21.model.ResponseServerArray;
import com.claudebernard.projetbf21.view.AdapterCardClient;
import com.claudebernard.projetbf21.view.DialogModifyMeal;
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

    private ApiInterface _apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
    public static  ArrayList<FoodPlan> _foodPlans;
    public static  ArrayList<FoodPlan> _foodPlansClient;
    private FoodPlan foodPlan;
    private boolean isCorrect;


    //=====
    @Override
    public ArrayList<FoodPlan> getDataAll(String option) {

        Call<ResponseServerArray> call = _apiInterface.findAllPlans();

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
                    _foodPlans = gson.fromJson(string, listType);
                }
            }

            @Override
            public void onFailure(Call<ResponseServerArray> call, Throwable t) {
                Log.e("FoodPlan Control", "Error - getDataFoodPlans");
                _foodPlans = null;
            }
        });

        return _foodPlans;
    }


    //=====
    @Override
    public FoodPlan getData(String option, Integer id) {
        return null;
    }


    //=====
    public ArrayList<FoodPlan> getDataAllClient(final Client _client, final String option) {

        StringBuilder sb = new StringBuilder("/foodPlan/list?idClient=");
        sb.append(_client.get_id());

        Call<ResponseServerArray> call = _apiInterface.findAllPlansClient(sb.toString());

        call.enqueue(new Callback<ResponseServerArray>() {
            @Override
            public void onResponse(Call<ResponseServerArray> call, Response<ResponseServerArray> response) {

                if(response.body() != null) {
                    GsonBuilder gsonBuilder = new GsonBuilder();

                    gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
                    gsonBuilder.registerTypeAdapter(Date.class, new DataDeserializer());

                    Gson gson = gsonBuilder.create();

                    String string = gson.toJson(response.body().getMeta());

                    Type listType = new TypeToken<ArrayList<FoodPlan>>() {}.getType();
                    _foodPlansClient = gson.fromJson(string, listType);

                    if (option.equals("cardClient")) {
                        AdapterCardClient.dialogYesNo(_client);

                    } else if (option.equals("refresh")){
                        DialogModifyMeal.refreshGrid(_foodPlansClient);
                    }
                } else {
                    AdapterCardClient.showDialogClient(_client);
                }
            }

            @Override
            public void onFailure(Call<ResponseServerArray> call, Throwable t) {
                Log.e("FoodPlan Control", "Error - getDataFoodPlans");
                _foodPlansClient = null;
            }
        });

        return _foodPlansClient;
    }


    //=====
    @Override
    public boolean saveData(FoodPlan object) {

        Call<ResponseServer> call = _apiInterface.saveFoodPlan(object);

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


    //=====
    public boolean saveDaysToPlan(Integer id, PlanDays planDays){

        Call<ResponseServer> call = _apiInterface.addDayToPlan(id, planDays);

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


    //=====
    @Override
    public boolean editData(FoodPlan object) {

        Call<ResponseServer> call = _apiInterface.editFoodPlan(foodPlan);

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


    //=====
    @Override
    public boolean deleteData(FoodPlan object) {

        StringBuilder sb = new StringBuilder("/foodPlan?idFoodPlan=");
        sb.append(object.get_idFoodPlan());

        Call<ResponseServer> call = _apiInterface.deleteFoodPlan(sb.toString());

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("FoodPlan Control", "Success - deleteData");
                if(response.code() == 202) {
//                    Snackbar.make(view, "Le plan a été supprimé avec succès.", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
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
}