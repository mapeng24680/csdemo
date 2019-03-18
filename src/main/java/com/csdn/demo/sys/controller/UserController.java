package com.csdn.demo.sys.controller;


import com.csdn.demo.common.base.constant.SystemStaticConst;
import com.csdn.demo.common.base.controller.GenericController;
import com.csdn.demo.common.base.entity.Page;
import com.csdn.demo.common.base.service.GenericService;
import com.csdn.demo.common.util.json.JsonHelper;
import com.csdn.demo.common.util.user.UserInfo;
import com.csdn.demo.sys.dao.UserDao;
import com.csdn.demo.sys.entity.QueryUser;
import com.csdn.demo.sys.entity.Tree;
import com.csdn.demo.sys.entity.User;
import com.csdn.demo.sys.entity.UserRole;
import com.csdn.demo.sys.service.TreeService;
import com.csdn.demo.sys.service.UserAssociateRoleService;
import com.csdn.demo.sys.service.UserRoleService;
import com.csdn.demo.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.*;

/*
* 类描述：用户维护controller
* @auther linzf
* @create 2017/9/7 0007
*/
@Controller
@RequestMapping("/user")
public class UserController extends GenericController<User, QueryUser> {

    @Inject
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Inject
    private TreeService treeService;
    @Inject
    private UserRoleService userRoleService;
    @Inject
    private UserAssociateRoleService userAssociateRoleService;

    @Override
    protected GenericService<User, QueryUser> getService() {
        return userService;
    }

    /**
     * 功能描述：加载首页菜单节点的数据
     *
     * @return
     */
    @RequestMapping(value = "/mainTree", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> mainTree() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Tree> trees = UserInfo.loadUserTree(treeService);
        result.put("data", trees);
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        return result;
    }

    /**
     * 功能描述：更新用户状态为禁用/启用
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/userControl", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> userControl(User entity) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        if (userService.userControl(entity)) {
            result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
            result.put(SystemStaticConst.MSG, "更新用户状态成功！");
            result.put("entity", entity);
        } else {
            result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
            result.put(SystemStaticConst.MSG, "更新用户状态失败！");
        }
        return result;
    }

    /**
     * 功能描述：跳转到更新用户的页面
     *
     * @param entity
     * @param model
     * @return
     * @throws Exception
     */
    @Override
    public String updatePage(User entity, Model model) throws Exception {
        entity = getService().get(entity);
        entity.setRoleArray(JsonHelper.list2json(Optional.ofNullable(userService.findByLogin(entity.getLogin())).filter(u -> u != null).orElse(new User()).getRoles()));
        model.addAttribute("entity", entity);
        return getPageBaseRoot() + UPDATEPAGE;
    }

    /**
     * 功能描述：加载所有的权限数据
     *
     * @return
     */
    @RequestMapping(value = "/selectByLogin", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> selectByLogin(String login) {
        Map<String, Object> result = new HashMap<String, Object>();
        User user = userService.findByLogin(login);
        if(user!=null){
            result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
            result.put(SystemStaticConst.MSG, "账号已存在");
        }else{
            result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        }
        return result;
    }

    /**
     * 功能描述：加载所有的权限数据
     *
     * @return
     */
    @RequestMapping(value = "/loadRoles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> loadRoles() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<UserRole> userRoleList = userRoleService.query(null);
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put("list", userRoleList);
        return result;
    }

    /**
     * 功能描述：跳转到选择组织架构页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/pickGroup")
    public String pickGroup() throws Exception {
        return getPageBaseRoot() + "/group";
    }

    /**
     * 功能描述：加载所有的权限数据
     *
     * @return
     */
    @RequestMapping(value = "/registered", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> registered() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<UserRole> userRoleList = userRoleService.query(null);
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put("list", userRoleList);
        return result;
    }


    /**
     * 根据账号获取用户信息
     * @return
     */
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUser() {
        List list = new ArrayList<>();
        Integer uId = UserInfo.getUser().getId();
        Map<String,Object> result = new HashMap<String, Object>();
        User user = new User();
        user.setId(uId);
        User user1= userService.get(user);
        list.add(user1);
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put("totalCount",1);
        result.put("result",list);
        return result;
    }



    /**
     * 根据账号获取用户信息
     * @return
     */
    @RequestMapping(value = "/updatePassWord", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUser(String newPassWord,String oldPassword,Integer type) {
        Map<String,Object> result = new HashMap<String, Object>();
        String login= UserInfo.getUser().getLogin();
        if(1==type){

            User user=userService.findByLogin(login);
            String olpsw = UserInfo.encode(oldPassword);
            String newpsw = UserInfo.encode(newPassWord);
            if(!olpsw.equals(user.getPassword())){
                result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
                result.put(SystemStaticConst.MSG, "旧密码不正确");
                return result;
            }
            userDao.updateUserPassWord(newpsw,login);
        }else if(2==type){
            userDao.updateUserPassWord(UserInfo.encode("123456"),login);
        }
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        return result;
    }
}
