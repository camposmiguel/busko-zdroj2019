package com.miguelcr.a02_customlistview;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class MyStudentRecyclerViewAdapter extends RecyclerView.Adapter<MyStudentRecyclerViewAdapter.ViewHolder> {

    private final List<Student> mValues;
    private Context ctx;

    public MyStudentRecyclerViewAdapter(Context context, int layout, List<Student> items) {
        mValues = items;
        ctx = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_student, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.textViewName.setText(holder.mItem.getName());
        holder.textViewAge.setText(String.valueOf(holder.mItem.getAge()));

        Glide.with(ctx)
                .load(holder.mItem.getPhotoUrl())
                .into(holder.imageViewPhoto);
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView imageViewPhoto;
        public final TextView textViewName;
        public final TextView textViewAge;
        public Student mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            imageViewPhoto = view.findViewById(R.id.image_view_photo);
            textViewName = view.findViewById(R.id.text_view_name);
            textViewAge = view.findViewById(R.id.text_view_age);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewName.getText() + "'";
        }
    }
}
