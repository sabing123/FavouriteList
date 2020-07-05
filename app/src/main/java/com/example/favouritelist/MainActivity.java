package com.example.favouritelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<favItem> favItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycleview);
        Button btndasgboard = findViewById(R.id.dashboard);
        btndasgboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Dashboard.class));
            }
        });


        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new adapter(favItems,this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        favItems.add(new favItem("latte", "0", "0", R.drawable.ic_launcher_foreground));
        favItems.add(new favItem("sabin", "1", "0", R.drawable.ic_launcher_background));
        favItems.add(new favItem("sagar", "2", "0", R.drawable.ic_launcher_foreground));
        favItems.add(new favItem("aaa", "3", "0", R.drawable.ic_launcher_foreground));
        favItems.add(new favItem("bbbbb", "4", "0", R.drawable.ic_launcher_foreground));
        favItems.add(new favItem("ccc", "5", "0", R.drawable.ic_launcher_foreground));
        favItems.add(new favItem("dddd", "6", "0", R.drawable.ic_launcher_foreground));
        favItems.add(new favItem("suman", "7", "0", R.drawable.ic_launcher_foreground));
        favItems.add(new favItem("lwww", "8", "0", R.drawable.ic_launcher_foreground));
    }
}