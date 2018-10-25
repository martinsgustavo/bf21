package com.claudebernard.projetbf21.control;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.claudebernard.projetbf21.comm.ApiClient;
import com.claudebernard.projetbf21.comm.ApiInterface;
import com.claudebernard.projetbf21.model.Client;
import com.claudebernard.projetbf21.model.FoodPlan;
import com.claudebernard.projetbf21.model.FoodPlanBD;
import com.claudebernard.projetbf21.model.PlanDays;
import com.claudebernard.projetbf21.model.PlanDaysBD;
import com.claudebernard.projetbf21.model.ResponseServer;
import com.claudebernard.projetbf21.model.ResponseServerArray;
import com.claudebernard.projetbf21.view.ActivityPlan;
import com.claudebernard.projetbf21.view.AdapterCardClient;
import com.claudebernard.projetbf21.view.DialogAddPlan;
import com.claudebernard.projetbf21.view.DialogModifyMeal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

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
    public void getDataAll(String option) {

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
    }


    //=====
    @Override
    public void getData(String option, Integer id) {

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
                        AdapterCardClient.dialogYesNo1(_client);

                    } else if (option.equals("refresh")){
                        DialogModifyMeal.refreshGrid(_foodPlansClient);

                    } else if (option.equals("activityClient")) {
                        Context _context = DialogAddPlan._context;
                        Intent intent = new Intent(_context, ActivityPlan.class);
                        _context.startActivity(intent);
                    }

                } else {
                    AdapterCardClient.dialogYesNo2(_client);
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
    public void saveData(final FoodPlan object) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
        gsonBuilder.registerTypeAdapter(Date.class, new DataDeserializer());
        Gson gson = gsonBuilder.create();

        String strJson = gson.toJson(object);
        FoodPlanBD foodPlanBD = gson.fromJson(strJson, FoodPlanBD.class);

        Call<ResponseServer> call = _apiInterface.saveFoodPlan(foodPlanBD);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("FoodPlan Control", "Success - saveData");

                if(response.code() == 201) {

                    if (DialogAddPlan._option.equals("activityClient")){
                        getDataAllClient(object.get_client(), "activityClient");

                    } else if (DialogAddPlan._option.equals("activityPlan")) {
                        ActivityPlan.messageView("Nouveau Plan sauvegarde avec Succès !");
                        getDataAllClient(object.get_client(), "activityClient");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("FoodPlan Control", "Error - saveData");
            }
        });
    }


    //=====
    public boolean saveDaysToPlan(Integer id, PlanDays planDays){

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
        gsonBuilder.registerTypeAdapter(Date.class, new DataDeserializer());
        Gson gson = gsonBuilder.create();

        String strJson = gson.toJson(planDays);
        PlanDaysBD planDaysBD = gson.fromJson(strJson, PlanDaysBD.class);

        Call<ResponseServer> call = _apiInterface.addDayToPlan(id, planDaysBD);

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
    public void editData(FoodPlan object) {
        Call<ResponseServer> call = _apiInterface.editFoodPlan(foodPlan);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("FoodPlan Control", "Success - editData");
                if(response.code() == 200) {
                    ActivityPlan.messageView("Mis à jour du Plan sauvegarde avec Succès !");
                }
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("FoodPlan Control", "Error - editData");
            }
        });
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
                if (response.code()==200 || response.code() == 202) {
                    isCorrect = true;
                    ActivityPlan.messageView("Plan Supprimé avec Succès !");
                } else {
                    isCorrect = false;
                    ActivityPlan.messageView("Échec pendant la suppression du Plan !");
                }
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("FoodPlan Control", "Error - deleteData");
                isCorrect = false;
                ActivityPlan.messageView("Échec pendant la suppression du Plan !");
            }
        });
        return isCorrect;
    }
}