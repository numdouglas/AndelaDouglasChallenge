package com.example.user.andeladouglaschallenge;

import android.os.Parcel;
import android.os.Parcelable;

public class UserDetails implements Parcelable {

    //use creator class to write a new parcel or a set
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public UserDetails createFromParcel(Parcel in) {
            return new UserDetails(in);
        }

        public UserDetails[] newArray(int size) {
            return new UserDetails[size];
        }
    };
//variables well store in a parcel
    private String name;
    private String track;
    private  String email;
    private  String phone_number;
    private String country;

    // Parcel constructor
    public UserDetails(String name, String track, String email,String phone_number,String country){
        this.name = name;
        this.track = track;
        this.email = email;
        this.phone_number=phone_number;
        this.country=country;
    }
    //easy to use constructor for testing
    public UserDetails(String testData){
        this.name = testData;
        this.track = testData;
        this.email = testData;
        this.phone_number=testData;
        this.country=testData;
    }


    //getters and setters for the variables
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track=track;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_No() {
        return phone_number;
    }

    public void setPhone_No(String phone_no) {
        this.phone_number = phone_no;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // Parcel the variables
    public UserDetails(Parcel in){
        this.name = in.readString();
        this.track =  in.readString();
        this.email =  in.readString();
        this.phone_number =  in.readString();
        this.country=in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.track);
        dest.writeString(this.email);
        dest.writeString(this.phone_number);
        dest.writeString(this.country);
    }

}