package com.example.Shetkari_Mitra;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmergencyContactsAdapter extends RecyclerView.Adapter<EmergencyContactsAdapter.ViewHolder> {

    public interface OnContactActionListener {
        void onEdit(EmergencyContact contact);
        void onDelete(EmergencyContact contact);
    }

    private final Context context;
    private final List<EmergencyContact> contactList;
    private final OnContactActionListener listener;

    public EmergencyContactsAdapter(Context context, List<EmergencyContact> contactList, OnContactActionListener listener) {
        this.context = context;
        this.contactList = contactList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_emergency_contacts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EmergencyContact contact = contactList.get(position);
        holder.textViewName.setText(contact.getName());
        holder.textViewNumber.setText(contact.getNumber());

        holder.btnCall.setOnClickListener(v -> {
            String phoneNumber = contact.getNumber();
            if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber.trim()));
                context.startActivity(intent);
            } else {
                Toast.makeText(context, R.string.phone_not_available, Toast.LENGTH_SHORT).show();
            }
        });

        if (holder.btnEdit != null) {
            holder.btnEdit.setOnClickListener(v -> {
                if (listener != null) listener.onEdit(contact);
            });
        }

        if (holder.btnDelete != null) {
            holder.btnDelete.setOnClickListener(v -> {
                if (listener != null) listener.onDelete(contact);
            });
        }
    }

    @Override
    public int getItemCount() {
        return contactList != null ? contactList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewNumber;
        ImageButton btnCall;
        ImageButton btnEdit;
        ImageButton btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewEmergencyContactName);
            textViewNumber = itemView.findViewById(R.id.textViewEmergencyContactNumber);
            btnCall = itemView.findViewById(R.id.btnCallContact);
            btnEdit = itemView.findViewById(R.id.btnEditContact);
            btnDelete = itemView.findViewById(R.id.btnDeleteContact);
        }
    }
}
