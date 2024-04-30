package com.example.praktikum4;

import android.icu.util.GregorianCalendar;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class Post implements Parcelable {
    private String caption;
    private Integer img, accountID;
    private Uri imgUri;
    private GregorianCalendar postedDate;

    public Post(String caption, Integer img) {
        this.caption = caption;
        this.img = img;
        this.postedDate = new GregorianCalendar();
    }

    public Post(String caption, Uri imgUri) {
        this.caption = caption;
        this.imgUri = imgUri;
        this.postedDate = new GregorianCalendar();
    }

    public Post(String caption, Integer img, GregorianCalendar postedDate) {
        this.caption = caption;
        this.img = img;
        this.postedDate = postedDate;
    }

    public Uri getImgUri() {
        return imgUri;
    }

    protected Post(Parcel in) {
        caption = in.readString();
        if (in.readByte() == 0) {
            img = null;
        } else {
            img = in.readInt();
        }
        if (in.readByte() == 0) {
            accountID = null;
        } else {
            accountID = in.readInt();
        }
        imgUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public GregorianCalendar getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(GregorianCalendar postedDate) {
        this.postedDate = postedDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(caption);
        if (img == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(img);
        }
        if (accountID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(accountID);
        }
        dest.writeParcelable(imgUri, flags);
    }
}
