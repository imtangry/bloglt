package win.bloglt.publicview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import win.bloglt.article.entity.Article;
import win.bloglt.article.service.ArticleService;

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
    *Create by tryu 2017/7/11 9:41
    *进入博客首页的请求
    */
    @RequestMapping
    public String index(Model model) {
        List<Article> articleList=articleService.getIndexArticle();
        model.addAttribute("articleList",articleList);
        System.out.println("接收到进入博客首页的请求");
        return "index";
    }
    
    /**
    *Create by Trye 2017/7/21 17:48
    *进入文章板块,此版块打算作为归档页面，首页充当文章浏览的功能
    */
    @RequestMapping("article")
    public String article(){
        System.out.println("接受到进入文章页面的请求");
        return "article";
    }

    /**
     *Create by Trye 2017/7/21 17:48
     *进入文章板块,此版块打算作为归档页面，首页充当文章浏览的功能
     */
    @RequestMapping("readarticle")
    public String theArticle(){
        System.out.println("接受到进入文章页面的请求");
        return "article";
    }

    /**
    *Create by Trye 2017/7/22 9:20
    *首页加载更多的功能
    */
    @RequestMapping("loadmore")
    public String loadMore(Model model,int articleId){
        List<Article> articleList=articleService.loadMoreArticle(articleId);
        model.addAttribute("articleList",articleList);
        return "loadmore";
    }
}
