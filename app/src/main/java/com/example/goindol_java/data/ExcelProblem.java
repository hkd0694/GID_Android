package com.example.goindol_java.data;

import java.util.Date;

public class ExcelProblem {
    //문제번호
    private int excel_no;
    //시대
    private String era;
    //문제
    private String problem;
    //보기(1)
    private String exam_1;
    //보기(2)
    private String exam_2;
    //보기(3)
    private String exam_3;
    //보기(4)
    private String exam_4;
    //보기(5)
    private String exam_5;
    // 답
    private int answer;
    // 해설
    private String solution;
    // 정리
    private String summary;
    //여기까진 엑셀에 있는 내용들..!!
    // 정답 여부 확인
    private String exam_check;

    /*// 스크랩 체크 여부
    private boolean script_check;
    // 시간 체크
    private Date date;*/

    public ExcelProblem() {
    }

    public ExcelProblem(int excel_no, String era, String problem, String exam_1, String exam_2, String exam_3, String exam_4, String exam_5, int answer, String solution, String summary, String exam_check) {
        this.excel_no = excel_no;
        this.era = era;
        this.problem = problem;
        this.exam_1 = exam_1;
        this.exam_2 = exam_2;
        this.exam_3 = exam_3;
        this.exam_4 = exam_4;
        this.exam_5 = exam_5;
        this.answer = answer;
        this.solution = solution;
        this.summary = summary;
        this.exam_check = exam_check;
    }

    public int getExcel_no() {
        return excel_no;
    }

    public void setExcel_no(int excel_no) {
        this.excel_no = excel_no;
    }

    public String getEra() {
        return era;
    }

    public void setEra(String era) {
        this.era = era;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getExam_1() {
        return exam_1;
    }

    public void setExam_1(String exam_1) {
        this.exam_1 = exam_1;
    }

    public String getExam_2() {
        return exam_2;
    }

    public void setExam_2(String exam_2) {
        this.exam_2 = exam_2;
    }

    public String getExam_3() {
        return exam_3;
    }

    public void setExam_3(String exam_3) {
        this.exam_3 = exam_3;
    }

    public String getExam_4() {
        return exam_4;
    }

    public void setExam_4(String exam_4) {
        this.exam_4 = exam_4;
    }

    public String getExam_5() {
        return exam_5;
    }

    public void setExam_5(String exam_5) {
        this.exam_5 = exam_5;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getExam_check() {
        return exam_check;
    }

    public void setExam_check(String exam_check) {
        this.exam_check = exam_check;
    }

}
