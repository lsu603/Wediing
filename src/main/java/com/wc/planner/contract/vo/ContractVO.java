package com.wc.planner.contract.vo;

import org.springframework.stereotype.Component;

@Component("ContractVO")
public class ContractVO {
    private int c_mem_num;
    private String c_hall;
    private String c_stu_shop;
    private String c_et;
    private String c_p_id;
    private String c_p_name;
    private String c_day;
    private String c_man;
    private String c_woman;

    public String getC_day() {
        return c_day;
    }

    public void setC_day(String c_day) {
        this.c_day = c_day;
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

    public String getC_p_id() {
        return c_p_id;
    }

    public void setC_p_id(String c_p_id) {
        this.c_p_id = c_p_id;
    }

    public String getC_p_name() {
        return c_p_name;
    }

    public void setC_p_name(String c_p_name) {
        this.c_p_name = c_p_name;
    }

    public int getC_mem_num() {
        return c_mem_num;
    }

    public void setC_mem_num(int c_mem_num) {
        this.c_mem_num = c_mem_num;
    }

    public String getC_hall() {
        return c_hall;
    }

    public void setC_hall(String c_hall) {
        this.c_hall = c_hall;
    }

    public String getC_stu_shop() {
        return c_stu_shop;
    }

    public void setC_stu_shop(String c_stu_shop) {
        this.c_stu_shop = c_stu_shop;
    }

    public String getC_et() {
        return c_et;
    }

    public void setC_et(String c_et) {
        this.c_et = c_et;
    }
}
