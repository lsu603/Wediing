package com.wc.b_member.service;

import com.wc.b_member.vo.CoupleVO;
import com.wc.b_member.vo.MemberVO;

import java.util.Map;


public interface MemberService {
    public MemberVO login(Map loginMap) throws Exception;
    public void addMember(MemberVO memberVO) throws Exception;
    public void addCouple(CoupleVO coupleVO) throws Exception;
    public String overlapped(String id) throws Exception;
    public String selectPlanner(String id) throws Exception;
}
