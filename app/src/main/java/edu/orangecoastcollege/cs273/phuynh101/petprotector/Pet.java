package edu.orangecoastcollege.cs273.phuynh101.petprotector;

import android.net.Uri;

/**
 * Created by phuynh101 on 10/26/2017.
 */

/**
 * Pet
 */
public class Pet {
    private int mId;
    private String mName;
    private String mDetails;
    private String mPhone;
    private Uri mImageUri;

    /**
     * Constructor
     * @param name name
     * @param details details
     * @param phone phone number
     * @param imageNameUri image uri
     */
    public Pet(String name, String details, String phone, Uri imageNameUri) {
        this.mName = name;
        this.mDetails = details;
        this.mPhone = phone;
        this.mImageUri = imageNameUri;
    }

    /**
     * Constructor with id
     * @param id unique id of the pet
     * @param name name
     * @param details details
     * @param phone phone number
     * @param imageNameUri image uri
     */
    public Pet(int id, String name, String details, String phone, Uri imageNameUri) {
        this.mId = id;
        this.mName = name;
        this.mDetails = details;
        this.mPhone = phone;
        this.mImageUri = imageNameUri;
    }

    /**
     * get id of the pet
     * @return id
     */
    public int getId() {
        return mId;
    }

    /**
     * get name of the pet
     * @return name
     */
    public String getName() {
        return mName;
    }

    /**
     * set the name of the pet
     * @param name name
     */
    public void setName(String name) {
        this.mName = name;
    }

    /**
     * get details of the pet
     * @return details
     */
    public String getDetails() {
        return mDetails;
    }

    /**
     * set the new details
     * @param details details
     */
    public void setDetails(String details) {
        this.mDetails = details;
    }

    /**
     * get the phone number
     * @return phone number
     */
    public String getPhone() {
        return mPhone;
    }

    /**
     * set the new phone number
     * @param phone phone number
     */
    public void setPhone(String phone) {
        this.mPhone = phone;
    }

    /**
     * get the image uri
     * @return the image uri
     */
    public Uri getImageUri() {
        return mImageUri;
    }

    /**
     * set the new image uri
     * @param imageNameUri the image uri
     */
    public void setImageUri(Uri imageNameUri) {
        this.mImageUri = imageNameUri;
    }

    /**
     * provides developers with information of the pet
     * @return information
     */
    @Override
    public String toString() {
        return "Pet{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mDetails='" + mDetails + '\'' +
                ", mPhone='" + mPhone + '\'' +
                ", mImageUri=" + mImageUri +
                '}';
    }
}
