package br.com.concretesolutions.kappuccino.sample;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> subjects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateSubjectList();
        setupRecyclerView(this);
        setupButton();
    }

    private void setupButton() {
        final String WHATS_PACKAGE_NAME = "com.whatsapp";
        final String PLAY_STORE_URL = "https://play.google.com/store/apps/details?id=";
        Button button = findViewById(R.id.btn_start_activity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                                         Uri.parse(PLAY_STORE_URL + WHATS_PACKAGE_NAME)));
            }
        });
    }

    private void setupRecyclerView(Context context) {
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new RecyclerViewAdapter(context, subjects));

        SwipeToDeleteCallback callback = new SwipeToDeleteCallback(context) {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                RecyclerViewAdapter adapter = (RecyclerViewAdapter) recyclerView.getAdapter();
                adapter.removeAt(viewHolder.getAdapterPosition());
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void populateSubjectList() {
        String[] items =
                {
                        "ANDROID",
                        "PHP",
                        "BLOGGER",
                        "WORDPRESS",
                        "JOOMLA",
                        "ASP.NET",
                        "JAVA",
                        "C++",
                        "MATHS",
                        "HINDI",
                        "ENGLISH"
                };

        subjects.addAll(Arrays.asList(items));
    }

}
