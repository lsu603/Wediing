package com.wc.planner.couple.vo;

import org.springframework.stereotype.Component;

@Component("C_memoVO")
public class C_memoVO {
    private int c_mem_num;
    private String c_memo;
    private int c_memo_num;
    private String c_date;

    public int getC_mem_num() {
        return c_mem_num;
    }

    public void setC_mem_num(int c_mem_num) {
        this.c_mem_num = c_mem_num;
    }

    public String getC_memo() {
        return c_memo;
    }

    public void setC_memo(String c_memo) {
        this.c_memo = c_memo;
    }

    public int getC_memo_num() {
        return c_memo_num;
    }

    public void setC_memo_num(int c_memo_num) {
        this.c_memo_num = c_memo_num;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }
}
