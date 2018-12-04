package br.com.concretesolutions.kappuccino.sample.recyclerview;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.ParametersAreNonnullByDefault;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.concretesolutions.kappuccino.sample.R;
import br.com.concretesolutions.kappuccino.sample.SwipeToDeleteCallback;

@ParametersAreNonnullByDefault
public class RecyclerViewActivity extends AppCompatActivity {

    @RestrictTo(RestrictTo.Scope.TESTS)
    public static int subjectListSize() {
        return populateSubjectList().size();
    }

    private static ArrayList<String> populateSubjectList() {
        return new ArrayList<>(Arrays.asList("ANDROID", "PHP", "BLOGGER", "WORDPRESS", "JOOMLA", "ASP.NET", "JAVA", "C++", "MATHS"));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview_activity);
        setupRecyclerView(this);
    }

    private void setupRecyclerView(Context context) {
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new RecyclerViewAdapter(populateSubjectList()));

        SwipeToDeleteCallback callback = new SwipeToDeleteCallback(context) {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                RecyclerViewAdapter adapter = (RecyclerViewAdapter) recyclerView.getAdapter();
                if (adapter != null) {
                    adapter.removeAt(viewHolder.getAdapterPosition());
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

}