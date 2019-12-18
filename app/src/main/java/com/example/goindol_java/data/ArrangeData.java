package com.example.goindol_java.data;

import java.io.Serializable;

public class ArrangeData implements Serializable {

    private String number;
    private String check;
    private String summary;

    public ArrangeData(){
    }

    public ArrangeData(String number, String check, String summary) {
        this.number = number;
        this.check = check;
        this.summary = summary;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
