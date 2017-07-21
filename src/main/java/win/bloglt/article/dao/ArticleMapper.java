package win.bloglt.article.dao;

import win.bloglt.article.entity.Article;
import win.bloglt.article.vo.QueryArticle;
import win.bloglt.common.pages.Pages;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    List<Article> selectIndexArticle();

    List<Article> selectQueryArticle(QueryArticle queryArticle);

    int getRecord(QueryArticle queryArticle);
}