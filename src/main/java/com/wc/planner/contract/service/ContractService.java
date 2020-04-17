package com.wc.planner.contract.service;

import com.wc.admin.planner.PagingVO;
import com.wc.b_member.vo.CoupleVO;
import com.wc.planner.PlannerVO;
import com.wc.planner.contract.vo.ContractVO;
import com.wc.planner.contract.vo.EtVO;
import com.wc.planner.contract.vo.HallVO;
import com.wc.planner.contract.vo.ShopVO;
import com.wc.planner.couple.vo.C_memoVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ContractService {
    public List selectCoupleName(String name) throws Exception;
    public void addContract(int num) throws Exception;
    public int aCountContract() throws Exception;
    public int pCountContract(String id) throws Exception;
    public int searchCountCon(String name) throws Exception;
    public int searchCon(String name) throws Exception;
    public int selectContNum(int num) throws Exception;
    public List aConCriteria(PagingVO pagingVO) throws Exception;
    public List pConCriteria(PagingVO pagingVO,String id) throws Exception;
    public List searchConCriteria(PagingVO pagingVO,String name) throws Exception;
    public List searchCriteria(PagingVO pagingVO,String name) throws Exception;
    public List selectPlannerName() throws Exception;
    public HallVO selectHall(int num) throws Exception;
    public ShopVO selectShop(int num) throws Exception;
    public EtVO selectEt(int num) throws Exception;
    public ContractVO selectCont(int num) throws Exception;
    public void addHSE(int num) throws Exception;
    public void updateHall(ContractVO contractVO,HallVO hallVO) throws Exception;
    public void updateShop(ContractVO contractVO,ShopVO shopVO) throws Exception;
    public void updateEt(ContractVO contractVO,EtVO etVO) throws Exception;
}
