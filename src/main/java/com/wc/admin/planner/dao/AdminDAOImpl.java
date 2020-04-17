package com.wc.admin.planner.dao;

import com.wc.admin.planner.PagingVO;
import com.wc.b_member.vo.MemberVO;
import com.wc.planner.PlannerVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("adminDAO")
public class AdminDAOImpl implements AdminDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List selectAllPlannerList() throws DataAccessException {
        List<PlannerVO> plannersList = sqlSession.selectList("mapper.adminPlanner.selectAllPlannerList");
        return plannersList;
    }

    @Override
    public int countPlanners( ) throws DataAccessException {
        return sqlSession.selectOne("mapper.adminPlanner.countPlanners");
    }

    @Override
    public List<PlannerVO> listCriteria(PagingVO pagingVO) throws Exception {
        return sqlSession.selectList("mapper.adminPlanner.listCriteria",pagingVO);
    }

    @Override
    public MemberVO selectMemberID(String id) throws Exception {
        return sqlSession.selectOne("mapper.adminPlanner.selectMemberID",id);
    }

    @Override
    public int selectPlannerID(String id) throws Exception {
        return sqlSession.selectOne("mapper.adminPlanner.selectPlannerID",id);
    }

    @Override
    public void insertNewPlanner(MemberVO member) throws Exception {
        sqlSession.insert("mapper.adminPlanner.insertNewPlanner",member);
    }

    @Override
    public PlannerVO plannerInfo(String id) throws Exception {
        return sqlSession.selectOne("mapper.adminPlanner.plannerInfo",id);
    }

    @Override
    public void updatePlanner(Map map) throws Exception {
        sqlSession.update("mapper.adminPlanner.updatePlanner", map);
    }

    @Override
    public void updateMember(Map map) throws Exception {
        sqlSession.update("mapper.adminPlanner.updateMember", map);
    }

    @Override
    public void deletePlanner(String id) throws Exception {
        sqlSession.delete("mapper.adminPlanner.deletePlanner", id);
    }
}
