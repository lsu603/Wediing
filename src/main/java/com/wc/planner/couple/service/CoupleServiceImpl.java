package com.wc.planner.couple.service;

import com.wc.admin.planner.PagingVO;
import com.wc.b_member.vo.CoupleVO;
import com.wc.planner.couple.dao.CoupleDAO;
import com.wc.planner.couple.vo.C_memoVO;
import com.wc.planner.PlannerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("coupleService")
public class CoupleServiceImpl implements CoupleService {

    @Autowired
    private CoupleDAO plannerDAO;

    @Override
    public int pCountCouples(String id) throws DataAccessException {
        return plannerDAO.pCountCouples(id);
    }

    @Override
    public int aCountCouples() throws DataAccessException {
        return plannerDAO.aCountCouples();
    }

    @Override
    public int searchCountCouples(String name) throws DataAccessException {
        return plannerDAO.searchCountCouples(name);
    }

    @Override
    public List<CoupleVO> aMemCriteria(PagingVO pagingVO) throws Exception {
        return plannerDAO.aMemCriteria(pagingVO);
    }

    @Override
    public List<CoupleVO> pMemCriteria(PagingVO pagingVO) throws Exception {
        return plannerDAO.pMemCriteria(pagingVO);
    }

    @Override
    public List<CoupleVO> searchMemCriteria(PagingVO pagingVO) throws Exception {
        return plannerDAO.searchMemCriteria(pagingVO);
    }

    @Override
    public CoupleVO coupleInfo(int num) throws Exception {
        return plannerDAO.coupleInfo(num);
    }

    @Override
    public List<C_memoVO> selectC_Memo(int num) throws Exception {
        return plannerDAO.selectC_Memo(num);
    }

    @Override
    public List selectPlannerName() throws Exception {
        return plannerDAO.selectPlannerName();
    }

    @Override
    public List<PlannerVO> searchPlannerName(String name) throws Exception {
        return plannerDAO.searchPlannerName(name);
    }

    @Override
    public PlannerVO searchPlannerId(String id) throws Exception {
        return plannerDAO.searchPlannerId(id);
    }

    @Override
    public void updateCouplePlanner(CoupleVO coupleVO) throws Exception {
        plannerDAO.updateCouplePlanner(coupleVO);
    }

    @Override
    public void updateCouple(CoupleVO coupleVO) throws Exception {
        plannerDAO.updateCouple(coupleVO);
    }

    @Override
    public void addC_Memo(C_memoVO c_memoVO) throws Exception {
        plannerDAO.addC_Memo(c_memoVO);
    }


}
