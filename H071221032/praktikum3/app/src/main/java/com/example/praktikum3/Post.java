package com.example.praktikum3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Post implements Parcelable {
    private String caption, username;
    private Integer img, pfp, story;

    public Post(Account account, String caption, Integer img, Integer storyy) {
        this.username = account.getUsername();
        this.pfp = account.getPfp();
        this.caption = caption;
        this.img = img;
        this.story = storyy;
    }


    protected Post(Parcel in) {
        caption = in.readString();
        username = in.readString();
        if (in.readByte() == 0) {
            img = null;
        } else {
            img = in.readInt();
        }
        if (in.readByte() == 0) {
            pfp = null;
        } else {
            pfp = in.readInt();
        }
        if (in.readByte() == 0) {
            story = null;
        } else {
            story = in.readInt();
        }
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPfp() {
        return pfp;
    }

    public void setPfp(Integer pfp) {
        this.pfp = pfp;
    }

    public Integer getStory() {
        return story;
    }

    public void setStory(Integer story) {
        this.story = story;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(caption);
        dest.writeString(username);
        if (img == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(img);
        }
        if (pfp == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(pfp);
        }
        if (story == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(story);
        }
    }
}
