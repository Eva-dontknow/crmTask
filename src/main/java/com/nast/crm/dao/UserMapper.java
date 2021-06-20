package com.nast.crm.dao;


import com.nast.base.BaseMapper;
import com.nast.crm.vo.User;

import java.util.List;
import java.util.Map;

/**
 * 提供一个查询userModel信息的方法
 */
public interface UserMapper extends BaseMapper<User, Integer> {

    // 通过用户名查询用户记录，返回用户对象
    public User queryUserByName(String userName);

    // 查询所有的销售人员
    List<Map<String, Object>> queryAllSales();

    // 查询所有的客户经理
    List<Map<String, Object>> queryAllCustomerManagers();
}