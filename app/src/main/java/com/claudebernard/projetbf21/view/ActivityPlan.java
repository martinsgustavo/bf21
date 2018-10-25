package com.claudebernard.projetbf21.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.github.clans.fab.FloatingActionButton;
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
import com.claudebernard.projetbf21.control.FoodPlanControl;
import com.claudebernard.projetbf21.control.ValidationLogin;
import com.claudebernard.projetbf21.model.FoodPlan;
import com.github.clans.fab.FloatingActionMenu;

import java.util.List;

public class ActivityPlan extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static Activity _activity;
    private static Context _context;
    private static AdapterCardPlan _adapterPlan;
    private static GridView _gridPlan;
    private static NavigationView _navigationView;
    private static TextView _namePersonal;
    private CoachControl _coachControl = new CoachControl();
    public static List<FoodPlan> _foodPlansClient;
    public static FoodPlan _foodPlanSelect;
    private CoachControl coachControl;
    private static TextView _nameClient;
    private static TextView _namePlan;
    private static TextView _objClient;
    private static TextView _caloriesJourClient;
    private static TextView _proteineClient;
    private static TextView _lipidesClient;
    private static TextView _glucideClient;
    public FoodPlanControl _foodPlanControl = new FoodPlanControl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        _activity = this;
        _context = this;
        _gridPlan = (GridView) findViewById(R.id._gridPlan);
        _foodPlansClient = FoodPlanControl._foodPlansClient;

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

        _coachControl.getData("plan", ValidationLogin.coach.get_id());

        loadReferenceView();
        loadHeaderPlan();
        loadGridPlans();
        definitionsFloatingButtons();
    }


    //=====
    public static void loadNameCoach(String nameCoach){
        _namePersonal.setText(nameCoach);
    }

    //=====
    public void definitionsFloatingButtons(){

        final FloatingActionMenu _menu = (FloatingActionMenu) findViewById(R.id._floatingActionMenu);

        FloatingActionButton _fabAdd = (FloatingActionButton) findViewById(R.id._fabAdd);
        _fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAddPlan dialogAddPlan = new DialogAddPlan(_activity, _context, _foodPlanSelect.get_client(), _foodPlanSelect.get_coach());
                dialogAddPlan.show();
                _menu.close(true);
            }
        });

        FloatingActionButton _fabEmail = (FloatingActionButton) findViewById(R.id._fabEmail);
        _fabEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Disponible seulement dans la version Web.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                _menu.close(true);
            }
        });

        FloatingActionButton _fabCahngePlan = (FloatingActionButton) findViewById(R.id._fabCahngePlan);
        _fabCahngePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogChangePlan dcp = new DialogChangePlan(_activity, _context);
                dcp.show();
                _menu.close(true);
            }
        });

        FloatingActionButton _fabTrash = (FloatingActionButton) findViewById(R.id._fabTrash);
        _fabTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _foodPlanControl.deleteData(_foodPlanSelect);
                for (int x = 0; x < _foodPlansClient.size(); x++) {
                    if (_foodPlansClient.get(x).get_idFoodPlan() == _foodPlanSelect.get_idFoodPlan()) {
                        _foodPlansClient.remove(x);

                        if (_foodPlansClient.size() > 0 ){
                            _foodPlanSelect = _foodPlansClient.get(0);
                            loadHeaderPlan();
                            loadGridPlans();
                        } else {
                            _activity.finish();
                        }
                    }
                }
                _menu.close(true);
            }
        });
    }


    //=====
    public void loadReferenceView(){

        _nameClient = (TextView) findViewById(R.id._nameClient);
        _namePlan = (TextView) findViewById(R.id._namePlan);
        _objClient = (TextView) findViewById(R.id._objClient);
        _caloriesJourClient = (TextView) findViewById(R.id._caloriesJourClient);
        _proteineClient = (TextView) findViewById(R.id._proteineClient);
        _lipidesClient = (TextView) findViewById(R.id._lipidesClient);
        _glucideClient = (TextView) findViewById(R.id._glucideClient);

        _foodPlanSelect = _foodPlansClient.get(0);
    }

    //=====
    public static void loadHeaderPlan(){

        _nameClient.setText(_foodPlanSelect.get_client().get_name().toString());
        _namePlan.setText(_foodPlanSelect.get_namePlan().toString());

        if (_foodPlanSelect.get_client().getClientGoal().get_idClientGoal() == 1) {
            _objClient.setText("Perdre du poids");

        } else if (_foodPlanSelect.get_client().getClientGoal().get_idClientGoal() == 2) {
            _objClient.setText("Garder le poids");

        } else if (_foodPlanSelect.get_client().getClientGoal().get_idClientGoal() == 3) {
            _objClient.setText("Prendre du poids");
        }

        _caloriesJourClient.setText(String.valueOf(_foodPlanSelect.get_client().get_tdce()));

        _proteineClient.setText("0" + " G./jour");
        _lipidesClient.setText("0" + " G./jour");
        _glucideClient.setText("0" + " G./jour");
    }


    //=====
    public static void loadGridPlans(){

        _adapterPlan = new AdapterCardPlan(_activity, _context, _foodPlanSelect);
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
