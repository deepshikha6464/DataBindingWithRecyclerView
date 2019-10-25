package com.example.databindngwithrecyclerview.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.databindngwithrecyclerview.R;
import com.example.databindngwithrecyclerview.databinding.PostRowItemBinding;
import com.example.databindngwithrecyclerview.model.Post;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private static final String TAG = "PostAdapter";


    //vars
    private List<Post> postList;
    private LayoutInflater layoutInflater;
    private PostsAdapterListener listener;

    public PostAdapter(List<Post> postList, PostsAdapterListener listener) {
        this.postList = postList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        PostRowItemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.post_row_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
        holder.binding.setPost(postList.get(position));


        holder.binding.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onPostClicked(postList.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private final PostRowItemBinding binding;


        public MyViewHolder(PostRowItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

    public interface PostsAdapterListener{
        void onPostClicked(Post post);
    }
}
