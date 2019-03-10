package com.csdn.demo.sys.dao;

import com.csdn.demo.sys.entity.Contract;
import org.apache.ibatis.annotations.Param;

/**
 * 合同
 * Created by dell on 2019/3/8.
 */
public interface ContractDao {
    /**
     * 新增合同
     */
    void insert(Contract contract);

    /**
     * 查看合同
     * @param orderId
     * @return
     */
    Contract select(@Param("orderId")Integer orderId);

    /**
     * 签约合同/过期合同
     * @param contract
     */
    void update(Contract contract);
}
