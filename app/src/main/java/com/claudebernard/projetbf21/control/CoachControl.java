package com.claudebernard.projetbf21.control;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.claudebernard.projetbf21.comm.ApiClient;
import com.claudebernard.projetbf21.comm.ApiInterface;
import com.claudebernard.projetbf21.model.Coach;
import com.claudebernard.projetbf21.model.ResponseServer;
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
    private Coach coach;

    @Override
    public List<Coach> getDataAll() {
        Call<ResponseServer> call = apiInterface.findAllCoaches();

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Coach Control", "Success - getDataCoaches");
                if(response.body().toString() != ""){
                    String string = new Gson().toJson(response.body().getMeta());

                    Type listType = new TypeToken<ArrayList<Coach>>() {}.getType();
                    coaches = new Gson().fromJson(string, listType);
                }
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Coach Control", "Error - getDataCoaches");
                coaches = null;
            }
        });

        return coaches;
    }

    @Override
    public Coach getData(Integer id) {
        Call<ResponseServer> call = apiInterface.findCoach(id);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Coach Control", "Success - getDataCoach");
                String string = new Gson().toJson(response.body().getMeta());
                coach = new Gson().fromJson(string, Coach.class);
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

        return false;
    }

    @Override
    public boolean editData(Coach object) {

        return false;
    }

    @Override
    public boolean deleteData(Coach object) {

        return false;
    }

//    public void saveCoach (final Context c, Coach coach){
//
//        Call<Coach> call = apiInterface.saveCoach(coach);
//
//        call.enqueue(new Callback<Coach>() {
//            @Override
//            public void onResponse(Call<Coach> call, Response<Coach> response) {
//                if(response.isSuccessful()) {
//                    Log.i("Coach GenericControl", "Coach saved");
//                    Toast.makeText(c, "Client saved", Toast.LENGTH_SHORT);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Coach> call, Throwable t) {
//                Log.e("Coach GenericControl", "Error - Coach not saved");
//            }
//        });
//    }
//
//    public void deleteCoach (final Context c, Coach coach) {
//
//        Call<Coach> call = apiInterface.deleteCoach(coach.get_id());
//
//        call.enqueue(new Callback<Coach>() {
//            @Override
//            public void onResponse(Call<Coach> call, Response<Coach> response) {
//                Log.i("Coach GenericControl", "Coach deleted");
//                Toast.makeText(c, "Coach deleted", Toast.LENGTH_SHORT);
//            }
//
//            @Override
//            public void onFailure(Call<Coach> call, Throwable t) {
//                Log.e("Coach GenericControl", "Error - Coach not deleted");
//            }
//        });
//
//    }
//
//    public void editCoach (final Context c, Coach coach) {
//
//        Call<Coach> call = apiInterface.editCoach(coach.get_id(), coach);
//
//        call.enqueue(new Callback<Coach>() {
//            @Override
//            public void onResponse(Call<Coach> call, Response<Coach> response) {
//                Log.i("Coach GenericControl", "Coach edited");
//                Toast.makeText(c, "Coach edited", Toast.LENGTH_SHORT);
//            }
//
//            @Override
//            public void onFailure(Call<Coach> call, Throwable t) {
//                Log.e("Coach GenericControl", "Error - Coach not edited");
//            }
//        });
//    }

}
