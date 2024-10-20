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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Acitivity_identify_snake extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;

    private static final int REQUEST_CAMERA_PERMISSION = 101;
    private ImageView imageView;
    private Uri imageUri;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acitivity_identify_snake);

        imageView = findViewById(R.id.imageView);
        Button captureButton = findViewById(R.id.cameraBtn);
        Button pickButton = findViewById(R.id.galleryBtn);
        Button sendonwhatsapp = findViewById(R.id.sendBtn);

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        pickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        sendonwhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendImageOnWhatsApp();
            }
        });
    }

    private void sendImageOnWhatsApp() {
        if (imageUri != null) {
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
            sendIntent.setType("image/*");

            // Specify the fixed WhatsApp number
            String phoneNumber = "8806136681"; // Replace with the desired WhatsApp number

            // Set the WhatsApp number with country code
            String whatsappNumber = "91" + phoneNumber; // Add the country code if required

            // Set the WhatsApp chat URL with the specified number
            sendIntent.putExtra("jid", whatsappNumber + "@s.whatsapp.net");

            // Set the package to WhatsApp
            sendIntent.setPackage("com.whatsapp");

            // Start the specific activity
            startActivity(sendIntent);

        }
        else {
            Toast.makeText(this,"Select Image first",Toast.LENGTH_SHORT).show();

        }
    }

    private void dispatchTakePictureIntent() {
        // Check if the Camera permission has been granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted. Check if we should show an explanation.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                // You can use a dialog or a snackbar to show this explanation according to your design
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            }
        } else {
            // Permission has already been granted, proceed with the camera
            startCamera();
        }
    }


    private void startCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted, yay! Do the
                // camera-related task you need to do.
                startCamera();
            } else {
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
                // You can also alert the user that the feature is unavailable because the permission is not granted.
            }
        }}


    private void openGallery() {
        Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhotoIntent, REQUEST_IMAGE_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imageBitmap);
                // Save the captured image to a file
                imageUri = getImageUri(imageBitmap);
            } else if (requestCode == REQUEST_IMAGE_PICK) {
                imageUri = data.getData();
                imageView.setImageURI(imageUri);
            }
        }
    }

    private Uri getImageUri(Bitmap bitmap) {
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Image", null);
        return Uri.parse(path);
    }
}