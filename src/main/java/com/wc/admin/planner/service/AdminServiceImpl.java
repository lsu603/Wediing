package com.wc.admin.planner.service;

import com.wc.admin.planner.PagingVO;
import com.wc.admin.planner.dao.AdminDAO;
import com.wc.b_member.vo.MemberVO;
import com.wc.planner.PlannerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Override
    public List listPlanners() throws DataAccessException {
        List plannersList = null;
        plannersList = adminDAO.selectAllPlannerList();
        return plannersList;
    }

    @Override
    public int countPlanners( ) throws DataAccessException {
        return adminDAO.countPlanners();
    }

    @Override
    public List<PlannerVO> listCriteria(PagingVO pagingVO) throws Exception {
        return adminDAO.listCriteria(pagingVO);

    }

    @Override
    public MemberVO selectMemberID(String id) throws Exception {
        return adminDAO.selectMemberID(id);
    }

    @Override
    public int selectPlannerID(String id) throws Exception {
        return adminDAO.selectPlannerID(id);
    }

    @Override
    public void insertNewPlanner(MemberVO member) throws Exception {
        adminDAO.insertNewPlanner(member);
    }

    @Override
    public PlannerVO plannerInfo(String id) throws Exception {
        return adminDAO.plannerInfo(id);
    }

    @Override
    public void updatePlanner(Map map) throws Exception {
        adminDAO.updatePlanner(map);
    }

    @Override
    public void updateMember(Map map) throws Exception {
        adminDAO.updateMember(map);
    }

    @Override
    public void deletePlanner(String id) throws Exception {
        adminDAO.deletePlanner(id);
    }
}
