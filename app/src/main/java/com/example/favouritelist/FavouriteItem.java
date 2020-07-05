package com.example.favouritelist;

public class FavouriteItem {

    private String itemName;
    private String key_id;
    private int imageResourse;

    public FavouriteItem() {
    }

    public FavouriteItem(String itemName, String key_id, int imageResourse) {
        this.itemName = itemName;
        this.key_id = key_id;
        this.imageResourse = imageResourse;
    }

    public String getItemName() {
        return itemName;
    }

    public String getKey_id() {
        return key_id;
    }

    public int getImageResourse() {
        return imageResourse;
    }
}
