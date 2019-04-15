package com.accountingManage.demo.sys.entity;


import com.accountingManage.demo.common.util.user.CommonUserUtil;

public class Message {
    private Integer id;
    private String userName;
    private String message;
    private String createTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateTime() {
        return  CommonUserUtil.timeChange(createTime);
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
