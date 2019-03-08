package com.csdn.demo.sys.controller;/**
 * Created by dell on 2019/3/6.
 */

import com.csdn.demo.common.base.constant.SystemStaticConst;
import com.csdn.demo.sys.entity.EnterprisePublished;
import com.csdn.demo.sys.entity.Order;
import com.csdn.demo.sys.service.EnterprisePublishedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 企业信息发布
 * @author: mapeng
 * @create: 2019-03-06 10:59
 **/
@Controller
@RequestMapping("/publish")
public class EnterprisePublishedController {
    @Autowired
    private EnterprisePublishedService enterprisePublishedService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> insert(@RequestBody EnterprisePublished enterprisePublished) {
        enterprisePublishedService.save(enterprisePublished);
        Map<String,Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG,"保持成功");
        return result;
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> update(@RequestBody EnterprisePublished enterprisePublished) {
        enterprisePublishedService.update(enterprisePublished);
        Map<String,Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG,"更新成功");
        return result;
    }

    @RequestMapping(value = "/selectList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> update(Order order) {
       List<EnterprisePublished> list =enterprisePublishedService.selectList(order.getSenderId());
        Map<String,Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG,"查询成功");
        result.put("data",list);
        return result;
    }
}
