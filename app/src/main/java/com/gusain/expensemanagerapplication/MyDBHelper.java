package com.gusain.expensemanagerapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Double3;
import android.widget.Toast;

/**
 * Created by Bobby on 7/9/2016.
 */
public class MyDBHelper extends SQLiteOpenHelper
{
    private static final String DBNAME="munshidb";// Name of database
    private static final int VERSION=1;//Version of Database

    private static final String TABLE_NAME= "TripDetail";
    private static  final String TRIP_ID= "trip_id";
    private static  final String SOURCE= "source";
    private static final String DESTINATION= "destination";
    private static final String DATE_START= "stdate";
    private static final String DATE_END= "eddate";
    private static final String BUDGET= "budget";

    private static final String TABLE_NAME1= "ExpenseDetails";
    private static  final String TRIP_ID1= "trip_id";
    private static  final String CATEGORY= "category";
    private static final String AMOUNT= "amount";
    private static final String DATE= "tdate";
    private static final String EXPENSE_ID= "expense_id";



    SQLiteDatabase myDB;

    //constructor
    public MyDBHelper(Context context)
    {
        super(context, DBNAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String queryTable="create table if not exists " +TABLE_NAME + " (" +
                TRIP_ID + "  varchar PRIMARY KEY, " +
                SOURCE + " TEXT, " +
                DESTINATION + " TEXT," +
                DATE_START + " TEXT," +
                 DATE_END + " TEXT," +
                BUDGET + " REAL )";

        db.execSQL(queryTable);
        String queryTable1="create table if not exists "+TABLE_NAME1+
                "("+EXPENSE_ID+"  INTEGER PRIMARY KEY AUTOINCREMENT,"+
                CATEGORY+" TEXT,"+
                AMOUNT+" REAL,"+
                DATE+" TEXT,"+
                TRIP_ID1+" TEXT)";
        db.execSQL(queryTable1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }

    public void openDB()
    {
        myDB=getWritableDatabase();
    }
    public void closeDB() {
        if (myDB != null && myDB.isOpen()) {
            myDB.close();
        }
    }
     public long insert(int id, String source, String destination, String sdate, String edate, Double budget){
     ContentValues values=new ContentValues();
        values.put(TRIP_ID,id);
         values.put(SOURCE,source);
         values.put(DESTINATION,destination);
         values.put(DATE_START,sdate);
         values.put(DATE_END,edate);
         values.put(BUDGET,budget);
        return myDB.insert(TABLE_NAME,null,values);//returns row id
     }
    public long update(int id, String source, String destination, String sdate, String edate, Double budget){
        ContentValues values=new ContentValues();
        values.put(TRIP_ID,id);
        values.put(SOURCE,source);
        values.put(DESTINATION,destination);
        values.put(DATE_START,sdate);
        values.put(DATE_END,edate);
        values.put(BUDGET,budget);
        String where=TRIP_ID+"="+id;
        return myDB.update(TABLE_NAME,values,where,null);//returns number of rows affected if 0 no change
    }
    public long delete(int id){
        String where=TRIP_ID+"="+id;
                return myDB.delete(TABLE_NAME,where,null);//returns row id
    }
    public Cursor getAllRecords(){
        //myDB.query(TABLE_NAME,null,null,null,null,null,null);//tablename,column name,selection,selection args,sortby,
        String query="SELECT * FROM"+TABLE_NAME;
        return myDB.rawQuery(query,null);

    }







    public long insert1(String category,Double amount, String sdate, int id){
        ContentValues values=new ContentValues();
        values.put(CATEGORY,category);
        values.put(AMOUNT,amount);
        values.put(DATE,sdate);
        values.put(TRIP_ID1,id);

        return myDB.insert(TABLE_NAME1,null,values);//returns row id
    }
   /* public long update1(int id, String source, String destination, String sdate, String edate, Double budget){
        ContentValues values=new ContentValues();
        values.put(TRIP_ID,id);
        values.put(SOURCE,source);
        values.put(DESTINATION,destination);
        values.put(DATE_START,sdate);
        values.put(DATE_END,edate);
        values.put(BUDGET,budget);
        String where=TRIP_ID+"="+id;
        return myDB.update(TABLE_NAME1,values,where,null);//returns number of rows affected if 0 no change
    }
    public long delete1(int id){
        String where=TRIP_ID+"="+id;
        return myDB.delete(TABLE_NAME1,where,null);//returns row id
    }*/
    public Cursor getAllRecords1() {
        //myDB.query(TABLE_NAME,null,null,null,null,null,null);//tablename,column name,selection,selection args,sortby,
        String query = "SELECT * FROM" + TABLE_NAME1;
        return myDB.rawQuery(query, null);
    }




}
