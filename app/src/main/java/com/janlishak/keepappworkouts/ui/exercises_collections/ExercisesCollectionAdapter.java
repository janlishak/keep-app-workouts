package com.janlishak.keepappworkouts.ui.exercises_collections;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.janlishak.keepappworkouts.R;

import java.util.List;

public class ExercisesCollectionAdapter extends RecyclerView.Adapter<ExercisesCollectionAdapter.ViewHolder> {
    List<String> titles;
    List<Integer> images;

    public ExercisesCollectionAdapter(Context ctx, List<String> titles, List<Integer> images){
        this.titles = titles;
        this.images = images;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.exercise_collection_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
        holder.gridIcon.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView gridIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.collection_name);
            gridIcon = itemView.findViewById(R.id.collection_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 5/10/2021 https://developer.android.com/guide/navigation/navigation-pass-data
                    Navigation.findNavController(v).navigate(R.id.navigation_exercise_browser);
                }
            });
        }
    }
}