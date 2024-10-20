package com.example.Shetkari_Mitra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EmergencyContactsAdapter extends RecyclerView.Adapter<EmergencyContactsAdapter.EmergencyContactViewHolder> {

    private Context context;
    private List<EmergencyContact> emergencyContactsList;
    private int selectedPosition = RecyclerView.NO_POSITION;

    public EmergencyContactsAdapter(Context context, List<EmergencyContact> emergencyContactsList) {
        this.context = context;
        this.emergencyContactsList = emergencyContactsList;
    }

    @NonNull
    @Override
    public EmergencyContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_emergency_contacts, parent, false);
        return new EmergencyContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmergencyContactViewHolder holder, int position) {
        EmergencyContact emergencyContact = emergencyContactsList.get(position);
        holder.bind(emergencyContact);

        // Highlight selected item
        holder.itemView.setSelected(selectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return emergencyContactsList.size();
    }

    public class EmergencyContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nameTextView, numberTextView;

        public EmergencyContactViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            numberTextView = itemView.findViewById(R.id.numberTextView);
            itemView.setOnClickListener(this);
        }

        public void bind(EmergencyContact emergencyContact) {
            nameTextView.setText(emergencyContact.getName());
            numberTextView.setText(emergencyContact.getNumber());
        }

        @Override
        public void onClick(View v) {
            selectedPosition = getAdapterPosition();
            notifyDataSetChanged(); // Update the view to reflect the change

            // You can also pass the selected position to your activity or fragment
            // through an interface listener if needed
        }
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }
}
