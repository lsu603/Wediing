package com.wc.planner.couple.controller;

import com.wc.admin.planner.PagingVO;
import com.wc.b_member.vo.CoupleVO;
import com.wc.planner.couple.vo.C_memoVO;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CoupleController {
    public ModelAndView listCouple(PagingVO pagingVO,Model model
            , @RequestParam(value="nowPage", required=false)String nowPage
            , @RequestParam(value="cntPerPage", required=false)String cntPerPage,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ModelAndView coupleInfo(@RequestParam int c_num,HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ModelAndView searchPlanner(@RequestParam(required=false) String sch_name,
                                      @RequestParam int c_mem_num,HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ResponseEntity modifyCouplePlanner(@RequestParam String p_id,
                                              @RequestParam int c_mem_num,HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ModelAndView modifyInfo(@RequestParam int c_num,
                                   @ModelAttribute CoupleVO coupleVO,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ModelAndView addMemo(@RequestParam int c_num,
                                @ModelAttribute C_memoVO c_memoVO,
                                HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ModelAndView searchCouple (PagingVO pagingVO,@RequestParam String name
            , @RequestParam(value = "nowPage", required = false) String nowPage
            , @RequestParam(value = "cntPerPage", required = false) String cntPerPage,
                                      HttpServletRequest request, HttpServletResponse response) throws Exception;

}
