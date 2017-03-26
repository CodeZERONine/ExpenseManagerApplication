package com.gusain.expensemanagerapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ViewActivity extends AppCompatActivity {
    MyDBHelper dbHelper;
    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Toast.makeText(ViewActivity.this, "Hola", Toast.LENGTH_SHORT).show();
        Toolbar toolbar=(Toolbar)findViewById(R.id.app_bar1);
        setSupportActionBar(toolbar);
        textView1=(TextView)findViewById(R.id.textView1);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        dbHelper=new MyDBHelper(ViewActivity.this);
        toolbar.setLogo(R.drawable.munshi_icon);//to tell the OS to use my custom toolbar
        getSupportActionBar().setHomeButtonEnabled(true);//to set home button on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//to set the back button to take 1 level up not all the way up
        SQLiteDatabase db=openOrCreateDatabase("munshidb",MODE_APPEND,null);
        Cursor c=db.rawQuery("select * from TripDetail",null);
        String text="";
        while(c.moveToNext())
        {
            String temp="";
            String a=c.getString(0);
            temp=temp+a;
            String b=c.getString(1);temp=temp+b+"-";
            String d=c.getString(2);temp=temp+d+"-";
            String e=c.getString(3);temp=temp+e+"-";
            String f=c.getString(4);temp=temp+f+"-";
            String g=c.getString(5);temp=temp+g+"-";
            text=text+temp+"\n";


        }
        textView1.setText(text);
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
