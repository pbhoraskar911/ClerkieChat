package com.clerkiechat.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pranav Bhoraskar
 */

public class User implements Parcelable {

    private String nameOfUser;
    private String userEmailId;

    public String getNameOfUser() {
        return nameOfUser;
    }

    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nameOfUser);
        dest.writeString(this.userEmailId);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.nameOfUser = in.readString();
        this.userEmailId = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}