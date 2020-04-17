package com.wc.admin.planner.service;

import com.wc.admin.planner.PagingVO;
import com.wc.b_member.vo.MemberVO;
import com.wc.planner.PlannerVO;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface AdminService {
    public List listPlanners() throws DataAccessException;
    public int countPlanners( ) throws DataAccessException;
    public List<PlannerVO> listCriteria(PagingVO pagingVO) throws Exception;
    public MemberVO selectMemberID(String id) throws  Exception;
    public int selectPlannerID(String id) throws  Exception;
    public void insertNewPlanner(MemberVO member) throws Exception;
    public PlannerVO plannerInfo(String id) throws Exception;
    public void updatePlanner(Map map) throws Exception;
    public void updateMember(Map map) throws Exception;
    public void deletePlanner(String id) throws Exception;
}
