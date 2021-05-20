package com.janlishak.keepappworkouts.activities;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.janlishak.keepappworkouts.R;
import com.janlishak.keepappworkouts.services.api.ImgurRepository;
import com.janlishak.keepappworkouts.services.api.Link;
import java.io.File;

public class SecondActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMG = 123;
    private ImageView imageView;
    private ImgurRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        repository = ImgurRepository.getInstance();
        repository.searchForImage("RljrIhF").observe(this, new Observer<Link>() {
            @Override
            public void onChanged(Link link) {
                Log.i("Retrofit", link.toString());
                if(!link.isLoading()) Glide.with(getApplicationContext()).load(link.getLink()).into(imageView);
            }
        });
    }

    public void click(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            assert cursor != null;
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String mediaPath = cursor.getString(columnIndex);
            File file = new File(mediaPath);
//            repository.uploadImage(file);

        }else {
            Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }




}
