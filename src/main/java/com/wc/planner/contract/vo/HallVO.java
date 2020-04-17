package com.wc.planner.contract.vo;

import org.springframework.stereotype.Component;

@Component("HallVO")
public class HallVO {
    private int c_mem_num;
    private String c_hall_name;
    private String c_hall_memo;
    private String c_floor;
    private String c_time;
    private int c_person;
    private int c_pay;
    private int c_propay;

    public int getC_mem_num() {
        return c_mem_num;
    }

    public void setC_mem_num(int c_mem_num) {
        this.c_mem_num = c_mem_num;
    }

    public String getC_hall_name() {
        return c_hall_name;
    }

    public void setC_hall_name(String c_hall_name) {
        this.c_hall_name = c_hall_name;
    }

    public String getC_hall_memo() {
        return c_hall_memo;
    }

    public void setC_hall_memo(String c_hall_memo) {
        this.c_hall_memo = c_hall_memo;
    }

    public String getC_floor() {
        return c_floor;
    }

    public void setC_floor(String c_floor) {
        this.c_floor = c_floor;
    }

    public String getC_time() {
        return c_time;
    }

    public void setC_time(String c_time) {
        this.c_time = c_time;
    }

    public int getC_person() {
        return c_person;
    }

    public void setC_person(int c_person) {
        this.c_person = c_person;
    }

    public int getC_pay() {
        return c_pay;
    }

    public void setC_pay(int c_pay) {
        this.c_pay = c_pay;
    }

    public int getC_propay() {
        return c_propay;
    }

    public void setC_propay(int c_propay) {
        this.c_propay = c_propay;
    }
}
