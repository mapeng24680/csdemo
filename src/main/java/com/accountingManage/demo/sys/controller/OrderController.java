package com.accountingManage.demo.sys.controller;/**
 * Created by dell on 2019/3/6.
 */

import com.accountingManage.demo.common.base.constant.SystemStaticConst;
import com.accountingManage.demo.common.util.location.DateUtil;
import com.accountingManage.demo.common.util.user.CommonUserUtil;
import com.accountingManage.demo.common.util.user.UserInfo;
import com.accountingManage.demo.sys.dao.ContractDao;
import com.accountingManage.demo.sys.dao.EvaluationDao;
import com.accountingManage.demo.sys.dao.OrderDao;
import com.accountingManage.demo.sys.dto.UserDTO;
import com.accountingManage.demo.sys.entity.*;
import com.accountingManage.demo.sys.service.OrderService;
import com.accountingManage.demo.sys.service.UserAssociateRoleService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

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
    private ContractDao contractDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private EvaluationDao evaluationDao;
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
    public Map<String, Object> insert(Order order) {
        order.setSenderId(UserInfo.getUser().getId());
        order.setOrderNum(CommonUserUtil.randomCode().toString());
        orderService.insert(order);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG, "保存成功");
        return result;
    }
    @RequestMapping(value="/addPage")
    public String addPage() throws Exception{
        return "sys/order/add";
    }

    /**
     * 修改订单
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> update(Order order) {
        if(order.getStatus()==2){
            Integer userId = UserInfo.getUser().getId();
            order.setUserId(userId);
            orderService.update(order);
            Order od =  orderService.selectMsgById(order.getId());
            Contract contract = new Contract();
            contract.setUserId(od.getUserId());
            contract.setOrderNum(od.getOrderNum());
            contract.setEnterpriseId(od.getSenderId());
            contract.setSenderName(od.getSenderName());
            contract.setcUserName(od.getCuserName());
            contract.setOrderId(od.getId());
            contract.setContractNum(CommonUserUtil.recountNew(20));
            contractDao.insert(contract);
            Evaluation evaluation = new Evaluation();
            evaluation.setOrderId(od.getId());
            evaluation.setSenderId(od.getSenderId());
            evaluation.setUserId(od.getUserId());
            evaluationDao.insert(evaluation);

        }
        if(order.getStatus()==3){
            orderService.dealStatusAndAccount(order);
        }

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
                result.put(SystemStaticConst.RESULT, orderService.selectUserOrder(id,userDTO.getName(),userDTO.getStatus()==""?null:Integer.parseInt(userDTO.getStatus()),userDTO.getSenderName()));
                result.put("totalCount",orderService.selectUserOrder(id,userDTO.getName(),userDTO.getStatus()==""?null:Integer.parseInt(userDTO.getStatus()),userDTO.getSenderName()).size());
            } else if (5 == roleId) {
                result.put(SystemStaticConst.RESULT, orderService.selectList(id,userDTO.getName(),userDTO.getStatus()==""?null:Integer.parseInt(userDTO.getStatus()),userDTO.getSenderName()));
                result.put("totalCount",orderService.selectList(id,userDTO.getName(),userDTO.getStatus()==""?null:Integer.parseInt(userDTO.getStatus()),userDTO.getSenderName()).size());
            }
        }
        return result;
    }


    @RequestMapping(value = "/selectOrderCount", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> selectOrderCount() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT,orderDao.selectOrderCount());
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
    public Map<String, Object> delete(Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        orderService.delete(id);
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        return result;
    }

    /**
     *查看合同内容
     * @param
     * @return
     */
    @RequestMapping(value = "/selectContract", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> selectContract(Integer orderId) {
        Contract contract =  contractDao.select(orderId);
        contract.setContractMsg(contract.getContractMsg()==null?"":contract.getContractMsg());
        Integer a=1;
        if(contract.getStatus().equals(a)){
            Boolean bl =  CommonUserUtil.compareTime(contract.getOverdueTime());
            contract.setStatus(bl==true?2:1);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put("dataValue",contract);
        return result;
    }

    /**
     *签约合同
     * @param
     * @return
     */
    @RequestMapping(value = "/updateContract", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateContract(Contract contract) {
        contract.setStatus(1);
        contractDao.update(contract);
        Order order = new Order();
        order.setId(contract.getOrderId());
        order.setStatus(3);
        orderService.update(order);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        return result;
    }

}
