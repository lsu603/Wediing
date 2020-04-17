package com.wc.planner.couple.dao;

import com.wc.admin.planner.PagingVO;
import com.wc.b_member.vo.CoupleVO;
import com.wc.planner.couple.vo.C_memoVO;
import com.wc.planner.PlannerVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface CoupleDAO {
    public int pCountCouples(String id) throws DataAccessException;
    public int aCountCouples() throws DataAccessException;
    public int searchCountCouples(String name) throws DataAccessException;
    public List<CoupleVO> aMemCriteria(PagingVO pagingVO) throws Exception;
    public List<CoupleVO> pMemCriteria(PagingVO pagingVO) throws Exception;
    public List<CoupleVO> searchMemCriteria(PagingVO pagingVO) throws Exception;
    public CoupleVO coupleInfo(int num) throws Exception;
    public List<C_memoVO> selectC_Memo(int num) throws Exception;
    public List selectPlannerName() throws Exception;
    public List<PlannerVO> searchPlannerName(String name) throws Exception;
    public PlannerVO searchPlannerId(String id) throws Exception;
    public void  updateCouplePlanner(CoupleVO coupleVO) throws Exception;
    public void  updateCouple(CoupleVO coupleVO) throws Exception;
    public void  addC_Memo(C_memoVO c_memoVO) throws Exception;
}
