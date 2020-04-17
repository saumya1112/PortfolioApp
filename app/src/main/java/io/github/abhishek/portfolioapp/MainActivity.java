package io.github.abhishek.portfolioapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.github.abhishek.portfolioapp.models.Portfolio;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_DETAILS_CODE = 1337;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String githubUserName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addDetailsButton = findViewById(R.id.btn_add_details);
        addDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
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

        // Then we set the data to appropriate fields
        TextView textViewName = findViewById(R.id.tv_name);
        TextView textViewPosition = findViewById(R.id.tv_title);
        TextView textViewEducationTitle = findViewById(R.id.tv_education_title);
        TextView textViewEducationDegree = findViewById(R.id.tv_education_degree);
        TextView textViewEducationYear = findViewById(R.id.tv_education_year);
        TextView textViewCourseTitle = findViewById(R.id.tv_course_title);
        TextView textViewCourseDegree = findViewById(R.id.tv_course_degree);
        TextView textViewCourseYear = findViewById(R.id.tv_course_year);

        textViewName.setText(portfolio.getName());
        textViewPosition.setText(portfolio.getPosition());
        textViewEducationTitle.setText(portfolio.getEducation().getTitle());
        textViewEducationDegree.setText(portfolio.getEducation().getDegree());
        textViewEducationYear.setText(portfolio.getEducation().getYear());
        textViewCourseTitle.setText(portfolio.getCourse().getName());
        textViewCourseDegree.setText(portfolio.getCourse().getOrganisation());
        textViewCourseYear.setText(portfolio.getCourse().getYear());

        // Set GitHub Username
        githubUserName = portfolio.getGithubUserName();
    }
}
