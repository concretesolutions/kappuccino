package br.com.concretesolutions.kappuccino.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class VisibilityAssertionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visibility);

        Button btn = (Button) findViewById(R.id.btn_to_main);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VisibilityAssertionsActivity.this, MainActivity.class));
            }
        });
    }
}
