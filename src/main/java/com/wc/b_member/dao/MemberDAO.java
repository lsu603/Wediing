package com.wc.b_member.dao;

import com.wc.b_member.vo.CoupleVO;
import com.wc.b_member.vo.MemberVO;
import org.springframework.dao.DataAccessException;

import java.util.Map;

public interface MemberDAO {
    public MemberVO login(Map loginMap) throws DataAccessException;
    public void insertNewMember(MemberVO memberVO) throws DataAccessException;
    public void insertNewCouple(CoupleVO coupleVO) throws DataAccessException;
    public String selectOverlappedID(String id) throws DataAccessException;
    public String selectPlannerID(String id) throws DataAccessException;
}
