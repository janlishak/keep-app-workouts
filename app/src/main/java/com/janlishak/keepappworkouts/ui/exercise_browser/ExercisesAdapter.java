package com.janlishak.keepappworkouts.ui.exercise_browser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.janlishak.keepappworkouts.R;
import com.janlishak.keepappworkouts.model.Exercise;

import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ViewHolder> {
    List<Exercise> exercises;
    View.OnClickListener listener;

    public void setData(List<Exercise> exercises){
        this.exercises = exercises;
        this.notifyDataSetChanged();
    }

    public void setListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.exercise_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.exerciseName.setText(exercises.get(position).getName());
        Glide.with(holder.itemView.getContext()).load(exercises.get(position).getImageLink()).into(holder.exerciseImage);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView exerciseName;
        ImageView exerciseImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.textview_exercise_name);
            exerciseImage = itemView.findViewById(R.id.imageview_exercise);
            itemView.setOnClickListener(listener);
        }
    }

    public Exercise getExercise(int position){
        return exercises.get(position);
    }
}