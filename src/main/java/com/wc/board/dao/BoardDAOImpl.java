package com.wc.board.dao;

import com.wc.admin.planner.PagingVO;
import com.wc.board.vo.ArticleVO;
import com.wc.board.vo.ImageVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List selectAllArticlesList() throws DataAccessException {
        List<ArticleVO> articlesList = sqlSession.selectList("mapper.board.selectAllArticlesList");
//        sqlSession의 selectList로 board sql문인 selectAllArticlesList의 값을 가져옴
//        selectList는 여러개의 select값을 가져올때 사용
        return articlesList;
    }

    @Override
    public List articleCriteria(PagingVO pagingVO) throws DataAccessException {
        return sqlSession.selectList("mapper.board.articleCriteria",pagingVO);
    }

    @Override
    public int CountArticle() throws DataAccessException {
        return sqlSession.selectOne("mapper.board.CountArticle");
    }

    @Override
    public int insertNewArticle(Map articleMap) throws DataAccessException {
        sqlSession.insert("mapper.board.insertNewArticle",articleMap);
        int articleNO = selectNewArticleNO();
        return  articleNO;
    }

    @Override
    public void insertNewImage(Map articleMap) throws DataAccessException {
        List<ImageVO> imageFileList = (ArrayList)articleMap.get("imageFileList");
        //이미지 파일목록 정보를 가져옴
        int articleNO = Integer.parseInt((String) articleMap.get("articleNO")); //글번호 가져옴
        int imageFileNO = selectNewImageFileNO();
        for(ImageVO imageVO : imageFileList){
            //파일 객체를 하나씩 가져옴
            imageVO.setImageFileNO(++imageFileNO);
            imageVO.setArticleNO(articleNO);
            //이미지번호과 글번호 속성 설정
        }
        sqlSession.insert("mapper.board.insertNewImage",imageFileList);
    }

    @Override
    public ArticleVO selectArticle(int articleNO) throws DataAccessException {
        return sqlSession.selectOne("mapper.board.selectArticle",articleNO);
    }

    @Override
    public List selectImageFileList(int articleNO) throws DataAccessException {
        List<ImageVO> imageFileList = sqlSession.selectList("mapper.board.selectImageFileList",articleNO);
        return imageFileList;
    }


    @Override
    public void deleteImage(int no) throws DataAccessException {
        sqlSession.delete("mapper.board.deleteImage", no);
    }

    @Override
    public void updateArticle(ArticleVO articleVO) throws DataAccessException {
        sqlSession.update("mapper.board.updateArticle", articleVO);
    }


    private int selectNewArticleNO() throws DataAccessException {
        return sqlSession.selectOne("mapper.board.selectNewArticleNO");
    }

    private int selectNewImageFileNO() throws DataAccessException {
        return sqlSession.selectOne("mapper.board.selectNewImageFileNO");
    }
}
