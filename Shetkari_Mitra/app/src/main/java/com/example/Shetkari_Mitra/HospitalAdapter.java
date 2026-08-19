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

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder> implements Filterable {

    private final Context context;
    private final List<Hospital_Info> hospitalList;
    private List<Hospital_Info> hospitalListFiltered;

    public HospitalAdapter(Context context, List<Hospital_Info> hospitalList) {
        this.context = context;
        this.hospitalList = hospitalList != null ? hospitalList : new ArrayList<>();
        this.hospitalListFiltered = new ArrayList<>(this.hospitalList);
    }

    public void updateData(List<Hospital_Info> newHospitals) {
        this.hospitalList.clear();
        if (newHospitals != null) {
            this.hospitalList.addAll(newHospitals);
        }
        this.hospitalListFiltered = new ArrayList<>(this.hospitalList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_hospital_list_item, parent, false);
        return new HospitalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalViewHolder holder, int position) {
        Hospital_Info hospital = hospitalListFiltered.get(position);

        holder.tvHospitalName.setText(hospital.getHospitalName());
        holder.tvContactPerson.setText(hospital.getContactPerson());
        holder.tvHospitalNumber.setText(hospital.getHospitalNumber());
        holder.tvTalukaDistrict.setText(hospital.getTaluka() + ", " + hospital.getDistrict());
        holder.tvAddress.setText(hospital.getAddress());

        holder.btnCall.setOnClickListener(v -> {
            String phoneNumber = hospital.getHospitalNumber();
            if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber.trim()));
                context.startActivity(intent);
            } else {
                Toast.makeText(context, R.string.phone_not_available, Toast.LENGTH_SHORT).show();
            }
        });

        if (holder.btnMap != null) {
            holder.btnMap.setOnClickListener(v -> {
                Intent intent = new Intent(context, MapsActivity.class);
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return hospitalListFiltered.size();
    }

    public static class HospitalViewHolder extends RecyclerView.ViewHolder {
        TextView tvHospitalName, tvContactPerson, tvHospitalNumber, tvTalukaDistrict, tvAddress;
        Button btnCall, btnMap;

        public HospitalViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHospitalName = itemView.findViewById(R.id.hospitalNameTextView);
            tvContactPerson = itemView.findViewById(R.id.contactPersonTextView);
            tvHospitalNumber = itemView.findViewById(R.id.hospitalNumberTextView);
            tvTalukaDistrict = itemView.findViewById(R.id.talukaDistrictTextView);
            tvAddress = itemView.findViewById(R.id.addressTextView);
            btnCall = itemView.findViewById(R.id.btnCallHospital);
            btnMap = itemView.findViewById(R.id.btnViewOnMap);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String query = constraint != null ? constraint.toString().toLowerCase().trim() : "";
                List<Hospital_Info> filtered = new ArrayList<>();

                if (query.isEmpty()) {
                    filtered.addAll(hospitalList);
                } else {
                    for (Hospital_Info h : hospitalList) {
                        if ((h.getHospitalName() != null && h.getHospitalName().toLowerCase().contains(query)) ||
                                (h.getTaluka() != null && h.getTaluka().toLowerCase().contains(query)) ||
                                (h.getDistrict() != null && h.getDistrict().toLowerCase().contains(query)) ||
                                (h.getAddress() != null && h.getAddress().toLowerCase().contains(query))) {
                            filtered.add(h);
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
                hospitalListFiltered = (List<Hospital_Info>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
