package win.bloglt.Login.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import win.bloglt.user.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by tryu on 2017/7/13.
 * 后台文章管理的登陆界面,已经登录时再次访问此页面的逻辑暂没添加
 */
@Controller
@RequestMapping("backstage")
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * Create by tryu 2017/7/13 15:45
     * 登陆的页面
     */
    @RequestMapping("/loginview")
    public String loginIndex() {
        return "login";
    }

    /**
     * Create by tryu 2017/7/13 15:47
     * 校验前台登陆数据，校验成功进行请求转发至后台主页，不成功ajax返回消息。
     */
    @RequestMapping("/login")
    @ResponseBody
    public String loginCheck(HttpServletRequest req) {
        String userName = req.getParameter("uname");
        String password = req.getParameter("password");

        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return "success";
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return "failed";
    }

    /**
     * Create by tryu 2017/7/18 17:22
     * 退出登录
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/";
    }
}

