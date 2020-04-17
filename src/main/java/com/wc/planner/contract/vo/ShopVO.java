package com.wc.planner.contract.vo;

import org.springframework.stereotype.Component;

@Component("ShopVO")
public class ShopVO {
    private int c_mem_num;
    private String c_studio_name;
    private String c_shop_memo;
    private String c_studio_day;
    private String c_shop_name;
    private String c_studio_time;
    private int c_pay;
    private int c_propay;

    public int getC_mem_num() {
        return c_mem_num;
    }

    public void setC_mem_num(int c_mem_num) {
        this.c_mem_num = c_mem_num;
    }

    public String getC_studio_name() {
        return c_studio_name;
    }

    public void setC_studio_name(String c_studio_name) {
        this.c_studio_name = c_studio_name;
    }

    public String getC_shop_memo() {
        return c_shop_memo;
    }

    public void setC_shop_memo(String c_shop_memo) {
        this.c_shop_memo = c_shop_memo;
    }

    public String getC_studio_day() {
        return c_studio_day;
    }

    public void setC_studio_day(String c_studio_day) {
        this.c_studio_day = c_studio_day;
    }

    public String getC_shop_name() {
        return c_shop_name;
    }

    public void setC_shop_name(String c_shop_name) {
        this.c_shop_name = c_shop_name;
    }

    public String getC_studio_time() {
        return c_studio_time;
    }

    public void setC_studio_time(String c_studio_time) {
        this.c_studio_time = c_studio_time;
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
