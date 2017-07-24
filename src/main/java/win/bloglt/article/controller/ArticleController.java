package win.bloglt.article.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import win.bloglt.article.entity.Article;
import win.bloglt.article.service.ArticleService;
import win.bloglt.article.vo.QueryArticle;
import win.bloglt.common.pages.Pages;
import win.bloglt.common.utils.imgUploadUtil;
import win.bloglt.user.entity.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tryu on 2017/7/13.
 * 登陆成功后，对文章进行管理的一系列操作,权限
 */
@Controller
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * Create by tryu 2017/7/13 18:20
     * 登陆成功返回的页面(后台管理的首页)
     */
    @RequiresPermissions("article")
    @RequestMapping("/manager")
    public String managerArticle() {
        return "backstage";
    }

    /**
     * Create by Trye 2017/7/19 17:07
     * 返回显示文章列表的页面
     */
    @RequiresPermissions("article")
    @RequestMapping("/list")
    public String articleList() {
        return "articlelist";
    }

    /**
     * Create by Trye 2017/7/19 17:18
     * 根据前端的查询条件返回文章的json数据
     */
    @RequiresPermissions("article")
    @RequestMapping("showlist")
    @ResponseBody
    public Pages showList(HttpSession session, QueryArticle queryArticle) {
        int id = ((Users) session.getAttribute("userInfo")).getUserId();
        queryArticle.setArticleAuthorId(id);
        Pages pages = articleService.getQueryArticle(queryArticle);
        return pages;
    }

    /**
     * Create by tryu 2017/7/13 15:56
     * 处理创建新文章的请求
     */
    @RequiresPermissions("article:write")
    @RequestMapping("/write")
    public String writeArticle() {
        return "write";
    }

    /**
     * Create by tryu 2017/7/16 9:37
     * 测试CKEditor的功能(成功)
     * 提交新文章的请求,并设置浏览次数为0
     */
    @RequiresPermissions("article:write")
    @RequestMapping("/save")
    public String showData(Article article, HttpSession session) {
        int articleAuthorId = ((Users) (session.getAttribute("userInfo"))).getUserId();
        article.setArticleAuthorId(articleAuthorId);
        article.setViews(0);
        int status = articleService.writeArticle(article);
        if (status > 0) {
            return "article";
        } else {
            return "failed";
        }
    }

    /**
     * Create by tryu 2017/7/13 15:50
     * 处理编辑已存在文章的请求,取出数据库中的文章
     */
    @RequiresPermissions("article:edit")
    @RequestMapping("/edit")
    public String editArticle(Model model, int articleId) {
        Article article = articleService.editArticle(articleId);
        model.addAttribute("Article", article);
        model.addAttribute("id", articleId);
        return "edit";
    }

    /**
     * Create by Trye 2017/7/20 11:14
     * 更新编辑完成的文章到数据库。
     */
    @RequiresPermissions("article:edit")
    @RequestMapping("editandsave")
    public String editAndSave(Model model, Article article) {
        int status = articleService.saveEditedArticle(article);
        if (status > 0) {
            model.addAttribute("article", article);
            return "article";
        } else {
            return "failed";
        }
    }

    /**
     * Create by Trye 2017/7/24 15:46
     * 图片异步上传
     */
    @RequestMapping("/img_upload")
    public void imgUpload(HttpServletRequest request, HttpServletResponse response) {
        String filePath = "bloglt-static-file/upload_img/";
        try {
            imgUploadUtil.ckeditor(request, response, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
