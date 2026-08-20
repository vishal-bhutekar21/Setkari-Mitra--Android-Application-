package com.example.Shetkari_Mitra;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;

public class Acitivity_identify_snake extends AppCompatActivity {

    private ImageView imageView;
    private Uri imageUri;
    private View cardIdentificationResult, tvTapToCaptureHint, layoutEmptyPlaceholder;
    private TextView tvMatchHeader, tvConfidenceScore, tvResultCommonName, tvResultScientificName, tvResultTraits;
    private View btnWhatShouldIDo, btnFindRescuerActionResult, btnLearnMoreActionResult;

    private final ActivityResultLauncher<String> cameraPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    launchCamera();
                } else {
                    Toast.makeText(this, R.string.camera_permission_required, Toast.LENGTH_SHORT).show();
                }
            });

    private final ActivityResultLauncher<Intent> takePictureLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle extras = result.getData().getExtras();
                    if (extras != null) {
                        Bitmap imageBitmap = (Bitmap) extras.get("data");
                        if (imageBitmap != null) {
                            if (imageView != null) {
                                imageView.setVisibility(View.VISIBLE);
                                imageView.setImageBitmap(imageBitmap);
                            }
                            if (layoutEmptyPlaceholder != null) layoutEmptyPlaceholder.setVisibility(View.GONE);
                            saveBitmapToCacheAndSetUri(imageBitmap);
                            showSimulatedIdentificationResult();
                        }
                    }
                }
            });

    private final ActivityResultLauncher<String> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    imageUri = uri;
                    if (imageView != null) {
                        imageView.setVisibility(View.VISIBLE);
                        imageView.setImageURI(uri);
                    }
                    if (layoutEmptyPlaceholder != null) layoutEmptyPlaceholder.setVisibility(View.GONE);
                    showSimulatedIdentificationResult();
                }
            });

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acitivity_identify_snake);

        initViews();
        BottomNavigationHelper.setupBottomNavigation(this, findViewById(R.id.bottom_navigation), R.id.bottom_nav_hospitals);
    }

    private void initViews() {
        View btnGlassBack = findViewById(R.id.btnGlassBack);
        if (btnGlassBack != null) {
            btnGlassBack.setOnClickListener(v -> finish());
        }

        imageView = findViewById(R.id.imageView);
        layoutEmptyPlaceholder = findViewById(R.id.layoutEmptyPlaceholder);
        tvTapToCaptureHint = findViewById(R.id.tvTapToCaptureHint);
        cardIdentificationResult = findViewById(R.id.cardIdentificationResult);

        tvMatchHeader = findViewById(R.id.tvMatchHeader);
        tvConfidenceScore = findViewById(R.id.tvConfidenceScore);
        tvResultCommonName = findViewById(R.id.tvResultCommonName);
        tvResultScientificName = findViewById(R.id.tvResultScientificName);
        tvResultTraits = findViewById(R.id.tvResultTraits);

        btnWhatShouldIDo = findViewById(R.id.btnWhatShouldIDo);
        btnFindRescuerActionResult = findViewById(R.id.btnFindRescuerActionResult);
        btnLearnMoreActionResult = findViewById(R.id.btnLearnMoreActionResult);

        View captureButton = findViewById(R.id.cameraBtn);
        View pickButton = findViewById(R.id.galleryBtn);
        View sendOnWhatsApp = findViewById(R.id.sendBtn);

        if (captureButton != null) captureButton.setOnClickListener(v -> checkCameraPermissionAndLaunch());
        if (pickButton != null) pickButton.setOnClickListener(v -> pickImageLauncher.launch("image/*"));
        if (sendOnWhatsApp != null) sendOnWhatsApp.setOnClickListener(v -> sendImageOnWhatsApp());

        if (btnWhatShouldIDo != null) {
            btnWhatShouldIDo.setOnClickListener(v -> {
                Intent intent = new Intent(this, EmergencyActivity.class);
                startActivity(intent);
            });
        }

        if (btnFindRescuerActionResult != null) {
            btnFindRescuerActionResult.setOnClickListener(v -> {
                Intent intent = new Intent(this, RescuerDatabaseActivity.class);
                startActivity(intent);
            });
        }

        if (btnLearnMoreActionResult != null) {
            btnLearnMoreActionResult.setOnClickListener(v -> {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            });
        }
    }

    private void showSimulatedIdentificationResult() {
        if (tvTapToCaptureHint != null) tvTapToCaptureHint.setVisibility(View.GONE);
        if (cardIdentificationResult != null) {
            cardIdentificationResult.setVisibility(View.VISIBLE);
            if (tvMatchHeader != null) tvMatchHeader.setText(R.string.identify_possible_match);
            if (tvConfidenceScore != null) tvConfidenceScore.setText("Confidence: 82%");
            if (tvResultCommonName != null) tvResultCommonName.setText("Indian Rat Snake (धामण)");
            if (tvResultScientificName != null) tvResultScientificName.setText("Ptyas mucosa • Non-Venomous");
            if (tvResultTraits != null) {
                tvResultTraits.setText("Key features: Slender body, large eyes with round pupils, prominent black bars on lips and tail. Non-venomous and agricultural friend (rodent predator). Do not harm.");
            }
        }
    }

    private void checkCameraPermissionAndLaunch() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            launchCamera();
        } else {
            PermissionEducationDialog.newInstance(PermissionEducationDialog.PermissionType.CAMERA, () -> {
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA);
            }).show(getSupportFragmentManager(), "perm_camera");
        }
    }

    private void launchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            takePictureLauncher.launch(takePictureIntent);
        } else {
            Toast.makeText(this, R.string.no_camera_app, Toast.LENGTH_SHORT).show();
        }
    }

    private void saveBitmapToCacheAndSetUri(Bitmap bitmap) {
        try {
            File cachePath = new File(getCacheDir(), "images");
            if (!cachePath.exists()) cachePath.mkdirs();
            File imageFile = new File(cachePath, "captured_snake.jpg");
            FileOutputStream stream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
            stream.close();
            imageUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", imageFile);
        } catch (Exception ignored) {
        }
    }

    private void sendImageOnWhatsApp() {
        if (imageUri == null) {
            Toast.makeText(this, R.string.select_image_first, Toast.LENGTH_SHORT).show();
            return;
        }

        String phoneNumber = "918806136681";
        String message = "Emergency Snake Identification: Hello, I have encountered this snake in our farmland. Please help verify species and venom status.\n- Sent via Shetkari Mitra";

        try {
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setType("image/*");
            sendIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
            sendIntent.putExtra(Intent.EXTRA_TEXT, message);
            sendIntent.putExtra("jid", phoneNumber + "@s.whatsapp.net");
            sendIntent.setPackage("com.whatsapp");
            sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(sendIntent);
        } catch (Exception e) {
            Intent chooserIntent = new Intent(Intent.ACTION_SEND);
            chooserIntent.setType("image/*");
            chooserIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
            chooserIntent.putExtra(Intent.EXTRA_TEXT, message);
            chooserIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(chooserIntent, getString(R.string.send_to_expert)));
        }
    }
}