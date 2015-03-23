package org.spicydog.android.readable.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import org.spicydog.android.readable.R;
import org.spicydog.android.readable.utility.FileUtils;


public class MainActivity extends Activity {

    private static final String FILE_SAVE = "save";
    private EditText etText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etText = (EditText) findViewById(R.id.et_text);
        etText.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/PTSans.ttf"));

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            final String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
            etText.setText(sharedText);
        } else {
            final String lastText = FileUtils.loadStringToFile(getApplicationContext(), FILE_SAVE, "");
            etText.setText(lastText);
        }

    }

    @Override
    protected void onPause() {
        final String currentText = etText.getText().toString();
        FileUtils.saveStringFromFile(getApplicationContext(),FILE_SAVE,currentText);

        super.onPause();
    }
}
