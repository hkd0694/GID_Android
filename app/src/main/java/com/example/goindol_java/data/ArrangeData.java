package com.example.goindol_java.data;

import java.io.Serializable;

//중간 정리용 데이터 class
public class ArrangeData implements Serializable {

    //해당 번호
    private String number;
    //정답인지 오답인지 체크
    private String check;
    //해당 문제 요약
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
