package com.example.Shetkari_Mitra;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<DataClass> dataList;
    private boolean isMarathi = false;

    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void setSearchList(List<DataClass> dataSearchList) {
        this.dataList = dataSearchList;
        notifyDataSetChanged();
    }

    public void setLanguage(boolean isMarathi) {
        this.isMarathi = isMarathi;
        notifyDataSetChanged();
    }

    public boolean isMarathi() {
        return isMarathi;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataClass item = dataList.get(position);

        holder.recImage.setImageResource(item.getImageRes());
        holder.recTitle.setText(item.getName(isMarathi));
        holder.recScientific.setText(item.getScientificName());
        holder.recDesc.setText(item.getDesc(isMarathi));
        holder.recLocation.setText(item.getHabitat(isMarathi));
        holder.recLang.setText(item.getVenomStatus(isMarathi));

        // Set venom badge background color based on danger level
        if (item.getVenomLevel() == 1) {
            holder.recLang.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.color_emergency));
            holder.recLang.setTextColor(ContextCompat.getColor(context, R.color.color_on_emergency));
        } else if (item.getVenomLevel() == 2) {
            holder.recLang.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.color_warning));
            holder.recLang.setTextColor(ContextCompat.getColor(context, R.color.color_white));
        } else {
            holder.recLang.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.color_primary));
            holder.recLang.setTextColor(ContextCompat.getColor(context, R.color.color_on_primary));
        }

        holder.recCard.setOnClickListener(v -> {
            int currentPos = holder.getAdapterPosition();
            if (currentPos != RecyclerView.NO_POSITION && currentPos < dataList.size()) {
                DataClass selectedSnake = dataList.get(currentPos);
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("snake_item", selectedSnake);
                intent.putExtra("is_marathi", isMarathi);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView recImage;
    TextView recTitle, recScientific, recDesc, recLang, recLocation;
    View recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recImage = itemView.findViewById(R.id.recImage);
        recTitle = itemView.findViewById(R.id.recTitle);
        recScientific = itemView.findViewById(R.id.recScientific);
        recDesc = itemView.findViewById(R.id.recDesc);
        recLang = itemView.findViewById(R.id.recLang);
        recCard = itemView.findViewById(R.id.recCard);
        recLocation = itemView.findViewById(R.id.recLocation);
    }
}