package com.example.Shetkari_Mitra;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RescuerAdapter extends RecyclerView.Adapter<RescuerAdapter.RescuerViewHolder> implements Filterable {

    private final List<Rescuer> rescuerList;
    private List<Rescuer> rescuerListFiltered;

    public RescuerAdapter(List<Rescuer> rescuerList) {
        this.rescuerList = rescuerList != null ? rescuerList : new ArrayList<>();
        this.rescuerListFiltered = new ArrayList<>(this.rescuerList);
    }

    @NonNull
    @Override
    public RescuerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rescuer, parent, false);
        return new RescuerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RescuerViewHolder holder, int position) {
        Rescuer rescuer = rescuerListFiltered.get(position);
        holder.textViewRescuerName.setText(rescuer.getName());
        holder.address.setText(rescuer.getAddress());
        holder.textViewMobile.setText(rescuer.getMobile());
        holder.textTalukaDistrict.setText(rescuer.getTaluka() + ", " + rescuer.getDistrict());

        holder.btnCallRescuer.setOnClickListener(v -> {
            String phoneNumber = rescuer.getMobile();
            if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber.trim()));
                v.getContext().startActivity(intent);
            } else {
                Toast.makeText(v.getContext(), R.string.phone_not_available, Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnSms.setOnClickListener(v -> {
            String phoneNumber = rescuer.getMobile();
            if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
                String message = "EMERGENCY: Snake spotted near my location! Please assist. Contact: " + rescuer.getTaluka();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:" + phoneNumber.trim()));
                intent.putExtra("sms_body", message);
                try {
                    v.getContext().startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), R.string.cannot_send_sms, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(v.getContext(), R.string.phone_not_available, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return rescuerListFiltered.size();
    }

    static class RescuerViewHolder extends RecyclerView.ViewHolder {
        TextView textViewRescuerName, address, textViewMobile, textTalukaDistrict;
        Button btnCallRescuer, btnSms;

        public RescuerViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRescuerName = itemView.findViewById(R.id.textViewRescuerName);
            address = itemView.findViewById(R.id.address);
            textViewMobile = itemView.findViewById(R.id.testviewformobile);
            textTalukaDistrict = itemView.findViewById(R.id.talukaanddistrict);
            btnCallRescuer = itemView.findViewById(R.id.btnCallRescuer);
            btnSms = itemView.findViewById(R.id.btnSms);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String query = constraint != null ? constraint.toString().toLowerCase().trim() : "";
                List<Rescuer> filtered = new ArrayList<>();

                if (query.isEmpty()) {
                    filtered.addAll(rescuerList);
                } else {
                    for (Rescuer r : rescuerList) {
                        if ((r.getName() != null && r.getName().toLowerCase().contains(query)) ||
                                (r.getTaluka() != null && r.getTaluka().toLowerCase().contains(query)) ||
                                (r.getDistrict() != null && r.getDistrict().toLowerCase().contains(query)) ||
                                (r.getAddress() != null && r.getAddress().toLowerCase().contains(query))) {
                            filtered.add(r);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filtered;
                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                rescuerListFiltered = (List<Rescuer>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}