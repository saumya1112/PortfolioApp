package io.github.abhishek.portfolioapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Portfolio implements Parcelable {
    private String name;
    private String position;
    private Education education;
    private Course course;
    private String githubUserName;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.position);
        dest.writeParcelable(this.education, flags);
        dest.writeParcelable(this.course, flags);
        dest.writeString(this.githubUserName);
    }

    protected Portfolio(Parcel in) {
        this.name = in.readString();
        this.position = in.readString();
        this.education = in.readParcelable(Education.class.getClassLoader());
        this.course = in.readParcelable(Course.class.getClassLoader());
        this.githubUserName = in.readString();
    }

    public static final Creator<Portfolio> CREATOR = new Creator<Portfolio>() {
        @Override
        public Portfolio createFromParcel(Parcel source) {
            return new Portfolio(source);
        }

        @Override
        public Portfolio[] newArray(int size) {
            return new Portfolio[size];
        }
    };
}
