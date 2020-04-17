package com.wc.b_member.vo;

import org.springframework.stereotype.Component;

@Component("memberVO")
public class MemberVO {

    private String b_id;
    private String b_pwd;
    private String b_name;
    private String b_tel;
    private boolean b_hall;
    private boolean b_shop;
    private boolean b_et;
    private boolean b_gender;
    private boolean b_rank;
    private String b_date;

    public String getB_id() {
        return b_id;
    }

    public void setB_id(String b_id) {
        this.b_id = b_id;
    }

    public String getB_pw() {
        return b_pwd;
    }

    public void setB_pw(String b_pw) {
        this.b_pwd = b_pw;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public String getB_pwd() {
        return b_pwd;
    }

    public void setB_pwd(String b_pwd) {
        this.b_pwd = b_pwd;
    }

    public String getB_tel() {
        return b_tel;
    }

    public void setB_tel(String b_tel) {
        this.b_tel = b_tel;
    }

    public boolean getB_hall() {
        return b_hall;
    }

    public void setB_hall(boolean b_hall) {
        this.b_hall = b_hall;
    }

    public boolean getB_shop() {
        return b_shop;
    }

    public void setB_shop(boolean b_shop) {
        this.b_shop = b_shop;
    }

    public boolean getB_et() {
        return b_et;
    }

    public void setB_et(boolean b_et) {
        this.b_et = b_et;
    }

    public boolean getB_gender() {
        return b_gender;
    }

    public void setB_gender(boolean b_gender) {
        this.b_gender = b_gender;
    }

    public boolean getB_rank() {
        return b_rank;
    }

    public void setB_rank(boolean b_rank) {
        this.b_rank = b_rank;
    }

    public String getB_date() {
        return b_date;
    }

    public void setB_date(String b_date) {
        this.b_date = b_date;
    }
}
