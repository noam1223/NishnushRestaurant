package com.example.nishnushrestaurant.helpClasses;

import com.example.nishnushrestaurant.helpClasses.changesmodel.Changes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menu implements Serializable {

    List<Classification> classifications = new ArrayList<>();
    List<Changes> changesList = new ArrayList<>();

    public Menu() {
    }

    public Menu(List<Classification> classifications, List<Changes> changesList) {
        this.classifications = classifications;
        this.changesList = changesList;
    }

    public List<Classification> getClassifications() {
        return classifications;
    }

    public void setClassifications(List<Classification> classifications) {
        this.classifications = classifications;
    }

    public List<Changes> getChangesList() {
        return changesList;
    }

    public void setChangesList(List<Changes> changesList) {
        this.changesList = changesList;
    }
}
