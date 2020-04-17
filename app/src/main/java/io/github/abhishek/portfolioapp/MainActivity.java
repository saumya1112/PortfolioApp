package io.github.abhishek.portfolioapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * A click action for general view. Attached to GitHub button in XML.
     */
    public void openGithub(View view) {
        Toast.makeText(this, "Open GitHub", Toast.LENGTH_SHORT).show();
    }
}
