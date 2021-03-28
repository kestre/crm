package org.example.crm.service;

import org.example.crm.base.BaseService;
import org.example.crm.dao.UserMapper;
import org.example.crm.dao.UserRoleMapper;
import org.example.crm.model.UserModel;
import org.example.crm.utils.AssertUtil;
import org.example.crm.utils.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.example.crm.utils.PhoneUtil;
import org.example.crm.utils.UserIDBase64;
import org.example.crm.vo.User;
import org.example.crm.vo.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserService extends BaseService<User, Integer> {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    public  UserModel userLogin(String userName, String userPwd){

        checkLoginParams(userName, userPwd);

        User user = userMapper.queryUserByName(userName);

        AssertUtil.isTrue(user == null,"用户姓名不存在");

        checkUserPwd(userPwd, user.getUserPwd());

        return buildUserInfo(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePassWord(Integer userId, String oldPwd, String newPwd, String repeatPwd){
        User user = userMapper.selectByPrimaryKey(userId);

        AssertUtil.isTrue(null == user, "待更新记录不存在！");

        checkPasswordParams(user, oldPwd,newPwd, repeatPwd);

        user.setUserPwd(Md5Util.encode(newPwd));

        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) < 1, "修改密码失败！");
    }

    private void checkPasswordParams(User user, String oldPwd, String newPwd, String repeatPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(oldPwd), "原始密码不能为空");
        AssertUtil.isTrue(!user.getUserPwd().equals(Md5Util.encode(oldPwd)),"原始密码不正确");

        AssertUtil.isTrue(StringUtils.isBlank(newPwd),"新密码不能为空！");
        AssertUtil.isTrue(oldPwd.equals(newPwd),"新密码不能与原始密码相同！");
        AssertUtil.isTrue(StringUtils.isBlank(repeatPwd),"确认密码不能为空！");
        AssertUtil.isTrue(!newPwd.equals(repeatPwd),"确认密码与新密码不一致！");
    }

    private UserModel buildUserInfo(User user) {

        UserModel userModel = new UserModel();
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());

        return userModel;
    }

    private void checkUserPwd(String userPwd, String pwd) {
        userPwd = Md5Util.encode(userPwd);
        AssertUtil.isTrue(!userPwd.equals(pwd),"用户密码不正确");
    }

    private void checkLoginParams(String userName, String userPwd){
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户姓名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd), "用户密码不能为空");
    }
    //查询所有的销售人员
    public List<Map<String, Object>> queryAllSales(){
        return userMapper.queryAllSales();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user) {
        checkUserParams(user.getUserName(), user.getEmail(), user.getPhone(), user.getId());

        user.setIsValid(1);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        user.setUserPwd(Md5Util.encode("123456"));

        AssertUtil.isTrue(userMapper.insertSelective(user) != 1, "添加失败！");

        //  用户角色关联
        relationUserRole(user.getId(), user.getRoleIds());
    }

    private void relationUserRole(Integer userId, String roleIds) {
        Integer count = userRoleMapper.countUserRoleByUserId(userId);

        if(count > 0) {
            AssertUtil.isTrue(userRoleMapper.deleteUserRoleByUserId(userId) != count, "用户角色分配失败！");
        }

        if(StringUtils.isNotBlank(roleIds)) {
            List<UserRole> userRolesList = new ArrayList<>();

            String[] roleIdsArray = roleIds.split(",");
            for (String roleId: roleIdsArray) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(Integer.parseInt(roleId));
                userRole.setUserId(userId);
                userRole.setCreateDate(new Date());
                userRole.setUpdateDate(new Date());

                userRolesList.add(userRole);
            }
            AssertUtil.isTrue(userRoleMapper.insertBatch(userRolesList) != userRolesList.size(), "用户角色分配失败！");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user) {

        AssertUtil.isTrue(null == user.getId(), "待更新记录不存在！");

        User temp = userMapper.selectByPrimaryKey(user.getId());

        AssertUtil.isTrue(null == temp, "待更新记录不存在！");
        checkUserParams(user.getUserName(), user.getEmail(), user.getPhone(), user.getId());

        user.setUpdateDate(new Date());

        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) != 1, "添加失败！");

        relationUserRole(user.getId(), user.getRoleIds());
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(Integer[] ids){

        AssertUtil.isTrue(null == ids || ids.length < 1, "请选择！");

        AssertUtil.isTrue(userMapper.deleteBatch(ids) != ids.length, "删除失败！");

        for (Integer userId: ids) {
            Integer count = userRoleMapper.countUserRoleByUserId(userId);

            if (count > 0) {
                AssertUtil.isTrue(userRoleMapper.deleteUserRoleByUserId(userId) != count, "删除失败！");
            }
        }
    }

    private void checkUserParams(String userName, String email, String phone, Integer userId) {

        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名不能为空！");
        User temp = userMapper.queryUserByName(userName);

        AssertUtil.isTrue(null != temp && !(temp.getId().equals(userId)), "用户名已存在！");
        AssertUtil.isTrue(StringUtils.isBlank(email), "用户邮箱不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(phone), "手机号码不能为空！");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone), "手机号码格式不正确！");
    }

}
