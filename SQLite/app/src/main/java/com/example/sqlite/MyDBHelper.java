package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {

//USE static coz we are accessing it inside the constructor without creating it's object and use final keyword we do not need to resign the again it's name
    private static final String DATABASE_NAME = "RestaurantDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_DASHBOARD = "dashboard"; //table name

//    table column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";
    private static final String KEY_NUMBER = "number";

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//    call onCreate() method when first time app launch and database create in our app
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        create table for storing the database

        //        cursor need for read particular data in the table
// this .execSQL() function only need when we want to perform insert , delete , update , create table operations in our database & this method is not returning
// cursor & SQL is not case sensitive. & table name is always unique
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_DASHBOARD +
                "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PRICE + " TEXT," + KEY_NUMBER + " TEXT" + ")");

//        Query - CREATE TABLE dashboard (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, price TEXT, number Text)

//  Open the database ( getWriteableDatabase()-> use when we want perform operations and getReadableDatabase()-> use when we want to only read the data )
//        and all operations perform on this database
//        SQLiteDatabase database = this.getWritableDatabase();
////
//////        execute queries
////
//////        database close
//        database.close();


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

//        use when we want to upgrade our database so, drop old database then call onCreate Method for creating or updating the database with new schema
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_DASHBOARD);
        onCreate(sqLiteDatabase);
    }

//    Create functions for operations

//    no need to give id in parameter coz id is autoincrement from default value 1
    public void insertData(int id, String name, String price, String number){

//        Open the database
        SQLiteDatabase db = this.getWritableDatabase();

//        store values in ContentValues and it stores values in key-value pair
        ContentValues values = new ContentValues();
        values.put(KEY_ID,id);
        values.put(KEY_NAME,  name);
        values.put(KEY_PRICE, price);
        values.put(KEY_NUMBER, number);

//        insert values in the table of database
        db.insert(TABLE_DASHBOARD,null,values);

    }

//    fetchData (store data in arraylist)
    public ArrayList<DataClass> fetchData(){

//        only select query returns thr cursor (by the help of cursor we can fetch the data from the table)
//        open database
        SQLiteDatabase db = this.getReadableDatabase(); // use ReadableDatabase() coz we are only fetching the data

//        selection argument use when we want to fetch any particular data like give where condition in sql query
//        but if we do not want to use when condition then we use here null
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_DASHBOARD , null); //this rawQuery() function provides cursor

//        store value in cursor then move the cursor in the table one by one till the last row for fetching the data of all rows
        ArrayList<DataClass> arrModel = new ArrayList<>(); // store data in arraylist

        while (cursor.moveToNext()){
            DataClass model = new DataClass();
            model.id = cursor.getInt(0); // give first index of column of the table so, first index is 0
            model.name = cursor.getString(1); // give second index of column of the table so, second index is 1
            model.price = cursor.getString(2);
            model.number = cursor.getString(3);

//            add model in arraylist
            arrModel.add(model);
        }
        return arrModel;
    }

//    Update the data
    public void updateData(DataClass dataClass){

//        open db
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,dataClass.name);
        cv.put(KEY_PRICE,dataClass.price);
        cv.put(KEY_NUMBER,dataClass.number);

        db.update(TABLE_DASHBOARD,cv,KEY_ID +" = "+ dataClass.id,null);

    }

//    Delete the data from database
    public void deleteData(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+ TABLE_DASHBOARD);
    }

}

