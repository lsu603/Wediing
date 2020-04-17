package com.wc.board.dao;

import com.wc.admin.planner.PagingVO;
import com.wc.board.vo.ArticleVO;
import com.wc.board.vo.ImageVO;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface BoardDAO {
    public List selectAllArticlesList() throws DataAccessException;
    public List articleCriteria(PagingVO pagingVO) throws DataAccessException;
    public int CountArticle() throws DataAccessException;
    public int insertNewArticle(Map articleMap)throws DataAccessException;
    public void insertNewImage(Map articleMap)throws DataAccessException;
    public ArticleVO selectArticle(int articleNO) throws DataAccessException;
    public List selectImageFileList(int articleNO) throws DataAccessException;
    public void deleteImage(int no) throws DataAccessException;
    public void updateArticle(ArticleVO articleVO) throws DataAccessException;

}
