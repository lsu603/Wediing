package com.wc.b_member.service;

import com.wc.b_member.dao.MemberDAO;
import com.wc.b_member.vo.CoupleVO;
import com.wc.b_member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("memberService")
//@Transactional(propagation= Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {

//    @Autowired
//    private B_member b_member;

    @Autowired
    private MemberDAO memberDAO;

    @Override
    public MemberVO login(Map loginMap) throws Exception {
//        return b_member.login(loginMap);
        return memberDAO.login(loginMap);
    }

    @Override
    public void addMember(MemberVO memberVO) throws Exception {
//        b_member.insertNewMember(memberVO);
        memberDAO.insertNewMember(memberVO);
    }

    @Override
    public void addCouple(CoupleVO coupleVO) throws Exception {
        memberDAO.insertNewCouple(coupleVO);
    }

    @Override
    public String overlapped(String id) throws Exception {
//        return b_member.selectOverlappedID(id);
        return memberDAO.selectOverlappedID(id);
    }

    @Override
    public String selectPlanner(String id) throws Exception {
//        return b_member.selectPlannerID(id);
        return memberDAO.selectPlannerID(id);
    }


}
