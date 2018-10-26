package com.claudebernard.projetbf21.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.control.CoachControl;
import com.claudebernard.projetbf21.control.ValidationLogin;
import com.claudebernard.projetbf21.model.Coach;

import java.util.ArrayList;

public class ActivityCoach extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static Context _context;
    public  static Activity _activity;
    private static GridView _gridCoaches;
    public  static TextView _namePersonal;
    private static NavigationView _navigationView;
    private static AdapterCardCoach _adapterCoach;
    private CoachControl _coachControl = new CoachControl();
    public  static DialogCoach _dialogCoach;
    public  static View _view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach);
        _view = (View) findViewById(android.R.id.content);
        _activity = this;
        _context = this;
        _gridCoaches = (GridView) findViewById(R.id._gridCoachs);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        _navigationView = (NavigationView) findViewById(R.id.nav_view);
        _navigationView.setNavigationItemSelectedListener(this);

        View headerView = _navigationView.getHeaderView(0);
        _namePersonal = (TextView) headerView.findViewById(R.id._namePersonal);

        FloatingActionButton _fabAddClient = (FloatingActionButton) findViewById(R.id.btn_add_coach);
        _fabAddClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _dialogCoach = new DialogCoach(_activity, _context,"add", null);
                _dialogCoach.show();
            }
        });

        _coachControl.getData("coach", ValidationLogin.coach.get_id());
        _coachControl.getDataAll("");
    }


    //=====
    public static void loadNameCoach(String nameCoach){
        _namePersonal.setText(nameCoach);

    }


    //=====
    public static void loadGridCoaches(ArrayList<Coach> _listCoaches){

        _adapterCoach = new AdapterCardCoach(_activity, _context, _listCoaches);
        _gridCoaches.setAdapter(_adapterCoach);
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

        } else if (id == R.id.nav_logout) {
            intent = new Intent(_context, ActivityMain.class);
            startActivity(intent);
            finish();

            if (ActivityClient._activity != null)
            ActivityClient._activity.finish();

            if (ActivityFood._activity != null)
            ActivityFood._activity.finish();

            if (ActivityPlan._activity != null)
            ActivityPlan._activity.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    //=====
    public static void dismissView(){
        _dialogCoach.dismiss();

    }

    //=====
    public static void messageView(String _msg){
        Snackbar.make(_view, _msg, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }
}