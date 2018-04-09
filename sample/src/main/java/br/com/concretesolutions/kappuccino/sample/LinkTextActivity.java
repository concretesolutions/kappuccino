package br.com.concretesolutions.kappuccino.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

public class LinkTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_text);

        TextView txtLink = findViewById(R.id.txt_link);
        txtLink.setText(buildSpannableText());
    }

    private CharSequence buildSpannableText() {

        final String textOne = "This is the ";
        final String textTwo = "first link";
        final String textThree = ", and this is the ";
        final String textFour = "second link";
        final String finalString = String.format("%s %s %s %s", textOne, textTwo, textThree, textFour);
        final Spannable spannableString = new SpannableString(finalString);

        final int beginFirstLink = finalString.indexOf(textTwo);
        final int endFirstLink = beginFirstLink + textTwo.length();
        final int beginSecondLink = finalString.indexOf(textFour);
        final int endSecondLink = beginSecondLink + textFour.length();

        spannableString.setSpan(new URLSpan(textTwo) {
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(LinkTextActivity.this, MainActivity.class));
            }
        }, beginFirstLink, endFirstLink, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableString.setSpan(new URLSpan("https://www.google.com") {
        }, beginSecondLink, endSecondLink, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }
}
