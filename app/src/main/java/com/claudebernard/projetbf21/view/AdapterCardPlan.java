package com.claudebernard.projetbf21.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.model.Client;

import java.util.ArrayList;


public class AdapterCardPlan extends BaseAdapter {

    public static final String EXTRA_MESSAGE_HOME = "com.claudebernard.projetbf21.HOME";
    public Context _context;
    public ArrayList<Client> _listClients;


    //=====
    public AdapterCardPlan(Context c, ArrayList<Client> clients){

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
            view = LayoutInflater.from(_context).inflate(R.layout.card_plan_plate,viewGroup,false);
        }

        final Client _client = (Client) this.getItem(position);

        ImageView _img = (ImageView) view.findViewById(R.id._photoClient);
        TextView _name = (TextView)  view.findViewById(R.id._info_name);
        TextView _obj  = (TextView)  view.findViewById(R.id._info_objective);

        _name.setText(_client.get_name());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_context,ActivityPlan.class);
                String message = String.valueOf(_client.get_name());
                intent.putExtra(EXTRA_MESSAGE_HOME, message);
                _context.startActivity(intent);
            }
        });

        return view;
    }
}
