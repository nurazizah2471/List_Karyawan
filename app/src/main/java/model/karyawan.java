package model;

import android.os.Parcel;
import android.os.Parcelable;

public class karyawan implements Parcelable {
    private String full_name, age, addres;

    public karyawan(){
        this.full_name="";
        this.age="";
        this.addres="";
    }
    public karyawan(String full_name, String age, String addres){
        this.full_name=full_name;
        this.age=age;
        this.addres=addres;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    protected karyawan(Parcel in) {
        full_name = in.readString();
        age = in.readString();
        addres = in.readString();
    }

    public static final Creator<karyawan> CREATOR = new Creator<karyawan>() {
        @Override
        public karyawan createFromParcel(Parcel in) {
            return new karyawan(in);
        }

        @Override
        public karyawan[] newArray(int size) {
            return new karyawan[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(full_name);
        dest.writeString(age);
        dest.writeString(addres);
    }
}
