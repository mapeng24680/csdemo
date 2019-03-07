package com.csdn.demo.sys.dao;/**
 * Created by dell on 2019/3/7.
 */

import com.csdn.demo.sys.entity.Evaluation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 评价
 * @author: mapeng
 * @create: 2019-03-07 10:20
 **/

public interface EvaluationDao {
    /**
     * 新增评价
     * @param evaluation
     */
    void insert(Evaluation evaluation);

    /**
     * 修改评价状态
     * @param id
     */
    void update(@Param("id")Integer id);

    /**
     * 企业获取评价信息
     * @return
     */
    List<Evaluation> selectSender(@Param("senderId")Integer senderId);

    /**
     * 用户获取企业评价信息
     * @return
     */
    List<Evaluation> selectUser(@Param("userId")Integer userId);
}
