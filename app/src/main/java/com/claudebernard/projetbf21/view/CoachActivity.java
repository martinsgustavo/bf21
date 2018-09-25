package com.claudebernard.projetbf21.view;

import android.app.Activity;
import android.app.SearchManager;
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
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.control.ClientControl;
import com.claudebernard.projetbf21.control.CoachControl;
import com.claudebernard.projetbf21.control.ValidationLogin;
import com.claudebernard.projetbf21.model.Coach;

public class CoachActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Coach _coach;
    private Activity _activity;
    private Context _context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach);
        _activity = this;
        _context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton _fabAddClient = (FloatingActionButton) findViewById(R.id.btn_add_coach);
        _fabAddClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogCoach dialogCoach = new DialogCoach(_activity, "add", null);
                dialogCoach.show();
            }
        });

        definitionsMenu();
        loadGridCoaches();
    }


    //=====
    public void definitionsMenu(){

        Intent _intent = getIntent();
        String _login = _intent.getStringExtra(ValidationLogin.EXTRA_MESSAGE);

        _coach = CoachControl.getDataCoach(_login);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        TextView _namePersonal = (TextView) headerView.findViewById(R.id._namePersonal);
        _namePersonal.setText(_coach.get_lastName()+", "+_coach.get_firstName());
    }


    //=====
    public void loadGridCoaches(){

        GridView _gv = (GridView) findViewById(R.id._gridCoachs);
        AdapterCardCoach _adapter = new AdapterCardCoach(_activity, _context, CoachControl.getDataCoaches());
        _gv.setAdapter(_adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_view, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        if ( searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        searchView.setIconifiedByDefault(false);

        return true;
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

            intent = new Intent(_context, ClientActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_food) {

            intent = new Intent(_context, FoodActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_coach) {

            intent = new Intent(_context, CoachActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_faq) {

            DialogFAQ dialogFAQ = new DialogFAQ(_activity);
            dialogFAQ.show();

        } else if (id == R.id.nav_logout) {
            intent = new Intent(_context, MainActivity.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}