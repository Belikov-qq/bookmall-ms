package com.bookmall.ms.service;

import com.bookmall.ms.dao.UserDAO;
import com.bookmall.ms.dto.User;

// 管理员相关业务实现
public class UserService {

    /**
     * 管理员登陆业务：根据账号密码验证管理员登陆，验证通过返回user对象，否则返回null
     *
     * @param userName
     * @param userPwd
     * @param salt
     * @return
     */
    public User checkLogin(String userName, String userPwd, String salt) {
        //1.根据userName查询管理员信息
        UserDAO userDAO = new UserDAO();
        User user = userDAO.selectUserByUserName(userName);
        //2.判断密码
        if (user == null) {
            return null;
        } else if (EncryptSha256.getSha256Str(user.getUserPwd() + salt).equals(userPwd)) {
            //密码相等，登陆成功！
            return user;
        } else {
            return null;
        }
    }
}
