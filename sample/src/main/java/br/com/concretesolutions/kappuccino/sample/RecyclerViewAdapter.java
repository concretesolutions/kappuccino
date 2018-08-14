package br.com.concretesolutions.kappuccino.sample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> subjectValues;
    private Context context;

    RecyclerViewAdapter(Context context, ArrayList<String> subjectValues){
        this.subjectValues = subjectValues;
        this.context = context;
    }

    public void removeAt(int position) {
        subjectValues.remove(position);
        notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        ViewHolder(View v){
            super(v);
            textView = v.findViewById(R.id.subject_textview);
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        holder.textView.setText(subjectValues.get(position));
    }

    @Override
    public int getItemCount(){
        return subjectValues.size();
    }
}
