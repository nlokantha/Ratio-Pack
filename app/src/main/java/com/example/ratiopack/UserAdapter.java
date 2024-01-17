package com.example.ratiopack;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ratiopack.RoomDatabase.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.myViewHolder>{

    List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row,parent,false);
        return new myViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.tv_color.setText(userList.get(position).getColor());
        holder.tv_size.setText(userList.get(position).getSize());
        holder.tv_quantity.setText(userList.get(position).getQuantity());
        holder.tv_barcode.setText(userList.get(position).getBarCode());

    }
    @Override
    public int getItemCount() {
        return userList.size();
    }
    class myViewHolder extends RecyclerView.ViewHolder {

        TextView tv_color,tv_size,tv_quantity,tv_barcode;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_color=itemView.findViewById(R.id.tv_color);
            tv_size=itemView.findViewById(R.id.tv_size);
            tv_quantity=itemView.findViewById(R.id.tv_quantity);
            tv_barcode=itemView.findViewById(R.id.tv_barcode);
        }
    }
}
