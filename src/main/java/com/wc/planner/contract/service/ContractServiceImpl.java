package com.wc.planner.contract.service;

import com.wc.admin.planner.PagingVO;
import com.wc.b_member.vo.CoupleVO;
import com.wc.planner.PlannerVO;
import com.wc.planner.contract.dao.ContractDAO;
import com.wc.planner.contract.vo.ContractVO;
import com.wc.planner.contract.vo.EtVO;
import com.wc.planner.contract.vo.HallVO;
import com.wc.planner.contract.vo.ShopVO;
import com.wc.planner.couple.dao.CoupleDAO;
import com.wc.planner.couple.vo.C_memoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("contractService")
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractDAO contractDAO;
    @Autowired
    private ContractVO contractVO;
    @Autowired
    private CoupleVO coupleVO;


    @Override
    public List selectCoupleName(String name) throws Exception {
        return contractDAO.selectCoupleName(name);
    }

    @Override
    public void addContract(int num) throws Exception {
        coupleVO = contractDAO.coupleInfo(num);
        contractVO.setC_mem_num(coupleVO.getC_mem_num());
        contractVO.setC_p_id(coupleVO.getC_planner());
        contractVO.setC_p_name(coupleVO.getC_p_name());
        contractVO.setC_day(coupleVO.getC_day());
        contractVO.setC_man(coupleVO.getC_man());
        contractVO.setC_woman(coupleVO.getC_woman());
        contractDAO.addContract(contractVO);
    }

    @Override
    public int aCountContract() throws Exception {
        return contractDAO.aCountContract();
    }

    @Override
    public int pCountContract(String id) throws Exception {
        return contractDAO.pCountContract(id);
    }

    @Override
    public int searchCountCon(String name) throws Exception {
        return contractDAO.searchCountCon(name);
    }

    @Override
    public int searchCon(String name) throws Exception {
        return contractDAO.searchCon(name);
    }

    @Override
    public int selectContNum(int num) throws Exception {
        return contractDAO.selectContNum(num);
    }

    @Override
    public List aConCriteria(PagingVO pagingVO) throws Exception {
        return contractDAO.aConCriteria(pagingVO);
    }

    @Override
    public List pConCriteria(PagingVO pagingVO,String id) throws Exception {
        pagingVO.setId_name(id);
        return contractDAO.pConCriteria(pagingVO);
    }

    @Override
    public List searchConCriteria(PagingVO pagingVO, String name) throws Exception {
        pagingVO.setId_name(name);
        return contractDAO.searchConCriteria(pagingVO);
    }

    @Override
    public List searchCriteria(PagingVO pagingVO, String name) throws Exception {
        pagingVO.setId_name(name);
        return contractDAO.searchCriteria(pagingVO);
    }

    @Override
    public List selectPlannerName() throws Exception {
        return contractDAO.selectPlannerName();
    }

    @Override
    public HallVO selectHall(int num) throws Exception {
        return contractDAO.selectHall(num);
    }

    @Override
    public ShopVO selectShop(int num) throws Exception {
        return contractDAO.selectShop(num);
    }

    @Override
    public EtVO selectEt(int num) throws Exception {
        return contractDAO.selectEt(num);
    }

    @Override
    public ContractVO selectCont(int num) throws Exception {
        return contractDAO.selectCont(num);
    }

    @Override
    public void addHSE(int num) throws Exception {
        contractDAO.addHall(num);
        contractDAO.addShop(num);
        contractDAO.addEt(num);
    }

    @Override
    public void updateHall(ContractVO contractVO,HallVO hallVO) throws Exception {
        contractDAO.updateContHall(contractVO);
        contractDAO.updateHall(hallVO);
    }

    @Override
    public void updateShop(ContractVO contractVO,ShopVO shopVO) throws Exception {
        contractDAO.updateContShop(contractVO);
        contractDAO.updateShop(shopVO);
    }

    @Override
    public void updateEt(ContractVO contractVO,EtVO etVO) throws Exception {
        contractDAO.updateContEt(contractVO);
        contractDAO.updateEt(etVO);
    }
}
