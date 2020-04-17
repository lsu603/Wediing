package com.wc.board.controller;

import com.wc.admin.planner.PagingVO;
import com.wc.b_member.vo.MemberVO;
import com.wc.board.service.BoardService;
import com.wc.board.vo.ArticleVO;
import com.wc.board.vo.ImageVO;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Controller("boardController")
@RequestMapping(value = "/board")
public class BoardControllerImpl implements BoardController {
    private static final String ARTICLE_IMAGE_REPO = "C:\\Intellij_Project\\Wediing\\web\\resources\\board\\article_image";
    @Autowired
    private BoardService boardService;
    @Autowired
    ArticleVO articleVO;

    @RequestMapping(value= "/listArticles.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listArticles(PagingVO pagingVO
            , @RequestParam(value = "nowPage", required = false) String nowPage
            , @RequestParam(value = "cntPerPage", required = false) String cntPerPage, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        boolean isLogOn = (boolean) session.getAttribute("isLogOn");
        String rank = (String) session.getAttribute("rank");
        MemberVO user = (MemberVO) session.getAttribute("memberInfo");
        int total = 0;
        ModelAndView mav = new ModelAndView("/board/listArticles");

        List articlesList =null;

        if (isLogOn) {
            if ("planner".equals(rank)||"admin".equals(rank)) {
                total = boardService.CountArticle();
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

        if ("planner".equals(rank)||"admin".equals(rank)) {
            pagingVO.setId_name(user.getB_name());
            articlesList = boardService.articleCriteria(pagingVO);
        }

        mav.addObject("user", user);
        mav.addObject("paging", pagingVO);
        mav.addObject("articlesList", articlesList); //글정보 바인딩 후 jsp로 전달
        return mav;
    }

    @RequestMapping(value="/viewArticle.do" ,method = RequestMethod.GET)
    public ModelAndView viewArticle(@RequestParam("articleNO") int articleNO,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        MemberVO user = (MemberVO) session.getAttribute("memberInfo");
        Map articleMap=boardService.viewArticle(articleNO);
        //글정보와 이미지정보를 담은 map을 가져옴
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/board/viewArticle");
        mav.addObject("articleMap", articleMap);
        mav.addObject("user",user);
        //글정보와 이미지정보를 jsp파일로 보냄
        return mav;
    }


    @RequestMapping(value = "/articleForm.do", method =  RequestMethod.GET)
    private ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("/board/articleForm");
        return mav;
        //글쓰기 창 나타냄
    }

    @RequestMapping(value="/addNewArticle.do" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
        multipartRequest.setCharacterEncoding("utf-8");
        String imageFileName=null;

        Map articleMap = new HashMap(); //글 정보를 담을 map
        Enumeration enu=multipartRequest.getParameterNames();   //글정보의 name(key)값들 저장
        while(enu.hasMoreElements()){
            String name=(String)enu.nextElement();
            String value=multipartRequest.getParameter(name);
//		key값으로 value값을 가져와 글정보를 담는 map에 저장
            articleMap.put(name,value);
        }

        //로그인 시 세션에 저장된 회원 정보에서 글쓴이 아이디를 얻어와서 Map에 저장합니다.
        HttpSession session = multipartRequest.getSession();
        MemberVO user = (MemberVO) session.getAttribute("memberInfo");  //로그인중인 member정보
        String name = user.getB_name();
        String id = user.getB_id();
        articleMap.put("id",id);
        articleMap.put("name",name);
//	로그인중인 id값 (작성자 id)


        List<String> fileList =upload(multipartRequest);
//	첨부한 파일이름을 list에 저장
        List<ImageVO> imageFileList = new ArrayList<ImageVO>();
        if(fileList!= null && fileList.size()!=0) {
//	    파일이 존재할때
            for(String fileName : fileList) {
                ImageVO imageVO = new ImageVO();
                imageVO.setImageFileName(fileName);
//			파일이름으로 이미지를 하나씩 꺼내서 imageFileList에 저장
                imageFileList.add(imageVO);
            }
            articleMap.put("imageFileList", imageFileList);
        }
        String message;
        ResponseEntity resEnt=null;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        try {
            int articleNO = boardService.addNewArticle(articleMap); //DB에 새 글 추가
            if(imageFileList!=null && imageFileList.size()!=0) {
                //파일이 존재할때
                System.out.println("222");
                for(ImageVO  imageVO:imageFileList) {
                    imageFileName = imageVO.getImageFileName(); //파일이름 저장
                    File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
                    File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
//                    destDir.mkdirs();
                    FileUtils.moveFileToDirectory(srcFile, destDir,true);
                    //srcFile을 destDir폴더로 이동, 디렉토리 생성까지
                }
            }

            message = "<script>";
            message += " alert('새글을 추가했습니다.');";
            message += " location.href='"+multipartRequest.getContextPath()+"/board/listArticles.do'; ";
            message +=" </script>";
            resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);


        }catch(Exception e) {
            if(imageFileList!=null && imageFileList.size()!=0) {
                for(ImageVO  imageVO:imageFileList) {
                    imageFileName = imageVO.getImageFileName();
                    File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
                    srcFile.delete();
//		 	오류발생시 temp폴더의 이미지 모두 삭제
                }
            }

            message = " <script>";
            message +=" alert('오류가 발생했습니다. 다시 시도해 주세요');');";
            message +=" location.href='"+multipartRequest.getContextPath()+"/board/articleForm.do'; ";
            message +=" </script>";
            resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
            e.printStackTrace();
        }
        return resEnt;
    }

