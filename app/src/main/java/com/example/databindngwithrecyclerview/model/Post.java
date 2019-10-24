package com.example.databindngwithrecyclerview.model;


import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.databinding.BindingAdapter;

//this class provide data to recycler view
//provides image view
public class Post {

    private static final String TAG = "Post";

    //vars
    private  String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view , String imagURL)
   {
       Glide.with(view.getContext()).load(imagURL).into(view);
   }
}
