package com.claudebernard.projetbf21.control;

import android.util.Log;
import android.view.View;

import com.claudebernard.projetbf21.comm.ApiClient;
import com.claudebernard.projetbf21.comm.ApiInterface;
import com.claudebernard.projetbf21.model.Client;
import com.claudebernard.projetbf21.model.Food;
import com.claudebernard.projetbf21.model.ResponseServer;
import com.claudebernard.projetbf21.model.ResponseServerArray;
import com.claudebernard.projetbf21.view.ActivityClient;
import com.claudebernard.projetbf21.view.ActivityFood;
import com.claudebernard.projetbf21.view.AdapterCardFood;
import com.claudebernard.projetbf21.view.DialogModifyMeal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodControl implements GenericControl<Food> {

    private ApiInterface _apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
    private ArrayList<Food> _foods;
    private Food _food;
    private boolean _isCorrect;


    //=====
    @Override
    public ArrayList<Food> getDataAll(final String option) {
        Call<ResponseServerArray> call = _apiInterface.findAllFoods();

        call.enqueue(new Callback<ResponseServerArray>() {
            @Override
            public void onResponse(Call<ResponseServerArray> call, Response<ResponseServerArray> response) {
                Log.i("Food Control", "Success - getDataFoods");
                if(response.body().toString() != ""){
                    String string = new Gson().toJson(response.body().getMeta());

                    Type listType = new TypeToken<ArrayList<Food>>() {}.getType();
                    _foods = new Gson().fromJson(string, listType);

                    Collections.sort(_foods, new Comparator<Food>() {
                        public int compare(Food f1, Food f2) {
                            return f1.get_name().compareTo(f2.get_name());
                        }
                    });

                    if (option.equals("ActivityFood")) {
                        ActivityFood.loadGridFoods(_foods);

                    } else if (option.equals("DialogModifyMeal")) {
                        DialogModifyMeal.loadInfoDialog(_foods);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseServerArray> call, Throwable t) {
                Log.e("Food Control", "Error - getDataFoods");
                _foods = null;
            }
        });
        return _foods;
    }


    //=====
    @Override
    public Food getData(String option, Integer id) {
        StringBuilder sb = new StringBuilder("/food?idFood=");
        sb.append(id);

        Call<ResponseServer> call = _apiInterface.findFood(sb.toString());

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Food Control", "Success - getDataFood");
                String string = new Gson().toJson(response.body().getMeta());
                _food = new Gson().fromJson(string, Food.class);
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Food Control", "Error - getDataFood");
                _food = null;
            }
        });
        return _food;
    }


    //=====
    @Override
    public boolean saveData(Food object) {
        Call<ResponseServer> call = _apiInterface.saveFood(object);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                if (response.code()==201) {
                    Log.i("Food Control", "Success - saveData");
                    _isCorrect = true;
                    ActivityFood.dismissView();
                    getDataAll("ActivityFood");
                }
            }
            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Food Control", "Error - saveData");
                _isCorrect = false;
            }
        });
        return _isCorrect;
    }


    //=====
    @Override
    public boolean editData(Food object) {
        Call<ResponseServer> call = _apiInterface.editFood(object);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                if (response.code()==200) {
                    Log.i("Food Control", "Success - editData");
                    _isCorrect = true;
                    AdapterCardFood.dismissView();
                    getDataAll("ActivityFood");
                }
            }
            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Food Control", "Error - editData");
                _isCorrect = false;
            }
        });
        return _isCorrect;
    }


    //=====
    @Override
    public boolean deleteData(Food object) {
        StringBuilder sb = new StringBuilder("/food?idFood=");
        sb.append(object.get_id());

        Call<ResponseServer> call = _apiInterface.deleteFood(sb.toString());

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Food Control", "Success - deleteData");
                _isCorrect = true;
                getDataAll("ActivityFood");
            }
            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Food Control", "Error - deleteData");
                _isCorrect = false;
            }
        });
        return _isCorrect;
    }
}
