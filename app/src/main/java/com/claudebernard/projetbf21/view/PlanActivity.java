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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.claudebernard.projetbf21.R;
import com.claudebernard.projetbf21.model.Client;

import java.util.ArrayList;

public class PlanActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Activity _activity;
    private Context _context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        _activity = this;
        _context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btn_email);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadGridClients();
    }


    //=====
    public void loadGridClients(){

        Intent _intent = getIntent();
        String _login = _intent.getStringExtra(AdapterCardClient.EXTRA_MESSAGE_HOME);

        GridView _gv = (GridView) findViewById(R.id._gridPlan);
        AdapterCardClient _adapter = new AdapterCardClient(this, this, getData());
        _gv.setAdapter(_adapter);
    }


    //=====
    private ArrayList getData() {

        ArrayList<Client> clients = new ArrayList<>();

        for (int index = 0; index < 30; index++) {

            Client client = new Client();

            client.set_lastName("Nom du Client - ");
            client.set_firstName(String.valueOf(index+1));

            clients.add(client);
        }
        return clients;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_view, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
