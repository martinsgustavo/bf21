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

        if (view == null) {
            view = LayoutInflater.from(_context).inflate(R.layout.card_list_all, viewGroup, false);
        }

        final Coach _coach = (Coach) this.getItem(position);

        TextView _name = (TextView) view.findViewById(R.id._info_name);
//        ImageButton _btn_edit = (ImageButton) view.findViewById(R.id._btn_edit);
//        ImageButton _btn_delete = (ImageButton) view.findViewById(R.id._btn_delete);

        _name.setText(_coach.get_lastName() + "" + _coach.get_firstName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCoach dialogCoach = new DialogCoach(_activity, "view", _coach);
                dialogCoach.show();
            }
        });

//        _btn_edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogCoach dialogCoach = new DialogCoach(_activity, "modify", _coach);
//                dialogCoach.show();
//            }
//        });
//
//        _btn_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        return view;
    }
}