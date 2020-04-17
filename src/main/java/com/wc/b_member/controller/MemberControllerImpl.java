package com.wc.b_member.controller;

import com.wc.b_member.service.MemberService;
import com.wc.b_member.vo.CoupleVO;
import com.wc.b_member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller("memberController")
@RequestMapping(value = "/member")
public class MemberControllerImpl implements MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberVO memberVO;
    @Autowired
    private CoupleVO coupleVO;



    @RequestMapping (value = {"/loginForm.do","/memberForm.do"})
    public  ModelAndView loginForm (HttpServletRequest request, HttpServletResponse response)throws Exception{
        ModelAndView mav = new ModelAndView();
        mav.setViewName(getViewName(request));
        return mav;
    }

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public ModelAndView main (@RequestParam Map<String, String> loginMap,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        memberVO = memberService.login(loginMap);
        HttpSession session = request.getSession();

        if (memberVO != null && memberVO.getB_id() != null) {
            session.setAttribute("isLogOn", true);
            session.setAttribute("memberInfo", memberVO);
            String id = memberVO.getB_id();

            if (memberService.selectPlanner(id).equals("true")) {
                session.setAttribute("rank", "planner");
                if (memberVO.getB_rank()==true){
                    session.removeAttribute("rank");
                    session.setAttribute("rank","admin");
                }
                mav.setViewName("forward:/member/loginForm.do");
            } else {
                mav.setViewName("forward:/member/loginForm.do");
            }
        }else{
                String message="아이디나  비밀번호가 틀립니다. 다시 로그인해주세요";
                mav.addObject("message", message);
                mav.setViewName("/member/loginForm.do");
            }
            return mav;
    }

    @Override
    @RequestMapping(value="/logout.do" ,method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        HttpSession session=request.getSession();
        session.setAttribute("isLogOn", false);
        session.removeAttribute("memberInfo");
        session.removeAttribute("rank");
        mav.setViewName("redirect:/member/loginForm.do");
        return mav;
    }

    @Override
    @RequestMapping(value="/addMember.do" ,method = RequestMethod.POST)
    public ResponseEntity addMember(@ModelAttribute("memberVO") MemberVO _memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String message = null;
        ResponseEntity resEntity = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        try {
            memberService.addMember(_memberVO);	//DB에 회원추가
            memberService.addCouple(coupleInfo(_memberVO));
            message  = "<script>";
            message +=" alert('회원 가입을 마쳤습니다.로그인창으로 이동합니다.');";
            message += " location.href='"+request.getContextPath()+"/member/loginForm.do';";
            message += " </script>";

        }catch(Exception e) {
            message  = "<script>";
            message +=" alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
            message += " location.href='"+request.getContextPath()+"/member/memberForm.do';";
            message += " </script>";
            e.printStackTrace();
        }
        resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
        return resEntity;

    }

    @Override
    @RequestMapping(value="/overlapped.do" ,method = RequestMethod.POST)
    public ResponseEntity overlapped(String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseEntity resEntity = null;
        String result = memberService.overlapped(id);	//ID중복검사
        resEntity =new ResponseEntity(result, HttpStatus.OK);
        return resEntity;
    }

    private CoupleVO coupleInfo (MemberVO _memberVO) throws Exception{
        boolean gender = _memberVO.getB_gender();
        String con = "";
        HashMap<String, Boolean> conCh = new HashMap<String, Boolean>();
        CoupleVO cVO = new CoupleVO();

        conCh.put("웨딩홀",_memberVO.getB_hall());
        conCh.put("스드메",_memberVO.getB_shop());
        conCh.put("기타",_memberVO.getB_et());
        System.out.println(conCh.get("웨딩홀"));
        System.out.println(conCh.get("스드메"));
        System.out.println(conCh.get("기타"));

        System.out.println(conCh);
        //관심사 문자열로 저장
        for (Map.Entry<String, Boolean> entry: conCh.entrySet()) {
            System.out.println("key : " + entry.getKey() + " / value : " + entry.getValue());
            if (entry.getValue().equals(true)) {
                con += entry.getKey() + " ";
            }
        }
        cVO.setC_con(con);
        System.out.println(con);
        //가입정보가 여자일때
        if (gender==true) {
            cVO.setC_woman(_memberVO.getB_name());
            cVO.setC_w_tel(_memberVO.getB_tel());
        } else {
            cVO.setC_man(_memberVO.getB_name());
            cVO.setC_m_tel(_memberVO.getB_tel());
        }
        System.out.println(cVO);
        return cVO;
    }

    private String getViewName(HttpServletRequest request) throws Exception {
        String contextPath = request.getContextPath();
        String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
        if (uri == null || uri.trim().equals("")) {
            uri = request.getRequestURI();
        }

        int begin = 0;
        if (!((contextPath == null) || ("".equals(contextPath)))) {
            begin = contextPath.length();
        }

        int end;
        if (uri.indexOf(";") != -1) {
            end = uri.indexOf(";");
        } else if (uri.indexOf("?") != -1) {
            end = uri.indexOf("?");
        } else {
            end = uri.length();
        }

        String viewName = uri.substring(begin, end);
        if (viewName.indexOf(".") != -1) {
            viewName = viewName.substring(0, viewName.lastIndexOf("."));
        }
        if (viewName.lastIndexOf("/") != -1) {
            viewName = viewName.substring(viewName.lastIndexOf("/",1), viewName.length());
        }
        return viewName;
    }


}
