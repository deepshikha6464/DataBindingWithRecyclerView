package com.example.databindngwithrecyclerview.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.example.databindngwithrecyclerview.R;
import com.example.databindngwithrecyclerview.databinding.ActivityMainBinding;
import com.example.databindngwithrecyclerview.model.Post;
import com.example.databindngwithrecyclerview.model.User;
import com.example.databindngwithrecyclerview.util.GridSpacingItemDecoration;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements PostAdapter.PostsAdapterListener{

    private User user;
    ActivityMainBinding binding;
    MyClickHandlers handlers;
    RecyclerView recyclerView;
    PostAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        binding  = DataBindingUtil.setContentView(this,R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        handlers = new MyClickHandlers(this);

        renderProfile();

        initRecyclerView();
    }

    /**
     * Renders RecyclerView with Grid Images in 3 columns
     */
    private void initRecyclerView() {
        recyclerView = binding.content.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(4), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        mAdapter = new PostAdapter(getPosts(), this);
        recyclerView.setAdapter(mAdapter);
    }
    private void renderProfile() {
        user = new User();
        user.setName("David Attenborough");
        user.setEmail("david@natgeo.com");
        user.setProfileImage("https://api.androidhive.info/images/nature/david.jpg");
        user.setAbout("Naturalist");

        // ObservableField doesn't have setter method, instead will
        // be called using set() method
        user.numberOfPosts.set(3400L);
        user.numberOfFollowers.set(3050890L);
        user.numberOfFollowing.set(150L);


        // display user
        binding.setUser(user);

        // assign click handlers
        binding.content.setHandlers(handlers);
    }
    private ArrayList<Post> getPosts() {
        ArrayList<Post> posts = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Post post = new Post();
            post.setImageUrl("https://api.androidhive.info/images/nature/" + i + ".jpg");

            posts.add(post);
        }

        return posts;
    }

    @Override
    public void onPostClicked(Post post) {
        Toast.makeText(getApplicationContext(), "Post clicked! " + post.getImageUrl(), Toast.LENGTH_SHORT).show();
    }


    public class MyClickHandlers {
        Context context;

        public MyClickHandlers(Context context) {
            this.context = context;
        }

        public void onProfileFabClicked(View view) {
            user.setName("Sir David Attenborough");
            user.setProfileImage("https://api.androidhive.info/images/nature/david1.jpg");

            // updating ObservableField
            user.numberOfPosts.set(5500L);
            user.numberOfFollowers.set(5050890L);
            user.numberOfFollowing.set(180L);
        }

        public boolean onProfileImageLongPressed(View view) {
            Toast.makeText(getApplicationContext(), "Profile image long pressed!", Toast.LENGTH_LONG).show();
            return false;
        }


        public void onFollowersClicked(View view) {
            Toast.makeText(context, "Followers is clicked!", Toast.LENGTH_SHORT).show();
        }

        public void onFollowingClicked(View view) {
            Toast.makeText(context, "Following is clicked!", Toast.LENGTH_SHORT).show();
        }

        public void onPostsClicked(View view) {
            Toast.makeText(context, "Posts is clicked!", Toast.LENGTH_SHORT).show();
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}

