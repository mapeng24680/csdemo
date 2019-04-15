package com.accountingManage.demo.sys.service;/**
 * Created by dell on 2019/3/6.
 */

import com.accountingManage.demo.sys.entity.EnterprisePublished;

import java.util.List;

/**
 * @description: 企业信息发布管理
 * @author: mapeng
 * @create: 2019-03-06 10:47
 **/
public interface EnterprisePublishedService {
    /**
     * 企业发布需求
     * @param enterprisePublished
     */
    void save(EnterprisePublished enterprisePublished);

    /**
     * 企业修改发布的需求
     * @param enterprisePublished
     */
    void update(EnterprisePublished enterprisePublished);

    /**
     * 查询企业发布的需求
     * @param userId
     * @return
     */
    List<EnterprisePublished> selectList(Integer userId);

}
