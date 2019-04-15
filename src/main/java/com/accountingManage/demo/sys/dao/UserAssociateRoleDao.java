package com.accountingManage.demo.sys.dao;


import com.accountingManage.demo.common.base.dao.GenericDao;
import com.accountingManage.demo.sys.entity.QueryUserAssociateRole;
import com.accountingManage.demo.sys.entity.User;
import com.accountingManage.demo.sys.entity.UserAssociateRole;

/**
 *@author linzf
 **/
public interface UserAssociateRoleDao extends GenericDao<UserAssociateRole, QueryUserAssociateRole> {

    /**
     * 功能描述：根据用户的ID来删除用户的权限数据
     * @param user
     * @return
     */
    int removeUserRole(User user);
}