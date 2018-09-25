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
import com.claudebernard.projetbf21.model.Client;

import java.util.ArrayList;

public class AdapterCardClient extends BaseAdapter {

    public static final String EXTRA_MESSAGE_HOME = "com.claudebernard.projetbf21.HOME";
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

        if(view == null) {
            view = LayoutInflater.from(_context).inflate(R.layout.card_list_all,viewGroup,false);
        }

        final Client _client = (Client) this.getItem(position);

        TextView _name          = (TextView)     view.findViewById(R.id._info_name);
        ImageButton _btn_edit   = (ImageButton)  view.findViewById(R.id._btn_edit);
        ImageButton _btn_delete = (ImageButton)  view.findViewById(R.id._btn_delete);

        _name.setText(_client.get_lastName()+""+_client.get_firstName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_context,PlanActivity.class);
                String message = String.valueOf(_client.get_lastName()+", "+_client.get_firstName());
                intent.putExtra(EXTRA_MESSAGE_HOME, message);
                _context.startActivity(intent);
            }
        });

        _btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogClient dialogClient = new DialogClient(_activity, _context,"modify", _client);
                dialogClient.show();
            }
        });

        _btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
