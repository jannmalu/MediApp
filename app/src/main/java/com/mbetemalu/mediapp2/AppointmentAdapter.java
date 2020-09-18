package com.mbetemalu.mediapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


public class AppointmentAdapter extends FirestoreRecyclerAdapter<AppointmentHelper, AppointmentAdapter.AppointmentHolder> {

    public AppointmentAdapter(@NonNull FirestoreRecyclerOptions<AppointmentHelper> options) {
        super(options);
    }


    @NonNull
    @Override
    public AppointmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.records_layout, parent, false);
        return new AppointmentHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull AppointmentHolder holder, int position, @NonNull AppointmentHelper model) {
        holder.list_doctor.setText(model.getDoctor());
        holder.list_date.setText(model.getDate());
        holder.list_time.setText(model.getTime());
        holder.list_desc.setText(model.getDescription());
    }

    class AppointmentHolder extends RecyclerView.ViewHolder{
        TextView list_doctor;
        TextView list_date;
        TextView list_time;
        TextView list_desc;

        public AppointmentHolder(@NonNull View itemView) {
            super(itemView);
            list_doctor = itemView.findViewById(R.id.doc);
            list_date = itemView.findViewById(R.id.date);
            list_time = itemView.findViewById(R.id.doc_time);
            list_desc = itemView.findViewById(R.id.desc);
        }
    }
}
