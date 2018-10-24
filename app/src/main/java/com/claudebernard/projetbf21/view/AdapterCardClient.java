package com.claudebernard.projetbf21.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.control.ClientControl;
import com.claudebernard.projetbf21.control.FoodPlanControl;
import com.claudebernard.projetbf21.model.Client;

import java.util.ArrayList;

public class AdapterCardClient extends BaseAdapter {

    public static Context _context;
    public static Activity _activity;
    public ArrayList<Client> _listClients;
    public static DialogClient _dialogClient;
    public FoodPlanControl _foodPlanControl = new FoodPlanControl();


    //=====
    public AdapterCardClient(Activity a,Context c, ArrayList<Client> clients){
        this._activity = a;
        this._context = c;
        this._listClients = clients;
    }


    //=====
    @Override
    public int getCount() {

        return _listClients.size();
    }


    //=====
    @Override
    public Object getItem(int position) {

        return _listClients.get(position);
    }


    //=====
    @Override
    public long getItemId(int position) {

        return position;
    }


    //=====
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        final Client _client = (Client) this.getItem(position);

        if(view == null) {
            view = LayoutInflater.from(_context).inflate(R.layout.card_list_all,viewGroup,false);
        }

        TextView _inputName = (TextView) view.findViewById(R.id._info_name);
        _inputName.setText(_client.get_name());

        CardView _colorCard = (CardView) view.findViewById(R.id._card_list_all);
        _colorCard.setCardBackgroundColor(Color.parseColor("#1DA0D0"));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _foodPlanControl.getDataAllClient(_client, "cardClient");

            }
        });

        return view;
    }


    //=====
    public static void showDialogClient(Client client){
        _dialogClient = new DialogClient(_activity, _context,"view", client);
        _dialogClient.show();
    }


    //=====
    public static void dismissView(){
        _dialogClient.dismiss();
    }


    //=====
    public static void dialogYesNo(final Client _clientYN) {

        AlertDialog.Builder builder = new AlertDialog.Builder(_context);

        builder.setMessage("Que voulez-vous voir chez le client ?").setPositiveButton("Plan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(_context, ActivityPlan.class);
                _context.startActivity(intent);

            }
        }).setNegativeButton("Inscription", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showDialogClient(_clientYN);
            }
        }).create();

        AlertDialog alert = builder.create();
        alert.show();
    }
}
