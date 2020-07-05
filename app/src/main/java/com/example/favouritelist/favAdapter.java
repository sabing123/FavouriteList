package com.example.favouritelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class favAdapter extends RecyclerView.Adapter<favAdapter.ViewHolder> {

    private List<FavouriteItem> favItems;
    private Context context;
    private favDB favDB;

    public favAdapter(List<FavouriteItem> favItems, Context context) {
        this.context = context;
        this.favItems = favItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fave_item, parent, false);
        favDB = new favDB(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.favTextView.setText(favItems.get(position).getItemName());
        holder.favImageView.setImageResource(favItems.get(position).getImageResourse());
    }

    @Override
    public int getItemCount() {
        return favItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView favTextView;
        Button favBtn;
        ImageView favImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            favTextView = itemView.findViewById(R.id.favTextView);
            favBtn = itemView.findViewById(R.id.favBtn);
            favImageView = itemView.findViewById(R.id.favImageView);

            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final FavouriteItem favItem = favItems.get(position);
                    favDB.remove_fav(favItem.getKey_id());
                    removeItem(position);

                }
            });
        }
    }

    private void removeItem(int position) {
        favItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,favItems.size());
    }
}
