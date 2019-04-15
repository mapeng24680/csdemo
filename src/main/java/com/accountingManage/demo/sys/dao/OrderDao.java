package com.accountingManage.demo.sys.dao;

import com.accountingManage.demo.sys.entity.Order;
import com.accountingManage.demo.sys.entity.aaa;
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
    List<Order> selectList(@Param("senderId") Integer senderId, @Param("name") String name, @Param("status") Integer status, @Param("senderName") String senderName);

    /**
     * 用户查询自己的订单
     *
     * @return
     */
    List<Order> selectUserOrder(@Param("userId") Integer userId, @Param("name") String name, @Param("status") Integer status, @Param("senderName") String senderName);

    /**
     * 查询所有可接单的订单
     *
     * @return
     */
    List<Order> selectPublishOrder();

    /**
     * 企业删除发布订单
     * @param id
     */
    void delete(@Param("id") Integer id);

    /**
     * 查询订单信息
     * @param id
     * @return
     */
    Order selectOrder(@Param("id")Integer id);

    /**
     * 根据订单id查询订单信息
     * @param id
     * @return
     */
    Order selectMsgById(@Param("id")Integer id);

    /**
     * 返回订单信息
     * @return
     */
    List<aaa> selectOrderCount();
}
