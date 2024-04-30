package com.example.praktikum4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Account implements Parcelable {
    private static int lastId = 0;
    private String username, name;
    private Integer pfp, following, followers, objectId;
//    private ArrayList<Post> posts = new ArrayList();

    public Account(String username, String name, Integer following, Integer followers, Integer pfp) {
        this.username = username;
        this.name = name;
        this.following = following;
        this.followers = followers;
        this.pfp = pfp;
        this.objectId = lastId++;
    }

    protected Account(Parcel in) {
        username = in.readString();
        name = in.readString();
        if (in.readByte() == 0) {
            pfp = null;
        } else {
            pfp = in.readInt();
        }
        if (in.readByte() == 0) {
            following = null;
        } else {
            following = in.readInt();
        }
        if (in.readByte() == 0) {
            followers = null;
        } else {
            followers = in.readInt();
        }
//        posts = in.createTypedArrayList(Post.CREATOR);
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    public void createPost(Post post) {
        post.setAccountID(objectId);
//        posts.add(post);
        DataSource.allPost.add(post);
        Collections.sort(DataSource.allPost, new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                return o2.getPostedDate().compareTo(o1.getPostedDate());
            }
        });
    }

    public void deletePost(int index) {
//        TODO: do
    }

    public Integer getObjectId() {
        return objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(name);
        if (pfp == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(pfp);
        }
        if (following == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(following);
        }
        if (followers == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(followers);
        }
//        dest.writeTypedList(posts);
    }
}
