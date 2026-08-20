package com.example.Shetkari_Mitra;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GovtPortalAdapter extends RecyclerView.Adapter<GovtPortalAdapter.PortalViewHolder> {

    private final Context context;
    private final List<GovtPortalItem> portalList;

    public GovtPortalAdapter(Context context, List<GovtPortalItem> portalList) {
        this.context = context;
        this.portalList = portalList;
    }

    @NonNull
    @Override
    public PortalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_govt_portal, parent, false);
        return new PortalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PortalViewHolder holder, int position) {
        GovtPortalItem item = portalList.get(position);
        holder.tvPortalTitle.setText(item.getTitle());
        holder.tvPortalMarathiTitle.setText(item.getMarathiTitle());
        holder.tvDepartment.setText(item.getDepartment());
        holder.tvDescription.setText(item.getDescription());

        holder.btnOpenPortal.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
            try {
                context.startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(context, "Cannot open portal", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnCallHelpline.setOnClickListener(v -> {
            if (item.getHelpline() != null && !item.getHelpline().isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + item.getHelpline()));
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Helpline unavailable", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return portalList.size();
    }

    static class PortalViewHolder extends RecyclerView.ViewHolder {
        TextView tvPortalTitle, tvPortalMarathiTitle, tvDepartment, tvDescription;
        Button btnOpenPortal, btnCallHelpline;

        public PortalViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPortalTitle = itemView.findViewById(R.id.tvPortalTitle);
            tvPortalMarathiTitle = itemView.findViewById(R.id.tvPortalMarathiTitle);
            tvDepartment = itemView.findViewById(R.id.tvDepartment);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            btnOpenPortal = itemView.findViewById(R.id.btnOpenPortal);
            btnCallHelpline = itemView.findViewById(R.id.btnCallHelpline);
        }
    }
}
