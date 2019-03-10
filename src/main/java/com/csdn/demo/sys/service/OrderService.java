package com.csdn.demo.sys.service;

import com.csdn.demo.sys.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单操作
 * Created by dell on 2019/3/6.
 */
public interface OrderService {
    /**
     * 添加订单
     */
    void insert(Order order);

    /**
     * 修改订单状态
     *
     * @param order
     */
    void update(Order order);

    /**
     * 企业查询已发布订单
     *
     * @return
     */
    List<Order> selectList(Integer senderId,String name,Integer status,String senderName);

    /**
     * 用户查询自己的订单
     *
     * @return
     */
    List<Order> selectUserOrder(Integer userId,String name,Integer status,String senderName);

    /**
     * 查询所有可接单的订单
     *
     * @return
     */
    List<Order> selectPublishOrder();

    /**
     * 删除企业发布订单
     * @param id
     */
    void delete(Integer id);

    /**
     * 修改订单状态和流水处理
     */
    void dealStatusAndAccount(Order order);

    /**
     * 根据订单id查询订单信息
     * @param id
     * @return
     */
    Order selectMsgById(Integer id);
}
