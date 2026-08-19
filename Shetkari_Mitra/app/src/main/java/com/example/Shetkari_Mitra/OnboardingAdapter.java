package com.example.Shetkari_Mitra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder> {

    private final List<OnboardingItem> items;

    public OnboardingAdapter(List<OnboardingItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_onboarding_slide, parent, false);
        return new OnboardingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        OnboardingItem item = items.get(position);
        holder.ivIllustration.setImageResource(item.getImageRes());
        holder.tvTitle.setText(item.getTitle());
        holder.tvMarathi.setText(item.getMarathiTitle());
        holder.tvDesc.setText(item.getDescription());
        holder.tvBadge.setText(item.getBadge());
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    static class OnboardingViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIllustration;
        TextView tvTitle, tvMarathi, tvDesc, tvBadge;

        public OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIllustration = itemView.findViewById(R.id.ivSlideIllustration);
            tvTitle = itemView.findViewById(R.id.tvSlideTitle);
            tvMarathi = itemView.findViewById(R.id.tvSlideMarathi);
            tvDesc = itemView.findViewById(R.id.tvSlideDesc);
            tvBadge = itemView.findViewById(R.id.tvSlideBadge);
        }
    }
}
