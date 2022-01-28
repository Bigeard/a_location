package com.ynov.a_location.bo;

import android.os.Parcel;
import android.os.Parcelable;


public class Housing implements Parcelable {
    String title;
    String price;
    String created_at;

    public Housing(String title, String created_at) {
        this.title = title;
        this.price = price;
        this.created_at = created_at;
    }

    protected Housing(Parcel in) {
        title = in.readString();
        price = in.readString();
        created_at = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(price);
        dest.writeString(created_at);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Housing> CREATOR = new Creator<Housing>() {
        @Override
        public Housing createFromParcel(Parcel in) {
            return new Housing(in);
        }

        @Override
        public Housing[] newArray(int size) {
            return new Housing[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}

