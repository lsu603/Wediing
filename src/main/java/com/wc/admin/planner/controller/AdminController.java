package com.wc.admin.planner.controller;

import com.wc.admin.planner.PagingVO;
import com.wc.b_member.vo.MemberVO;
import com.wc.planner.PlannerVO;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AdminController {
    public ModelAndView plannerList(PagingVO vo, Model model
            , @RequestParam(value="nowPage", required=false)String nowPage
            , @RequestParam(value="cntPerPage", required=false)String cntPerPage) throws Exception;
    public ModelAndView searchPlanner(@RequestParam(required=false) String sch_id,HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ResponseEntity addPlanner (@RequestParam(required=false) String id, HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ModelAndView plannerInfo (@RequestParam String id, HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ModelAndView modifyInfo (@ModelAttribute PlannerVO plannerVO, @ModelAttribute MemberVO memberVO, @RequestParam String id, HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ModelAndView deletePlanner (@RequestParam String id, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
