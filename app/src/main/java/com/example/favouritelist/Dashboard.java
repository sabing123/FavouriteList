package com.example.favouritelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {
    RecyclerView recyclerView11;
    private favDB favdB;
    private List<FavouriteItem> favItemList = new ArrayList<>();
    private favAdapter favAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        favdB = new favDB(this);
        recyclerView11 = findViewById(R.id.recycleview1);
        recyclerView11.setHasFixedSize(true);
        recyclerView11.setHasFixedSize(true);
        recyclerView11.setLayoutManager(new LinearLayoutManager(this));
     loaddata();


    }

    private void loaddata() {
        if (favItemList != null) {
            favItemList.clear();
        }
        SQLiteDatabase db = favdB.getReadableDatabase();
        Cursor cursor = favdB.select_all_favorite_list();
        try {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(favDB.ITEM_NAME));
                String id = cursor.getString(cursor.getColumnIndex(favDB.KEY_ID));
                int image = Integer.parseInt(cursor.getString(cursor.getColumnIndex(favDB.ITEM_IMAGE)));
                FavouriteItem favItem = new FavouriteItem(name, id, image);
                favItemList.add(favItem);
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }

        favAdapter = new favAdapter(favItemList, this);
        recyclerView11.setAdapter(favAdapter);
    }
}