package com.accountingManage.demo.sys.dao;

import com.accountingManage.demo.sys.entity.Message;

import java.util.List;

/**
 * 消息发布
 * Created by 10093 on 2019/3/10.
 */
public interface MessageDao {
    /**
     * 新增管理员发布信息
     * @param message
     */
    void insert(Message message);

    /**
     * 查询管理员发布信息
     * @return
     */
    List<Message> select();
}
