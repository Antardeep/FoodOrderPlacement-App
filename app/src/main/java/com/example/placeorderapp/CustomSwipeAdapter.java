package com.example.placeorderapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomSwipeAdapter extends PagerAdapter {
    private int[] image_resourses = {R.drawable.pasta,R.drawable.calzones,R.drawable.chicken,R.drawable.fries,R.drawable.pizza,R.drawable.soda};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public CustomSwipeAdapter(Context ctx){    //constructor
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return image_resourses.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == (ConstraintLayout)o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout,container,false);

        ImageView image_View = item_view.findViewById(R.id.image_view);

        image_View.setImageResource(image_resourses[position]);

        container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
