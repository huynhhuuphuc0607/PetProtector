package edu.orangecoastcollege.cs273.phuynh101.petprotector;

import android.net.Uri;

/**
 * Created by phuynh101 on 10/26/2017.
 */

public class Pet {
    private int mId;
    private String mName;
    private String mDetails;
    private String mPhone;
    private Uri mImageNameUri;

    public Pet(String mName, String mDetails, String mPhone, Uri mImageNameUri) {
        this.mName = mName;
        this.mDetails = mDetails;
        this.mPhone = mPhone;
        this.mImageNameUri = mImageNameUri;
    }

    public Pet(int mId, String mName, String mDetails, String mPhone, Uri mImageNameUri) {
        this.mId = mId;
        this.mName = mName;
        this.mDetails = mDetails;
        this.mPhone = mPhone;
        this.mImageNameUri = mImageNameUri;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDetails() {
        return mDetails;
    }

    public void setDetails(String mDetails) {
        this.mDetails = mDetails;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public Uri getImageNameUri() {
        return mImageNameUri;
    }

    public void setImageNameUri(Uri mImageNameUri) {
        this.mImageNameUri = mImageNameUri;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mDetails='" + mDetails + '\'' +
                ", mPhone='" + mPhone + '\'' +
                ", mImageNameUri=" + mImageNameUri +
                '}';
    }
}
