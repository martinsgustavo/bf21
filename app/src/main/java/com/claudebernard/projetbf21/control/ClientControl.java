package com.claudebernard.projetbf21.control;

import android.util.Log;

import com.claudebernard.projetbf21.comm.ApiClient;
import com.claudebernard.projetbf21.comm.ApiInterface;
import com.claudebernard.projetbf21.model.Client;
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

public class ClientControl implements GenericControl<Client> {

    private ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
    private List<Client> clients;
    private Client client;
    private boolean isCorrect;

    @Override
    public List<Client> getDataAll() {
        Call<ResponseServerArray> call = apiInterface.findAllClients();

        call.enqueue(new Callback<ResponseServerArray>() {
            @Override
            public void onResponse(Call<ResponseServerArray> call, Response<ResponseServerArray> response) {
                Log.i("Client Control", "Success - getDataClients");
                if(response.body().getMeta().toString() != ""){
                    String string = new Gson().toJson(response.body().getMeta());

                    Type listType = new TypeToken<ArrayList<Client>>() {}.getType();
                    clients = new Gson().fromJson(string, listType);
                } else {
                    clients = null;
                }
            }

            @Override
            public void onFailure(Call<ResponseServerArray> call, Throwable t) {
                Log.e("Client Control", "Error - getDataClients");
                clients = null;
            }
        });

        return clients;
    }

    @Override
    public Client getData(Integer id) {
        StringBuilder sb = new StringBuilder("/client?idClient=");
        sb.append(id);

        Call<ResponseServer> call = apiInterface.findClient(sb.toString());

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Client Control", "Success - getDataClient");

                String string = new Gson().toJson(response.body().getMeta());
                client = new Gson().fromJson(string, Client.class);
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Client Control", "Error - getDataClient");
                client = null;
            }
        });
        return client;
    }

    @Override
    public boolean saveData(Client object) {
        Call<ResponseServer> call = apiInterface.saveClient(object);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Client Control", "Success - saveData");
                isCorrect = true;
            }
            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Client Control", "Error - saveData");
                isCorrect = false;
            }
        });
        return isCorrect;
    }

    @Override
    public boolean editData(Client object) {
        Call<ResponseServer> call = apiInterface.editClient(object);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Client Control", "Success - editData");
                isCorrect = true;
            }
            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Client Control", "Error - editData");
                isCorrect = false;
            }
        });
        return isCorrect;
    }

    @Override
    public boolean deleteData(Client object) {
        StringBuilder sb = new StringBuilder("/client?idClient=");
        sb.append(object.get_id());

        Call<ResponseServer> call = apiInterface.deleteClient(sb.toString());

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Client Control", "Success - deleteData");
                isCorrect = true;
            }
            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Client Control", "Error - deleteData");
                isCorrect = false;
            }
        });
        return isCorrect;
    }
}
