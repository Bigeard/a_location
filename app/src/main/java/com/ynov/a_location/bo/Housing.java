package com.ynov.a_location.bo;

import android.os.Parcel;
import android.os.Parcelable;


public class Housing implements Parcelable {
    String title;
    String price;
    String created_at;
    HousingImage illustrations;

    public Housing(String title, String created_at, String price, HousingImage illustrations) {
        this.title = title;
        this.price = price;
        this.created_at = created_at;
        this.illustrations = illustrations;
    }


    protected Housing(Parcel in) {
        title = in.readString();
        price = in.readString();
        created_at = in.readString();
        illustrations = in.readParcelable(HousingImage.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(price);
        dest.writeString(created_at);
        dest.writeParcelable(illustrations, flags);
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

    public HousingImage getIllustrations() {
        return illustrations;
    }

    public void setIllustrations(HousingImage illustrations) {
        this.illustrations = illustrations;
    }
}

