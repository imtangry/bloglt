package win.bloglt.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.applet.Main;
import win.bloglt.article.dao.ArticleMapper;
import win.bloglt.article.entity.Article;
import win.bloglt.article.vo.QueryArticle;
import win.bloglt.common.pages.Pages;

import java.util.List;

/**
 * Created by tryu on 2017/7/16.
 */
@Service("articleServiceImpl")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * Create by tryu 2017/7/17 16:49
     * 插入文章，保存新文章到数据库
     */
    @Override
    public int writeArticle(Article article) {
        int status = articleMapper.insertSelective(article);
        return status;
    }

    /**
     * Create by Trye 2017/7/19 16:15
     * 根据ID从数据库取出一篇文章
     */
    @Override
    public Article editArticle(int articleId) {
        return articleMapper.selectByPrimaryKey(articleId);
    }

    /**
     * Create by tryu 2017/7/17 16:49
     * 从数据库获得首页文章数据
     */
    @Override
    public List<Article> getIndexArticle() {
        return articleMapper.selectIndexArticle();
    }

    /**
     * Create by Trye 2017/7/19 18:00
     * 根据条件查询文章
     */
    @Override
    public Pages getQueryArticle(QueryArticle queryArticle) {
        int record = articleMapper.getRecord(queryArticle);
        if (record > 0) {
            queryArticle.initPagesLimit(record);
            List<Article> articles = articleMapper.selectQueryArticle(queryArticle);
            queryArticle.setDataList(articles);
            return queryArticle;
        } else {
            return null;
        }
    }

    /**
     * Create by Trye 2017/7/20 16:14
     * 存储编辑完的文章
     */
    @Override
    public int saveEditedArticle(Article article) {
        return articleMapper.updateByPrimaryKeySelective(article);
    }

    /**
     * Create by Trye 2017/7/22 9:27
     * 首页加载更多文章功能
     */
    @Override
    public List<Article> loadMoreArticle(int articleId) {
        return articleMapper.selectIndexMoreArticle(articleId);
    }

    /**
     * Create by Trye 2017/7/24 19:34
     * 文章浏览数+1
     */
    @Override
    public int viewsAddOne(int articleId) {
        return articleMapper.updateViewsByPrimaryKey(articleId);
    }
}
