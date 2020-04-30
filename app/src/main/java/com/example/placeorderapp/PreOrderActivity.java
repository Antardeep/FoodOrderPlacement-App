package com.example.placeorderapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PreOrderActivity extends AppCompatActivity {
    private ListView listView;
    private static final String TAG = "ListDataActivity";
    Order o = new Order(this);    //object of order class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_order);

        Button backToHome = findViewById(R.id.backToHome);
        listView = findViewById(R.id.listView);
        Log.d(TAG, "populateListView: displaying data in the List View");

        Intent intent = getIntent();
        final int id = intent.getIntExtra("id",0);
        String user_id = String.valueOf(id);

        final Cursor data = o.getItemID(user_id);


        ArrayList<String> listData = new ArrayList<>();                                             //creating array list
        while (data.moveToNext()){
            //getting the value from database and adding to ArrayLiat
            listData.add("Order Number: "+data.getString(0)+"\n"+data.getString(1));
        }

        //create and set the list adapter
        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        listView.setAdapter(adapter);

        /******************calling menu activity when clicking the list item*****************************/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Intent intent = new Intent(PreOrderActivity.this, MenuActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);


            }
        });

        /*****************back to menu button*********************/
        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreOrderActivity.this,StartActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

    }
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
