package io.github.abhishek.portfolioapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import io.github.abhishek.portfolioapp.databinding.ActivityMainBinding;
import io.github.abhishek.portfolioapp.models.Course;
import io.github.abhishek.portfolioapp.models.Education;
import io.github.abhishek.portfolioapp.models.Portfolio;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public static final int REQUEST_DETAILS_CODE = 1337;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String githubUserName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initSampleData();
        binding.btnAddDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                Portfolio portfolio = binding.getPortfolio();
                intent.putExtra(AddActivity.PARAM_PORTFOLIO, portfolio);
                startActivityForResult(intent, REQUEST_DETAILS_CODE);
            }
        });
    }

    /**
     * A click action for general view. Attached to GitHub button in XML.
     */
    public void openGithub(View view) {
        String githubUrl = "https://github.com/";
        if (!TextUtils.isEmpty(githubUserName)) {
            githubUrl = String.format("https://github.com/%s/", githubUserName);
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, String.format("requestCode=%d, resultCode=%d", requestCode, resultCode));

        // Check for Valid Response
        if (requestCode == REQUEST_DETAILS_CODE && resultCode == RESULT_OK && data != null) {
            Portfolio portfolio = data.getParcelableExtra(AddActivity.PARAM_PORTFOLIO);
            // setDetails(portfolio); This would give us warning due to @NonNull annotation
            if (portfolio != null) {
                setDetails(portfolio);
            }
        }
    }

    private void setDetails(@NonNull Portfolio portfolio) {
        // First we check by printing Portfolio
        Log.i(TAG, String.valueOf(portfolio));

        // We just need to set the variable
        binding.setPortfolio(portfolio);

        // Set GitHub Username
        githubUserName = portfolio.getGithubUserName();
    }

    private void initSampleData() {
        Education education = new Education("College/University Name    ",
                "Degree", "Year");

        Course course = new Course("Organisation", "Course Name", "Year");

        Portfolio portfolio = new Portfolio("Name", "Position",
                education, course, "AbhishekChd");
        binding.setPortfolio(portfolio);
    }
}
