package com.bookmall.ms.dao;

import com.bookmall.ms.dto.User;
import com.bookmall.ms.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import java.sql.SQLException;

/**
 * 用于实现管理员数据库操作
 */
public class UserDAO {
    public User selectUserByUserName(String userName){
        User user = null;
        try {
            String sql = "select user_id userId,user_name userName,user_pwd userPwd,real_name realName,user_gender userGender,user_tel userTel,user_desc userDesc from users where user_name=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            user = queryRunner.query(sql, new BeanHandler<User>(User.class),userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
