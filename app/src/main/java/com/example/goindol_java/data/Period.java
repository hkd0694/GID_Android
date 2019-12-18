package com.example.goindol_java.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Period Class를 다시 ArrayList로 묶어서 SharedPreferences로 관리 한다.
public class Period implements Serializable {

    //시대별 ex) 기원과 형성, 고대사회, 조선전기, 일제 강점기...
    private String periodic;
    //시대별 Data을 ArrayList로 묶음
    private List<ExcelProblem> period_data = new ArrayList<ExcelProblem>();
    //시대별로 마지막에 푼 문제 인덱스
    private int index;
    //스크랩 체크 부분? 및 시간 여기다 저장

    public Period(String periodic, List<ExcelProblem> period_data) {
        this.periodic = periodic;
        this.period_data = period_data;
        index = 2;
    }

    public String getPeriodic() {
        return periodic;
    }

    public void setPeriodic(String periodic) {
        this.periodic = periodic;
    }

    public List<ExcelProblem> getPeriod_data() {
        return period_data;
    }

    public void setPeriod_data(List<ExcelProblem> period_data) {
        this.period_data = period_data;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
