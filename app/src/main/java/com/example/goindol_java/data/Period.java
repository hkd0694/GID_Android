package com.example.goindol_java.data;

import java.util.ArrayList;

//Period Class를 다시 ArrayList로 묶어서 SharedPreferences로 관리 한다.
public class Period {

    //시대별 ex) 기원과 형성, 고대사회, 조선전기, 일제 강점기...
    private String periodic;
    //시대별 Data을 ArrayList로 묶음
    private ArrayList<String> period_data = new ArrayList<>();

    public Period(String periodic, ArrayList<String> period_data) {
        this.periodic = periodic;
        this.period_data = period_data;
    }

    public String getPeriodic() {
        return periodic;
    }

    public void setPeriodic(String periodic) {
        this.periodic = periodic;
    }

    public ArrayList<String> getPeriod_data() {
        return period_data;
    }

    public void setPeriod_data(ArrayList<String> period_data) {
        this.period_data = period_data;
    }
}
