package com.s.m.shahi.androidictquestion.Model;

public class Model {

    private String undefined, QUESTION, DIFF, CORRECT, OPTION1, OPTION2, OPTION3, OPTION4;

    public Model() {
    }

    public Model(String undefined, String QUESTION, String DIFF, String CORRECT, String OPTION1, String OPTION2, String OPTION3, String OPTION4) {
        this.undefined = undefined;
        this.QUESTION = QUESTION;
        this.DIFF = DIFF;
        this.CORRECT = CORRECT;
        this.OPTION1 = OPTION1;
        this.OPTION2 = OPTION2;
        this.OPTION3 = OPTION3;
        this.OPTION4 = OPTION4;
    }

    public String getUndefined() {
        return undefined;
    }

    public void setUndefined(String undefined) {
        this.undefined = undefined;
    }

    public String getQUESTION() {
        return QUESTION;
    }

    public void setQUESTION(String QUESTION) {
        this.QUESTION = QUESTION;
    }

    public String getDIFF() {
        return DIFF;
    }

    public void setDIFF(String DIFF) {
        this.DIFF = DIFF;
    }

    public String getCORRECT() {
        return CORRECT;
    }

    public void setCORRECT(String CORRECT) {
        this.CORRECT = CORRECT;
    }

    public String getOPTION1() {
        return OPTION1;
    }

    public void setOPTION1(String OPTION1) {
        this.OPTION1 = OPTION1;
    }

    public String getOPTION2() {
        return OPTION2;
    }

    public void setOPTION2(String OPTION2) {
        this.OPTION2 = OPTION2;
    }

    public String getOPTION3() {
        return OPTION3;
    }

    public void setOPTION3(String OPTION3) {
        this.OPTION3 = OPTION3;
    }

    public String getOPTION4() {
        return OPTION4;
    }

    public void setOPTION4(String OPTION4) {
        this.OPTION4 = OPTION4;
    }
}
