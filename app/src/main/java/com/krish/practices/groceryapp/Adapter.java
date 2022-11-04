package com.krish.practices.groceryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {

    private final RecyclerviewInterface recyclerviewInterface;
    Context context;
    ArrayList<User_Fruit> userArrayList;
    private Object Glide;

    public Adapter(Context context, ArrayList<User_Fruit> userArrayList, RecyclerviewInterface recyclerviewInterface) {
        this.context = context;
        this.userArrayList = userArrayList;
        this.recyclerviewInterface = recyclerviewInterface;
    }

    @NonNull
    @Override
    public Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new Adapter.viewHolder(v,recyclerviewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.viewHolder holder, int position) {
        User_Fruit user_fruit = userArrayList.get(position);
        holder.Fruit_Name.setText(user_fruit.Fruit_Name);
        holder.Quantity.setText(user_fruit.Quantity);
        holder.Price.setText(user_fruit.Price);

        String Imageuri=null;
        Imageuri=user_fruit.getImage();
        Picasso.get().load(Imageuri).into(holder.Image);
    }

    @Override
    public int getItemCount() {
        return userArrayList.size() ;
    }


    public static class viewHolder extends RecyclerView.ViewHolder {

        TextView Fruit_Name,Price,Quantity;
        ImageView Image;
        public viewHolder(@NonNull View itemView,RecyclerviewInterface recyclerviewInterface) {
            super(itemView);
            Fruit_Name = itemView.findViewById(R.id.name);
            Price = itemView.findViewById(R.id.price);
            Quantity = itemView.findViewById(R.id.quantity);
            Image = (ImageView) itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerviewInterface != null ){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            recyclerviewInterface.onItemClick(pos);
                        }
                    }
//                    Intent intent = new Intent(context,Main2Activity.class);
//                    intent.putExtra("Image", (Parcelable) Image);

                }
            });
        }
    }
}