    @RequestMapping(value="/modify.do" ,method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView modifyArti(@RequestParam int articleNO,
            HttpServletRequest request,  //업로드,다운로드 관련은 multipartRequest
            HttpServletResponse response) throws Exception{
        ModelAndView mav = new ModelAndView("/board/modify");

        Map<String, Object> articleMap = new HashMap<>();
        articleMap = boardService.modiForm(articleNO);

        mav.addObject("articleMap", articleMap);
        return mav;

    }


    @RequestMapping(value="/modArticle.do" ,method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ResponseEntity modArticle(
            MultipartHttpServletRequest multipartRequest,  //업로드,다운로드 관련은 multipartRequest
                                     HttpServletResponse response) throws Exception{
        multipartRequest.setCharacterEncoding("utf-8");
        Map<String,Object> articleMap = new HashMap<String, Object>();  //글 정보를 담을 map
        Enumeration enu=multipartRequest.getParameterNames();//request의 정보의 name(key)의 값들만 저장
        String imageFileName=null;

        while(enu.hasMoreElements()){
            //name값을 하나씩 꺼냄
            String name=(String)enu.nextElement();
            String value=multipartRequest.getParameter(name);
            //key값으로 value값 저장
            articleMap.put(name,value);
            //map에 글 정보를 하나씩 저장
        }

        List<String> fileList =upload(multipartRequest);
//	첨부한 파일이름을 list에 저장
        List<ImageVO> imageFileList = new ArrayList<ImageVO>();
        if(fileList!= null && fileList.size()!=0) {
//	    파일이 존재할때
            for(String fileName : fileList) {
                ImageVO imageVO = new ImageVO();
                imageVO.setImageFileName(fileName);
//			파일이름으로 이미지를 하나씩 꺼내서 imageFileList에 저장
                imageFileList.add(imageVO);
            }
            articleMap.put("imageFileList", imageFileList);
        }
        System.out.println(articleMap.get("articleNO"));
        articleVO.setContent((String) articleMap.get("content"));
        articleVO.setTitle((String) articleMap.get("title"));
        int articleNO=0;   //글번호 가져옴
        articleNO = Integer.parseInt((String) articleMap.get("articleNO"));
        articleVO.setArticleNO(articleNO);

        String message;
        ResponseEntity resEnt=null;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");

        try {
            boardService.updateArticle(articleVO);

            if (imageFileList != null && imageFileList.size() != 0) {
                //파일이 존재할때
                boardService.modArticle(articleMap);
                File Dir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
                FileUtils.deleteDirectory(Dir); //폴더,파일 삭제
                for (ImageVO imageVO : imageFileList) {
                    imageFileName = imageVO.getImageFileName(); //파일이름 저장
                    File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
                    File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
                    destDir.mkdirs();
                    FileUtils.moveFileToDirectory(srcFile, destDir, true);
                    //srcFile을 destDir폴더로 이동, 디렉토리 생성까지
                }
            }
            message = "<script>";
            message += " alert('글을 수정했습니다.');";
            message += " location.href='"+multipartRequest.getContextPath()+"/board/viewArticle.do?articleNO="+articleNO+"';";
            message +=" </script>";
            resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
        }catch(Exception e) {
            if (imageFileList != null && imageFileList.size() != 0) {
                for (ImageVO imageVO : imageFileList) {
                    imageFileName = imageVO.getImageFileName();
                    File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
                    srcFile.delete();
//		 	오류발생시 temp폴더의 이미지 모두 삭제
                }
            }
            message = "<script>";
            message += " alert('오류가 발생했습니다.다시 수정해주세요');";
            message += " location.href='"+multipartRequest.getContextPath()+"/board/viewArticle.do?articleNO="+articleNO+"';";
            message +=" </script>";
            resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
                e.printStackTrace();
        }
        return resEnt;
    }

    //다중 이미지 업로드하기
    private List<String> upload(MultipartHttpServletRequest multipartRequest) throws Exception{
        List<String> fileList= new ArrayList<String>();
        Iterator<String> fileNames = multipartRequest.getFileNames();
        while(fileNames.hasNext()){
//		    파일 이름이 존재할때
            String fileName = fileNames.next();
            MultipartFile mFile = multipartRequest.getFile(fileName);
            String originalFileName=mFile.getOriginalFilename();
            fileList.add(originalFileName);
            //이미지 파일들의 실제이름을 저장
            File file = new File(ARTICLE_IMAGE_REPO +"\\"+ fileName);
            if(mFile.getSize()!=0){ //mfile이 존재하고
                if(! file.exists()){ //file파일이 존재하지 않을 경우
                    if(file.getParentFile().mkdirs()){ //경로에 해당하는 디렉토리들을 생성
                        file.createNewFile(); //이후 파일 생성
                    }
                }
                mFile.transferTo(new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+ "\\"+originalFileName));
                //임시로 저장된 multipartFile을 실제 파일로 전송
                //mFile의 내용을 new file에 넣어서 저장 > 경로를 지정해 파일 저장 [비어있는 파일에 mfile내용저장]
            }
        }
        return fileList;
    }
}
