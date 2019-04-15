package com.accountingManage.demo.sys.dao;

import com.accountingManage.demo.sys.entity.Auth;
import com.accountingManage.demo.sys.entity.Information;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 10093 on 2019/3/10.
 */
public interface InformationDao {
    /**
     * 新增聊天
     */
    void insert(Information information);

    /**
     * 查询聊天记录
     * @param userId
     * @param sessionId
     * @return
     */
    List<Information> select(@Param("userId")Integer userId,@Param("sessionId")String sessionId);
}
