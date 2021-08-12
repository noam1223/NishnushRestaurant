package com.example.nishnushrestaurant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nishnushrestaurant.R;
import com.example.nishnushrestaurant.helpClasses.Classification;
import com.example.nishnushrestaurant.helpClasses.Dish;
import com.example.nishnushrestaurant.helpClasses.changesmodel.Changes;
import com.example.nishnushrestaurant.helpClasses.changesmodel.PizzaChange;
import com.example.nishnushrestaurant.helpClasses.changesmodel.RegularChange;
import com.google.gson.Gson;

import java.util.HashMap;

public class DishAdapter extends BaseAdapter {

    Context context;
    Classification classification;
    LayoutInflater layoutInflater;


    public DishAdapter(Context context, Classification classification) {
        this.context = context;
        this.classification = classification;
        this.layoutInflater = LayoutInflater.from(this.context);
    }



    @Override
    public int getCount() {
        return classification.getDishList().size();
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

        convertView = this.layoutInflater.inflate(R.layout.dish_detail_order_item, null);


        TextView dishNameTextView = convertView.findViewById(R.id.dish_name_order_dish_detail_order_item);
        TextView detailDishTextView = convertView.findViewById(R.id.dish_detail_order_dish_detail_order_item);


        dishNameTextView.setText(classification.getDishList().get(position).getName());
        detailDishTextView.setText(classification.getDishList().get(position).getDetails());

        StringBuilder stringBuilder = new StringBuilder();
//        int costDishWithChanges = 0;

//            for (int i = 0; i < classification.getDishList().get(position).getChanges().size(); i++) {
//
//                Changes.ChangesTypesEnum changesTypesEnum = classification.getDishList().get(position).getChanges().get(i).getChangesTypesEnum();
//
//                for (int j = 0; j < classification.getDishList().get(position).getChanges().get(i).getChangesByTypesList().size(); j++) {
//
//
//                    if (changesTypesEnum == Changes.ChangesTypesEnum.DISH_CHOICE || changesTypesEnum == Changes.ChangesTypesEnum.CLASSIFICATION_CHOICE) {
//
//                        Dish dish;
//
//                        try {
//                            HashMap<String, Object> map = (HashMap<String, Object>) classification.getDishList().get(position).getChanges().get(i).getChangesByTypesList().get(j);
//                            dish = new Gson().fromJson(new Gson().toJson(map), Dish.class);
//                        } catch (Exception e) {
//                            dish = (Dish) classification.getDishList().get(position).getChanges().get(i).getChangesByTypesList().get(j);
//                        }
//
//
//                        stringBuilder.append(dish.getName()).append(" - ");
//
//                        if (dish.getChanges().size() > 0) {
//                            for (int k = 0; k < dish.getChanges().size(); k++) {
//
//                                if (dish.getChanges().get(k).getChangesByTypesList().size() > 0) {
//
//                                    if (classification.getDishList().get(position).getChanges().get(i).getFreeSelection() == classification.getDishList().get(position).getChanges().get(i).getChangesByTypesList().size()) {
//
//                                        //NO NEED TO ADD CHANGE COST
//                                        continue;
//                                    }
//
////                                    costDishWithChanges += dish.getPrice();
//                                }
//                            }
//
//                        }
//
//
//                    } else if (changesTypesEnum == Changes.ChangesTypesEnum.ONE_CHOICE) {
//
//                        RegularChange regularChange;
//
//                        try {
//                            HashMap<String, Object> map = (HashMap<String, Object>) classification.getDishList().get(position).getChanges().get(i).getChangesByTypesList().get(j);
//                            regularChange = new Gson().fromJson(new Gson().toJson(map), RegularChange.class);
//                        } catch (Exception e) {
//                            regularChange = (RegularChange) classification.getDishList().get(position).getChanges().get(i).getChangesByTypesList().get(j);
//                        }
//
//
//                        stringBuilder.append(regularChange.getChange());
////                        costDishWithChanges += regularChange.getPrice();
//
//
//                    } else if (changesTypesEnum == Changes.ChangesTypesEnum.MULTIPLE) {
//
//
//                        RegularChange regularChange;
//
//                        try {
//                            HashMap<String, Object> map = (HashMap<String, Object>) classification.getDishList().get(position).getChanges().get(i).getChangesByTypesList().get(j);
//                            regularChange = new Gson().fromJson(new Gson().toJson(map), RegularChange.class);
//                        } catch (Exception e) {
//                            regularChange = (RegularChange) classification.getDishList().get(position).getChanges().get(i).getChangesByTypesList().get(j);
//                        }
//
//
//                        stringBuilder.append(regularChange.getChange());
////                        costDishWithChanges += regularChange.getPrice();
//
//
//                    } else if (changesTypesEnum == Changes.ChangesTypesEnum.VOLUME) {
//
//
//                        RegularChange regularChange;
//
//                        try {
//                            HashMap<String, Object> map = (HashMap<String, Object>) classification.getDishList().get(position).getChanges().get(i).getChangesByTypesList().get(j);
//                            regularChange = new Gson().fromJson(new Gson().toJson(map), RegularChange.class);
//                        } catch (Exception e) {
//                            regularChange = (RegularChange) classification.getDishList().get(position).getChanges().get(i).getChangesByTypesList().get(j);
//                        }
//
//                        stringBuilder.append(regularChange.getChange());
//
//
//                        stringBuilder.append("הוספת ").append(regularChange.getNumOfAdded());
////                        costDishWithChanges += (regularChange.getPrice() * regularChange.getNumOfAdded());
//
//
//                    } else if (changesTypesEnum == Changes.ChangesTypesEnum.PIZZA) {
//
//                        PizzaChange pizzaChange;
//
//                        try {
//                            HashMap<String, Object> map = (HashMap<String, Object>) classification.getDishList().get(position).getChanges().get(i).getChangesByTypesList().get(j);
//                            pizzaChange = new Gson().fromJson(new Gson().toJson(map), PizzaChange.class);
//                        } catch (Exception e) {
//                            pizzaChange = (PizzaChange) classification.getDishList().get(position).getChanges().get(i).getChangesByTypesList().get(j);
//                        }
//
//
//                        stringBuilder.append(pizzaChange.getName()).append(" ");
//
//                        if (pizzaChange.isBothSides()) {
//
//                            stringBuilder.append("כל הפיצה");
//
//                        } else {
//
//                            if (pizzaChange.isLeftSide())
//                                stringBuilder.append("חצי שמאל");
//                            else
//                                stringBuilder.append("חצי ימין");
//                        }
//
//
////                        costDishWithChanges += pizzaChange.getCost();
//                    }
//
//                }
//
//                stringBuilder.append(",").append(" ");
//
//            }


//            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
//            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
//            stringBuilder.append(".");
//
//            detailDishTextView.setText(stringBuilder.toString());


        return convertView;
    }
}
