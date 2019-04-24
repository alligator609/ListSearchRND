package com.example.alligator.listsearchrnd;

/**
 * Created by Alligator on 3/13/2018.
 */
public class Country {

    String name;
    int flag;
    String iso_code;

    Country(String name, String iso_code, int flag) {
        this.name = name;
        this.iso_code = iso_code;
        this.flag = flag;
    }

    public String getIso_code() {
        return iso_code;
    }

    public void setIso_code(String iso_code) {
        this.iso_code = iso_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

}