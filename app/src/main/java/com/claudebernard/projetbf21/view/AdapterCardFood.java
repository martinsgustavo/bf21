package com.claudebernard.projetbf21.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.model.Food;

import java.util.ArrayList;

public class AdapterCardFood  extends BaseAdapter {

    public Context _context;
    public Activity _activity;
    public ArrayList<Food> _listFoods;
    public static DialogFood _dialogFood;


    //=====
    public AdapterCardFood(Activity a,Context c, ArrayList<Food> foods){
        this._activity = a;
        this._context = c;
        this._listFoods = foods;
    }


    //=====
    @Override
    public int getCount() {

        return _listFoods.size();
    }


    //=====
    @Override
    public Object getItem(int position) {

        return _listFoods.get(position);
    }


    //=====
    @Override
    public long getItemId(int position) {

        return position;
    }


    //=====
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        final Food _food = (Food) this.getItem(position);

        if(view == null) {
            view = LayoutInflater.from(_context).inflate(R.layout.card_list_all,viewGroup,false);
        }

        TextView _inputName = (TextView) view.findViewById(R.id._info_name);
        _inputName.setText(_food.get_name());

        CardView _colorCard = (CardView) view.findViewById(R.id._card_list_all);
        _colorCard.setCardBackgroundColor(Color.parseColor("#34B51C"));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _dialogFood = new DialogFood(_activity, _context,"view", _food);
                _dialogFood.show();
            }
        });

        return view;
    }


    //=====
    public static void dismissView(){
        _dialogFood.dismiss();
    }
}
