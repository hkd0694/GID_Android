package com.example.goindol_java.data;

import java.io.Serializable;

public class ArrangeData implements Serializable {

    private String number;
    private boolean check;
    private String summary;

    public ArrangeData(){

    }

    public ArrangeData(String number, boolean check, String summary) {
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

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
