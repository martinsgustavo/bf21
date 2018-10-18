package com.claudebernard.projetbf21.control;

import android.util.Log;

import com.claudebernard.projetbf21.comm.ApiClient;
import com.claudebernard.projetbf21.comm.ApiInterface;
import com.claudebernard.projetbf21.model.Coach;
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

public class CoachControl implements GenericControl<Coach>{

    private ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
    private List<Coach> coaches;
    private Coach coach = new Coach();
    private boolean isCorrect;

    @Override
    public List<Coach> getDataAll() {
        Call<ResponseServerArray> call = apiInterface.findAllCoaches();

        call.enqueue(new Callback<ResponseServerArray>() {
            @Override
            public void onResponse(Call<ResponseServerArray> call, Response<ResponseServerArray> response) {
                Log.i("Coach Control", "Success - getDataCoaches");
                if(response.body().toString() != ""){
                    String string = new Gson().toJson(response.body().getMeta());

                    Type listType = new TypeToken<ArrayList<Coach>>() {}.getType();
                    coaches = new Gson().fromJson(string, listType);
                }
            }

            @Override
            public void onFailure(Call<ResponseServerArray> call, Throwable t) {
                Log.e("Coach Control", "Error - getDataCoaches");
                coaches = null;
            }
        });

        return coaches;
    }

    @Override
    public Coach getData(Integer id) {
        StringBuilder sb = new StringBuilder("/coach?idCoach=");
        sb.append(id);

        Call<ResponseServer> call = apiInterface.findCoach(sb.toString());

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Coach Control", "Success - getDataCoach");
                String string = new Gson().toJson(response.body().getMeta());
                coach = new Gson().fromJson(string, Coach.class);
                System.out.println(coach.get_name());
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Coach Control", "Error - getDataCoach");
                coach = null;
            }
        });
        return coach;
    }

    @Override
    public boolean saveData(Coach object) {
        Call<ResponseServer> call = apiInterface.saveCoach(object);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Coach Control", "Success - saveData");
                isCorrect = true;
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Coach Control", "Error - saveData");
                isCorrect = false;
            }
        });

        return isCorrect;
    }

    @Override
    public boolean editData(Coach object) {
        Call<ResponseServer> call = apiInterface.editCoach(object);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Coach Control", "Success - editData");
                isCorrect = true;
            }
            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Coach Control", "Error - editData");
                isCorrect = false;
            }
        });
        return isCorrect;
    }

    @Override
    public boolean deleteData(Coach object) {
        StringBuilder sb = new StringBuilder("/coach?idCoach=");
        sb.append(object.get_id());

        Call<ResponseServer> call = apiInterface.deleteCoach(sb.toString());

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Coach Control", "Success - deleteData");
                isCorrect = true;
            }
            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Coach Control", "Error - deleteData");
                isCorrect = false;
            }
        });
        return isCorrect;
    }

}
