package com.wc.planner.couple.dao;

import com.wc.admin.planner.PagingVO;
import com.wc.b_member.vo.CoupleVO;
import com.wc.planner.couple.vo.C_memoVO;
import com.wc.planner.PlannerVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("coupleDAO")
public class CoupleDAOImpl implements CoupleDAO {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public int pCountCouples(String id) throws DataAccessException {
        return sqlSession.selectOne("mapper.planner.pCountCouples",id);
    }

    @Override
    public int aCountCouples() throws DataAccessException {
        return sqlSession.selectOne("mapper.planner.aCountCouples");
    }

    @Override
    public int searchCountCouples(String name) throws DataAccessException {
        return sqlSession.selectOne("mapper.planner.searchCountCouples",name);
    }

    @Override
    public List<CoupleVO> aMemCriteria(PagingVO pagingVO) throws Exception {
        return sqlSession.selectList("mapper.planner.aMemCriteria", pagingVO);
    }

    @Override
    public List<CoupleVO> pMemCriteria(PagingVO pagingVO) throws Exception {
        return sqlSession.selectList("mapper.planner.pMemCriteria", pagingVO);
    }

    @Override
    public List<CoupleVO> searchMemCriteria(PagingVO pagingVO) throws Exception {
        return sqlSession.selectList("mapper.planner.searchMemCriteria", pagingVO);
    }

    @Override
    public CoupleVO coupleInfo(int num) throws Exception {
        return sqlSession.selectOne("mapper.planner.coupleInfo",num);
    }

    @Override
    public List<C_memoVO> selectC_Memo(int num) throws Exception {
        return sqlSession.selectList("mapper.planner.selectC_Memo",num);
    }

    @Override
    public List selectPlannerName() throws Exception {
        return sqlSession.selectList("mapper.planner.selectPlannerName");
    }

    @Override
    public List<PlannerVO> searchPlannerName(String name) throws Exception {
        return sqlSession.selectList("mapper.planner.searchPlannerName",name);
    }

    @Override
    public PlannerVO searchPlannerId(String id) throws Exception {
        return sqlSession.selectOne("mapper.planner.searchPlannerId",id);
    }

    @Override
    public void updateCouplePlanner(CoupleVO coupleVO) throws Exception {
        sqlSession.update("mapper.planner.updateCouplePlanner", coupleVO);
    }

    @Override
    public void updateCouple(CoupleVO coupleVO) throws Exception {
        sqlSession.update("mapper.planner.updateCouple", coupleVO);
    }

    @Override
    public void addC_Memo(C_memoVO c_memoVO) throws Exception {
        sqlSession.insert("mapper.planner.addC_Memo", c_memoVO);
    }

}
