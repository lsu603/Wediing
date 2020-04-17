package com.wc.admin.planner.controller;

import com.wc.admin.planner.PagingVO;
import com.wc.admin.planner.service.AdminService;
import com.wc.b_member.vo.MemberVO;
import com.wc.planner.PlannerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller("adminController")
@RequestMapping(value = "/admin")
public class AdminControllerImpl implements AdminController {

    @Autowired
    private AdminService adminService;

    @Override
    @GetMapping("listPage")
    public ModelAndView plannerList(PagingVO vo, Model model
            , @RequestParam(value="nowPage", required=false)String nowPage
            , @RequestParam(value="cntPerPage", required=false)String cntPerPage) throws Exception {

        int total = adminService.countPlanners();
        ModelAndView mav = new ModelAndView();
        System.out.println("now : "+nowPage);
        System.out.println("cnt : "+cntPerPage);

        if (nowPage == null && cntPerPage == null) {
            nowPage = "1";
            cntPerPage = "10";
        } else if (nowPage == null) {
            nowPage = "1";
        } else if (cntPerPage == null) {
            cntPerPage = "10";
        }

        vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));

        mav.setViewName("/admin/listPage");
        mav.addObject("paging", vo);
        mav.addObject("listPlanner", adminService.listCriteria(vo));
        return mav;
    }

    @Override
    @RequestMapping (value = "/searchPlanner.do", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView searchPlanner(@RequestParam(required=false) String sch_id,HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        if (sch_id != null) {
            MemberVO vo = adminService.selectMemberID(sch_id);	//ID검색

            mav.addObject("member",vo);
        }
        mav.setViewName("/admin/searchPlanner");
        return mav;
    }

    @Override
    @RequestMapping(value = "/addPlanner.do", method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseEntity addPlanner(@RequestParam String id,HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String message = null;
        ResponseEntity resEntity = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        MemberVO member = adminService.selectMemberID(id);

        try {
            if (adminService.selectPlannerID(id) < 1) {
                adminService.insertNewPlanner(member);
                message = "<script>";
                message += " alert('플래너를 추가하였습니다.');";
                message += " location.href='" + request.getContextPath() + "/admin/listPage.do';";
                message += " </script>";
            }else {
                message = "<script>";
                message += " alert('이미 추가된 아이디입니다.');";
                message += " location.href='" + request.getContextPath() + "/admin/listPage.do';";
                message += " </script>";
            }
        } catch (Exception e) {
            message  = "<script>";
            message +=" alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
            message += " location.href='"+request.getContextPath()+"/admin/listPage.do';";
            message += " </script>";
            e.printStackTrace();
        }
        resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
        return resEntity;
    }

    @Override
    @RequestMapping(value = "/plannerInfo.do", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView plannerInfo(@RequestParam String p_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();

        PlannerVO plannerVO = adminService.plannerInfo(p_id);
        MemberVO memberVO = adminService.selectMemberID(p_id);

        mav.setViewName("/admin/plannerInfo");
        mav.addObject("planner", plannerVO);
        mav.addObject("member", memberVO);


        return mav;
    }

    @Override
    @RequestMapping(value = "/modifyInfo.do", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView modifyInfo(@ModelAttribute PlannerVO plannerVO,
                                   @ModelAttribute MemberVO memberVO,@RequestParam String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        Map<String, String> map = new HashMap<>();
        String message = null;

        System.out.println(memberVO.getB_tel());
        System.out.println(id);
        System.out.println(plannerVO.getP_memo());

        map.put("id",id);
        map.put("memo", plannerVO.getP_memo());
        map.put("tel",memberVO.getB_tel());

        try {
            adminService.updatePlanner(map);
            adminService.updateMember(map);
            message = "<script>";
            message += " alert('플래너 정보를 수정하였습니다.');";
            message += " location.href='" + request.getContextPath() + "/admin/plannerInfo.do?p_id="+id;
            message += " </script>";
        } catch (Exception e) {
            message  = "<script>";
            message +=" alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
            message += " location.href='"+request.getContextPath()+"/admin/listPage.do';";
            message += " </script>";
            e.printStackTrace();
        }

        PlannerVO planner = adminService.plannerInfo(id);
        MemberVO member = adminService.selectMemberID(id);

        mav.setViewName("/admin/plannerInfo");
        mav.addObject("planner", planner);
        mav.addObject("member", member);
        return mav;
    }

    @Override
    @RequestMapping (value = "/deletePlanner.do",method = {RequestMethod.DELETE,RequestMethod.GET})
    public ModelAndView deletePlanner(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("redirect:/admin/listPage.do");
        adminService.deletePlanner(id);
        System.out.println(id);
        return mav;
    }

}
