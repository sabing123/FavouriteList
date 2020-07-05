package com.example.favouritelist;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    private ArrayList<favItem> favItems;
    private Context context;
    private favDB favDB;

    public adapter(ArrayList<favItem> favItems, Context context) {
        this.context = context;
        this.favItems = favItems;
    }

    @NonNull
    @Override
    public adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        favDB = new favDB(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        boolean firstStart = sharedPreferences.getBoolean("firstStart", true);

        if (firstStart) {
            createTableOnFirstStart();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull adapter.ViewHolder holder, int position) {

        final favItem coffeeItem = favItems.get(position);
        readCursorData(coffeeItem, holder);
        holder.imageView.setImageResource(coffeeItem.getImageResourse());
        holder.titleTextView.setText(coffeeItem.getItemName());
    }

    private void readCursorData( favItem favItem, ViewHolder viewHolder) {
        Cursor cursor = favDB.read_all_data(favItem.getKey_id());
        SQLiteDatabase db = favDB.getReadableDatabase();
        try {
            while (cursor.moveToNext()) {
                String item_fav_status = cursor.getString(cursor.getColumnIndex(favDB.FAVORITE_STATUS));
                favItem.setFavStatus(item_fav_status);

                //check fav status
                if (item_fav_status != null && item_fav_status.equals("1")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                } else if (item_fav_status != null && item_fav_status.equals("0")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }

    }


    @Override
    public int getItemCount() {
        return favItems.size();
    }

    private void createTableOnFirstStart() {
        favDB.insertEmpty();
        SharedPreferences sharedPreferences = context.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("firstStart",false);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleTextView, likeCountTextView;
        Button favBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            favBtn = itemView.findViewById(R.id.favBtn);
            likeCountTextView = itemView.findViewById(R.id.likeCountTextView);

            //add to fav btn
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    favItem coffeeItem = favItems.get(position);

                    if (coffeeItem.getFavStatus().equals("0")){
                        coffeeItem.setFavStatus("1");
                        favDB.insertintoDatabase(coffeeItem.getItemName(),coffeeItem.getImageResourse(),
                                coffeeItem.getKey_id(),coffeeItem.getFavStatus());
                        favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                    }
                    else {
                    coffeeItem.setFavStatus("0");
                        favDB.remove_fav(coffeeItem.getKey_id());
                        favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                        favBtn.setSelected(false);
                    }

                }
            });
        }


    }
}
