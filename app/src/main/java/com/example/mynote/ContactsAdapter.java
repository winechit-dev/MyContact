package com.example.mynote;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mynote.Model.Contact;

import java.util.List;

import static com.example.mynote.R.color.colorPrimary;
import static com.example.mynote.R.color.colorPrimaryDark;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private OnLongPress onLongPress;
    private OnItemClick itemClick;
    private List<Contact> contactList;
    Context context;

    public ContactsAdapter(Context context, List<Contact> contactList, OnItemClick itemClick, OnLongPress onLongPress){

        this.contactList = contactList;
        this.context     = context;
        this.itemClick   = itemClick;
        this.onLongPress = onLongPress;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.contact,viewGroup,false);

        return new ViewHolder(view,itemClick,onLongPress);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.name.setText(contactList.get(position).getName());
            holder.phone.setText(contactList.get(position).getPhone());
        Glide.with(context).load(R.drawable.cartoon_profile).into(holder.profile);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name , phone;
        ImageView profile, checked;

        public ViewHolder(@NonNull final View itemView, final OnItemClick itemClick, final OnLongPress onLongPress) {
            super(itemView);
            name    = itemView.findViewById(R.id.name);
            phone   = itemView.findViewById(R.id.phone);
            profile = itemView.findViewById(R.id.profile);
            checked = itemView.findViewById(R.id.checked);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClick.onItemClicked(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    onLongPress.onLongPress(getAdapterPosition());
                   if ((checked.getVisibility() == View.VISIBLE)){
                       checked.setVisibility(View.INVISIBLE);
                       itemView.getBackground();


                   }else {checked.setVisibility(View.VISIBLE); }



                   return true; }
            });

        }



    }

    public interface OnLongPress{
        void onLongPress(int id);
    }

   public interface OnItemClick{
        void onItemClicked(int id);
    }

}
