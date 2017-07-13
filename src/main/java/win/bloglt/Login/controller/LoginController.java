package win.bloglt.Login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tryu on 2017/7/13.
 * 后台文章管理的登陆界面
 */
@Controller
@RequestMapping("backstage")
public class LoginController {

    /**
     * Create by tryu 2017/7/13 15:45
     * 登陆的页面
     */
    @RequestMapping("/loginview")
    public String index() {
        return "login";
    }

    /**
     * Create by tryu 2017/7/13 15:47
     * 校验前台登陆数据，校验成功进行请求转发至后台主页，不成功ajax返回消息。
     */
    @RequestMapping("/login")
    public String writeArticle() {
        return "write";
    }

}

