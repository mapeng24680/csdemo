package com.csdn.demo.sys.controller;


import com.csdn.demo.common.base.constant.SystemStaticConst;
import com.csdn.demo.sys.entity.Auth;
import com.csdn.demo.sys.entity.Message;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/message")
public class MessageController {


    @RequestMapping(value = "/addPage",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addPage() {
        return "sys/sendMsg/add";
    }


    @RequestMapping(value = "/select", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> selectMessage() {
        List list = new ArrayList<>();
        Message message = new Message();
        message.setUserName("mapeng");
        message.setMessage("你好啊");
        message.setCreateTime(new Date());
        Message message1 = new Message();
        message1.setUserName("cqn");
        message1.setMessage("你好啊");
        message1.setCreateTime(new Date());
        Message message2 = new Message();
        message2.setUserName("our");
        message2.setMessage("我们大家都是很好的啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊");
        message2.setCreateTime(new Date());
        list.add(message);
        list.add(message1);
        list.add(message2);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT,list);
        result.put("totalCount",list.size());
        return result;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insert(Message message) {
        Map<String, Object> result = new HashMap<String, Object>();
        System.out.println(message.getMessage());
        return result;
    }
}
