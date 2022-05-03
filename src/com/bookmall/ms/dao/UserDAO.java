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
        String sql = "";
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        try{
            user = queryRunner.query(sql, new BeanHandler<User>(User.class));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
}
