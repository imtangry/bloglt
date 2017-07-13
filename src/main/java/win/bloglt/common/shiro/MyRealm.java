package win.bloglt.common.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import win.bloglt.user.entity.Permission;
import win.bloglt.user.entity.Role;
import win.bloglt.user.entity.Users;
import win.bloglt.user.service.UserService;

/**
 * Created by tryu on 2017/7/13.
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    /**
     * Create by tryu 2017/7/13 16:30
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        Users user = userService.getUserByUserName(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoleList()) {
            authorizationInfo.addRole(role.getRoleName());
            for (Permission permission : role.getPermissionList()) {
                authorizationInfo.addStringPermission(permission.getPermissionOperation());
            }
        }
        return authorizationInfo;
    }

    /**
     * Create by tryu 2017/7/13 16:30
     * 登陆校验
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String userName = usernamePasswordToken.getUsername();
        Users user = userService.getUserByUserName(userName);
        if (user == null) {
            return null;
        } else {
            AuthenticationInfo info = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
            SecurityUtils.getSubject().getSession().setAttribute("userInfo", user);
            return info;
        }
    }
}
