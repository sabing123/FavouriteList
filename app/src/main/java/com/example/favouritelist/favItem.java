package com.example.favouritelist;

public class favItem {
    private String itemName;
    private String key_id;
    private String favStatus;
    private int imageResourse;
    public favItem() {
    }

    public favItem(String itemName, String key_id, String favStatus, int imageResourse) {
        this.itemName = itemName;
        this.key_id = key_id;
        this.favStatus = favStatus;
        this.imageResourse = imageResourse;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }

    public int getImageResourse() {
        return imageResourse;
    }

    public void setImageResourse(int imageResourse) {
        this.imageResourse = imageResourse;
    }
}
