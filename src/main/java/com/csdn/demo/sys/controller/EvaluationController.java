package com.csdn.demo.sys.controller;/**
 * Created by dell on 2019/3/7.
 */

import com.csdn.demo.common.base.constant.SystemStaticConst;
import com.csdn.demo.common.util.user.UserInfo;
import com.csdn.demo.sys.dao.EvaluationDao;
import com.csdn.demo.sys.entity.Evaluation;
import com.csdn.demo.sys.entity.QueryUserAssociateRole;
import com.csdn.demo.sys.entity.UserAssociateRole;
import com.csdn.demo.sys.service.UserAssociateRoleService;
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
    public Map<String, Object> insert(@RequestBody Evaluation evaluation) {
        evaluationDao.insert(evaluation);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put(SystemStaticConst.MSG, "保存成功");
        return result;
    }

    /**
     * 删除评价
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> update(Integer id) {
        evaluationDao.update(id);
        Map<String, Object> result = new HashMap<String, Object>();
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
    public Map<String, Object> selectList() {
        Integer uId = UserInfo.getUser().getId();
        QueryUserAssociateRole queryUserAssociateRole = new QueryUserAssociateRole();
        queryUserAssociateRole.setUserId(uId);
        Map<String, Object> result = new HashMap<String, Object>();
        List<UserAssociateRole> rolelist = userAssociateRoleService.query(queryUserAssociateRole);
        if (rolelist != null && rolelist.size() > 0) {
            long roleId = rolelist.get(0).getRoleId();
            if(5==roleId){
                result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
                result.put("data", evaluationDao.selectSender(uId));
            }else if(3==roleId){
                result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
                result.put("data", evaluationDao.selectUser(uId));
            }
        }
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        return result;
    }
}
