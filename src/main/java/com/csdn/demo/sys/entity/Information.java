package com.csdn.demo.sys.entity;

/**
 * Created by 10093 on 2019/3/15.
 */
public class Information {
    private Integer id;
    private String msg;
    private Integer userId;
    private String createTime;
    private String sessionId;
    private Integer type;
    private Integer bSendeUserId;

    public Integer getbSendeUserId() {
        return bSendeUserId;
    }

    public void setbSendeUserId(Integer bSendeUserId) {
        this.bSendeUserId = bSendeUserId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
