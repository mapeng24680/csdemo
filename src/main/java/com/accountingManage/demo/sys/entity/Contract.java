package com.accountingManage.demo.sys.entity;/**
 * Created by dell on 2019/3/8.
 */

import com.accountingManage.demo.common.util.user.CommonUserUtil;

/**
 * @description: 合同
 * @author: mapeng
 * @create: 2019-03-08 09:35
 **/
public class Contract {
    private Integer id;
    private String contractNum;
    private Integer orderId;
    private String contractMsg;
    private String orderNum;
    private Integer userId;
    private Integer enterpriseId;
    private String overdueTime;
    private String creatTime;
    private Integer status;
    private Integer tag;
    /**扩展字段**/
    private String cUserName;
    private String senderName;

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public String getContractMsg() {
        return contractMsg;
    }

    public void setContractMsg(String contractMsg) {
        this.contractMsg = contractMsg;
    }

    public String getOverdueTime() {
        return CommonUserUtil.timeChange(overdueTime);
    }

    public void setOverdueTime(String overdueTime) {
        this.overdueTime = overdueTime;
    }

    public String getCreatTime() {
        return CommonUserUtil.timeChange(creatTime);
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getcUserName() {
        return cUserName;
    }

    public void setcUserName(String cUserName) {
        this.cUserName = cUserName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}
