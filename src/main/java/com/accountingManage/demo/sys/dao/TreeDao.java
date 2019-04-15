package com.accountingManage.demo.sys.dao;


import com.accountingManage.demo.common.base.dao.GenericDao;
import com.accountingManage.demo.sys.entity.QueryTree;
import com.accountingManage.demo.sys.entity.Tree;
import com.accountingManage.demo.sys.entity.User;

import java.util.List;

/**
 *@author linzf
 **/
public interface TreeDao extends GenericDao<Tree, QueryTree> {

    /**
     * 功能描述：加载用户的菜单树的数据
     * @param user
     * @return
     */
	List<Tree> loadUserTree(User user);
}