package com.claudebernard.projetbf21.control;

import android.util.Log;

import com.claudebernard.projetbf21.comm.ApiClient;
import com.claudebernard.projetbf21.comm.ApiInterface;
import com.claudebernard.projetbf21.model.FoodPlan;
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
        return null;
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

        return false;
    }

    @Override
    public boolean editData(FoodPlan object) {

        return false;
    }

    @Override
    public boolean deleteData(FoodPlan object) {

        return false;
    }
}
