package com.wc.planner.contract.controller;

import com.sun.org.apache.xpath.internal.objects.XNull;
import com.wc.admin.planner.PagingVO;
import com.wc.b_member.vo.CoupleVO;
import com.wc.b_member.vo.MemberVO;
import com.wc.planner.PlannerVO;
import com.wc.planner.contract.service.ContractService;
import com.wc.planner.contract.vo.ContractVO;
import com.wc.planner.contract.vo.EtVO;
import com.wc.planner.contract.vo.HallVO;
import com.wc.planner.contract.vo.ShopVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller("contractController")
@RequestMapping(value = "planner/contract")
public class ContractControllerImpl implements ContractController {

    @Autowired
    private ContractService contractService;
    @Autowired
    private ContractVO contractVO;
    private List proList = Arrays.asList("없음", "가계약", "정계약", "취소");

    @Override
    @RequestMapping(value = "/listCont.do", method = RequestMethod.GET)
    public ModelAndView listCont(PagingVO pagingVO
            , @RequestParam(value = "nowPage", required = false) String nowPage
            , @RequestParam(value = "cntPerPage", required = false) String cntPerPage,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        MemberVO user = (MemberVO) session.getAttribute("memberInfo");
        int total = 0;
        String message = null;
        List listCont = null;
        String rank = (String) session.getAttribute("rank");
        boolean isLogOn = (boolean) session.getAttribute("isLogOn");

        mav.setViewName("/planner/listCont");


        if (isLogOn) {
            if ("planner".equals(rank)) {
                total = contractService.pCountContract(user.getB_id());
            }else if ("admin".equals(rank) ) {
                total = contractService.aCountContract();
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
            listCont = contractService.pConCriteria(pagingVO,user.getB_id());

        }else if ("admin".equals(rank)) {
            listCont = contractService.aConCriteria(pagingVO);

        }
         List plannerList = contractService.selectPlannerName();

        mav.addObject("proList",proList);
        mav.addObject("paging", pagingVO);
        mav.addObject("listCont", listCont);
        mav.addObject("plannerList", plannerList);
        mav.addObject("user", user.getB_name());
        return mav;
    }

    @RequestMapping(value = "/searchCouple.do",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView searchCouple(@RequestParam(required = false) String name, HttpServletRequest request, HttpServletResponse response)throws Exception {
        ModelAndView mav = new ModelAndView();
        List<CoupleVO> coupleList = null;
        if (name != null) {
            coupleList = contractService.selectCoupleName(name);	//ID검색
            mav.addObject("coupleList",coupleList);
        }
        mav.setViewName("/planner/searchCouple");
        return mav;
    }

    @Override
    @RequestMapping(value = "/addContCouple.do",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity addContCouple(@ModelAttribute int c_mem_num,HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String message = null;
        ResponseEntity resEntity = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        try {
            if (contractService.selectContNum(c_mem_num) > 0){
                message = "<script>";
                message += " alert('이미 추가된 계약입니다.');";
                message += " location.href='" + request.getContextPath() + "/planner/contract/listCont.do';";
                message += " </script>";
            }else {
                contractService.addContract(c_mem_num);
                contractService.addHSE(c_mem_num);
                message = "<script>";
                message += " alert('계약을 추가하였습니다.');";
                message += " location.href='" + request.getContextPath() + "/planner/contract/listCont.do';";
                message += " </script>";
            }
        } catch (Exception e) {
            message  = "<script>";
            message +=" alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
            message += " location.href='"+request.getContextPath()+"/planner/contract/listCont.do';";
            message += " </script>";
            e.printStackTrace();
        }
        resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
        return resEntity;
    }

    @Override
    @RequestMapping(value = "/searchPlannerCon.do" ,method = RequestMethod.GET)
    public ModelAndView searchPlannerCon(PagingVO pagingVO,@RequestParam String name
            , @RequestParam(value = "nowPage", required = false) String nowPage
            , @RequestParam(value = "cntPerPage", required = false) String cntPerPage,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        int total = 0;
        String message = null;
        List listCont = null;
        String rank = (String) session.getAttribute("rank");
        boolean isLogOn = (boolean) session.getAttribute("isLogOn");

        mav.setViewName("/planner/listCont");


        if (isLogOn) {
            if ("planner".equals(rank)||"admin".equals(rank)) {
                total = contractService.searchCountCon(name);

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
            listCont = contractService.searchConCriteria(pagingVO,name);
        }
         List plannerList = contractService.selectPlannerName();

        mav.addObject("paging", pagingVO);
        mav.addObject("listCont", listCont);
        mav.addObject("plannerList", plannerList);
        return mav;
    }

    @Override
    @RequestMapping(value = "/searchCont.do" ,method = RequestMethod.GET)
    public ModelAndView searchCont(PagingVO pagingVO,@RequestParam String name
            , @RequestParam(value = "nowPage", required = false) String nowPage
            , @RequestParam(value = "cntPerPage", required = false) String cntPerPage,
                                         HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        int total = 0;
        String message = null;
        List listCont = null;
        String rank = (String) session.getAttribute("rank");
        boolean isLogOn = (boolean) session.getAttribute("isLogOn");

        mav.setViewName("/planner/listCont");


        if (isLogOn) {
            if ("planner".equals(rank)||"admin".equals(rank)) {
                total = contractService.searchCon(name);

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
            listCont = contractService.searchCriteria(pagingVO,name);
        }
         List plannerList = contractService.selectPlannerName();

        mav.addObject("paging", pagingVO);
        mav.addObject("listCont", listCont);
        mav.addObject("plannerList", plannerList);
        return mav;
    }
    @Override
    @RequestMapping(value = "/conHall.do", method = RequestMethod.GET)
    public ModelAndView conHall(@RequestParam int c_mem_num,
                                @RequestParam(required = false) String meg,
                                HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mav = new ModelAndView("/planner/conHall");
        HttpSession session = request.getSession();
        String rank = (String) session.getAttribute("rank");
        boolean isLogOn = (boolean) session.getAttribute("isLogOn");
        HallVO hallVO = null;

        if (isLogOn) {
            if ("planner".equals(rank)||"admin".equals(rank) ) {
                hallVO = contractService.selectHall(c_mem_num);
                contractVO = contractService.selectCont(c_mem_num);
            }else
                mav.setViewName("/member/loginForm");
        } else {
            mav.setViewName("/member/loginForm");
        }

        if (meg != null) {
            mav.addObject("meg",meg);
        }
        mav.addObject("proList",proList);
        mav.addObject("hall",hallVO);
        mav.addObject("cont",contractVO);
        return mav;
    }
    @Override
    @RequestMapping(value = "/conShop.do", method = RequestMethod.GET)
    public ModelAndView conShop(@RequestParam int c_mem_num,
                                @RequestParam(required = false) String meg,HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mav = new ModelAndView("/planner/conShop");
        HttpSession session = request.getSession();
        String rank = (String) session.getAttribute("rank");
        boolean isLogOn = (boolean) session.getAttribute("isLogOn");
        ShopVO shopVO = null;

        if (isLogOn) {
            if ("planner".equals(rank)||"admin".equals(rank) ) {
                shopVO = contractService.selectShop(c_mem_num);
                contractVO = contractService.selectCont(c_mem_num);
            }else
                mav.setViewName("/member/loginForm");
        } else {
            mav.setViewName("/member/loginForm");
        }
        if (meg != null) {
            mav.addObject("meg", meg);
        }
        mav.addObject("proList",proList);
        mav.addObject("shop",shopVO);
        mav.addObject("cont",contractVO);
        return mav;
    }
    @Override
    @RequestMapping(value = "/conEt.do", method = RequestMethod.GET)
    public ModelAndView conEt(@RequestParam int c_mem_num,
                              @RequestParam(required = false) String meg,HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mav = new ModelAndView("/planner/conEt");
        HttpSession session = request.getSession();
        String rank = (String) session.getAttribute("rank");
        boolean isLogOn = (boolean) session.getAttribute("isLogOn");
        EtVO etVO = null;

        if (isLogOn) {
            if ("planner".equals(rank)||"admin".equals(rank) ) {
                etVO = contractService.selectEt(c_mem_num);
                contractVO = contractService.selectCont(c_mem_num);
            }else
                mav.setViewName("/member/loginForm");
        } else {
            mav.setViewName("/member/loginForm");
        }
        if (meg != null) {
            mav.addObject("meg", meg);
        }
        mav.addObject("proList",proList);
        mav.addObject("et",etVO);
        mav.addObject("cont",contractVO);
        return mav;
    }

    @Override
    @RequestMapping(value = "/modifyHall.do", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView modifyHall(@RequestParam int c_mem_num,HallVO hallVO, ContractVO contractVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("/member/loginForm");
        HttpSession session = request.getSession();
        String rank = (String) session.getAttribute("rank");
        boolean isLogOn = (boolean) session.getAttribute("isLogOn");
        String message = null;

        if (isLogOn) {
            if ("planner".equals(rank)||"admin".equals(rank) ) {
                try {
                    contractVO.setC_mem_num(c_mem_num);
                    contractService.updateHall(contractVO, hallVO);
                    message = "'수정되었습니다.'";
                    mav.setViewName("redirect:/planner/contract/conHall.do?c_mem_num=" + c_mem_num + "&meg=" + message);
                } catch (Exception e) {
                    message ="'작업 중 오류가 발생했습니다. 다시 시도해 주세요'";
                    e.printStackTrace();
                    mav.setViewName("redirect:/planner/contract/conHall.do?c_mem_num=" + c_mem_num + "&meg=" + message);
                }
            }
        }

        return mav;
    }

    @Override
    @RequestMapping(value = "/modifyShop.do", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView modifyShop(@RequestParam int c_mem_num,ShopVO shopVO, ContractVO contractVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("/member/loginForm");
        HttpSession session = request.getSession();
        String rank = (String) session.getAttribute("rank");
        boolean isLogOn = (boolean) session.getAttribute("isLogOn");
        String message = null;

        if (isLogOn) {
            if ("planner".equals(rank)||"admin".equals(rank) ) {
                try {
                    contractVO.setC_mem_num(c_mem_num);
                    contractService.updateShop(contractVO, shopVO);
                    System.out.println(contractVO.getC_mem_num());
                    message = "'수정되었습니다.'";
                    mav.setViewName("redirect:/planner/contract/conShop.do?c_mem_num=" + c_mem_num + "&meg=" + message);
                } catch (Exception e) {
                    message ="'작업 중 오류가 발생했습니다. 다시 시도해 주세요'";
                    e.printStackTrace();
                    mav.setViewName("redirect:/planner/contract/conShop.do?c_mem_num=" + c_mem_num + "&meg=" + message);
                }
            }
        }

        return mav;
    }
    @Override
    @RequestMapping(value = "/modifyEt.do", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView modifyEt(@RequestParam int c_mem_num,EtVO etVO, ContractVO contractVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("/member/loginForm");
        HttpSession session = request.getSession();
        String rank = (String) session.getAttribute("rank");
        boolean isLogOn = (boolean) session.getAttribute("isLogOn");
        String message = null;

        if (isLogOn) {
            if ("planner".equals(rank)||"admin".equals(rank) ) {
                try {
                    contractVO.setC_mem_num(c_mem_num);
                    etVO.setC_mem_num(c_mem_num);
                    contractService.updateEt(contractVO, etVO);
                    System.out.println(etVO.getC_et_memo());
                    System.out.println(contractVO.getC_et());
                    message = "'수정되었습니다.'";
                    mav.setViewName("redirect:/planner/contract/conEt.do?c_mem_num=" + c_mem_num + "&meg=" + message);
                } catch (Exception e) {
                    message ="'작업 중 오류가 발생했습니다. 다시 시도해 주세요'";
                    e.printStackTrace();
                    mav.setViewName("redirect:/planner/contract/conEt.do?c_mem_num=" + c_mem_num + "&meg=" + message);
                }
            }
        }

        return mav;
    }
}
