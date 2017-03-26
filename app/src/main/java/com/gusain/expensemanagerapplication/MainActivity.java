package com.gusain.expensemanagerapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{   private Toolbar toolbar;//toolbar variable
    DrawerLayout drawerLayout;//drawerlayout variable
    ActionBarDrawerToggle action;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.app_bar1);//find toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setLogo(R.drawable.munshi_icon);//to tell the OS to use my custom toolbar
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        action=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(action);
        navigationView=(NavigationView)findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
            public boolean onNavigationItemSelected(MenuItem item) {
               int id=item.getItemId();
                switch(id)
                {
                    case R.id.addevent:startActivity(new Intent(MainActivity.this,AddTripActivity.class));
                                       drawerLayout.closeDrawers();//to close the drawer automatically
                                       break;
                    case R.id.View:startActivity(new Intent(MainActivity.this,ViewActivity.class)); drawerLayout.closeDrawers();
                                    break;
                    case R.id.delete:startActivity(new Intent(MainActivity.this,DeleteActivity.class)); drawerLayout.closeDrawers();
                                       break;
                    case R.id.update:startActivity(new Intent(MainActivity.this,UpdateActivity.class)); drawerLayout.closeDrawers();
                        break;
                    case R.id.tripdetails:startActivity(new Intent(MainActivity.this,UpdateActivity.class)); drawerLayout.closeDrawers();
                        break;
                }
                return false;
            }
        });//end of setNavigationItemSelectListner
        //Creation of database and tables
       /* SQLiteDatabase database=openOrCreateDatabase("munshidatabase",MODE_APPEND,null);
        database.execSQL("create table if not exists TripDetails(tripid varchar,source varchar,destination varchar,dateofstart varchar," +
                         "dateofend varchar,budget varchar,balance varchar,PRIMARY KEY (tripid))");
        database.execSQL("create table if not exists ExpenseDetails(EXPENSE_ID varchar,CATEGORY varchar,AMOUNTSPEND varchar," +
                "DATE varchar,TRIP_ID varchar,FOREIGN KEY (TRIP_ID) REFERENCES TripDetails(tripid))");*/
    }//end of onCreate()

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.add_expense:startActivity(new Intent(MainActivity.this,AddExpenseActivity.class));
                       break;
            case R.id.about:
                Toast.makeText(MainActivity.this, "DEV: AKSHANSH", Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this, "Build:1.12", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        action.syncState();
    }

    @Override//To inflate the buttonos on app bar
    public boolean onCreateOptionsMenu(Menu menu)
    { getMenuInflater().inflate(R.menu.addexpense,menu);
        return super.onCreateOptionsMenu(menu);
    }//end of onCreateOptionsMenu
}
