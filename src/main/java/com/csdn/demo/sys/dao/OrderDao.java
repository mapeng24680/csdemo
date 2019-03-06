package com.csdn.demo.sys.dao;

import com.csdn.demo.sys.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单操作
 * Created by dell on 2019/3/6.
 */
public interface OrderDao {
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
    List<Order> selectList(@Param("senderId") Integer senderId);

    /**
     * 用户查询自己的订单
     *
     * @return
     */
    List<Order> selectUserOrder(@Param("userId") Integer userId);

    /**
     * 查询所有可接单的订单
     *
     * @return
     */
    List<Order> selectPublishOrder();
}
