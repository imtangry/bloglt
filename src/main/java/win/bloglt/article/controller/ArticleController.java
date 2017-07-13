package win.bloglt.article.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tryu on 2017/7/13.
 * 登陆成功后，对文章进行管理的一系列操作
 */
@Controller
@RequestMapping("article")
public class ArticleController {

    /**
     * Create by tryu 2017/7/13 15:56
     * 处理创建新文章的请求
     */
    @RequestMapping("/write")
    public String writeArticle() {
        return "write";
    }

    /**
     * Create by tryu 2017/7/13 15:50
     * 处理编辑已存在文章的请求
     */
    @RequestMapping("/edit")
    public String editArticle() {
        return "write";
    }
}
