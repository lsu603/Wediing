package com.wc.planner.couple.controller;

import com.wc.admin.planner.PagingVO;
import com.wc.b_member.vo.CoupleVO;
import com.wc.b_member.vo.MemberVO;
import com.wc.planner.couple.service.CoupleService;
import com.wc.planner.couple.vo.C_memoVO;
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
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller("coupleController")
@RequestMapping(value = "planner/couple")
public class CoupleControllerImpl implements CoupleController {

    @Autowired
    private CoupleService plannerService;

    @Autowired
    private CoupleVO coupleVO;
    @Autowired
    private C_memoVO c_memoVO;

    private List proList = Arrays.asList("없음", "가", "정", "완료", "취소");


    @Override
    @GetMapping("listCouple")
    public ModelAndView listCouple(PagingVO pagingVO,Model model
            , @RequestParam(value = "nowPage", required = false) String nowPage
            , @RequestParam(value = "cntPerPage", required = false) String cntPerPage,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        MemberVO user = (MemberVO) session.getAttribute("memberInfo");
        int total = 0;
        String message = null;
        List listCouple = null;
        String rank = (String) session.getAttribute("rank");
        boolean isLogOn = (boolean) session.getAttribute("isLogOn");

        mav.setViewName("/planner/listCouple");


        if (isLogOn) {
            if ("planner".equals(rank)) {
                total = plannerService.pCountCouples(user.getB_id());
            }else if ("admin".equals(rank) ) {
                total = plannerService.aCountCouples();
            }else
            mav.setViewName("/member/loginForm");
        } else {
            mav.setViewName("/member/loginForm");
        }


        if (nowPage == null && cntPerPage == null) {
            nowPage = "1";
            cntPerPage = "10";
        } else if (nowPage == null) {
            nowPage = "1";
        } else if (cntPerPage == null) {
            cntPerPage = "10";
        }
        pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));

        if ("planner".equals(rank)) {
            pagingVO.setId_name(user.getB_id());
            listCouple = plannerService.pMemCriteria(pagingVO);

        }else if ("admin".equals(rank)) {
            listCouple = plannerService.aMemCriteria(pagingVO);

        }


        mav.addObject("paging", pagingVO);
        mav.addObject("listCouple", listCouple);
        return mav;
    }

    @Override
    @RequestMapping(value = "/coupleInfo.do", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView coupleInfo(@RequestParam int c_num,HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        boolean isLogOn = (boolean) session.getAttribute("isLogOn");
        String rank = (String) session.getAttribute("rank");

        ModelAndView mav = new ModelAndView("/planner/coupleInfo");
        List memoList = null;
        List plannerList = null;

        System.out.println(c_num);

        if (isLogOn) {
            if ("planner".equals(rank)||"admin".equals(rank)) {
                coupleVO = plannerService.coupleInfo(c_num);
                memoList = plannerService.selectC_Memo(c_num);
                plannerList = plannerService.selectPlannerName();
            }else
                mav.setViewName("/member/loginForm");
        } else {
            mav.setViewName("/member/loginForm");
        }

        mav.addObject("couple", coupleVO);
        mav.addObject("memoList", memoList);
        mav.addObject("plannerList", plannerList);
        mav.addObject("proList", proList);

        return  mav;
    }

    @Override
    @RequestMapping (value = "/searchPlanner.do", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView searchPlanner(@RequestParam(required=false) String sch_name,
                                      @RequestParam(defaultValue = "0") int c_mem_num,HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        if (sch_name != null) {
            List<PlannerVO> plannerList = plannerService.searchPlannerName(sch_name);	//ID검색

            mav.addObject("plannerList",plannerList);
        }
        System.out.println(c_mem_num);
        mav.setViewName("/planner/searchPlanner");
        mav.addObject("c_mem_num", c_mem_num);
        return mav;
    }
    @Override
    @RequestMapping(value = "/modifyCouplePlanner.do", method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseEntity modifyCouplePlanner(@RequestParam String p_id,
                                              @RequestParam int c_mem_num,HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String message = null;
        ResponseEntity resEntity = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        PlannerVO planner = (PlannerVO) plannerService.searchPlannerId(p_id);
        System.out.println(planner.getP_id());
        System.out.println(planner.getP_name());
        coupleVO.setC_p_name(planner.getP_name());
        coupleVO.setC_planner(planner.getP_id());
        coupleVO.setC_mem_num(c_mem_num);
        System.out.println(c_mem_num);

        try {
            plannerService.updateCouplePlanner(coupleVO);
            message = "<script>";
            message += " alert('플래너를 변경하였습니다.');";
            message += " location.href='" + request.getContextPath() + "/planner/couple/coupleInfo.do?c_num="+c_mem_num+"';";
            message += " </script>";
        } catch (Exception e) {
            message  = "<script>";
            message +=" alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
            message += " location.href='"+request.getContextPath()+"/planner/couple/listPage.do';";
            message += " </script>";
            e.printStackTrace();
        }
        resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
        return resEntity;
    }

    @Override
    @RequestMapping(value = "/modifyInfo.do" , method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView modifyInfo(@RequestParam int c_num,
                                   @ModelAttribute CoupleVO couple,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        boolean isLogOn = (boolean) session.getAttribute("isLogOn");
        String rank = (String) session.getAttribute("rank");
        String message = null;

        ModelAndView mav = new ModelAndView("/planner/coupleInfo");
        List memoList = null;
        List plannerList = null;

        if (isLogOn) {
            if ("planner".equals(rank)||"admin".equals(rank)) {
                try {
                    couple.setC_mem_num(c_num);
                    plannerService.updateCouple(couple);
                    message = "'수정되었습니다.'";
                } catch (Exception e) {
                    message ="'작업 중 오류가 발생했습니다. 다시 시도해 주세요'";
                    mav.setViewName("/planner/listCouple.do");
                    e.printStackTrace();
                }
                coupleVO = plannerService.coupleInfo(c_num);
                memoList = plannerService.selectC_Memo(c_num);
                plannerList = plannerService.selectPlannerName();
            }else
                mav.setViewName("/member/loginForm");
        } else {
            mav.setViewName("/member/loginForm");
        }

        mav.addObject("couple", couple);
        mav.addObject("memoList", memoList);
        mav.addObject("plannerList", plannerList);
        mav.addObject("proList", proList);
        mav.addObject("msg", message);
        return  mav;
    }

    @Override
    @RequestMapping(value = "/addMemo.do" , method = {RequestMethod.POST,RequestMethod.GET} )
    public ModelAndView addMemo(@RequestParam int c_num,
                                @ModelAttribute C_memoVO c_memoVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        boolean isLogOn = (boolean) session.getAttribute("isLogOn");
        String rank = (String) session.getAttribute("rank");
        String message = null;

        ModelAndView mav = new ModelAndView("/planner/coupleInfo");
        List memoList = null;
        List plannerList = null;

        if (isLogOn) {
            if ("planner".equals(rank)||"admin".equals(rank)) {
                try {
                    c_memoVO.setC_mem_num(c_num);
                    plannerService.addC_Memo(c_memoVO);
                } catch (Exception e) {
                    message ="작업 중 오류가 발생했습니다. 다시 시도해 주세요";
                    mav.setViewName("/planner/listCouple");
                    e.printStackTrace();
                }
                coupleVO = plannerService.coupleInfo(c_num);
                memoList = plannerService.selectC_Memo(c_num);
                plannerList = plannerService.selectPlannerName();
            }else
                mav.setViewName("/member/loginForm");
        } else {
            mav.setViewName("/member/loginForm");
        }

        mav.addObject("couple", coupleVO);
        mav.addObject("memoList", memoList);
        mav.addObject("plannerList", plannerList);
        mav.addObject("proList", proList);
        mav.addObject("msg", message);
        return  mav;
    }

    @Override
    @RequestMapping(value = "/searchCouple.do" ,method = RequestMethod.GET)
    public ModelAndView searchCouple(PagingVO pagingVO,@RequestParam String name
            , @RequestParam(value = "nowPage", required = false) String nowPage
            , @RequestParam(value = "cntPerPage", required = false) String cntPerPage,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
//        MemberVO user = (MemberVO) session.getAttribute("memberInfo");
        int total = 0;
        String message = null;
        List listCouple = null;
        String rank = (String) session.getAttribute("rank");
        boolean isLogOn = (boolean) session.getAttribute("isLogOn");

        mav.setViewName("/planner/listCouple");


        if (isLogOn) {
            if ("planner".equals(rank)||"admin".equals(rank)) {
                total = plannerService.searchCountCouples(name);

            }else
                mav.setViewName("/member/loginForm");
        } else {
            mav.setViewName("/member/loginForm");
        }


        if (nowPage == null && cntPerPage == null) {
            nowPage = "1";
            cntPerPage = "10";
        } else if (nowPage == null) {
            nowPage = "1";
        } else if (cntPerPage == null) {
            cntPerPage = "10";
        }
        pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));

        if ("planner".equals(rank) || "admin".equals(rank)) {
            pagingVO.setId_name(name);
            listCouple = plannerService.searchMemCriteria(pagingVO);
        }


        mav.addObject("paging", pagingVO);
        mav.addObject("listCouple", listCouple);
        return mav;
    }
}
