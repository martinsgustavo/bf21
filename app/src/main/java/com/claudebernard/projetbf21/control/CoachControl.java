package com.claudebernard.projetbf21.control;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.claudebernard.projetbf21.comm.ApiClient;
import com.claudebernard.projetbf21.comm.ApiInterface;
import com.claudebernard.projetbf21.model.Coach;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoachControl {

    private static ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);

    //=====
    public static ArrayList getDataCoaches() {

        final ArrayList<Coach> coaches = new ArrayList<>();
        Call<List<Coach>> call = apiInterface.findAllCoaches();

        call.enqueue(new Callback<List<Coach>>() {
            @Override
            public void onResponse(Call<List<Coach>> call, Response<List<Coach>> response) {
                for (Coach client : response.body()) {
                    Log.i("Coach Control", "Success - getDataCoaches");
                    coaches.add(client);
                }
            }

            @Override
            public void onFailure(Call<List<Coach>> call, Throwable t) {
                Log.e("Coach Control", "Error - getDataCoaches");
            }
        });

        return coaches;
    }

    //=====
    public static Coach getDataCoach(String login) {

        Coach coachRet = new Coach();

        coachRet.set_firstName("FirstName Coach");
        coachRet.set_lastName("LastName Coach");
        coachRet.set_address("Address Coach");
        coachRet.set_eMail("E-mail Coach");
        coachRet.set_phone("Phone Coach");
        coachRet.set_login("Login Coach");

        return coachRet;
    }

    public void saveCoach (final Context c, Coach coach){

        Call<Coach> call = apiInterface.saveCoach(coach);

        call.enqueue(new Callback<Coach>() {
            @Override
            public void onResponse(Call<Coach> call, Response<Coach> response) {
                if(response.isSuccessful()) {
                    Log.i("Coach Control", "Coach saved");
                    Toast.makeText(c, "Client saved", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<Coach> call, Throwable t) {
                Log.e("Coach Control", "Error - Coach not saved");
            }
        });
    }

    public void deleteCoach (final Context c, Coach coach) {

        Call<Coach> call = apiInterface.deleteCoach(coach.get_id());

        call.enqueue(new Callback<Coach>() {
            @Override
            public void onResponse(Call<Coach> call, Response<Coach> response) {
                Log.i("Coach Control", "Coach deleted");
                Toast.makeText(c, "Coach deleted", Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<Coach> call, Throwable t) {
                Log.e("Coach Control", "Error - Coach not deleted");
            }
        });

    }

    public void editCoach (final Context c, Coach coach) {

        Call<Coach> call = apiInterface.editCoach(coach.get_id(), coach);

        call.enqueue(new Callback<Coach>() {
            @Override
            public void onResponse(Call<Coach> call, Response<Coach> response) {
                Log.i("Coach Control", "Coach edited");
                Toast.makeText(c, "Coach edited", Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<Coach> call, Throwable t) {
                Log.e("Coach Control", "Error - Coach not edited");
            }
        });
    }
}
