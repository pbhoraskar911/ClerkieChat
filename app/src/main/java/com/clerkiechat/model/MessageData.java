package com.clerkiechat.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Pranav Bhoraskar
 */

public class MessageData implements Parcelable {

    private String userId;
    private String messageBody;
    private String senderName;
    private String photoUrl;
    private String timeStamp;

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public MessageData() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.messageBody);
        dest.writeString(this.senderName);
        dest.writeString(this.photoUrl);
        dest.writeString(this.timeStamp);
    }

    protected MessageData(Parcel in) {
        this.userId = in.readString();
        this.messageBody = in.readString();
        this.senderName = in.readString();
        this.photoUrl = in.readString();
        this.timeStamp = in.readString();
    }

    public static final Creator<MessageData> CREATOR = new Creator<MessageData>() {
        @Override
        public MessageData createFromParcel(Parcel source) {
            return new MessageData(source);
        }

        @Override
        public MessageData[] newArray(int size) {
            return new MessageData[size];
        }
    };


    public String getFormattedTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        Date dateNow = Calendar.getInstance().getTime();

        return dateFormat.format(dateNow);
    }

    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
        Date dateNow = Calendar.getInstance().getTime();

        return dateFormat.format(dateNow);
    }
}