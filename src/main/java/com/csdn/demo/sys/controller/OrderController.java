package com.csdn.demo.sys.controller;/**
 * Created by dell on 2019/3/6.
 */

import com.csdn.demo.common.base.constant.SystemStaticConst;
import com.csdn.demo.common.util.user.UserInfo;
import com.csdn.demo.sys.dto.UserDTO;
import com.csdn.demo.sys.entity.Order;
import com.csdn.demo.sys.entity.QueryUserAssociateRole;
import com.csdn.demo.sys.entity.UserAssociateRole;
import com.csdn.demo.sys.service.OrderService;
import com.csdn.demo.sys.service.UserAssociateRoleService;
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
 * @description: 订单控制类
 * @author: mapeng
 * @create: 2019-03-06 16:18
 **/
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserAssociateRoleService userAssociateRoleService;

    /**
     * 发布订单
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> insert(@RequestBody Order order) {
        order.setSenderId(UserInfo.getUser().getId());
        orderService.insert(order);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG, "保存成功");
        return result;
    }

    /**
     * 修改订单
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> update(@RequestBody Order order) {
        orderService.update(order);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG, "更新成功");
        return result;
    }

    /**
     * 企业查询已发布订单/用户查询已接单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> selectList(UserDTO userDTO) {
        Integer id = UserInfo.getUser().getId();
        QueryUserAssociateRole queryUserAssociateRole = new QueryUserAssociateRole();
        queryUserAssociateRole.setUserId(id);

        List<UserAssociateRole> rolelist = userAssociateRoleService.query(queryUserAssociateRole);
        Map<String, Object> result = new HashMap<String, Object>();
        if (rolelist != null && rolelist.size() > 0) {
            long roleId = rolelist.get(0).getRoleId();
            if (3 == roleId) {
                result.put(SystemStaticConst.RESULT, orderService.selectUserOrder(id,userDTO.getName(),userDTO.getStatus(),userDTO.getSenderName()));
                result.put("totalCount",orderService.selectUserOrder(id,userDTO.getName(),userDTO.getStatus(),userDTO.getSenderName()).size());
            } else if (5 == roleId) {
                result.put(SystemStaticConst.RESULT, orderService.selectList(id,userDTO.getName(),userDTO.getStatus(),userDTO.getSenderName()));
                result.put("totalCount",orderService.selectList(id,userDTO.getName(),userDTO.getStatus(),userDTO.getSenderName()).size());
            }
        }
        return result;
    }

//    /**
//     * 用户查询自己的订单
//     *
//     * @param userId
//     * @return
//     */
//    @RequestMapping(value = "/selectUserOrder", method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String, Object> selectUserOrder(Integer userId) {
//        List<Order> list = orderService.selectUserOrder(userId);
//        Map<String, Object> result = new HashMap<String, Object>();
//        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
//        result.put("data", list);
//        return result;
//    }

    /**
     * 查询所有可接单的订单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/selectUserOrder", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> selectUserOrder() {
        List<Order> list = orderService.selectPublishOrder();
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put("data", list);
        return result;
    }

    /**
     * 查询当前用户角色
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/roleId", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> roleId() {
        Integer id = UserInfo.getUser().getId();
        QueryUserAssociateRole queryUserAssociateRole = new QueryUserAssociateRole();
        queryUserAssociateRole.setUserId(id);

        List<UserAssociateRole> rolelist = userAssociateRoleService.query(queryUserAssociateRole);
        Map<String, Object> result = new HashMap<String, Object>();
        if (rolelist != null && rolelist.size() > 0) {
            result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
            result.put("data", rolelist.get(0).getRoleId());
        } else {
            result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
        }
        return result;
    }

    /**
     * 删除企业订单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestBody Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        orderService.delete(id);
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
        return result;
    }
}
