package com.accountingManage.demo.sys.service;

import com.accountingManage.demo.common.base.dao.GenericDao;
import com.accountingManage.demo.common.base.service.GenericService;
import com.accountingManage.demo.sys.dao.RoleAssociateTreeDao;
import com.accountingManage.demo.sys.entity.QueryRoleAssociateTree;
import com.accountingManage.demo.sys.entity.RoleAssociateTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *@author linzf
 **/
@Service("roleAssociateTreeService")
@Transactional(rollbackFor={IllegalArgumentException.class})
public class RoleAssociateTreeService extends GenericService<RoleAssociateTree, QueryRoleAssociateTree> {
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private RoleAssociateTreeDao roleAssociateTreeDao;
	@Override
	protected GenericDao<RoleAssociateTree, QueryRoleAssociateTree> getDao() {
		return roleAssociateTreeDao;
	}
}