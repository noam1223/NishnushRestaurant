package com.example.nishnushrestaurant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nishnushrestaurant.R;
import com.example.nishnushrestaurant.helpClasses.Menu;


public class ClassificationAdapter extends BaseAdapter {

    Context context;
    Menu orderMenu;
    LayoutInflater layoutInflater;


    public ClassificationAdapter(Context context, Menu orderMenu) {
        this.context = context;
        this.orderMenu = orderMenu;
        this.layoutInflater = LayoutInflater.from(this.context);
    }



    @Override
    public int getCount() {
        return orderMenu.getClassifications().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = this.layoutInflater.inflate(R.layout.dish_classification_item, null);


        TextView classificationTextView = convertView.findViewById(R.id.classification_name_text_view_dish_classification_item);
        ListView dishListView = convertView.findViewById(R.id.dish_details_list_view_dish_classification_item);

        classificationTextView.setText(orderMenu.getClassifications().get(position).getClassificationName());
        dishListView.setAdapter(new DishAdapter(context, orderMenu.getClassifications().get(position)));


        return convertView;
    }
}
