package com.accountingManage.demo.sys.dao;/**
 * Created by dell on 2019/3/7.
 */

import com.accountingManage.demo.sys.entity.Evaluation;
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
     * 删除评价
     * @param
     */
    void update(@Param("orderId")Integer orderId,@Param("roleId")Integer roleId);

    /**添加评价内容
     * @param
     */
    void updateMsg(@Param("orderId")Integer orderId,@Param("roleId")Integer roleId,@Param("msg")String msg);

    /**
     * 获取评价信息
     * @return
     */
    Evaluation select(@Param("orderId")Integer orderId);

}
