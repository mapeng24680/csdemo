package com.accountingManage.demo.sys.entity;/**
 * Created by dell on 2019/3/6.
 */

import com.accountingManage.demo.common.util.user.CommonUserUtil;

/**
 * @description:订单实体
 * @author: mapeng
 * @create: 2019-03-06 11:33
 **/
public class Order {
    private Integer id;
    private Integer senderId;
    private Integer userId;
    private String orderNum;
    private String orderMsg;
    private String commission;
    private Integer status;
    private String createTime;
    private String updateTime;
    /**扩展字段**/
    private String senderName;
    private String cuserName;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getCuserName() {
        return cuserName;
    }

    public void setCuserName(String cuserName) {
        this.cuserName = cuserName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderMsg() {
        return orderMsg;
    }

    public void setOrderMsg(String orderMsg) {
        this.orderMsg = orderMsg;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return CommonUserUtil.timeChange(createTime);
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return CommonUserUtil.timeChange(updateTime);
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
