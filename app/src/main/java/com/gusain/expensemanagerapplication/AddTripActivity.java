package com.gusain.expensemanagerapplication;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTripActivity extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;
    Button button1;
    MyDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        Toast.makeText(AddTripActivity.this, "Hola", Toast.LENGTH_SHORT).show();
        Toolbar toolbar=(Toolbar)findViewById(R.id.app_bar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setLogo(R.drawable.munshi_icon);//to tell the OS to use my custom toolbar
        getSupportActionBar().setHomeButtonEnabled(true);//to set home button on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//to set the back button to take 1 level up not all the way up
        editText1=(EditText)findViewById(R.id.editText1);//id
        editText2=(EditText)findViewById(R.id.editText2);//// from
        editText3=(EditText)findViewById(R.id.editText3);//to
        editText4=(EditText)findViewById(R.id.editText4);//start
        editText5=(EditText)findViewById(R.id.editText5);//end
        editText6=(EditText)findViewById(R.id.editText6);//budget
        button1=(Button)findViewById(R.id.button1);
        dbHelper=new MyDBHelper(AddTripActivity.this);

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
    public void add_database(View v)
    {
        int tripid=Integer.valueOf(editText1.getText().toString());
        String from=editText2.getText().toString();
        String to=editText3.getText().toString();
        String sd=editText4.getText().toString();
        String ed=editText5.getText().toString();
        Double bud=Double.valueOf(editText6.getText().toString());
       /* SQLiteDatabase database=openOrCreateDatabase("munshi_database",MODE_APPEND,null);
        database.execSQL("insert into TripDetails values('"+tripid+"','"+from+"','"+to+"','"+sd+"','"+ed+"','"+bud+"')");
        String q="select * from TripDetails";
        Cursor c=database.rawQuery(q,null);
        String temp;
        while(c.moveToNext())
        {
            temp=c.getString(0);
            Toast.makeText(AddTripActivity.this, temp, Toast.LENGTH_SHORT).show();
            temp=c.getString(1);Toast.makeText(AddTripActivity.this, temp, Toast.LENGTH_SHORT).show();
            temp=c.getString(2);Toast.makeText(AddTripActivity.this, temp, Toast.LENGTH_SHORT).show();
            temp=c.getString(3);Toast.makeText(AddTripActivity.this, temp, Toast.LENGTH_SHORT).show();
            temp=c.getString(4);Toast.makeText(AddTripActivity.this, temp, Toast.LENGTH_SHORT).show();
            temp=c.getString(5);Toast.makeText(AddTripActivity.this, temp, Toast.LENGTH_SHORT).show();

        }
        database.close();*/
      long result=  dbHelper.insert(tripid,from,to,sd,ed,bud);
        if(result==-1) {
            Toast.makeText(AddTripActivity.this, "Some Error occured" + result, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(AddTripActivity.this, "Success"+result, Toast.LENGTH_SHORT).show();
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
