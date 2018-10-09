package com.claudebernard.projetbf21.view;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.control.CoachControl;
import com.claudebernard.projetbf21.control.FoodControl;
import com.claudebernard.projetbf21.control.ValidationLogin;
import com.claudebernard.projetbf21.model.Coach;
import com.claudebernard.projetbf21.model.Food;

import java.util.ArrayList;

public class ActivityFood extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static Activity _activity;
    private static Context _context;
    private static AdapterCardFood _adapterFood;
    private static GridView _gridFood;

    private CoachControl coachControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        _activity = this;
        _context = this;
        _gridFood = (GridView) findViewById(R.id._gridFoods);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton _fabAddFood = (FloatingActionButton) findViewById(R.id.btn_add_food);
        _fabAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFood dialogFood = new DialogFood(_activity, _context,"add", null);
                dialogFood.show();
            }
        });

        definitionsMenu();
        loadGridFoods();
    }


    //=====
    public void definitionsMenu(){

        Intent _intent = getIntent();
        Integer _id = Integer.parseInt(_intent.getStringExtra(ValidationLogin.EXTRA_MESSAGE));

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        TextView _namePersonal = (TextView) headerView.findViewById(R.id._namePersonal);
        _namePersonal.setText(coachControl.getData(_id).get_login());
    }


    //=====
    public static void loadGridFoods(){

        FoodControl foodControl = new FoodControl();

        _adapterFood = new AdapterCardFood(_activity, _context, new ArrayList<>(foodControl.getDataAll()));
        _gridFood.setAdapter(_adapterFood);
    }

    //=====
    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //=====
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent intent = null;

        if (id == R.id.nav_client) {

            intent = new Intent(_context, ActivityClient.class);
            startActivity(intent);

        } else if (id == R.id.nav_food) {

            intent = new Intent(_context, ActivityFood.class);
            startActivity(intent);

        } else if (id == R.id.nav_coach) {

            intent = new Intent(_context, ActivityCoach.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {
            intent = new Intent(_context, ActivityMain.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

}