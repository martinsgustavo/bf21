package com.claudebernard.projetbf21.control;

import android.util.Log;

import com.claudebernard.projetbf21.comm.ApiClient;
import com.claudebernard.projetbf21.comm.ApiInterface;
import com.claudebernard.projetbf21.model.Client;
import com.claudebernard.projetbf21.model.ResponseServer;
import com.claudebernard.projetbf21.model.ResponseServerArray;
import com.claudebernard.projetbf21.view.ActivityClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientControl implements GenericControl<Client> {

    private ApiInterface _apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
    private ArrayList<Client> _clients;
    private Client _client;
    private boolean _isCorrect;

    
    //=====
    @Override
    public ArrayList<Client> getDataAll() {
        Call<ResponseServerArray> call = _apiInterface.findAllClients();

        call.enqueue(new Callback<ResponseServerArray>() {
            @Override
            public void onResponse(Call<ResponseServerArray> call, Response<ResponseServerArray> response) {
                Log.i("Client Control", "Success - getDataClients");
                if(response.body().toString() != ""){
                    String string = new Gson().toJson(response.body().getMeta());

                    Type listType = new TypeToken<ArrayList<Client>>() {}.getType();
                    _clients = new Gson().fromJson(string, listType);

                    Collections.sort(_clients, new Comparator<Client>() {
                        public int compare(Client c1, Client c2) {
                            return c1.get_name().compareTo(c2.get_name());
                        }
                    });

                    ActivityClient.loadGridClients(_clients);
                }
            }

            @Override
            public void onFailure(Call<ResponseServerArray> call, Throwable t) {
                Log.e("Client Control", "Error - getDataClients");
                _clients = null;
            }
        });
        return _clients;
    }


    //=====
    @Override
    public Client getData(String option, Integer id) {
        StringBuilder sb = new StringBuilder("/client?idClient=");
        sb.append(id);

        Call<ResponseServer> call = _apiInterface.findClient(sb.toString());

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Client Control", "Success - getDataClient");
                String string = new Gson().toJson(response.body().getMeta());
                _client = new Gson().fromJson(string, Client.class);
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Client Control", "Error - getDataClient");
                _client = null;
            }
        });
        return _client;
    }


    //=====
    @Override
    public boolean saveData(Client object) {
        Call<ResponseServer> call = _apiInterface.saveClient(object);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Client Control", "Success - saveData");
                _isCorrect = true;
                getDataAll();
            }
            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Client Control", "Error - saveData");
                _isCorrect = false;
            }
        });
        return _isCorrect;
    }


    //=====
    @Override
    public boolean editData(Client object) {
        Call<ResponseServer> call = _apiInterface.editClient(object);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Client Control", "Success - editData");
                _isCorrect = true;
                getDataAll();
            }
            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Client Control", "Error - editData");
                _isCorrect = false;
            }
        });
        return _isCorrect;
    }


    //=====
    @Override
    public boolean deleteData(Client object) {
        StringBuilder sb = new StringBuilder("/client?idClient=");
        sb.append(object.get_id());

        Call<ResponseServer> call = _apiInterface.deleteClient(sb.toString());

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Client Control", "Success - deleteData");
                _isCorrect = true;
                getDataAll();
            }
            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Client Control", "Error - deleteData");
                _isCorrect = false;
            }
        });
        return _isCorrect;
    }
}
