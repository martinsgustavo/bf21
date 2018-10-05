package com.claudebernard.projetbf21.control;

import android.util.Log;

import com.claudebernard.projetbf21.comm.ApiClient;
import com.claudebernard.projetbf21.comm.ApiInterface;
import com.claudebernard.projetbf21.model.Food;
import com.claudebernard.projetbf21.model.ResponseServer;
import com.claudebernard.projetbf21.model.ResponseServerArray;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodControl implements GenericControl<Food> {

    private ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
    private List<Food> foods;
    private Food food;
    private boolean isCorrect;

    @Override
    public List<Food> getDataAll() {
        Call<ResponseServerArray> call = apiInterface.findAllFoods();

        call.enqueue(new Callback<ResponseServerArray>() {
            @Override
            public void onResponse(Call<ResponseServerArray> call, Response<ResponseServerArray> response) {
                Log.i("Food Control", "Success - getDataFoods");
                if(response.body().toString() != ""){
                    String string = new Gson().toJson(response.body().getMeta());

                    Type listType = new TypeToken<ArrayList<Food>>() {}.getType();
                    foods = new Gson().fromJson(string, listType);
                }
            }

            @Override
            public void onFailure(Call<ResponseServerArray> call, Throwable t) {
                Log.e("Food Control", "Error - getDataFoods");
                foods = null;
            }
        });

        return foods;
    }

    @Override
    public Food getData(Integer id) {
        StringBuilder sb = new StringBuilder("/food?idFood=");
        sb.append(id);

        Call<ResponseServer> call = apiInterface.findFood(sb.toString());

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Food Control", "Success - getDataFood");
                String string = new Gson().toJson(response.body().getMeta());
                food = new Gson().fromJson(string, Food.class);
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Food Control", "Error - getDataFood");
                food = null;
            }
        });
        return food;
    }

    @Override
    public boolean saveData(Food object) {
        Call<ResponseServer> call = apiInterface.saveFood(object);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Food Control", "Success - saveData");
                isCorrect = true;
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Food Control", "Error - saveData");
                isCorrect = false;
            }
        });

        return isCorrect;
    }

    @Override
    public boolean editData(Food object) {
        Call<ResponseServer> call = apiInterface.editFood(object);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Food Control", "Success - editData");
                isCorrect = true;
            }
            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Food Control", "Error - editData");
                isCorrect = false;
            }
        });
        return isCorrect;
    }

    @Override
    public boolean deleteData(Food object) {
        StringBuilder sb = new StringBuilder("/food?idFood=");
        sb.append(object.get_id());

        Call<ResponseServer> call = apiInterface.deleteFood(sb.toString());

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Food Control", "Success - deleteData");
                isCorrect = true;
            }
            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Food Control", "Error - deleteData");
                isCorrect = false;
            }
        });
        return isCorrect;
    }
}
