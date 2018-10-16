package com.claudebernard.projetbf21.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.control.ClientControl;
import com.claudebernard.projetbf21.model.Client;

import java.util.ArrayList;

public class AdapterCardClient extends BaseAdapter {

    public Context _context;
    public Activity _activity;
    public ArrayList<Client> _listClients;


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

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              comentario : option view inscription client = ok
                DialogClient dialogClient = new DialogClient(_activity, _context,"view", _client);
                dialogClient.show();

//                option view plan client
//                Intent intent = new Intent(_context, ActivityPlan.class);
//                _context.startActivity(intent);

            }
        });

        return view;
    }
}
