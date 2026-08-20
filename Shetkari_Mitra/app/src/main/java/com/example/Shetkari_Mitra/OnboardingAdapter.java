package com.example.Shetkari_Mitra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder> {

    public interface OnLanguageSelectedListener {
        void onLanguageSelected(String langCode);
    }

    private final List<OnboardingItem> items;
    private final OnLanguageSelectedListener languageListener;
    private String selectedLanguage;

    public OnboardingAdapter(List<OnboardingItem> items, String currentLang, OnLanguageSelectedListener listener) {
        this.items = items;
        this.selectedLanguage = currentLang;
        this.languageListener = listener;
    }

    public void updateSelectedLanguage(String lang) {
        this.selectedLanguage = lang;
        notifyDataSetChanged();
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
        Context ctx = holder.itemView.getContext();

        holder.ivIllustration.setImageResource(item.getImageRes());
        holder.tvTitle.setText(item.getTitle());
        holder.tvMarathi.setText(item.getMarathiTitle());
        holder.tvDesc.setText(item.getDescription());
        holder.tvBadge.setText(item.getBadge());

        if (item.isLanguageSelector()) {
            holder.layoutLanguagePicker.setVisibility(View.VISIBLE);
            
            highlightButton(ctx, holder.btnLangEnglish, LocaleHelper.LANGUAGE_ENGLISH.equals(selectedLanguage));
            highlightButton(ctx, holder.btnLangMarathi, LocaleHelper.LANGUAGE_MARATHI.equals(selectedLanguage));
            highlightButton(ctx, holder.btnLangHindi, LocaleHelper.LANGUAGE_HINDI.equals(selectedLanguage));

            holder.btnLangEnglish.setOnClickListener(v -> {
                selectedLanguage = LocaleHelper.LANGUAGE_ENGLISH;
                if (languageListener != null) languageListener.onLanguageSelected(LocaleHelper.LANGUAGE_ENGLISH);
            });
            holder.btnLangMarathi.setOnClickListener(v -> {
                selectedLanguage = LocaleHelper.LANGUAGE_MARATHI;
                if (languageListener != null) languageListener.onLanguageSelected(LocaleHelper.LANGUAGE_MARATHI);
            });
            holder.btnLangHindi.setOnClickListener(v -> {
                selectedLanguage = LocaleHelper.LANGUAGE_HINDI;
                if (languageListener != null) languageListener.onLanguageSelected(LocaleHelper.LANGUAGE_HINDI);
            });
        } else {
            holder.layoutLanguagePicker.setVisibility(View.GONE);
        }
    }

    private void highlightButton(Context ctx, MaterialButton btn, boolean isSelected) {
        if (isSelected) {
            btn.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color_primary));
            btn.setTextColor(ContextCompat.getColor(ctx, R.color.color_on_primary));
            btn.setStrokeWidth(0);
        } else {
            btn.setBackgroundColor(ContextCompat.getColor(ctx, android.R.color.transparent));
            btn.setTextColor(ContextCompat.getColor(ctx, R.color.color_text_primary));
            btn.setStrokeWidth(2);
            btn.setStrokeColor(ContextCompat.getColorStateList(ctx, R.color.color_border));
        }
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    static class OnboardingViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIllustration;
        TextView tvTitle, tvMarathi, tvDesc, tvBadge;
        LinearLayout layoutLanguagePicker;
        MaterialButton btnLangEnglish, btnLangMarathi, btnLangHindi;

        public OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIllustration = itemView.findViewById(R.id.ivSlideIllustration);
            tvTitle = itemView.findViewById(R.id.tvSlideTitle);
            tvMarathi = itemView.findViewById(R.id.tvSlideMarathi);
            tvDesc = itemView.findViewById(R.id.tvSlideDesc);
            tvBadge = itemView.findViewById(R.id.tvSlideBadge);
            layoutLanguagePicker = itemView.findViewById(R.id.layoutLanguagePicker);
            btnLangEnglish = itemView.findViewById(R.id.btnLangEnglish);
            btnLangMarathi = itemView.findViewById(R.id.btnLangMarathi);
            btnLangHindi = itemView.findViewById(R.id.btnLangHindi);
        }
    }
}
