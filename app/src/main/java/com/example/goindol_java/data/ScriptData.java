package com.example.goindol_java.data;

public class ScriptData {

    //문제 번호
    private String number;
    //스크립을 선택한 총 갯수
    private int count;
    //스크립을 체크하면 true, 해제하면 false;
    private boolean script;

    public ScriptData(){

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isScript() {
        return script;
    }

    public void setScript(boolean script) {
        this.script = script;
    }
}
