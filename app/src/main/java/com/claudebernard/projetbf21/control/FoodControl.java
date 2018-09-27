package com.claudebernard.projetbf21.control;

import android.content.Context;
import android.util.Log;

import com.claudebernard.projetbf21.comm.ApiClient;
import com.claudebernard.projetbf21.comm.ApiInterface;
import com.claudebernard.projetbf21.model.Food;
import com.claudebernard.projetbf21.model.ResponseServer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodControl implements GenericControl<Food> {

    @Override
    public List<Food> getDataAll() {
        return null;
    }

    @Override
    public Food getData(Integer id) {
        return null;
    }

    @Override
    public void saveData(Context c, Food object) {

    }

    @Override
    public void editData(Context c, Food object) {

    }

    @Override
    public void deleteData(Context c, Food object) {

    }
}
