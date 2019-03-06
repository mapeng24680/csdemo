package com.csdn.demo.sys.controller;/**
 * Created by dell on 2019/3/6.
 */

import com.csdn.demo.common.base.constant.SystemStaticConst;
import com.csdn.demo.sys.entity.Order;
import com.csdn.demo.sys.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 订单控制类
 * @author: mapeng
 * @create: 2019-03-06 16:18
 **/
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 发布订单
     * @param order
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> insert(@RequestBody Order order) {
        orderService.insert(order);
        Map<String,Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG,"保存成功");
        return result;
    }

    /**
     * 修改订单
     * @param order
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> update(@RequestBody Order order) {
        orderService.update(order);
        Map<String,Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG,"更新成功");
        return result;
    }

    /**
     * 企业查询已发布订单
     * @param senderId
     * @return
     */
    @RequestMapping(value = "/selectList", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> selectList(Integer senderId) {
        List<Order> list=orderService.selectList(senderId);
        Map<String,Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG,"更新成功");
        result.put("data",list);
        return result;
    }

    /**
     *用户查询自己的订单
     * @param userId
     * @return
     */
    @RequestMapping(value = "/selectUserOrder", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> selectUserOrder(Integer userId) {
        List<Order> list= orderService.selectUserOrder(userId);
        Map<String,Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG,"更新成功");
        result.put("data",list);
        return result;
    }
}
