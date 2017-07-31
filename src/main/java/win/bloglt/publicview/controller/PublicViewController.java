package win.bloglt.publicview.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import win.bloglt.article.entity.Article;
import win.bloglt.article.service.ArticleService;
import win.bloglt.article.vo.QueryArticle;
import win.bloglt.common.pages.Pages;
import win.bloglt.user.entity.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by tryu on 2017/7/11.
 */
@Controller
@RequestMapping
public class PublicViewController {
    @Autowired
    private ArticleService articleService;

    /**
     * Create by tryu 2017/7/11 9:41
     * 进入博客首页的请求
     */
    @RequestMapping
    public String index(Model model) {
        List<Article> articleList = articleService.getIndexArticle();
        model.addAttribute("articleList", articleList);
        System.out.println("接收到进入博客首页的请求");
        return "index";
    }

    /**
     * Create by Trye 2017/7/21 17:48
     * 进入文章板块,此版块打算作为归档分类页面，首页可充当文章浏览的功能角色
     */
    @RequestMapping("article")
    public String article() {
        System.out.println("接受到进入文章页面的请求");
        return "article";
    }

    /**
     * Create by Trye 2017/7/19 17:18
     * 根据前端的查询条件返回文章的json数据
     */
    @RequestMapping("articlelist")
    @ResponseBody
    public Pages showList(QueryArticle queryArticle) {
        Pages pages = articleService.getQueryArticle(queryArticle);
        if (pages==null){
            return new Pages();
        }
        return pages;
    }

    /**
     * Create by Trye 2017/7/21 17:48
     * 文章的阅读更多,首页点击文章标题
     */
    @RequestMapping("readarticle")
    public String theArticle(Model model, HttpServletRequest request) {
        System.out.println("接受到进入文章页面的请求");
        String str = request.getParameter("articleId");
        if (str == null) {
            return null;
        } else {
            int articleId = Integer.parseInt(str);
            articleService.viewsAddOne(articleId);
            Article article = articleService.editArticle(articleId);
            model.addAttribute("article", article);
            return "article";
        }
    }

    /**
     * Create by Trye 2017/7/22 9:20
     * 首页加载更多的功能
     */
    @RequestMapping("loadmore")
    public String loadMore(Model model, int articleId) {
        List<Article> articleList = articleService.loadMoreArticle(articleId);
        model.addAttribute("articleList", articleList);
        return "loadmore";
    }
}
