package com.wc.board.service;

import com.wc.admin.planner.PagingVO;
import com.wc.b_member.vo.CoupleVO;
import com.wc.board.vo.ArticleVO;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface BoardService {
    public List<ArticleVO> listArticles() throws Exception;
    public List<ArticleVO> articleCriteria(PagingVO pagingVO) throws Exception;
    public int CountArticle() throws DataAccessException;
    public int addNewArticle(Map articleMap)throws Exception;
    public Map viewArticle(int articleNO) throws Exception;
    public void modArticle(Map articleMap) throws Exception;
    public Map modiForm(int articleNO) throws Exception;
    public void updateArticle(ArticleVO articleVO) throws Exception;
}
