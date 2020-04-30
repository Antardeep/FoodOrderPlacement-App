package com.example.placeorderapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    ImageView foodImage;
    TextView foodName, foodDesc;
    Button placeOrder,back;
    String name,desc;
    int id, image;

    Food f = new Food(this);                                              //object of Food Class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        foodImage = findViewById(R.id.foodImage);
        foodName = findViewById(R.id.foodName);
        foodDesc = findViewById(R.id.foodDesc);
        placeOrder = findViewById(R.id.placeOrder);
        back = findViewById(R.id.back);

        /**********************Receiving intent*********************/
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        name = getIntent().getStringExtra("name");
        image = getIntent().getIntExtra("image",0);

        foodName.setText(name);
        foodImage.setImageResource(image);

       /************************fetching data from Food Class *************************/
        Cursor data = f.getItemID(name);
        int itemID = -1;

        while(data.moveToNext()){
            itemID = data.getInt(0);
            name = data.getString(data.getColumnIndex(f.food_name_col));
            desc= data.getString(data.getColumnIndex(f.food_desc_col));
        }

        foodDesc.setText(desc);

        /*********************Back Button code**************************************/
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this,MenuActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        /***************************place Order Button code**************************/
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(name.equals("Pizza")||name.equals("Calzones")||name.equals("French Fries")){         //call Topping Activity for Pizza, Calzones and fries
                   Intent intent = new Intent(DetailsActivity.this,ToppingActivity.class);
                   intent.putExtra("fname",name);
                   intent.putExtra("id",id);
                   startActivity(intent);
                }
                else if(name.equals("Pasta")||name.equals("Chicken Wings")){                        //call size activity for pasta and chicken wings
                   Intent intent = new Intent(DetailsActivity.this,SizeActivity.class);
                   intent.putExtra("fname",name);
                   intent.putExtra("id",id);
                   startActivity(intent);
                }else{                                                                              //call soda activity
                   Intent intent = new Intent(DetailsActivity.this,SodaActivity.class);
                   intent.putExtra("fname",name);
                   intent.putExtra("id",id);
                   startActivity(intent);
               }
            }
        });
    }
}
