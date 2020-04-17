package com.wc.planner.contract.dao;

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

public interface ContractDAO {
    public List selectCoupleName(String name) throws DataAccessException;
    public void addContract(ContractVO vo) throws DataAccessException;
    public int aCountContract() throws DataAccessException;
    public int pCountContract(String id) throws DataAccessException;
    public int searchCountCon(String name) throws DataAccessException;
    public int searchCon(String name) throws DataAccessException;
    public int selectContNum(int num) throws DataAccessException;
    public List aConCriteria(PagingVO pagingVO) throws DataAccessException;
    public List pConCriteria(PagingVO pagingVO) throws DataAccessException;
    public List searchConCriteria(PagingVO pagingVO) throws DataAccessException;
    public List searchCriteria(PagingVO pagingVO) throws DataAccessException;
    public CoupleVO coupleInfo(int num) throws DataAccessException;
    public List selectPlannerName() throws DataAccessException;
    public HallVO selectHall(int num) throws DataAccessException;
    public ShopVO selectShop(int num) throws DataAccessException;
    public EtVO selectEt(int num) throws DataAccessException;
    public ContractVO selectCont(int num) throws DataAccessException;
    public void addHall(int num) throws DataAccessException;
    public void addShop(int num) throws DataAccessException;
    public void addEt(int num) throws DataAccessException;
    public void updateContHall(ContractVO contractVO) throws DataAccessException;
    public void updateContShop(ContractVO contractVO) throws DataAccessException;
    public void updateContEt(ContractVO contractVO) throws DataAccessException;
    public void updateHall(HallVO hallVO) throws DataAccessException;
    public void updateShop(ShopVO shopVO) throws DataAccessException;
    public void updateEt(EtVO etVO) throws DataAccessException;
}
