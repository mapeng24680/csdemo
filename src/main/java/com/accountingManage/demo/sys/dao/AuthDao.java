package com.accountingManage.demo.sys.dao;

import com.accountingManage.demo.sys.entity.Auth;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 10093 on 2019/3/10.
 */
public interface AuthDao {
    /**
     * 新增审核
     */
    void insert(Auth auth);

    /**
     *
     */
    void update(@Param("authName") String authName,@Param("id")Integer id);

    /**
     * 查询审核列表
      * @return
     */
    List<Auth> select(Auth auth);

    /**
     * 根据申请id获取详情
     * @param id
     */
    Auth selectOne(@Param("id") Integer id );
    /**
     * 根据用户id获取详情
     * @param
     */
    Auth selectByUserId(@Param("userId") Integer userId);

    /**
     * 审核通过
     */
   void updateRole(@Param("userId") Integer userId);

}
