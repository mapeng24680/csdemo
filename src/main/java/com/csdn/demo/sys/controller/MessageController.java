package com.csdn.demo.sys.controller;


import com.csdn.demo.common.base.constant.SystemStaticConst;
import com.csdn.demo.common.util.user.UserInfo;
import com.csdn.demo.sys.dao.MessageDao;
import com.csdn.demo.sys.entity.Auth;
import com.csdn.demo.sys.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageDao messageDao;

    @RequestMapping(value = "/addPage",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addPage() {
        return "sys/sendMsg/add";
    }


    @RequestMapping(value = "/select", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> selectMessage() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT,messageDao.select());
        result.put("totalCount",messageDao.select().size());
        return result;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insert(Message message) {
        message.setUserName(UserInfo.getUser().getUserName());
        messageDao.insert(message);
        Map<String, Object> result = new HashMap<String, Object>();
        System.out.println(message.getMessage());
        return result;
    }
}
