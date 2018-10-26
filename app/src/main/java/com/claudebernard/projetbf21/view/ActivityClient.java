package com.claudebernard.projetbf21.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.control.ClientControl;
import com.claudebernard.projetbf21.control.CoachControl;
import com.claudebernard.projetbf21.control.ValidationLogin;
import com.claudebernard.projetbf21.model.Client;

import java.util.ArrayList;

public class ActivityClient extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static Context  _context;
    public  static Activity _activity;
    private static GridView _gridClient;
    public  static TextView _namePersonal;
    private static NavigationView _navigationView;
    private static AdapterCardClient _adapterClient;
    private CoachControl _coachControl = new CoachControl();
    private ClientControl _clientControl = new ClientControl();
    public  static DialogClient _dialogClient;
    public  static View _view;


    //=====
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        _view = (View) findViewById(android.R.id.content);
        _activity = this;
        _context = this;
        _gridClient = (GridView) findViewById(R.id._gridClients);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(_activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        _navigationView = (NavigationView) findViewById(R.id.nav_view);
        _navigationView.setNavigationItemSelectedListener(this);

        View headerView = _navigationView.getHeaderView(0);
        _namePersonal = (TextView) headerView.findViewById(R.id._namePersonal);

        FloatingActionButton _fabAddClient = (FloatingActionButton) findViewById(R.id.btn_add_client);
        _fabAddClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _dialogClient = new DialogClient(_activity, _context,"add", null);
                _dialogClient.show();
            }
        });

        _coachControl.getData("client", ValidationLogin.coach.get_id());
        _clientControl.getDataAll("");
    }


    //=====
    public static void loadNameCoach(String nameCoach){
        _namePersonal.setText(nameCoach);
    }


    //=====
    public static void loadGridClients(ArrayList<Client> _listClients){

        _adapterClient = new AdapterCardClient(_activity, _context, _listClients);
        _gridClient.setAdapter(_adapterClient);
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


        if (id == R.id.nav_food) {
            intent = new Intent(_context, ActivityFood.class);
            startActivity(intent);

        } else if (id == R.id.nav_coach) {
            intent = new Intent(_context, ActivityCoach.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {
            intent = new Intent(_context, ActivityMain.class);
            startActivity(intent);
            finish();

            if (ActivityFood._activity != null)
            ActivityFood._activity.finish();

            if (ActivityCoach._activity != null)
            ActivityCoach._activity.finish();

            if (ActivityPlan._activity != null)
            ActivityPlan._activity.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    //=====
    public static void dismissView(){
        _dialogClient.dismiss();

    }

    //=====
    public static void messageView(String _msg){
        Snackbar.make(_view, _msg, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }
}
