package win.bloglt.article.service;

import win.bloglt.article.entity.Article;
import win.bloglt.article.vo.QueryArticle;
import win.bloglt.common.pages.Pages;

import java.util.List;

/**
 * Created by tryu on 2017/7/16.
 * 写文章，修改文章，删除文章
 */
public interface ArticleService {
    //将日志存入数据库
    int writeArticle(Article article);

    //将日志从数据库拿出
    Article editArticle(int articleId);

    List<Article> getIndexArticle();

    Pages getQueryArticle(QueryArticle queryArticle);

    int saveEditedArticle(Article article);

    List<Article> loadMoreArticle(int articleId);
}
