package com.claudebernard.projetbf21.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.model.Coach;

import java.util.ArrayList;

public class AdapterCardCoach extends BaseAdapter {

    public static final String EXTRA_MESSAGE_HOME = "com.claudebernard.projetbf21.HOME";
    public Context _context;
    public Activity _activity;
    public ArrayList<Coach> _listCoaches;


    //=====
    public AdapterCardCoach(Activity a, Context c, ArrayList<Coach> coaches) {
        this._activity = a;
        this._context = c;
        this._listCoaches = coaches;
    }


    //=====
    @Override
    public int getCount() {

        return _listCoaches.size();
    }


    //=====
    @Override
    public Object getItem(int position) {

        return _listCoaches.get(position);
    }


    //=====
    @Override
    public long getItemId(int position) {

        return position;
    }


    //=====
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        final Coach _coach = (Coach) this.getItem(position);

        if (view == null) {
            view = LayoutInflater.from(_context).inflate(R.layout.card_list_all, viewGroup, false);
        }

        TextView _inputName = (TextView) view.findViewById(R.id._info_name);
        _inputName.setText(_coach.get_name());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCoach dialogCoach = new DialogCoach(_activity, _context,"view", _coach);
                dialogCoach.show();
            }
        });

        return view;
    }
}