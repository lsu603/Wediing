package com.wc.board.service;

import com.wc.admin.planner.PagingVO;
import com.wc.board.dao.BoardDAO;
import com.wc.board.vo.ArticleVO;
import com.wc.board.vo.ImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardDAO boardDAO;

    @Override
    public List<ArticleVO> listArticles() throws Exception {
        List<ArticleVO> articlesList =  boardDAO.selectAllArticlesList(); //DAO의 모든 글 조회 메소드 호출
        return articlesList; //모든글을 조회한 목록 반환
    }

    @Override
    public List<ArticleVO> articleCriteria(PagingVO pagingVO) throws Exception {
        return boardDAO.articleCriteria(pagingVO);
    }

    @Override
    public int CountArticle() throws DataAccessException {
        return boardDAO.CountArticle();
    }

    @Override
    public int addNewArticle(Map articleMap) throws Exception {
        int articleNO = boardDAO.insertNewArticle(articleMap);
        articleMap.put("articleNO", articleNO); //글번호 저장후
        boardDAO.insertNewImage(articleMap);    //이미지 정보저장
        return articleNO;
    }

    @Override
    public Map viewArticle(int articleNO) throws Exception {
        Map articleMap = new HashMap();
        ArticleVO articleVO = boardDAO.selectArticle(articleNO);    //글정보 조회
        List<ImageVO> imageFileList = boardDAO.selectImageFileList(articleNO);  //이미지파일 정보 조회
        articleMap.put("article", articleVO);
        articleMap.put("imageFileList", imageFileList);
//		글정보와 이미지파일 정보를 Map에 담아서 반환
        return articleMap;
    }

    @Override
    public void modArticle(Map articleMap) throws Exception {
        int articleNO = 0;
        articleNO = Integer.parseInt((String) articleMap.get("articleNO"));
        boardDAO.deleteImage(articleNO);
        boardDAO.insertNewImage(articleMap);
    }

    @Override
    public Map modiForm(int articleNO) throws Exception {
        ArticleVO articleVO = boardDAO.selectArticle(articleNO);
        List<ImageVO> imageFileList = boardDAO.selectImageFileList(articleNO);
        Map<String,Object> articleMap = new HashMap();
        articleMap.put("article",articleVO);
        articleMap.put("image",imageFileList);
        return articleMap;
    }

    @Override
    public void updateArticle(ArticleVO articleVO) throws Exception {
        boardDAO.updateArticle(articleVO);
    }
}
