package com.csdn.demo.sys.controller;

import com.csdn.demo.common.base.constant.SystemStaticConst;
import com.csdn.demo.common.util.user.CommonUserUtil;
import com.csdn.demo.common.util.user.UserInfo;
import com.csdn.demo.sys.dao.AuthDao;
import com.csdn.demo.sys.entity.Auth;
import com.csdn.demo.sys.entity.Contract;
import com.csdn.demo.sys.entity.User;
import com.csdn.demo.sys.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员审核
 * Created by 10093 on 2019/3/10.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthDao authDao;

    /**
     *查看审核列表
     * @param
     * @return
     */
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> selectContract(Auth auth) {
        List<Auth> list =  authDao.select(auth);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.RESULT,list);
        result.put("totalCount",list.size());
        return result;
    }

    /**
     *查看审核列表
     * @param
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insert() {
       User user= UserInfo.getUser();
       Auth auth = new Auth();
       auth.setUserId(user.getId());
       auth.setUserName(user.getUserName());
       auth.setAddress(user.getAddress());
       auth.setLogin(user.getLogin());
       authDao.insert(auth);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        return result;
    }

    /**
     *更新 审核
     * @param
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(Integer id) {
        User user= UserInfo.getUser();
        authDao.update(user.getUserName(),id);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        return result;
    }

}
