package com.accountingManage.demo.sys.service;


import com.accountingManage.demo.common.base.dao.GenericDao;
import com.accountingManage.demo.common.base.service.GenericService;
import com.accountingManage.demo.sys.dao.UserAssociateRoleDao;
import com.accountingManage.demo.sys.entity.QueryUserAssociateRole;
import com.accountingManage.demo.sys.entity.User;
import com.accountingManage.demo.sys.entity.UserAssociateRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *@author linzf
 **/
@Service("userAssociateRoleService")
@Transactional(rollbackFor={IllegalArgumentException.class})
public class UserAssociateRoleService extends GenericService<UserAssociateRole, QueryUserAssociateRole> {
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private UserAssociateRoleDao userAssociateRoleDao;
	@Override
	protected GenericDao<UserAssociateRole, QueryUserAssociateRole> getDao() {
		return userAssociateRoleDao;
	}

	/**
	 * 功能描述：根据用户的ID来删除用户的权限数据
	 * @param user
	 * @return
	 */
	public boolean removeUserRole(User user){
		return userAssociateRoleDao.removeUserRole(user)>0;
	}
}