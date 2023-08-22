package com.yourcompany.gem_genie.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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

        Glide.with(context)
                .load(courseModel.getAvatar())
                .into(holder.binding.avatar);

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
