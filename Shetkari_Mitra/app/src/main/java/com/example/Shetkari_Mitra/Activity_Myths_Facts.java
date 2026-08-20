package com.example.Shetkari_Mitra;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class Activity_Myths_Facts extends AppCompatActivity {

    private static final String PREF_NAME = "shetkari_prefs";
    private static final String KEY_MYTHS_MARATHI = "myths_is_marathi";

    private LinearLayout layoutMythsContainer;
    private MaterialButton btnLangSwitch;
    private TextView tvMythsHeaderTitle, tvMythsHeaderSub;
    private boolean isMarathi = true;

    private static class MythItem {
        final String mythEn;
        final String factEn;
        final String mythMr;
        final String factMr;

        MythItem(String mythEn, String factEn, String mythMr, String factMr) {
            this.mythEn = mythEn;
            this.factEn = factEn;
            this.mythMr = mythMr;
            this.factMr = factMr;
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myths_facts);

        ImageButton btnGlassBack = findViewById(R.id.btnGlassBack);
        if (btnGlassBack != null) {
            btnGlassBack.setOnClickListener(v -> finish());
        }

        layoutMythsContainer = findViewById(R.id.layoutMythsContainer);
        btnLangSwitch = findViewById(R.id.btnLangSwitch);
        tvMythsHeaderTitle = findViewById(R.id.tvMythsHeaderTitle);
        tvMythsHeaderSub = findViewById(R.id.tvMythsHeaderSub);

        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        isMarathi = prefs.getBoolean(KEY_MYTHS_MARATHI, true);

        if (btnLangSwitch != null) {
            btnLangSwitch.setOnClickListener(v -> {
                isMarathi = !isMarathi;
                prefs.edit().putBoolean(KEY_MYTHS_MARATHI, isMarathi).apply();
                renderMyths();
            });
        }

        renderMyths();
    }

    private void renderMyths() {
        if (layoutMythsContainer == null) return;
        layoutMythsContainer.removeAllViews();

        if (btnLangSwitch != null) {
            btnLangSwitch.setText(isMarathi ? "English" : "मराठी");
        }
        if (tvMythsHeaderTitle != null) {
            tvMythsHeaderTitle.setText(isMarathi ? "सापांविषयी गैरसमज व वैज्ञानिक वास्तव" : "Snake Myths vs Scientific Facts");
        }
        if (tvMythsHeaderSub != null) {
            tvMythsHeaderSub.setText(isMarathi ? "अंधश्रद्धा निर्मूलन व सुरक्षितता जनजागृती" : "Scientific Awareness & Safety Education");
        }

        List<MythItem> items = getMythList();

        for (int i = 0; i < items.size(); i++) {
            MythItem item = items.get(i);
            String myth = isMarathi ? item.mythMr : item.mythEn;
            String fact = isMarathi ? item.factMr : item.factEn;

            MaterialCardView card = new MaterialCardView(this);
            card.setRadius(dpToPx(16));
            card.setCardElevation(0);
            card.setStrokeWidth(dpToPx(1));
            card.setStrokeColor(0xFFE2E8F0);
            card.setCardBackgroundColor(getResources().getColor(R.color.color_surface));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, dpToPx(6), 0, dpToPx(6));
            card.setLayoutParams(params);

            LinearLayout root = new LinearLayout(this);
            root.setOrientation(LinearLayout.VERTICAL);
            root.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(16));

            // Myth Header Box
            LinearLayout mythBox = new LinearLayout(this);
            mythBox.setOrientation(LinearLayout.VERTICAL);

            TextView tvMythBadge = new TextView(this);
            tvMythBadge.setText(isMarathi ? "गैरसमज (Myth #" + (i + 1) + ")" : "Myth #" + (i + 1));
            tvMythBadge.setTextSize(11);
            tvMythBadge.setTextColor(getResources().getColor(R.color.color_emergency));
            tvMythBadge.setTypeface(null, android.graphics.Typeface.BOLD);
            mythBox.addView(tvMythBadge);

            TextView tvMythText = new TextView(this);
            tvMythText.setText(myth);
            tvMythText.setTextSize(14);
            tvMythText.setTextColor(getResources().getColor(R.color.color_text_primary));
            tvMythText.setTypeface(null, android.graphics.Typeface.BOLD);
            tvMythText.setLineSpacing(dpToPx(2), 1.15f);
            mythBox.addView(tvMythText);

            root.addView(mythBox);

            // Divider line
            View divider = new View(this);
            divider.setBackgroundColor(0xFFF1F5F9);
            LinearLayout.LayoutParams divParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    dpToPx(1)
            );
            divParams.setMargins(0, dpToPx(10), 0, dpToPx(10));
            divider.setLayoutParams(divParams);
            root.addView(divider);

            // Fact Box
            LinearLayout factBox = new LinearLayout(this);
            factBox.setOrientation(LinearLayout.VERTICAL);

            TextView tvFactBadge = new TextView(this);
            tvFactBadge.setText(isMarathi ? "वैज्ञानिक वास्तव (Scientific Fact)" : "Scientific Fact");
            tvFactBadge.setTextSize(11);
            tvFactBadge.setTextColor(getResources().getColor(R.color.color_primary));
            tvFactBadge.setTypeface(null, android.graphics.Typeface.BOLD);
            factBox.addView(tvFactBadge);

            TextView tvFactText = new TextView(this);
            tvFactText.setText(fact);
            tvFactText.setTextSize(12);
            tvFactText.setTextColor(getResources().getColor(R.color.color_text_secondary));
            tvFactText.setLineSpacing(dpToPx(2), 1.25f);
            factBox.addView(tvFactText);

            root.addView(factBox);

            card.addView(root);
            layoutMythsContainer.addView(card);
        }
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    private List<MythItem> getMythList() {
        List<MythItem> list = new ArrayList<>();

        // 1. All snakes are venomous
        list.add(new MythItem(
                "All snakes are poisonous and will attack humans on sight.",
                "Over 80% of snakes found in Maharashtra are non-venomous and beneficial to farmers by controlling rodent pests. Snakes only bite in self-defense when accidentally stepped on or cornered.",
                "सर्वच साप विषारी असतात आणि माणसाला पाहताच चावतात.",
                "महाराष्ट्रातील ८०% पेक्षा जास्त साप पूर्णपणे बिनविषारी असतात आणि उंदरांचा नायनाट करून शेतकऱ्यांना मदत करतात. केवळ पाऊल पडल्यास किंवा जीवाला धोका वाटल्यासच साप स्वसंरक्षणार्थ दंश करतात."
        ));

        // 2. Snakes drink milk
        list.add(new MythItem(
                "Snakes drink milk on Nag Panchami and bless households.",
                "Snakes are strictly carnivorous cold-blooded reptiles. They cannot digest lactose or milk protein. Forcing them to drink milk causes dehydration, lung infections, and death.",
                "नागपंचमीला साप दूध पितात आणि आशीर्वाद देतात.",
                "साप मांसाहारी प्राणी आहेत. त्यांच्या शरीरात दूध पचवण्याची क्षमता नसते. उपाशी ठेवलेल्या सापाला जबरदस्तीने दूध पाजल्यास फुफ्फुसात संसर्ग होऊन सापाचा मृत्यू होतो."
        ));

        // 3. Snakes take revenge
        list.add(new MythItem(
                "If you hurt or kill a snake, it or its mate remembers your face and takes revenge.",
                "Snakes lack the cerebral cortex and memory capacity to recognize human faces or harbor feelings of vengeance. A snake found nearby is usually attracted by rodent scent, not revenge.",
                "सापाला मारल्यास त्याचा जोडीदार किंवा तो साप डोळ्यात फोटो काढून बदला घेतो.",
                "सापाचा मेंदू अतिशय प्राथमिक असतो. माणसाचा चेहरा लक्षात ठेवण्याची किंवा बदला घेण्याची क्षमता सापांमध्ये नसते. साप केवळ अन्न व उंदरांच्या शोधात फिरतात."
        ));

        // 4. Snake charmer Been music
        list.add(new MythItem(
                "Snakes dance and sway to the tunes of a snake charmer's Pungi/Been.",
                "Snakes have no external ear openings or eardrums and are completely deaf to airborne musical notes. The cobra merely tracks and mirrors the defensive motion of the charmer's moving instrument.",
                "गारुड्याच्या पुंगीच्या आवाजावर नाग डोलतो व नाचतो.",
                "सापाला बाह्य कान किंवा पडदा नसतो. हवेतील संगीताचा आवाज त्याला ऐकू येत नाही. गारुडी पुंगी हालवतो, त्या हालचालीकडे पाहून नाग स्वसंरक्षणासाठी फणा डोलवतो."
        ));

        // 5. Cutting, sucking venom, or tight tourniquet
        list.add(new MythItem(
                "Cutting the bite site, sucking venom with mouth, or tying a very tight cloth/rope saves lives.",
                "These practices are extremely dangerous and cause gangrene, severe arterial blockage, and severe tissue loss. The ONLY scientifically validated cure is reaching a hospital for Anti-Snake Venom (ASV).",
                "चावलेल्या जागी काप मारणे, तोंड लावून विष चोखणे किंवा घट्ट दोरी बांधल्याने जीव वाचतो.",
                "काप मारणे किंवा घट्ट आवळपट्टी बांधल्याने स्नायू सडतात आणि अवयव गमवावा लागतो. सर्पदंशावर एकमेव हमखास उपाय म्हणजे रुग्णालयात जाऊन प्रतिसर्पविष लस (ASV) घेणे."
        ));

        // 6. Nagmani and Ichhadhari snakes
        list.add(new MythItem(
                "100-year-old snakes turn into shape-shifters (Ichhadhari) or produce a shining Nagmani gem.",
                "Completely fictional myths popularized by folklore and movies. No reptile produces biological gems or alters its physical anatomy.",
                "१०० वर्षांचा जुना साप इच्छारूपी बनतो आणि त्याच्या डोक्यात नागमणी असतो.",
                "हा निव्वळ अंधश्रद्धेचा व सिनेमांचा भाग आहे. कोणत्याही सापाच्या डोक्यात मणी नसतो आणि साप रूप बदलू शकत नाही."
        ));

        // 7. Red Sand Boa (मांडूळ) double head and treasure
        list.add(new MythItem(
                "Two-headed Red Sand Boa snakes bring hidden gold, rain, and good fortune.",
                "The Red Sand Boa has a blunt, rounded tail evolved as natural camouflage to fool predators. It has only one head and zero supernatural properties.",
                "दोन तोंडी मांडूळ घरात ठेवल्यास पाऊस पडतो, गुप्तधन सापडते व पैसा येतो.",
                "मांडूळ सापाचे शेपूट बोथट असते जे तोंडासारखे दिसते, जेणेकरून शत्रूची दिशाभूल व्हावी. मांडूळ सापाला एकच तोंड असते आणि त्यात कोणतीही दैवी शक्ती नसते."
        ));

        // 8. Garlic, chemicals, or plants keep snakes away
        list.add(new MythItem(
                "Spreading garlic, turmeric, or planting snake plants permanently keeps snakes away from homes.",
                "Snakes use Jacobson's organ (chemosensory) and are not repelled by garlic or herbal plants. The only effective deterrent is keeping the surroundings free of trash, clutter, and rodent food sources.",
                "लसूण, हळद किंवा विशिष्ट झाडे लावल्याने घराभोवती साप येत नाहीत.",
                "सापांना लसूण किंवा वनस्पतींचा त्रास होत नाही. शेताभोवती व घराभोवती पालापाचोळा, कचरा व उंदीर नष्ट ठेवणे हाच सापांना दूर ठेवण्याचा खरा उपाय आहे."
        ));

        // 9. Traditional Sarpamani, Mantras & Ayurvedic stones
        list.add(new MythItem(
                "Mantras, babas, or applying a black 'Sarpamani' stone extracts venom from the wound.",
                "No mantra or porous stone can neutralize or extract venom once it enters circulation. Wasting the critical 1-hour golden window on quacks causes tragic preventable deaths.",
                "मंत्र-तंत्र, भगत किंवा काळा सर्पमणी चावलेल्या जागी लावल्यास विष उतरते.",
                "कोणताही मणी किंवा मंत्र रक्तातील विष काढू शकत नाही. भोंदूगिरीत वेळ वाया घालवल्याने रुग्णाचा जीव धोक्यात येतो. त्वरित शासकीय रुग्णालयात जाणे गरजेचे आहे."
        ));

        // 10. Dry Bites
        list.add(new MythItem(
                "If a person feels fine immediately after a venomous snakebite, medical care is not needed.",
                "Common Krait (मण्यार) venom causes zero pain or swelling initially, but leads to fatal respiratory paralysis within hours. Every snakebite victim must be monitored in a hospital for 24 hours.",
                "विषारी साप चावल्यानंतर काही त्रास झाला नाही तर दवाखान्यात जाण्याची गरज नाही.",
                "मण्यारसारखा घातक साप चावल्यास सुरुवातीला वेदना किंवा सूज येत नाही, परंतु काही तासांत श्वास बंद पडू शकतो. त्यामुळे कोणतीही लक्षणे नसली तरी २४ तास डॉक्टरांच्या देखरेखीखाली राहणे अनिवार्य आहे."
        ));

        return list;
    }
}
