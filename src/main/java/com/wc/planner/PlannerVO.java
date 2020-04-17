package com.wc.planner;

import org.springframework.stereotype.Component;

@Component("PlannerVO")
public class PlannerVO {
    private int p_num;
    private String p_id;
    private String p_name;
    private String p_memo;

    public int getP_num() {
        return p_num;
    }

    public void setP_num(int p_num) {
        this.p_num = p_num;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String pid) {
        this.p_id = pid;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_memo() {
        return p_memo;
    }

    public void setP_memo(String p_memo) {
        this.p_memo = p_memo;
    }
}
