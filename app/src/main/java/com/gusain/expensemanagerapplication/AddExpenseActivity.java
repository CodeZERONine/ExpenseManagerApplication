package com.gusain.expensemanagerapplication;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddExpenseActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    String category;
    EditText et2;
    EditText editText;
    EditText editText7;
    MyDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        Toast.makeText(AddExpenseActivity.this, "Hola", Toast.LENGTH_SHORT).show();
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setLogo(R.drawable.munshi_icon);
        //to tell the OS to use my custom toolbar
        getSupportActionBar().setHomeButtonEnabled(true);//to set home button on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//to set the back button to take 1 level up not all the way up
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        et2 = (EditText) findViewById(R.id.et2);
        dbHelper = new MyDBHelper(AddExpenseActivity.this);
        editText = (EditText) findViewById(R.id.editText);
        editText7 = (EditText) findViewById(R.id.editText7);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.radioButton:
                        category ="Travelling";
                        break;
                    case R.id.radioButton2:
                        category ="Food";
                        break;
                    case R.id.radioButton4:
                        category ="Lodging";
                        break;
                    case R.id.radioButton6:
                        category = "Miscellaneous";
                        break;
                }
             //   category = cat;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    public void confirm(View v) {
        Double a = Double.valueOf(et2.getText().toString());
        String tdate = editText.getText().toString();
        int id = Integer.valueOf(editText7.getText().toString());
        long result = dbHelper.insert1(category, a, tdate, id);
        if (result == -1) {
            Toast.makeText(AddExpenseActivity.this, "Some Error occured" + result, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AddExpenseActivity.this, "Success" + result, Toast.LENGTH_SHORT).show();
        }
        finish();

    }

    @Override
    protected void onStart() {
        super.onStart();
        dbHelper.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dbHelper.close();
    }
}