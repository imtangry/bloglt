package win.bloglt.publicview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tryu on 2017/7/11.
 */
@Controller
@RequestMapping
public class PublicViewController {
    /**
    *Create by tryu 2017/7/11 9:41
    *进入博客首页的请求
    */
    @RequestMapping
    public String index() {
        System.out.println("接收到进入博客首页的请求");
        return "index";
    }
    @RequestMapping("article")
    public String article(){
        System.out.println("接受到进入文章页面的请求");
        return "article";
    }
}
