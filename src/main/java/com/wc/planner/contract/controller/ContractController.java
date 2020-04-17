package com.wc.planner.contract.controller;

import com.wc.admin.planner.PagingVO;
import com.wc.b_member.vo.CoupleVO;
import com.wc.planner.contract.vo.ContractVO;
import com.wc.planner.contract.vo.EtVO;
import com.wc.planner.contract.vo.HallVO;
import com.wc.planner.contract.vo.ShopVO;
import com.wc.planner.couple.vo.C_memoVO;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ContractController {
    public ModelAndView listCont(PagingVO pagingVO
            , @RequestParam(value = "nowPage", required = false) String nowPage
            , @RequestParam(value = "cntPerPage", required = false) String cntPerPage,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ModelAndView searchCouple(@RequestParam String name,HttpServletRequest request, HttpServletResponse response)throws Exception;
    public ResponseEntity addContCouple(@RequestParam int c_mem_num, HttpServletRequest request, HttpServletResponse response)throws Exception;
    public ModelAndView searchPlannerCon(PagingVO pagingVO,@RequestParam String name
            , @RequestParam(value = "nowPage", required = false) String nowPage
            , @RequestParam(value = "cntPerPage", required = false) String cntPerPage,
                                         HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ModelAndView searchCont(PagingVO pagingVO,@RequestParam String name
            , @RequestParam(value = "nowPage", required = false) String nowPage
            , @RequestParam(value = "cntPerPage", required = false) String cntPerPage,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ModelAndView conHall(@RequestParam int c_mem_num,@RequestParam String meg, HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ModelAndView conShop(@RequestParam int c_mem_num,@RequestParam(required = false) String meg, HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ModelAndView conEt(@RequestParam int c_mem_num, @RequestParam(required = false) String meg, HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ModelAndView modifyHall(@RequestParam int c_mem_num,@ModelAttribute HallVO hallVO, @ModelAttribute ContractVO contractVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ModelAndView modifyShop(@RequestParam int c_mem_num, ShopVO shopVO, ContractVO contractVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ModelAndView modifyEt(@RequestParam int c_mem_num, EtVO etVO, ContractVO contractVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
