package com.example.bookshop.service;

import com.example.bookshop.dao.UserDao;
import com.example.bookshop.model.Page;
import com.example.bookshop.model.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;

/**
 * @className: UserService
 * @author: ZhaiJinPei
 * @discription: 处理用户的业务逻辑, 调用 UserDao层方法对数据库进行操作
 * 1.register(用户信息)   用户注册
 * 2.login(用户名,密码)   用户登录
 * 3.selectById(用户id)   查找用户
 * 4.updateUserAddress(用户信息)    更新用户收货信息
 * 5.updatePwd(用户信息)    修改用户密码
 * 6.getUserPage()    获取用户行数?
 * 7.delete(用户id)    删除用户
 */
@SuppressWarnings("unchecked,rawtypes")
@Slf4j
public class UserService {
    private final UserDao uDao = new UserDao();

    public boolean register(User user) {
        try {
            if (uDao.isUsernameExist(user.getUsername())) {
                return false;
            }
            if (uDao.isEmailExist(user.getEmail())) {
                return false;
            }
            uDao.addUser(user);
            return true;
        } catch (SQLException e) {
            log.info("SQLException", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public User login(String ue, String password) {
        User user = null;
        try {
            user = uDao.selectByUsernamePassword(ue, password);
        } catch (Exception e) {
            log.info("SQLException", e);
        }
        if (user != null) {
            return user;
        }
        try {
            user = uDao.selectByEmailPassword(ue, password);
        } catch (Exception e) {
            log.info("SQLException", e);
        }
        return user;
    }

    public User selectById(int id) {
        User u = null;
        try {
            u = uDao.selectById(id);
        } catch (Exception e) {
            log.info("SQLException", e);
        }
        return u;
    }

    public void updateUserAddress(User user) {
        try {
            uDao.updateUserAddress(user);
        } catch (Exception e) {
            log.info("SQLException", e);
        }
    }

    public void updatePwd(User user) {
        try {
            uDao.updatePwd(user);
        } catch (Exception e) {
            log.info("SQLException", e);
        }
    }

    public Page getUserPage(int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int pageSize = 7;
        int totalCount = 0;
        try {
            totalCount = uDao.selectUserCount();
        } catch (Exception e) {
            log.info("SQLException", e);
        }
        p.SetPageSizeAndTotalCount(pageSize, totalCount);
        List list = null;
        try {
            list = uDao.selectUserList(pageNumber, pageSize);
        } catch (Exception e) {
            log.info("SQLException", e);
        }
        p.setList(list);
        return p;
    }

    public boolean delete(int id) {
        try {
            uDao.delete(id);
            return true;
        } catch (Exception e) {
            log.info("SQLException", e);
            return false;
        }
    }
}
