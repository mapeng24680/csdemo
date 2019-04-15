package com.accountingManage.demo.sys.service.impl;/**
 * Created by dell on 2019/3/6.
 */

import com.accountingManage.demo.sys.dao.EnterprisePublishedDao;
import com.accountingManage.demo.sys.entity.EnterprisePublished;
import com.accountingManage.demo.sys.service.EnterprisePublishedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 企业信息管理
 * @author: mapeng
 * @create: 2019-03-06 10:49
 **/
@Service
public class EnterprisePublishedServiceImpl implements EnterprisePublishedService {
    @Autowired
    private EnterprisePublishedDao enterprisePublishedDao;
    @Override
    public void save(EnterprisePublished enterprisePublished) {
        enterprisePublishedDao.save(enterprisePublished);
    }

    @Override
    public void update(EnterprisePublished enterprisePublished) {
        enterprisePublishedDao.update(enterprisePublished);
    }

    @Override
    public List<EnterprisePublished> selectList(Integer userId) {
        return enterprisePublishedDao.selectList(userId);
    }
}
