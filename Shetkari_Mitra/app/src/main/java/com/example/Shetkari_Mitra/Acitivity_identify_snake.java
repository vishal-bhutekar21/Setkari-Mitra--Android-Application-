package com.example.Shetkari_Mitra;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Acitivity_identify_snake extends AppCompatActivity {

    private ImageView imageView;
    private Uri imageUri;

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
                            imageView.setImageBitmap(imageBitmap);
                            saveBitmapToCacheAndSetUri(imageBitmap);
                        }
                    }
                }
            });

    private final ActivityResultLauncher<String> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    imageUri = uri;
                    imageView.setImageURI(uri);
                }
            });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acitivity_identify_snake);

        View btnGlassBack = findViewById(R.id.btnGlassBack);
        if (btnGlassBack != null) {
            btnGlassBack.setOnClickListener(v -> finish());
        }

        imageView = findViewById(R.id.imageView);
        Button captureButton = findViewById(R.id.cameraBtn);
        Button pickButton = findViewById(R.id.galleryBtn);
        Button sendOnWhatsApp = findViewById(R.id.sendBtn);

        captureButton.setOnClickListener(v -> checkCameraPermissionAndLaunch());
        pickButton.setOnClickListener(v -> pickImageLauncher.launch("image/*"));
        sendOnWhatsApp.setOnClickListener(v -> sendImageOnWhatsApp());
    }

    private void checkCameraPermissionAndLaunch() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            launchCamera();
        } else {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA);
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
        } catch (Exception e) {
            // Fallback
        }
    }

    private void sendImageOnWhatsApp() {
        if (imageUri == null) {
            Toast.makeText(this, R.string.select_image_first, Toast.LENGTH_SHORT).show();
            return;
        }

        // WhatsApp rescuer contact for Jalna / Maharashtra region
        String phoneNumber = "918806136681";
        String message = "Emergency Snake Identification: Hello, I have encountered this snake. Please help identify if it is venomous.";

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
            // If WhatsApp package direct target fails, open general image share chooser
            Intent chooserIntent = new Intent(Intent.ACTION_SEND);
            chooserIntent.setType("image/*");
            chooserIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
            chooserIntent.putExtra(Intent.EXTRA_TEXT, message);
            chooserIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(chooserIntent, getString(R.string.send_to_expert)));
        }
    }
}