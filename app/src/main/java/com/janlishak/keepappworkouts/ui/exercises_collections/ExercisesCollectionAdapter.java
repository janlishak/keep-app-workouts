package com.janlishak.keepappworkouts.ui.exercises_collections;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.janlishak.keepappworkouts.R;
import com.janlishak.keepappworkouts.model.ExerciseCollection;

import java.util.List;

public class ExercisesCollectionAdapter extends RecyclerView.Adapter<ExercisesCollectionAdapter.ViewHolder> {
    List<ExerciseCollection> list;
    View.OnClickListener listener;

    public void setData(List<ExerciseCollection> exerciseCollectionList) {
        list = exerciseCollectionList;
        notifyDataSetChanged();
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public ExerciseCollection getExerciseCollection(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.exercise_collection_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.collectionName.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView collectionName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            collectionName = itemView.findViewById(R.id.collection_name);
            itemView.setOnClickListener(listener);
        }
    }
}