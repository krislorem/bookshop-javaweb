package com.example.bookshop.service;

import com.example.bookshop.mapper.UserMapper;
import com.example.bookshop.model.Page;
import com.example.bookshop.model.User;
import com.example.bookshop.utils.MyBatisUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
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
    public boolean register(User user) {
        if (
                !((boolean) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(UserMapper.class).isEmailExist(user.getEmail()))
                  && (boolean) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(UserMapper.class).isUsernameExist(user.getUsername())))
        ) {
            return MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(UserMapper.class).addUser(user)) == null;
        }
        return false;
    }

    public User login(String ue, String password) {
        User user = (User) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(UserMapper.class).selectByUsernamePassword(ue, password));
        if (user != null) return user;
        else
            return (User) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(UserMapper.class).selectByEmailPassword(ue, password));
    }

    public User selectById(int id) {
        return (User) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(UserMapper.class).selectById(id));
    }

    public void updateUserAddress(User user) {
        MyBatisUtils.executeUpdate(sqlSession -> sqlSession.getMapper(UserMapper.class).updateUserAddress(user));
    }

    public void updatePwd(User user) {
        MyBatisUtils.executeUpdate(sqlSession -> sqlSession.getMapper(UserMapper.class).updatePwd(user));
    }

    public Page getUserPage(int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int pageSize = 7;
        int totalCount = (int) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(UserMapper.class).selectUserCount());
        log.info(String.valueOf(totalCount));
        p.SetPageSizeAndTotalCount(pageSize, totalCount);
        List list = (List<User>) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(UserMapper.class).selectUserList((pageNumber - 1) * pageSize, pageSize));
        p.setList(list);
        return p;
    }

    public boolean delete(int id) {
        return (boolean) MyBatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(UserMapper.class).delete(id));
    }
}
