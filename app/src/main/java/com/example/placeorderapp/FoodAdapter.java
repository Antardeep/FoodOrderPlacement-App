package com.example.placeorderapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelFood>mlist;

    FoodAdapter(Context c, ArrayList<ModelFood>list){   //constructor
        context = c;
        mlist = list;
    }

    MenuActivity obj = new MenuActivity();                                              //getting id of user from menuActivity
    int id = obj.get();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyle_layout,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final ModelFood foodItem = mlist.get(i);

        ImageView image = viewHolder.item_image;
        TextView name,desc,price;

        name = viewHolder.item_name;
        desc = viewHolder.item_desc;
        price = viewHolder.item_price;

        image.setImageResource(mlist.get(i).getImage());

        name.setText(foodItem.getName());
        desc.setText(foodItem.getDesc());
        price.setText(foodItem.getPrice());

        viewHolder.rLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("image",foodItem.getImage());
                intent.putExtra("name",foodItem.getName());
                context.startActivities(new Intent[]{intent});
            }
        });


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView item_image;
        TextView item_name, item_desc, item_price;
        LinearLayout rLayout;

        public ViewHolder(View itemView){
            super(itemView);

            item_image = itemView.findViewById(R.id.item_image);
            item_name = itemView.findViewById(R.id.item_name);
            item_desc = itemView.findViewById(R.id.item_desc);
            item_price = itemView.findViewById(R.id.item_price);
            rLayout = itemView.findViewById(R.id.rLayout);
        }
    }
}
