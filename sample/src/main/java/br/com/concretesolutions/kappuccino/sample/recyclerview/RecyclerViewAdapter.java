package br.com.concretesolutions.kappuccino.sample.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import javax.annotation.ParametersAreNonnullByDefault;

import br.com.concretesolutions.kappuccino.sample.R;

@ParametersAreNonnullByDefault
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private final ArrayList<String> subjectValues;

    RecyclerViewAdapter(ArrayList<String> subjectValues) {
        this.subjectValues = subjectValues;
    }

    public void removeAt(int position) {
        subjectValues.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public @NonNull RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recyclerview_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(subjectValues.get(position));

        /* This line is to test notVisible child views, check RecyclerViewActivityTest#notDisplayed_issue99 */
        holder.editText.setVisibility(position == 1 ? View.GONE : View.VISIBLE);
    }

    @Override
    public int getItemCount(){
        return subjectValues.size();
    }

    @ParametersAreNonnullByDefault
    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private EditText editText;

        ViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.subject_text_view);
            editText = v.findViewById(R.id.subject_edit_text);
        }
    }

}