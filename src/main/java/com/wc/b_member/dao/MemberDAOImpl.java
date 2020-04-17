package com.wc.b_member.dao;

import com.wc.b_member.vo.CoupleVO;
import com.wc.b_member.vo.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public MemberVO login(Map loginMap) throws DataAccessException {
        MemberVO member=(MemberVO)sqlSession.selectOne("mapper.member.login",loginMap);
        return member;
    }

    @Override
    public void insertNewMember(MemberVO memberVO) throws DataAccessException {
        sqlSession.insert("mapper.member.insertNewMember",memberVO);
    }

    @Override
    public void insertNewCouple(CoupleVO coupleVO) throws DataAccessException {
        sqlSession.insert("mapper.member.insertNewCouple",coupleVO);
    }

    @Override
    public String selectOverlappedID(String id) throws DataAccessException {
        String result = (String) sqlSession.selectOne("mapper.member.selectOverlappedID",id);
        return result;
    }

    @Override
    public String selectPlannerID(String id) throws DataAccessException {
        String result = (String) sqlSession.selectOne("mapper.member.selectPlannerID",id);
        return result;
    }


}
