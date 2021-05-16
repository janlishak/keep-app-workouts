package com.janlishak.keepappworkouts.ui.session_exercise_browser;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.janlishak.keepappworkouts.R;
import com.janlishak.keepappworkouts.model.SessionExercise;

import java.util.List;

public class SessionExercisesAdapter extends RecyclerView.Adapter<SessionExercisesAdapter.ViewHolder> {
    List<SessionExercise> SessionExercises;
    View.OnClickListener listener;
    View.OnClickListener doneListener;

    public void setData(List<SessionExercise> SessionExercises){
        this.SessionExercises = SessionExercises;
        this.notifyDataSetChanged();
    }

    public void setListener(View.OnClickListener listener){
        this.listener = listener;
    }
    public void setDoneListener(View.OnClickListener listener){
        this.doneListener = listener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.session_exercise_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.card.setBackground(SessionExercises.get(position).isCompleted()? new ColorDrawable(0xFFD0D0D0) : new ColorDrawable(0xFFEEEEEE));

        holder.name.setText(SessionExercises.get(position).getName());
        holder.sets.setText(SessionExercises.get(position).getSets().toString());
        holder.reps.setText(SessionExercises.get(position).getReps().toString());
        holder.rest.setText(SessionExercises.get(position).getRest());
    }

    @Override
    public int getItemCount() {
        return SessionExercises.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView done;
        TextView name;
        TextView sets;
        TextView reps;
        TextView rest;
        CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.just_the_card);
            done = itemView.findViewById(R.id.imageview_session_exercise_done);
            name = itemView.findViewById(R.id.textview_session_exercise_name);
            sets = itemView.findViewById(R.id.textview_session_exercise_sets);
            reps = itemView.findViewById(R.id.textview_session_exercise_reps);
            rest = itemView.findViewById(R.id.textview_session_exercise_rest);
            done.setOnClickListener(doneListener);
            itemView.setOnClickListener(listener);
        }
    }

    public SessionExercise getSessionExercise(int position){
        return SessionExercises.get(position);
    }
}