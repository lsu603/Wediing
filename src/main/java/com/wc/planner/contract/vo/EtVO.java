package com.wc.planner.contract.vo;

import org.springframework.stereotype.Component;

@Component("EtVO")
public class EtVO {
    private int c_mem_num;
    private String c_et_memo;

    public int getC_mem_num() {
        return c_mem_num;
    }

    public void setC_mem_num(int c_mem_num) {
        this.c_mem_num = c_mem_num;
    }

    public String getC_et_memo() {
        return c_et_memo;
    }

    public void setC_et_memo(String c_et_memo) {
        this.c_et_memo = c_et_memo;
    }
}
