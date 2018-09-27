package com.claudebernard.projetbf21.control;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.claudebernard.projetbf21.comm.ApiClient;
import com.claudebernard.projetbf21.comm.ApiInterface;
import com.claudebernard.projetbf21.model.Client;
import com.claudebernard.projetbf21.model.ResponseServer;
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

    @Override
    public List<Client> getDataAll() {
        Call<ResponseServer> call = apiInterface.findAllClients();

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Client GenericControl", "Success - getDataClients");
                if(response.body().toString() != ""){
                    String string = new Gson().toJson(response.body().getMeta());

                    Type listType = new TypeToken<ArrayList<Client>>() {}.getType();
                    clients = new Gson().fromJson(string, listType);
                }
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Client GenericControl", "Error - getDataClients");
                clients = null;
            }
        });

        return clients;
    }

    @Override
    public Client getData(Integer id) {
        Call<ResponseServer> call = apiInterface.findClient(id);

        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.i("Client GenericControl", "Success - getDataClient");
                String string = new Gson().toJson(response.body().getMeta());
                client = new Gson().fromJson(string, Client.class);
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Log.e("Client GenericControl", "Error - getDataClient");
                client = null;
            }
        });

        return client;
    }

    @Override
    public void saveData(Context c, Client object) {

    }

    @Override
    public void editData(Context c, Client object) {

    }

    @Override
    public void deleteData(Context c, Client object) {

    }

//    public void saveClient (final Context c, Client client){
//
//        Call<Client> call = apiInterface.saveClient(client);
//
//        call.enqueue(new Callback<Client>() {
//            @Override
//            public void onResponse(Call<Client> call, Response<Client> response) {
//                if(response.isSuccessful()) {
//                    Log.i("Client GenericControl", "Client saved");
//                    Toast.makeText(c, "Client saved", Toast.LENGTH_SHORT);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Client> call, Throwable t) {
//                Log.e("Client GenericControl", "Error - Client not saved");
//            }
//        });
//    }
//
//    public void deleteClient (final Context c, Client client) {
//
//        Call<Client> call = apiInterface.deleteClient(client.get_id());
//
//        call.enqueue(new Callback<Client>() {
//            @Override
//            public void onResponse(Call<Client> call, Response<Client> response) {
//                Log.i("Client GenericControl", "Client deleted");
//                Toast.makeText(c, "Client deleted", Toast.LENGTH_SHORT);
//            }
//
//            @Override
//            public void onFailure(Call<Client> call, Throwable t) {
//                Log.e("Client GenericControl", "Error - Client not deleted");
//            }
//        });
//
//    }
//
//    public void editClient (final Context c, Client client) {
//
//        Call<Client> call = apiInterface.editClient(client);
//
//        call.enqueue(new Callback<Client>() {
//            @Override
//            public void onResponse(Call<Client> call, Response<Client> response) {
//                Log.i("Client GenericControl", "Client edited");
//                Toast.makeText(c, "Client edited", Toast.LENGTH_SHORT);
//            }
//
//            @Override
//            public void onFailure(Call<Client> call, Throwable t) {
//                Log.e("Client GenericControl", "Error - Client not edited");
//            }
//        });
//    }



}
