package com.claudebernard.projetbf21.control;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.claudebernard.projetbf21.comm.ApiClient;
import com.claudebernard.projetbf21.comm.ApiInterface;
import com.claudebernard.projetbf21.model.Coach;
import com.claudebernard.projetbf21.view.ClientActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValidationLogin {

    public static final String EXTRA_MESSAGE = "com.claudebernard.projetbf21";

    public static boolean accessSystem(final Context c, final String login, final String password){

        final String md5Password = md5(password);

        final ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Coach> call = apiInterface.login();

        call.enqueue(new Callback<Coach>() {
            @Override
            public void onResponse(Call<Coach> call, Response<Coach> response) {

                if (md5Password.equals(response.body().get_password())){
                    Log.i("Validation Login", "Validation OK");
                    Intent intent = new Intent(c, ClientActivity.class);
                    intent.putExtra(EXTRA_MESSAGE, login);
                    c.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Coach> call, Throwable t) {
                Log.e("Validation Login", "Validation Error");

            }
        });

        return false;
    }

    private static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
