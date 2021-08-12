package com.example.nishnushrestaurant.helpClasses;

import android.net.Uri;

import com.example.nishnushrestaurant.helpClasses.changesmodel.Changes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dish implements Serializable, Cloneable {

    String name;
    String details;
    List<Integer> changes;
    List<Changes> changesList = new ArrayList<>();
    int price;
    Uri image;


    public Dish() {
    }

    public Dish(String name, String details, List<Integer> changes, List<Changes> changesList, int price, Uri image) {
        this.name = name;
        this.details = details;
        this.changes = changes;
        this.changesList = changesList;
        this.price = price;
        this.image = image;
    }


//    public Dish(String name, String details, List<Integer> changes, int price, Uri image) {
//        this.name = name;
//        this.details = details;
//        this.changes = changes;
//        this.price = price;
//        this.image = image;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<Integer> getChanges() {
        return changes;
    }

    public void setChanges(List<Integer> changes) {
        this.changes = changes;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public List<Changes> getChangesList() {
        return changesList;
    }

    public void setChangesList(List<Changes> changesList) {
        this.changesList = changesList;
    }

    public Object clone()
    {
        try
        {
            return super.clone();
        }
        catch( CloneNotSupportedException e )
        {
            e.printStackTrace();
            return null;
        }
    }


}

