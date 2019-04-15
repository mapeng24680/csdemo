package com.accountingManage.demo.sys.controller;/**
 * Created by dell on 2019/3/7.
 */

import com.accountingManage.demo.common.base.constant.SystemStaticConst;
import com.accountingManage.demo.common.util.user.UserInfo;
import com.accountingManage.demo.sys.dao.EvaluationDao;
import com.accountingManage.demo.sys.entity.Evaluation;
import com.accountingManage.demo.sys.entity.QueryUserAssociateRole;
import com.accountingManage.demo.sys.entity.UserAssociateRole;
import com.accountingManage.demo.sys.service.UserAssociateRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 评价模块
 * @author: mapeng
 * @create: 2019-03-07 10:35
 **/
@Controller
@RequestMapping("/evaluation")
public class EvaluationController {
    @Autowired
    private EvaluationDao evaluationDao;
    @Autowired
    private UserAssociateRoleService userAssociateRoleService;

    /**
     * 新加评价
     *
     * @param evaluation
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> insert(Evaluation evaluation) {
        evaluationDao.insert(evaluation);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG, "保存成功");
        return result;
    }

    /**
     * 删除评价
     * @param
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> update(Integer  orderId) {
        Integer uid = UserInfo.getUser().getId();
        QueryUserAssociateRole queryUserAssociateRole = new QueryUserAssociateRole();
        queryUserAssociateRole.setUserId(uid);
        List<UserAssociateRole> rolelist = userAssociateRoleService.query(queryUserAssociateRole);
        Map<String, Object> result = new HashMap<String, Object>();
        Integer roleId=null;
        if (rolelist != null && rolelist.size() > 0) {
             roleId = (int)rolelist.get(0).getRoleId();
        }
        evaluationDao.update(orderId,roleId);
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG, "更新成功");
        return result;
    }

    /**
     * 更新评价
     * @param
     * @return
     */
    @RequestMapping(value = "/updateMsg", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> updateMsg(Integer  orderId,String msg) {
        Integer uid = UserInfo.getUser().getId();
        QueryUserAssociateRole queryUserAssociateRole = new QueryUserAssociateRole();
        queryUserAssociateRole.setUserId(uid);
        List<UserAssociateRole> rolelist = userAssociateRoleService.query(queryUserAssociateRole);
        Map<String, Object> result = new HashMap<String, Object>();
        Integer roleId=null;
        if (rolelist != null && rolelist.size() > 0) {
            roleId = (int)rolelist.get(0).getRoleId();
        }
        evaluationDao.updateMsg(orderId,roleId,msg);
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG, "更新成功");
        return result;
    }


    /**
     * 查询评价
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/selectList", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> selectList(Integer orderId) {
        Integer uid = UserInfo.getUser().getId();
        QueryUserAssociateRole queryUserAssociateRole = new QueryUserAssociateRole();
        queryUserAssociateRole.setUserId(uid);
        List<UserAssociateRole> rolelist = userAssociateRoleService.query(queryUserAssociateRole);
        Map<String, Object> result = new HashMap<String, Object>();
        Integer roleId=null;
        if (rolelist != null && rolelist.size() > 0) {
            roleId = (int)rolelist.get(0).getRoleId();
        }
        Evaluation el = evaluationDao.select(orderId);
        el.setRoleId(roleId);
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put("data", el);
        return result;
    }
}
