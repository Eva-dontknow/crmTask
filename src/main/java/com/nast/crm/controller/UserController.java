package com.nast.crm.controller;

import com.nast.base.ResultInfo;
import com.nast.crm.exceptions.ParamsException;
import com.nast.crm.model.UserModel;
import com.nast.crm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.annotation.Resources;

@Controller
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * 用户登录--控制层（接口代码）
     * @param userName
     * @param userPwd
     * @return
     */
    @PostMapping("user/login")
    @ResponseBody
    public ResultInfo login(String userName, String userPwd) {
        ResultInfo resultInfo = new ResultInfo();
        System.out.println("nengxingma");
        try {
            //拿到登录信息
            UserModel userModel = userService.login(userName, userPwd);
            /**
             * 登录成功后,有两种保存方案，采用第二种
             * 1.将用户的登录信息存入session
             * 2.将用户信息返回给客户端，由客户端（cookie)保存
             */
            resultInfo.setResult(userModel);
        } catch (ParamsException e) {//捕获参数异常
            e.printStackTrace();
            resultInfo.setCode(e.getCode());
            resultInfo.setMsg(e.getMsg());
        }catch (Exception e){
            resultInfo.setCode(500);
            resultInfo.setMsg("操作失败！");
        }
        return resultInfo;
    }
}
