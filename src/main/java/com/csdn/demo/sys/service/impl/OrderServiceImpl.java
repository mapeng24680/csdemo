package com.csdn.demo.sys.service.impl;/**
 * Created by dell on 2019/3/6.
 */

import com.csdn.demo.sys.dao.AccountChangeDao;
import com.csdn.demo.sys.dao.OrderDao;
import com.csdn.demo.sys.dao.UserDao;
import com.csdn.demo.sys.entity.AccountChange;
import com.csdn.demo.sys.entity.Order;
import com.csdn.demo.sys.entity.User;
import com.csdn.demo.sys.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    @Autowired
    private UserDao userDao;
    @Autowired
    private AccountChangeDao accountChangeDao;
    @Override
    public void insert(Order order) {
        orderDao.insert(order);
    }

    @Override
    public void update(Order order) {
        orderDao.update(order);
    }

    @Override
    public List<Order> selectList(Integer senderId, String name, Integer status,String senderName) {
        return orderDao.selectList(senderId, name, status,senderName);
    }

    @Override
    public List<Order> selectUserOrder(Integer userId, String name, Integer status,String senderName) {
        return orderDao.selectList(userId, name, status,senderName);
    }

    @Override
    public List<Order> selectPublishOrder() {
        return orderDao.selectPublishOrder();
    }

    @Override
    public void delete(Integer id) {
        orderDao.delete(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void dealStatusAndAccount(Order order) {
        orderDao.update(order);
        Order od = orderDao.selectOrder(order.getId());
        User user = new User();
        user.setId(od.getUserId());
        user =userDao.get(user);
        String account = new BigDecimal(od.getCommission()).add(new BigDecimal(user.getAccount())).toString();
        user.setAccount(account);
        userDao.updateAccount(user);
        AccountChange accountChange = new AccountChange();
        accountChange.setAccount(account);
        accountChange.setMoney(od.getCommission());
        accountChange.setUserId(od.getUserId());
        accountChange.setEnterpriseName(od.getSenderName());
        accountChange.setOrderMsg(od.getOrderMsg());
        accountChange.setOrderNum(od.getOrderNum());
        accountChangeDao.insert(accountChange);
    }
}
