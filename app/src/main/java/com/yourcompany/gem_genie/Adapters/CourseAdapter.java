package com.yourcompany.gem_genie.Adapters;

import android.content.Context;
import static android.content.ContentValues.TAG;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yourcompany.gem_genie.Activities.CourseDetActivity;
import com.yourcompany.gem_genie.Activities.SingleItemActivity;
import com.yourcompany.gem_genie.Models.CourseModel;
import com.yourcompany.gem_genie.R;
import com.yourcompany.gem_genie.databinding.CourseItemBinding;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView .Adapter<CourseAdapter.holder>{

    ArrayList<CourseModel> courseModelArrayList;
    Context context;

    public CourseAdapter(ArrayList<CourseModel> courseModelArrayList, Context context) {
        this.courseModelArrayList = courseModelArrayList;
        this.context = context;
    }



    @NonNull
    @Override
    public CourseAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_item,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.holder holder, int position) {

        CourseModel courseModel = courseModelArrayList.get(position);
        holder.binding.title.setText(courseModel.getTitle());
        holder.binding.desc.setText(courseModel.getDescription());

//        Glide.with(context)
//                .load(courseModel.getAvatar())
//                .into(holder.binding.avatar);

        String imageUrl = courseModel.getAvatar();
        Picasso.get().load(imageUrl).into(holder.binding.avatar);

        Log.e(TAG,courseModel.getVideoUrl());

        holder.binding.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, CourseDetActivity.class);
                intent.putExtra("videoUri",courseModel.getVideoUrl());
                intent.putExtra("header_title",courseModel.getTitle());
                intent.putExtra("desc_item",courseModel.getDescription());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return courseModelArrayList.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        CourseItemBinding binding;
        public holder(@NonNull View itemView) {
            super(itemView);
            binding = CourseItemBinding.bind(itemView);
        }
    }
}
