package com.accountingManage.demo.sys.dto;/**
 * Created by dell on 2019/3/7.
 */

/**
 * @description:
 * @author: mapeng
 * @create: 2019-03-07 14:29
 **/
public class UserDTO {
    private String name;
    private String status;
    private String senderName;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
