package com.csdn.demo.sys.controller;


import com.csdn.demo.common.base.constant.SystemStaticConst;
import com.csdn.demo.common.util.user.UserInfo;
import com.csdn.demo.sys.dao.InformationDao;
import com.csdn.demo.sys.dao.MessageDao;
import com.csdn.demo.sys.entity.Auth;
import com.csdn.demo.sys.entity.Information;
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
    @Autowired
    private InformationDao informationDao;
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
    @RequestMapping(value = "/insertInformation", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertInformation(Information information) {
        Integer userId = UserInfo.getUser().getId();
        Integer bsendeUserId = UserInfo.getUser().getId();
        information.setSessionId(userId>bsendeUserId?(bsendeUserId+"_"+userId):(userId+"_"+bsendeUserId));
        information.setUserId(UserInfo.getUser().getId());
        informationDao.insert(information);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        return result;
    }
    @RequestMapping(value = "/selectInformation", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> insertInformation(Integer bSendeUserId) {
        Integer userId = UserInfo.getUser().getId();
        String seesionId  = userId>bSendeUserId?(bSendeUserId+"_"+userId):(userId+"_"+bSendeUserId);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put("data", informationDao.select(userId,seesionId));
        return result;
    }

}
