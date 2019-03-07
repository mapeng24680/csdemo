package com.csdn.demo.sys.dao;/**
 * Created by dell on 2019/3/7.
 */

import com.csdn.demo.sys.entity.Evaluation;

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
}
