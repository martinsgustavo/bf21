package com.claudebernard.projetbf21.control;

import android.util.Log;

import com.claudebernard.projetbf21.comm.ApiClient;
import com.claudebernard.projetbf21.comm.ApiInterface;
import com.claudebernard.projetbf21.model.Client;
import com.claudebernard.projetbf21.model.ResponseServer;
import com.claudebernard.projetbf21.model.ResponseServerArray;
import com.claudebernard.projetbf21.view.ActivityClient;
import com.claudebernard.projetbf21.view.AdapterCardClient;
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
    private boolean _isCorrect;
    private Client _client;

    
    //=====
    @Override
    public void getDataAll(String option) {
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
    }


    //=====
    @Override
    public void getData(String option, Integer id) {
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
    }


    //=====
    @Override
    public void saveData(Client object) {
        Call<ResponseServer> call = _apiInterface.saveClient(object);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                if (response.code()==201) {
                    Log.i("Client Control", "Success - saveData");
                    ActivityClient.messageView("Nouveau Client sauvegarde avec Succès !");
                    ActivityClient.dismissView();
                    getDataAll("");
                }
            }
            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Client Control", "Error - saveData");
            }
        });
    }


    //=====
    @Override
    public void editData(Client object) {
        Call<ResponseServer> call = _apiInterface.editClient(object);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                if (response.code()==200) {
                    Log.i("Client Control", "Success - editData");
                    ActivityClient.messageView("Mis à jour du Client sauvegarde avec Succès !");
                    AdapterCardClient.dismissView();
                    getDataAll("");
                }
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Client Control", "Error - editData");
            }
        });
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
                if (response.code()==200 || response.code() == 202) {
                    Log.i("Client Control", "Success - deleteData");
                    _isCorrect = true;
                    ActivityClient.messageView("Client Supprimé avec Succès !");
                    getDataAll("");
                } else {
                    ActivityClient.messageView("Échec pendant la suppression du Client !");
                }
            }
            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Client Control", "Error - deleteData");
                _isCorrect = false;
                ActivityClient.messageView("Échec pendant la suppression du Client !");
            }
        });
        return _isCorrect;
    }
}
