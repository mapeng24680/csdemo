package com.accountingManage.demo.sys.entity;/**
 * Created by dell on 2019/3/6.
 */

/**
 * @description: 企业信息
 * @author: mapeng
 * @create: 2019-03-06 09:33
 **/
public class EnterprisePublished {
    private Integer id;
    private Integer userId;
    private String needMsg;
    private Integer status;
    private String createTime;
    private String updateTime;
    /**扩展字段**/
    private String userName;
    private String address;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNeedMsg() {
        return needMsg;
    }

    public void setNeedMsg(String needMsg) {
        this.needMsg = needMsg;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
