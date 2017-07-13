package win.bloglt.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import win.bloglt.user.dao.UsersMapper;
import win.bloglt.user.entity.Users;

/**
 * Created by tryu on 2017/7/13.
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    /**
     * Create by tryu 2017/7/13 16:43
     * 登陆时根据用户名返回用户
     */
    @Override
    public Users getUserByUserName(String userName) {
        return usersMapper.selectByUserName(userName);
    }
}
