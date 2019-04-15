package com.accountingManage.demo.sys.dao;/**
 * Created by dell on 2019/3/7.
 */

import com.accountingManage.demo.sys.entity.AccountChange;

/**
 * @description: 账号流水
 * @author: mapeng
 * @create: 2019-03-07 17:13
 **/
public interface AccountChangeDao {
    /**
     * 新增流水
     *
     * @param accountChange
     */
    void insert(AccountChange accountChange);
}
