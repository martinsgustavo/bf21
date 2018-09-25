package com.claudebernard.projetbf21.control;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.claudebernard.projetbf21.comm.ApiClient;
import com.claudebernard.projetbf21.comm.ApiInterface;
import com.claudebernard.projetbf21.model.Client;
import com.claudebernard.projetbf21.view.ClientActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientControl {

    private static ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);


    //=====
    public static ArrayList getDataClients() {

        final ArrayList<Client> clients = new ArrayList<>();
        Call<List<Client>> call = apiInterface.findAllClients();

        call.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                for (Client client : response.body()) {
                    Log.i("Client Control", "Success - getDataClients");
                    clients.add(client);
                }

            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Log.e("Client Control", "Error - getDataClients");
            }
        });

        return clients;
    }

    public static Client getDataClient(String id) {

        return null;
    }

    public void saveClient (final Context c, Client client){

        Call<Client> call = apiInterface.saveClient(client);

        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if(response.isSuccessful()) {
                    Log.i("Client Control", "Client saved");
                    Toast.makeText(c, "Client saved", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e("Client Control", "Error - Client not saved");
            }
        });
    }

    public void deleteClient (final Context c, Client client) {

        Call<Client> call = apiInterface.deleteClient(client.get_id());

        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                Log.i("Client Control", "Client deleted");
                Toast.makeText(c, "Client deleted", Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e("Client Control", "Error - Client not deleted");
            }
        });

    }

    public void editClient (final Context c, Client client) {

        Call<Client> call = apiInterface.editClient(client.get_id(), client);

        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                Log.i("Client Control", "Client edited");
                Toast.makeText(c, "Client edited", Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e("Client Control", "Error - Client not edited");
            }
        });
    }
}
