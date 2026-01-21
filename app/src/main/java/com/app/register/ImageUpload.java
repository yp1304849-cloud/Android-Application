package com.app.register;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class ImageUpload extends AppCompatActivity {

    ImageView imgPreview;
    Button btnSelect, btnUpload, btnBack;
    TextView tvStatus;

    Uri imageUri;
    final int PICK_IMAGE = 100;
    final long MAX_SIZE = 5 * 1024 * 1024; // 5MB

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        imgPreview = findViewById(R.id.imgPreview);
        btnSelect = findViewById(R.id.btnSelect);
        btnUpload = findViewById(R.id.btnUpload);
        btnBack = findViewById(R.id.btnBack);
        tvStatus = findViewById(R.id.tvStatus);

        btnSelect.setOnClickListener(v -> selectImage());

        btnUpload.setOnClickListener(v -> {
            if (imageUri != null) {
                tvStatus.setVisibility(TextView.VISIBLE);
            } else {
                Toast.makeText(this, "Please select image first", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(v -> {
            Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ImageUpload.this, Dashboard.class));
            finish();
        });
    }

    void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();

            if (getImageSize(imageUri) <= MAX_SIZE) {
                imgPreview.setImageURI(imageUri);
                tvStatus.setVisibility(TextView.GONE);
            } else {
                Toast.makeText(this, "Image must be under 5MB", Toast.LENGTH_LONG).show();
                imageUri = null;
            }
        }
    }

    long getImageSize(Uri uri) {
        String[] proj = {MediaStore.Images.Media.SIZE};
        Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            long size = cursor.getLong(0);
            cursor.close();
            return size;
        }
        return 0;
    }
}


