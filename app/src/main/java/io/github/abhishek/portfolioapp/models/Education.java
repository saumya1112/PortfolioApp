package io.github.abhishek.portfolioapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Education implements Parcelable {
    private String title;
    private String degree;
    private String year;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.degree);
        dest.writeString(this.year);
    }

    protected Education(Parcel in) {
        this.title = in.readString();
        this.degree = in.readString();
        this.year = in.readString();
    }

    public static final Parcelable.Creator<Education> CREATOR = new Parcelable.Creator<Education>() {
        @Override
        public Education createFromParcel(Parcel source) {
            return new Education(source);
        }

        @Override
        public Education[] newArray(int size) {
            return new Education[size];
        }
    };
}
