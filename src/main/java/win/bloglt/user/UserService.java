package win.bloglt.user;

import win.bloglt.user.entity.Users;

/**
 * Created by tryu on 2017/7/13.
 */
public interface UserService {
    Users getUserByUserName(String userName);
}
