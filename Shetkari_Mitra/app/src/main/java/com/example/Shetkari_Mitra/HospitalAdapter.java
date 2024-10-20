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
import com.google.firebase.database.DatabaseReference;
import java.util.ArrayList;
import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<Hospital_Info> hospitalList;
    private List<Hospital_Info> filteredList;
    private DatabaseReference databaseReference;

    public HospitalAdapter(Context context, DatabaseReference databaseReference) {
        this.context = context;
        this.databaseReference = databaseReference;
        this.hospitalList = new ArrayList<>();
        this.filteredList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_hospital_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hospital_Info hospital = filteredList.get(position);
        holder.textViewHospitalName.setText("Hospital Name : "+hospital.getHospital_Name());
        holder.textViewContactPerson.setText("Contact person : "+hospital.getHospital_Contact_Person());
        holder.textViewHospitalNumber.setText("Hospital Contact : "+hospital.getHospital_Number());
        holder.textViewDistrictTaluka.setText("Hospital Address : "+hospital.getHospital_Address()+"\n"+ "Taluka : " + hospital.getHospital_Taluka()+"  District : " + hospital.getHostpital_District() );

        holder.buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle call action
                String phoneNumber = hospital.getHospital_Number();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String query = constraint.toString().toLowerCase().trim();
                if (query.isEmpty()) {
                    filteredList.clear();
                    filteredList.addAll(hospitalList);
                } else {
                    List<Hospital_Info> tempList = new ArrayList<>();
                    for (Hospital_Info hospital : hospitalList) {
                        if (hospital.getHospital_Name().toLowerCase().contains(query) ||
                                hospital.getHospital_Taluka().toLowerCase().contains(query) ||
                                hospital.getHostpital_District().toLowerCase().contains(query)) {
                            tempList.add(hospital);
                        }
                    }
                    filteredList.clear();
                    filteredList.addAll(tempList);
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                notifyDataSetChanged();
            }
        };
    }

    public void updateData(List<Hospital_Info> newList) {
        hospitalList.clear();
        hospitalList.addAll(newList);
        filteredList.clear();
        filteredList.addAll(newList);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewHospitalName, textViewContactPerson, textViewHospitalNumber, textViewDistrictTaluka;
        Button buttonCall;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHospitalName = itemView.findViewById(R.id.textViewHospitalName);
            textViewContactPerson = itemView.findViewById(R.id.textViewContactPerson);
            textViewHospitalNumber = itemView.findViewById(R.id.textViewHospitalNumber);
            textViewDistrictTaluka = itemView.findViewById(R.id.textViewDistrictTaluka);
            buttonCall = itemView.findViewById(R.id.buttonCall);
        }
    }
}
