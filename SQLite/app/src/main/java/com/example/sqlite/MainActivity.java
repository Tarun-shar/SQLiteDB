package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//       insert details
        MyDBHelper object = new MyDBHelper(this);

        object.insertData(0,"DineIn","432","2847567");
        object.insertData(1,"Delivery","734","4758394");
        object.insertData(2,"Takeaway","234","5643245");
        object.insertData(3,"Total Order","532","6543234");
        object.insertData(4,"Tax","654","5434564");
        object.insertData(5,"Discount","712","6723456");

//        Update
//        DataClass model = new DataClass();
//        model.id = 1;
//        model.name = "Tarun";
//        model.price = "456";
//        model.number = "244453422334";
//
//        object.updateData(model);

//        object.deleteData();

//        fetch data
        ArrayList<DataClass> arrModel = object.fetchData();

        for (int i=0; i<arrModel.size(); i++){
            Log.d("Dashboard_data","Name: "+ arrModel.get(i).name + ", Price: "+ arrModel.get(i).price +", Number: "+ arrModel.get(i).number);
        }
    }
}