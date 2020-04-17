package com.wc.planner.contract.dao;

import com.wc.admin.planner.PagingVO;
import com.wc.b_member.vo.CoupleVO;
import com.wc.planner.PlannerVO;
import com.wc.planner.contract.vo.ContractVO;
import com.wc.planner.contract.vo.EtVO;
import com.wc.planner.contract.vo.HallVO;
import com.wc.planner.contract.vo.ShopVO;
import com.wc.planner.couple.vo.C_memoVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("contractDAO")
public class ContractDAOImpl implements ContractDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List selectCoupleName(String name) throws DataAccessException {
        return sqlSession.selectList("mapper.contract.selectCoupleName", name);
    }

    @Override
    public void addContract(ContractVO vo) throws DataAccessException {
        sqlSession.insert("mapper.contract.addContract", vo);
    }

    @Override
    public int aCountContract() throws DataAccessException {
        return sqlSession.selectOne("mapper.contract.aCountContract");
    }

    @Override
    public int pCountContract(String id) throws DataAccessException {
        return sqlSession.selectOne("mapper.contract.pCountContract",id);
    }

    @Override
    public int searchCountCon(String name) throws DataAccessException {
        return sqlSession.selectOne("mapper.contract.searchCountCon",name);
    }

    @Override
    public int searchCon(String name) throws DataAccessException {
        return sqlSession.selectOne("mapper.contract.searchCon",name);
    }

    @Override
    public int selectContNum(int num) throws DataAccessException {
        return sqlSession.selectOne("mapper.contract.selectContNum",num);
    }

    @Override
    public List aConCriteria(PagingVO pagingVO) throws DataAccessException {
        return sqlSession.selectList("mapper.contract.aConCriteria",pagingVO);
    }

    @Override
    public List pConCriteria(PagingVO pagingVO) throws DataAccessException {
        return sqlSession.selectList("mapper.contract.pConCriteria",pagingVO);
    }

    @Override
    public List searchConCriteria(PagingVO pagingVO) throws DataAccessException {
        return sqlSession.selectList("mapper.contract.searchConCriteria",pagingVO);
    }

    @Override
    public List searchCriteria(PagingVO pagingVO) throws DataAccessException {
        return sqlSession.selectList("mapper.contract.searchCriteria",pagingVO);
    }

    @Override
    public CoupleVO coupleInfo(int num) throws DataAccessException {
        return sqlSession.selectOne("mapper.planner.coupleInfo",num);
    }

    @Override
    public List selectPlannerName() throws DataAccessException {
        return sqlSession.selectList("mapper.planner.selectPlannerName");
    }

    @Override
    public HallVO selectHall(int num) throws DataAccessException {
        return sqlSession.selectOne("mapper.contract.selectHall",num);
    }

    @Override
    public ShopVO selectShop(int num) throws DataAccessException {
        return sqlSession.selectOne("mapper.contract.selectShop",num);
    }

    @Override
    public EtVO selectEt(int num) throws DataAccessException {
        return sqlSession.selectOne("mapper.contract.selectEt",num);
    }

    @Override
    public ContractVO selectCont(int num) throws DataAccessException {
        return sqlSession.selectOne("mapper.contract.selectCont",num);
    }

    @Override
    public void addHall(int num) throws DataAccessException {
        sqlSession.insert("mapper.contract.addHall", num);
    }

    @Override
    public void addShop(int num) throws DataAccessException {
        sqlSession.insert("mapper.contract.addShop", num);
    }

    @Override
    public void addEt(int num) throws DataAccessException {
        sqlSession.insert("mapper.contract.addEt", num);
    }

    @Override
    public void updateContHall(ContractVO contractVO) throws DataAccessException {
        sqlSession.update("mapper.contract.updateContHall", contractVO);
    }

    @Override
    public void updateContShop(ContractVO contractVO) throws DataAccessException {
        sqlSession.update("mapper.contract.updateContShop", contractVO);
    }

    @Override
    public void updateContEt(ContractVO contractVO) throws DataAccessException {
        sqlSession.update("mapper.contract.updateContEt", contractVO);
    }

    @Override
    public void updateHall(HallVO hallVO) throws DataAccessException {
        sqlSession.update("mapper.contract.updateHall", hallVO);
    }

    @Override
    public void updateShop(ShopVO shopVO) throws DataAccessException {
        sqlSession.update("mapper.contract.updateShop", shopVO);
    }

    @Override
    public void updateEt(EtVO etVO) throws DataAccessException {
        sqlSession.update("mapper.contract.updateEt", etVO);
    }
}
