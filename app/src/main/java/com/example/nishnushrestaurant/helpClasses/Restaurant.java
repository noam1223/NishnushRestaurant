package com.example.nishnushrestaurant.helpClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Restaurant implements Serializable {

    String id;
    String name;
    String restaurantUserName;
    String restaurantUserPassword;
    MyAddress myAddress;
    List<AreasForDelivery> areasForDeliveries;
    String phoneNumber;
    List<String> openHour, closeHour;
    String dateOfAdd;


    String logoUri;
    String profileImageUri;
    List<RecommendationRestaurant> recommendationRestaurants = new ArrayList<>();
    boolean kosher, discount;
    List<Integer> classificationList = new ArrayList<>();
    Menu menu;


    //ADDED
    float recommendationAvg = 0;
    double distanceFromCurrentUser;

    int creditAmount = 0;

    ///////////////////////////////////
    List<String> ordersKeys = new ArrayList<>();



    public Restaurant() {
    }



    public Restaurant(String name, MyAddress myAddress, List<AreasForDelivery> areasForDeliveries, String phoneNumber, List<String> openHour,
                      List<String> closeHour, String dateOfAdd, String logoUri, String profileImageUri, boolean kosher, boolean discount,
                      List<Integer> classificationList, Menu menu, String restaurantUserName, String restaurantUserPassword, List<String> ordersKeys) {
        this.name = name;
        this.myAddress = myAddress;
        this.areasForDeliveries = areasForDeliveries;
        this.phoneNumber = phoneNumber;
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.dateOfAdd = dateOfAdd;
        this.logoUri = logoUri;
        this.profileImageUri = profileImageUri;
        this.kosher = kosher;
        this.discount = discount;
        this.classificationList = classificationList;
        this.menu = menu;
        this.restaurantUserName = restaurantUserName;
        this.restaurantUserPassword = restaurantUserPassword;
        this.ordersKeys = ordersKeys;
    }

    public Restaurant(String id, String name, String restaurantUserName, String restaurantUserPassword, MyAddress myAddress, List<AreasForDelivery> areasForDeliveries, String phone, List<String> openHoursList, List<String> closeHoursList, String dateOfAdd, String logoImageUri, String profileImageUri, List<RecommendationRestaurant> recommendationRestaurants, boolean isKosher, boolean isDiscount, List<Integer> classificationHandleList, Menu menu, float recommendationAvg, int creditAmount, List<String> ordersKeys) {
        this.id = id;
        this.name = name;
        this.myAddress = myAddress;
        this.areasForDeliveries = areasForDeliveries;
        this.phoneNumber = phone;
        this.openHour = openHoursList;
        this.closeHour = closeHoursList;
        this.dateOfAdd = dateOfAdd;
        this.logoUri = logoImageUri;
        this.profileImageUri = profileImageUri;
        this.kosher = isKosher;
        this.discount = isDiscount;
        this.classificationList = classificationHandleList;
        this.menu = menu;
        this.restaurantUserName = restaurantUserName;
        this.restaurantUserPassword = restaurantUserPassword;
        this.ordersKeys = ordersKeys;
        this.recommendationRestaurants = recommendationRestaurants;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyAddress getMyAddress() {
        return myAddress;
    }

    public void setMyAddress(MyAddress myAddress) {
        this.myAddress = myAddress;
    }

    public List<AreasForDelivery> getAreasForDeliveries() {
        return areasForDeliveries;
    }

    public void setAreasForDeliveries(List<AreasForDelivery> areasForDeliveries) {
        this.areasForDeliveries = areasForDeliveries;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getOpenHour() {
        return openHour;
    }

    public void setOpenHour(List<String> openHour) {
        this.openHour = openHour;
    }

    public List<String> getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(List<String> closeHour) {
        this.closeHour = closeHour;
    }

    public String getDateOfAdd() {
        return dateOfAdd;
    }

    public void setDateOfAdd(String dateOfAdd) {
        this.dateOfAdd = dateOfAdd;
    }


    public String getLogoUri() {
        return logoUri;
    }

    public void setLogoUri(String logoUri) {
        this.logoUri = logoUri;
    }

    public String getProfileImageUri() {
        return profileImageUri;
    }

    public void setProfileImageUri(String profileImageUri) {
        this.profileImageUri = profileImageUri;
    }

    public List<RecommendationRestaurant> getRecommendationRestaurants() {
        return recommendationRestaurants;
    }

    public void setRecommendationRestaurants(List<RecommendationRestaurant> recommendationRestaurants) {
        this.recommendationRestaurants = recommendationRestaurants;
    }

    public boolean isKosher() {
        return kosher;
    }

    public void setKosher(boolean kosher) {
        this.kosher = kosher;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public List<Integer> getClassificationList() {
        return classificationList;
    }

    public void setClassificationList(List<Integer> classificationList) {
        this.classificationList = classificationList;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }



    public float getRecommendationAvg() {

        if (recommendationAvg == -1) {

            float avg = 0;

            for (int i = 0; i < recommendationRestaurants.size(); i++) {

                avg += recommendationRestaurants.get(i).getCreditStar();

            }

            recommendationAvg = (float) (avg/recommendationRestaurants.size());
        }

        return recommendationAvg;
    }


    public double getDistanceFromCurrentUser() {
        return distanceFromCurrentUser;
    }

    public void setDistanceFromCurrentUser(double distanceFromCurrentUser) {
        this.distanceFromCurrentUser = distanceFromCurrentUser;
    }

    public int getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(int creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getRestaurantUserName() {
        return restaurantUserName;
    }

    public void setRestaurantUserName(String restaurantUserName) {
        this.restaurantUserName = restaurantUserName;
    }

    public String getRestaurantUserPassword() {
        return restaurantUserPassword;
    }

    public void setRestaurantUserPassword(String restaurantUserPassword) {
        this.restaurantUserPassword = restaurantUserPassword;
    }

    public List<String> getOrdersKeys() {
        return ordersKeys;
    }

    public void setOrdersKeys(List<String> ordersKeys) {
        this.ordersKeys = ordersKeys;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
