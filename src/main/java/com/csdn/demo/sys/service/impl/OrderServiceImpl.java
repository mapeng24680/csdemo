package com.csdn.demo.sys.service.impl;/**
 * Created by dell on 2019/3/6.
 */

import com.csdn.demo.sys.dao.OrderDao;
import com.csdn.demo.sys.entity.Order;
import com.csdn.demo.sys.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 订单操作类
 * @author: mapeng
 * @create: 2019-03-06 16:03
 **/
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public void insert(Order order) {
        orderDao.insert(order);
    }

    @Override
    public void update(Order order) {
        orderDao.update(order);
    }

    @Override
    public List<Order> selectList(Integer senderId) {
        return orderDao.selectList(senderId);
    }

    @Override
    public List<Order> selectUserOrder(Integer userId) {
        return orderDao.selectList(userId);
    }

    @Override
    public List<Order> selectPublishOrder() {
        return orderDao.selectPublishOrder();
    }
}
