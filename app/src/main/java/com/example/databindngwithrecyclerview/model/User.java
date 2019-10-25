package com.example.databindngwithrecyclerview.model;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.databinding.library.baseAdapters.BR;

public class User extends BaseObservable {

    private static final String TAG = "User";

   //vars
    private String name, email, profileImage, about, mkite;

    public ObservableField<Long> numberOfFollowers = new ObservableField<>();
    public ObservableField<Long> numberOfPosts = new ObservableField<>();
    public ObservableField<Long> numberOfFollowing = new ObservableField<>();


    //constructor
    public User(String name, String email, String profileImage, String about) {
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
        this.about = about;
    }

    public User() {
    }




@Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
      notifyPropertyChanged(BR.name);
    }
@Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
       notifyPropertyChanged(BR.email);

    }
    @Bindable
    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
        notifyPropertyChanged(BR.profileImage);
    }
@Bindable
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
        notifyPropertyChanged(BR.about);
    }

    @BindingAdapter({"android:profileImage"})
    public  static  void loadImage(ImageView view , String url){
        Glide.with(view.getContext()).load(url).apply(RequestOptions.circleCropTransform()).into(view);
    }



    //observable field directly updating ui when data change
    public ObservableField<Long> getNumberOfFollowers() {
        return numberOfFollowers;
    }

    public ObservableField<Long> getNumberOfPosts() {
        return numberOfPosts;
    }

    public ObservableField<Long> getNumberOfFollowing() {
        return numberOfFollowing;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", about='" + about + '\'' +
                '}';
    }
  }
