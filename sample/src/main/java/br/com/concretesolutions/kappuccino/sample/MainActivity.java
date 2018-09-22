package br.com.concretesolutions.kappuccino.sample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

}
