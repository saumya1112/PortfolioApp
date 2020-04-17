package io.github.abhishek.portfolioapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Course implements Parcelable {
    private String organisation;
    private String name;
    private String year;

    public Course(String organisation, String name, String year) {
        this.organisation = organisation;
        this.name = name;
        this.year = year;
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Course{" +
                "organisation='" + organisation + '\'' +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.organisation);
        dest.writeString(this.name);
        dest.writeString(this.year);
    }

    protected Course(Parcel in) {
        this.organisation = in.readString();
        this.name = in.readString();
        this.year = in.readString();
    }

    public static final Parcelable.Creator<Course> CREATOR = new Parcelable.Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel source) {
            return new Course(source);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };
}
