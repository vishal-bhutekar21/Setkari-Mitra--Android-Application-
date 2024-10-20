package com.example.Shetkari_Mitra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SnakeAdapter extends RecyclerView.Adapter<SnakeAdapter.SnakeViewHolder> {

    List<Snakeitem> snakeitemList;

    public SnakeAdapter(List<Snakeitem> snakeitemList) {
        this.snakeitemList = snakeitemList;
    }

    @NonNull
    @Override
    public SnakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_snake,parent,false);
        return new SnakeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SnakeViewHolder holder, int position) {
        Snakeitem snakeitem=snakeitemList.get(position);
        holder.Symtominfo.setText(snakeitem.getSymtomsName());
        holder.Symtominfo2.setText(snakeitem.getSnakeinformation());
        holder.snake_image.setImageResource(snakeitem.getSymtomsImage());

    }

    @Override
    public int getItemCount() {
        return snakeitemList.size();
    }

    public static class SnakeViewHolder extends RecyclerView.ViewHolder
     {
         ImageView snake_image;
         TextView Symtominfo;
         TextView Symtominfo2;

         public SnakeViewHolder(@NonNull View itemView) {
             super(itemView);
             snake_image=itemView.findViewById(R.id.imageView_symtomes);
             Symtominfo=itemView.findViewById(R.id.sy1);
             Symtominfo2=itemView.findViewById(R.id.sy2);
         }
     }

}
