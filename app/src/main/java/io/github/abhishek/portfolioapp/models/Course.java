package io.github.abhishek.portfolioapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course implements Parcelable {
    private String organisation;
    private String name;
    private String year;

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
