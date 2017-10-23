package com.clerkiechat.ui.chatscreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.clerkiechat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Pranav Bhoraskar
 */

public class PhotoViewActivity extends AppCompatActivity {

    @BindView(R.id.view_photo_image)
    ImageView viewPhotoImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            String photoUrl = getIntent().getStringExtra("image_url");
            Glide.with(this).load(photoUrl).placeholder(R.mipmap.no_image).fitCenter().
                    into(viewPhotoImage);
        }
    }
}