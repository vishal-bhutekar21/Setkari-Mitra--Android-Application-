package com.example.Shetkari_Mitra;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetailActivity extends AppCompatActivity {

    private TextView detailDesc, detailTitle, detailLocation;
    private ImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        View btnGlassBack = findViewById(R.id.btnGlassBack);
        if (btnGlassBack != null) {
            btnGlassBack.setOnClickListener(v -> finish());
        }

        detailDesc = findViewById(R.id.detailDesc);
        detailTitle = findViewById(R.id.detailTitle);
        detailImage = findViewById(R.id.detailImage);
        detailLocation = findViewById(R.id.detailLocation);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String title = bundle.getString("Title", "Snake Details");
            detailTitle.setText(title);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(title);
            }

            detailDesc.setText(bundle.getString("Desc", ""));
            detailLocation.setText(bundle.getString("Location", ""));
            int imageRes = bundle.getInt("Image", 0);
            if (imageRes != 0) {
                detailImage.setImageResource(imageRes);
            }
        }
    }
}
