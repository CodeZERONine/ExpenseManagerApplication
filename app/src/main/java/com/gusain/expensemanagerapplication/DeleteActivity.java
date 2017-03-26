package com.gusain.expensemanagerapplication;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {

    EditText et1;
    MyDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        Toast.makeText(DeleteActivity.this, "Hola", Toast.LENGTH_SHORT).show();
        Toolbar toolbar=(Toolbar)findViewById(R.id.app_bar1);
        setSupportActionBar(toolbar);
        et1=(EditText)findViewById(R.id.editText8);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setLogo(R.drawable.munshi_icon);//to tell the OS to use my custom toolbar

        getSupportActionBar().setHomeButtonEnabled(true);//to set home button on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//to set the back button to take 1 level up not all the way up
        dbHelper=new MyDBHelper(DeleteActivity.this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home)
        {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
    public void delete(View v)
    {
         int id=Integer.valueOf(et1.getText().toString());
        long result=  dbHelper.delete(id);
       if(result==-1) {
            Toast.makeText(DeleteActivity.this, "Some Error occured" + result, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(DeleteActivity.this, "Deleted"+result, Toast.LENGTH_SHORT).show();
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
