package io.github.abhishek.portfolioapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import io.github.abhishek.portfolioapp.databinding.ActivityAddBinding;
import io.github.abhishek.portfolioapp.models.Course;
import io.github.abhishek.portfolioapp.models.Education;
import io.github.abhishek.portfolioapp.models.Portfolio;

public class AddActivity extends AppCompatActivity {
    public static final String PARAM_PORTFOLIO = "param-portfolio";
    private ActivityAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(PARAM_PORTFOLIO)) {
            Portfolio portfolio = extras.getParcelable(PARAM_PORTFOLIO);
            binding.setPortfolio(portfolio);
        }
    }

    public void submitData(View view) {
        String name = getText(binding.etName);
        String position = getText(binding.etTitle);
        String educationTitle = getText(binding.etEducationUniversity);
        String educationDegree = getText(binding.etEducationDegree);
        String educationYear = getText(binding.etEducationYear);
        String courseTitle = getText(binding.etCourseOrganisation);
        String courseDegree = getText(binding.etCourseTitle);
        String courseYear = getText(binding.etCourseYear);
        String github = getText(binding.etGithub);

        // Readability is increased and error prone code is reduced
        Education education = new Education(educationTitle, educationDegree, educationYear);
        Course course = new Course(courseTitle, courseDegree, courseYear);
        Portfolio portfolio = new Portfolio(name, position, education, course, github);

        Intent intent = new Intent();
        intent.putExtra(PARAM_PORTFOLIO, portfolio);
        setResult(RESULT_OK, intent);
        finish();
    }

    private String getText(@NonNull EditText editText) {
        return editText.getText().toString();
    }
}
