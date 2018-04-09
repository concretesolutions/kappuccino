package br.com.concretesolutions.kappuccino.sample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private String[] SubjectValues;
    private Context context;

    RecyclerViewAdapter(Context context1, String[] SubjectValues1){
        SubjectValues = SubjectValues1;
        context = context1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;

        ViewHolder(View v){
            super(v);
            textView = v.findViewById(R.id.subject_textview);
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view1 = LayoutInflater.from(context).inflate(R.layout.recyclerview_items, parent, false);
        return new ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        holder.textView.setText(SubjectValues[position]);
    }

    @Override
    public int getItemCount(){
        return SubjectValues.length;
    }
}
