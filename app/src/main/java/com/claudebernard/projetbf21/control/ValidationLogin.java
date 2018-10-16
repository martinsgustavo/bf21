package com.claudebernard.projetbf21.control;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.claudebernard.projetbf21.comm.ApiClient;
import com.claudebernard.projetbf21.comm.ApiInterface;
import com.claudebernard.projetbf21.model.Coach;
import com.claudebernard.projetbf21.model.ResponseServer;
import com.claudebernard.projetbf21.view.ActivityClient;
import com.google.gson.Gson;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValidationLogin {

    private static Coach coach;
    private static CoachControl _coachControl = new CoachControl();
    private static ClientControl _clientControl = new ClientControl();
    private static boolean isCorrect;

    public static boolean accessSystem(final Context c, final String login, final String password) {

//        final String md5Password = md5(password);
//
//        final ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
//        Call<ResponseServer> call = apiInterface.login(login);
//
//        call.enqueue(new Callback<ResponseServer>() {
//            @Override
//            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
//
//                String string = new Gson().toJson(response.body().getMeta());
//                coach = new Gson().fromJson(string, Coach.class);
//
//                if (md5Password.equals(coach.get_password())){
//                    Log.i("Validation Login", "Validation OK");
//                    Intent intent = new Intent(c, ActivityClient.class);
//                    c.startActivity(intent);
//
//                    isCorrect = true;
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseServer> call, Throwable t) {
//                Log.e("Validation Login", "Validation Error");
//                isCorrect = false;
//            }
//        });
//
//        return isCorrect;

//        _coachControl.getData("client", 5);
//        _clientControl.getDataAll();

        Intent intent = new Intent(c, ActivityClient.class);
        c.startActivity(intent);

        return true;
    }

//    private static final String md5(final String s) {
//        final String MD5 = "MD5";
//        try {
//            // Create MD5 Hash
//            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
//            digest.update(s.getBytes());
//            byte messageDigest[] = digest.digest();
//
//            // Create Hex String
//            StringBuilder hexString = new StringBuilder();
//            for (byte aMessageDigest : messageDigest) {
//                String h = Integer.toHexString(0xFF & aMessageDigest);
//                while (h.length() < 2)
//                    h = "0" + h;
//                hexString.append(h);
//            }
//            return hexString.toString();
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
}
