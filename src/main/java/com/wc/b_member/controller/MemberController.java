package com.wc.b_member.controller;

import com.wc.b_member.vo.MemberVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public interface MemberController {
    public ModelAndView main (@RequestParam Map<String, String> loginMap, HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ResponseEntity addMember(@ModelAttribute("member") MemberVO member,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ResponseEntity overlapped(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
