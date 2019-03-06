package com.csdn.demo.sys.dao;


import com.csdn.demo.sys.entity.EnterprisePublished;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 企业信息公布
 *
 * @author mp
 **/
public interface EnterprisePublishedDao {
    /**
     * 企业发布需求
     *
     * @param enterprisePublished
     */
    void save(EnterprisePublished enterprisePublished);

    /**
     * 企业修改发布的需求
     *
     * @param enterprisePublished
     */
    void update(EnterprisePublished enterprisePublished);

    /**
     * 查询企业发布的需求
     *
     * @param userId
     * @return
     */
    List<EnterprisePublished> selectList(@Param("userId") Integer userId);
}