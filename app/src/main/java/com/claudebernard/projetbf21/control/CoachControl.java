package com.claudebernard.projetbf21.control;

import android.util.Log;

import com.claudebernard.projetbf21.comm.ApiClient;
import com.claudebernard.projetbf21.comm.ApiInterface;
import com.claudebernard.projetbf21.model.Client;
import com.claudebernard.projetbf21.model.Coach;
import com.claudebernard.projetbf21.model.ResponseServer;
import com.claudebernard.projetbf21.model.ResponseServerArray;
import com.claudebernard.projetbf21.view.ActivityClient;
import com.claudebernard.projetbf21.view.ActivityCoach;
import com.claudebernard.projetbf21.view.ActivityFood;
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

public class CoachControl implements GenericControl<Coach>{

    private ApiInterface _apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
    private ArrayList<Coach> _coaches;
    public static Coach _coach;
    private boolean _isCorrect;


    //=====
    @Override
    public ArrayList<Coach> getDataAll() {
        Call<ResponseServerArray> call = _apiInterface.findAllCoaches();

        call.enqueue(new Callback<ResponseServerArray>() {
            @Override
            public void onResponse(Call<ResponseServerArray> call, Response<ResponseServerArray> response) {
                Log.i("Coach Control", "Success - getDataCoaches");
                if(response.body().toString() != ""){
                    String string = new Gson().toJson(response.body().getMeta());

                    Type listType = new TypeToken<ArrayList<Coach>>() {}.getType();
                    _coaches = new Gson().fromJson(string, listType);

                    Collections.sort(_coaches, new Comparator<Coach>() {
                        public int compare(Coach c1, Coach c2) {
                            return c1.get_name().compareTo(c2.get_name());
                        }
                    });

                    ActivityCoach.loadGridCoaches(_coaches);
                }
            }

            @Override
            public void onFailure(Call<ResponseServerArray> call, Throwable t) {
                Log.e("Coach Control", "Error - getDataCoaches");
                _coaches = null;
            }
        });
        return _coaches;
    }


    //=====
    @Override
    public Coach getData(String option, Integer id) {
        final String _option = option;
        StringBuilder sb = new StringBuilder("/coach?idCoach=");
        sb.append(id);

        Call<ResponseServer> call = _apiInterface.findCoach(sb.toString());

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Coach Control", "Success - getDataCoach");
                String string = new Gson().toJson(response.body().getMeta());
                _coach = new Gson().fromJson(string, Coach.class);
                if (_option.equals("client")){
                    ActivityClient.loadNameCoach(_coach.get_name());

                } else if (_option.equals("coach")){
                    ActivityCoach.loadNameCoach(_coach.get_name());

                } else {
                    ActivityFood.loadNameCoach(_coach.get_name());
                }
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Coach Control", "Error - getDataCoach");
                _coach = null;
            }
        });
        return _coach;
    }


    //=====
    @Override
    public boolean saveData(Coach object) {
        Call<ResponseServer> call = _apiInterface.saveCoach(object);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Coach Control", "Success - saveData");
                _isCorrect = true;
                getDataAll();
            }
            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Coach Control", "Error - saveData");
                _isCorrect = false;
            }
        });
        return _isCorrect;
    }


    //=====
    @Override
    public boolean editData(Coach object) {
        Call<ResponseServer> call = _apiInterface.editCoach(object);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Coach Control", "Success - editData");
                _isCorrect = true;
                getDataAll();
            }
            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Coach Control", "Error - editData");
                _isCorrect = false;
            }
        });
        return _isCorrect;
    }


    //=====
    @Override
    public boolean deleteData(Coach object) {
        StringBuilder sb = new StringBuilder("/coach?idCoach=");
        sb.append(object.get_id());

        Call<ResponseServer> call = _apiInterface.deleteCoach(sb.toString());

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Coach Control", "Success - deleteData");
                _isCorrect = true;
                getDataAll();
            }
            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Coach Control", "Error - deleteData");
                _isCorrect = false;
            }
        });
        return _isCorrect;
    }
}
