package com.wc.b_member.vo;

import org.springframework.stereotype.Component;

@Component("coupleVO")
public class CoupleVO {
    private String c_man;
    private String c_woman;
    private String c_w_tel;
    private String c_m_tel;
    private int c_mem_num;
    private String c_planner;
    private String c_pro;
    private String c_con;
    private String c_day;
    private String c_p_name;

    public String getC_p_name() {
        return c_p_name;
    }

    public void setC_p_name(String c_p_name) {
        this.c_p_name = c_p_name;
    }

    public String getC_man() {
        return c_man;
    }

    public void setC_man(String c_man) {
        this.c_man = c_man;
    }

    public String getC_woman() {
        return c_woman;
    }

    public void setC_woman(String c_woman) {
        this.c_woman = c_woman;
    }

    public String getC_w_tel() {
        return c_w_tel;
    }

    public void setC_w_tel(String c_w_tel) {
        this.c_w_tel = c_w_tel;
    }

    public String getC_m_tel() {
        return c_m_tel;
    }

    public void setC_m_tel(String c_m_tel) {
        this.c_m_tel = c_m_tel;
    }

    public int getC_mem_num() {
        return c_mem_num;
    }

    public void setC_mem_num(int c_mem_num) {
        this.c_mem_num = c_mem_num;
    }

    public String getC_planner() {
        return c_planner;
    }

    public void setC_planner(String c_planner) {
        this.c_planner = c_planner;
    }

    public String getC_pro() {
        return c_pro;
    }

    public void setC_pro(String c_pro) {
        this.c_pro = c_pro;
    }

    public String getC_con() {
        return c_con;
    }

    public void setC_con(String c_con) {
        this.c_con = c_con;
    }

    public String getC_day() {
        return c_day;
    }

    public void setC_day(String c_day) {
        this.c_day = c_day;
    }


}
