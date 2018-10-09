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
import com.claudebernard.projetbf21.control.CoachControl;
import com.claudebernard.projetbf21.control.PlanControl;
import com.claudebernard.projetbf21.control.ValidationLogin;

import java.util.ArrayList;

public class ActivityPlan extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static Activity _activity;
    private static Context _context;
    private static AdapterCardPlan _adapterPlan;
    private static GridView _gridPlan;

    private CoachControl coachControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        _activity = this;
        _context = this;
        _gridPlan = (GridView) findViewById(R.id._gridPlan);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(_activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        definitionsMenu();
        definitionsFloatingButtons();
        loadGridPlans();
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
    public void definitionsFloatingButtons(){

        FloatingActionButton _fabAdd = (FloatingActionButton) findViewById(R.id._fabAdd);
        _fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogPlan dialogPlan = new DialogPlan(_activity, _context, null);
                dialogPlan.show();
            }
        });

        FloatingActionButton _fabEmail = (FloatingActionButton) findViewById(R.id._fabEmail);
        _fabEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Action Email", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton _fabCahngePlan = (FloatingActionButton) findViewById(R.id._fabCahngePlan);
        _fabCahngePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Action Changed plan", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton _fabTrash = (FloatingActionButton) findViewById(R.id._fabTrash);
        _fabTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Action Trash", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    //=====
    public static void loadGridPlans(){

        PlanControl planControl = new PlanControl();

        _adapterPlan = new AdapterCardPlan(_activity, _context, new ArrayList<>(planControl.getDataAll()));
        _gridPlan.setAdapter(_adapterPlan);
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
