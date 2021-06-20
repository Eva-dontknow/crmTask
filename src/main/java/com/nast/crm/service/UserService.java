package com.nast.crm.service;


import com.nast.base.BaseService;
import com.nast.crm.dao.UserMapper;
import com.nast.crm.utils.AssertUtil;
import com.nast.crm.utils.Md5Util;
import com.nast.crm.vo.User;
import com.nast.crm.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User,Integer>{

    @Autowired
    private UserMapper userMapper;
    /**
     * 用户登录
     * @param userName
     * @param userPwd
     */
    public UserModel login(String userName,String userPwd){
        //1.登录表单的参数校验：用户名密码都非空
        checkLoginParams(userName,userPwd);
        //2.根据用户名，查询用户记录
        User user = userMapper.queryUserByName(userName);
        //3.用户存在性校验（不存在-》方法结束）
        AssertUtil.isTrue(null == user,"用户不存在或已注销！");
        //4.用户存在-》密码校验
        //Md5Util.encode(userPwd) 加密后的字符串
        AssertUtil.isTrue(!(user.getUserPwd().equals(Md5Util.encode(userPwd))),"用户密码不正确，请重新输入！");
        //5.密码正确
        return buildUserInfo(user);

    }

    /**
     * 获取model类型的信息，即返回给前端部分用户的数据库存储的信息
     * @param user
     * @return
     */
    private UserModel buildUserInfo(User user) {
        UserModel userModel = new UserModel();
        userModel.setTrueName(user.getTrueName());
        userModel.setUserName(user.getUserName());
        return userModel;
    }

    private void checkLoginParams(String userName, String userPwd) {
        //StringUtils工具类可以判断字符串是否为空
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"用户密码不能为空！");
    }
}
