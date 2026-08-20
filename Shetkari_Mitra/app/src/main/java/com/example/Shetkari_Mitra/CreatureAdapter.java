package com.example.Shetkari_Mitra;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CreatureAdapter extends RecyclerView.Adapter<CreatureAdapter.CreatureViewHolder> implements Filterable {

    private final Context context;
    private final List<HarmfulCreature> fullList;
    private List<HarmfulCreature> filteredList;
    private boolean isMarathiMode = true;
    private int lastAnimatedPosition = -1;

    public CreatureAdapter(Context context, List<HarmfulCreature> list) {
        this.context = context;
        this.fullList = new ArrayList<>(list);
        this.filteredList = new ArrayList<>(list);
    }

    public void setMarathiMode(boolean marathiMode) {
        this.isMarathiMode = marathiMode;
        notifyDataSetChanged();
    }

    public boolean isMarathiMode() {
        return isMarathiMode;
    }

    @NonNull
    @Override
    public CreatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_harmful_creature, parent, false);
        return new CreatureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreatureViewHolder holder, int position) {
        HarmfulCreature c = filteredList.get(position);

        holder.ivCreaturePhoto.setImageResource(c.getImageResId());

        if (isMarathiMode) {
            holder.tvPrimaryName.setText(c.getNameMr());
            holder.tvSecondaryName.setText(c.getNameEn());
            holder.tvScientificName.setText(c.getScientificName() + " • " + c.getCategory() + " (" + c.getDangerLevel() + ")");
            
            holder.tvToxicityLabel.setText("विषारी स्वरूप व तीव्रता");
            holder.tvToxicityInfo.setText(c.getToxicityTypeMr());

            holder.tvIdentificationLabel.setText("शारीरिक ओळख खुणा");
            holder.tvIdentification.setText(c.getIdentificationMr());

            holder.tvHabitatLabel.setText("शेतातील संभाव्य ठिकाणे व धोका");
            holder.tvHabitat.setText(c.getHabitatMr());

            holder.tvFirstAidLabel.setText("प्रथमोपचार व तातडीचे उपाय");
            holder.tvFirstAid.setText(c.getFirstAidMr());

            holder.tvPreventionLabel.setText("शेतकरी दक्षता व खबरदारी");
            holder.tvPrevention.setText(c.getPreventionMr());
        } else {
            holder.tvPrimaryName.setText(c.getNameEn());
            holder.tvSecondaryName.setText(c.getNameMr());
            holder.tvScientificName.setText(c.getScientificName() + " • " + c.getCategory() + " (" + c.getDangerLevel() + ")");

            holder.tvToxicityLabel.setText("Venom & Toxicity");
            holder.tvToxicityInfo.setText(c.getToxicityTypeEn());

            holder.tvIdentificationLabel.setText("Physical Markers");
            holder.tvIdentification.setText(c.getIdentificationEn());

            holder.tvHabitatLabel.setText("Farm Habitats & Risk Areas");
            holder.tvHabitat.setText(c.getHabitatEn());

            holder.tvFirstAidLabel.setText("First Aid Protocol");
            holder.tvFirstAid.setText(c.getFirstAidEn());

            holder.tvPreventionLabel.setText("Prevention Guide");
            holder.tvPrevention.setText(c.getPreventionEn());
        }

        // Tactile Spring Bounce on tap
        holder.itemView.setOnClickListener(v -> {
            PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.97f, 1.01f, 1.0f);
            PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.97f, 1.01f, 1.0f);
            ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v, scaleX, scaleY);
            animator.setDuration(260);
            animator.setInterpolator(new OvershootInterpolator(1.6f));
            animator.start();
        });

        // Cascading smooth entrance animation
        if (position > lastAnimatedPosition) {
            holder.itemView.setAlpha(0f);
            holder.itemView.setTranslationY(30f);

            holder.itemView.animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setDuration(350)
                    .setStartDelay(position * 50L)
                    .setInterpolator(new DecelerateInterpolator(1.3f))
                    .start();

            lastAnimatedPosition = position;
        }
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
                String query = constraint == null ? "" : constraint.toString().toLowerCase().trim();
                List<HarmfulCreature> result = new ArrayList<>();
                if (query.isEmpty()) {
                    result.addAll(fullList);
                } else {
                    for (HarmfulCreature c : fullList) {
                        if (c.getNameEn().toLowerCase().contains(query) ||
                                c.getNameMr().toLowerCase().contains(query) ||
                                c.getScientificName().toLowerCase().contains(query) ||
                                c.getCategory().toLowerCase().contains(query) ||
                                c.getHabitatEn().toLowerCase().contains(query) ||
                                c.getHabitatMr().toLowerCase().contains(query)) {
                            result.add(c);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = result;
                return filterResults;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (List<HarmfulCreature>) results.values;
                lastAnimatedPosition = -1;
                notifyDataSetChanged();
            }
        };
    }

    public static class CreatureViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCreaturePhoto;
        TextView tvPrimaryName, tvSecondaryName, tvScientificName;
        TextView tvToxicityLabel, tvToxicityInfo;
        TextView tvIdentificationLabel, tvIdentification;
        TextView tvHabitatLabel, tvHabitat;
        TextView tvFirstAidLabel, tvFirstAid;
        TextView tvPreventionLabel, tvPrevention;

        public CreatureViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCreaturePhoto = itemView.findViewById(R.id.ivCreaturePhoto);
            tvPrimaryName = itemView.findViewById(R.id.tvPrimaryName);
            tvSecondaryName = itemView.findViewById(R.id.tvSecondaryName);
            tvScientificName = itemView.findViewById(R.id.tvScientificName);
            tvToxicityLabel = itemView.findViewById(R.id.tvToxicityLabel);
            tvToxicityInfo = itemView.findViewById(R.id.tvToxicityInfo);
            tvIdentificationLabel = itemView.findViewById(R.id.tvIdentificationLabel);
            tvIdentification = itemView.findViewById(R.id.tvIdentification);
            tvHabitatLabel = itemView.findViewById(R.id.tvHabitatLabel);
            tvHabitat = itemView.findViewById(R.id.tvHabitat);
            tvFirstAidLabel = itemView.findViewById(R.id.tvFirstAidLabel);
            tvFirstAid = itemView.findViewById(R.id.tvFirstAid);
            tvPreventionLabel = itemView.findViewById(R.id.tvPreventionLabel);
            tvPrevention = itemView.findViewById(R.id.tvPrevention);
        }
    }
}
