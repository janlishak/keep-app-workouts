package com.janlishak.keepappworkouts.ui.exercise_browser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.janlishak.keepappworkouts.R;
import com.janlishak.keepappworkouts.model.Exercise;

import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ViewHolder> {
    LiveData<List<Exercise>> exercises;

    public ExercisesAdapter(LiveData<List<Exercise>> exercises){
        this.exercises = exercises;
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
        holder.exerciseName.setText(exercises.getValue().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return exercises.getValue().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView exerciseName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.textview_exercise_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 5/10/2021 https://developer.android.com/guide/navigation/navigation-pass-data
                    Navigation.findNavController(v).navigate(R.id.navigation_exercise_details);
                }
            });
        }
    }
}