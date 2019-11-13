package com.pengniao.rdpm.system.service.impl;

import com.alibaba.druid.sql.visitor.functions.Right;
import com.pengniao.rdpm.component.GetRightTree;
import com.pengniao.rdpm.component.Tools;
import com.pengniao.rdpm.component.UUIDUtil;
import com.pengniao.rdpm.entity.TRight;
import com.pengniao.rdpm.entity.TRoleRightRelation;
import com.pengniao.rdpm.entity.TUserRoleRelation;
import com.pengniao.rdpm.system.mapper.RightMapper;
import com.pengniao.rdpm.system.mapper.RoleMapper;
import com.pengniao.rdpm.system.mapper.RoleRightRelationMapper;
import com.pengniao.rdpm.system.service.RightService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: rdpm
 * @description: 权限
 * @author: lj
 * @create: 2019-10-22 09:46
 **/
@Transactional
@Service
public class RightServiceImpl implements RightService {

    @Autowired
    private RightMapper rightMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleRightRelationMapper roleRightRelationMapper;
    @Autowired
    private TRoleRightRelation roleRightRelation;


    
    /** 功能描述: 获取权限
     * @param 
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/22 9:46
    */
    @Override
    public Object getRight() throws Exception {
        List<TRight> rightList= rightMapper.getRight();
        TRight right = rightMapper.getRightById(0);
        String res = GetRightTree.getRightForGrid(rightList, right);
        JSONArray jArray=JSONArray.fromObject(res);
        return jArray;
    }

    /** 功能描述: 新增权限
     * @param right
    * @return: void
    * @Author: lj
    * @Date: 2019/10/22 14:26
    */
    @Override
    public void addRight(TRight right) throws Exception {
        right.setRightId(String.valueOf(rightMapper.getLastRightId()+1));
        rightMapper.addRight(right);
    }

    /** 功能描述: 通过权限ID获取权限
     * @param rightId 权限ID
    * @return: com.pengniao.rdpm.entity.TRight
    * @Author: lj
    * @Date: 2019/10/22 15:10
    */
    @Override
    public TRight getRightById(Integer rightId)throws Exception {
        return rightMapper.getRightById(rightId);
    }

    /** 功能描述: 修改权限
     * @param right
    * @return: void
    * @Author: lj
    * @Date: 2019/10/22 15:30
    */
    @Override
    public void updateRight(TRight right) throws Exception {
        rightMapper.updateRight(right);
    }

    /** 功能描述: 删除权限
     * @param rightId 权限ID
     * @return: void
     * @Author: lj
     * @Date: 2019/10/22 15:44
     */
    @Override
    public void deleteRight(Integer rightId) throws Exception {
        rightMapper.deleteRight(rightId);
    }

    /** 功能描述: 通过权限ID获取子节点
     * @param rightId
    * @return: java.util.List<com.pengniao.rdpm.entity.TRight>
    * @Author: lj
    * @Date: 2019/10/22 16:00
    */
    @Override
    public List<TRight> getChildRightById(Integer rightId) {
        return rightMapper.getChildRightById(rightId);
    }

    /** 功能描述: 通过角色获取权限
     * @param roleId 角色ID
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/24 15:19
    */
    @Override
    public Object getRight(String roleId) {
        //获取所有权限
        //List<TRight> rightList = rightMapper.getRight();
        //获取当前登录用户的权限
        List<TRight> rightList= rightMapper.getRightByUserId(Tools.getUserSession().getUserId());
        //获取当前角色已有的权限
        List<TRoleRightRelation> roleRightList= roleRightRelationMapper.getRoleRightByRoleId(roleId);
        String res = "[";
        for(TRight right : rightList)
        {
            boolean sign = false;
            for(TRoleRightRelation roleRight : roleRightList)
            {
                if(roleRight.getRightId().equals(right.getRightId()))
                {
                    if(right.getParentTrId() == null || right.getParentTrId().equals(""))
                    {
                        res += "{ id:"+right.getRightId()+", pId:0, name:'"+right.getRightName()+"',checked:true},";
                    }else{
                        res += "{ id:"+right.getRightId()+", pId:"+right.getParentTrId()+", name:'"+right.getRightName()+"',checked:true},";
                    }
                    sign = true;
                }
            }
            if(sign == false)
            {
                if(right.getParentTrId() == null || right.getParentTrId().equals(""))
                {
                    res += "{ id:"+right.getRightId()+", pId:0, name:'"+right.getRightName()+"'},";
                }else{
                    res += "{ id:"+right.getRightId()+", pId:"+right.getParentTrId()+", name:'"+right.getRightName()+"'},";
                }
            }
        }
        res = res.substring(0,res.length()-1);
        res += "]";
        JSONArray jArray=JSONArray.fromObject(res);
        return jArray;
    }

    /** 功能描述: 通过管理员ID获取管理员现有的权限
     * @param userId
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2019/10/25 9:03
    */
    @Override
    public Object getAdministratorRight(String userId) {
        //通过管理员ID查询出对应的角色ID
        TUserRoleRelation userRole = roleMapper.getAdministratorUserRoleRelation(userId);
        //获取所有权限
        List<TRight> rightList = rightMapper.getRight();
        //获取当前角色已有的权限
        List<TRoleRightRelation> roleRightList= roleRightRelationMapper.getRoleRightByRoleId(userRole.getRoleId());
        String res = "[";
        for(TRight right : rightList)
        {
            boolean sign = false;
            for(TRoleRightRelation roleRight : roleRightList)
            {
                System.out.println(roleRight.getRightId());
                System.out.println(right.getRightId());
                if(roleRight.getRightId().equals(right.getRightId()))
                {
                    if(right.getParentTrId() == null || right.getParentTrId().equals(""))
                    {
                        res += "{ id:"+right.getRightId()+", pId:0, name:'"+right.getRightName()+"',checked:true},";
                    }else{
                        res += "{ id:"+right.getRightId()+", pId:"+right.getParentTrId()+", name:'"+right.getRightName()+"',checked:true},";
                    }
                    sign = true;
                }
            }
            if(sign == false)
            {
                if(right.getParentTrId() == null || right.getParentTrId().equals(""))
                {
                    res += "{ id:"+right.getRightId()+", pId:0, name:'"+right.getRightName()+"'},";
                }else{
                    res += "{ id:"+right.getRightId()+", pId:"+right.getParentTrId()+", name:'"+right.getRightName()+"'},";
                }
            }
        }
        res = res.substring(0,res.length()-1);
        res += "]";
        JSONArray jArray=JSONArray.fromObject(res);
        return jArray;
    }

    /** 功能描述: 新增管理员权限
     * @param userId 管理员ID
     * @param rightStr 权限
    * @return: void
    * @Author: lj
    * @Date: 2019/10/24 17:54
    */
    @Override
    public void insertAdministratorRoleRight(String userId, String rightStr) throws Exception {
        //通过管理员ID查询出对应的角色ID
        TUserRoleRelation userRole = roleMapper.getAdministratorUserRoleRelation(userId);
        String rightId [];
        if(rightStr.equals(""))
        {
            rightId = null;
            //通过角色ID删除现有的角色功能
            roleRightRelationMapper.deleteRoleRightByRoleId(userRole.getRoleId());
        }else{
            rightStr = rightStr.substring(0,rightStr.length()-1);
            rightId = rightStr.split(",");
            List<TRoleRightRelation> rolesRightList = new ArrayList<TRoleRightRelation>();
            for(int i=0;i<rightId.length;i++)
            {
                roleRightRelation = new TRoleRightRelation();
                roleRightRelation.setTrrId(UUIDUtil.getUUID());
                roleRightRelation.setRoleId(userRole.getRoleId());
                roleRightRelation.setRightId(rightId[i]);//权限ID
                rolesRightList.add(roleRightRelation);
            }
            //通过角色ID删除现有的角色权限
            roleRightRelationMapper.deleteRoleRightByRoleId(userRole.getRoleId());
            //批量新增角色权限
            roleRightRelationMapper.insertRolesRightBatch(rolesRightList);
        }
    }

    /** 功能描述: 新增角色权限
     * @param roleId 角色ID
     * @param rightStr 权限
    * @return: void
    * @Author: lj
    * @Date: 2019/10/24 16:38
    */
    @Override
    public void insertRoleRight(String roleId, String rightStr) throws Exception {
        String rightId [];
        if(rightStr.equals(""))
        {
            rightId = null;
            //通过角色ID删除现有的角色功能
            roleRightRelationMapper.deleteRoleRightByRoleId(roleId);
        }else{
            rightStr = rightStr.substring(0,rightStr.length()-1);
            rightId = rightStr.split(",");
            List<TRoleRightRelation> rolesRightList = new ArrayList<TRoleRightRelation>();
            for(int i=0;i<rightId.length;i++)
            {
                roleRightRelation = new TRoleRightRelation();
                roleRightRelation.setTrrId(UUIDUtil.getUUID());
                roleRightRelation.setRoleId(roleId);
                roleRightRelation.setRightId(rightId[i]);//权限ID
                rolesRightList.add(roleRightRelation);
            }
            //通过角色ID删除现有的角色权限
            roleRightRelationMapper.deleteRoleRightByRoleId(roleId);
            //批量新增角色权限
            roleRightRelationMapper.insertRolesRightBatch(rolesRightList);
        }
    }

    /** 功能描述: 通过用户ID获取权限
     * @param userId
    * @return: java.util.List<com.pengniao.rdpm.entity.TRight>
    * @Author: lj
    * @Date: 2019/10/29 9:27
    */
    @Override
    public List<TRight> getRightByUserId(String userId) {
        return rightMapper.getRightByUserId(userId);
    }

    /** 功能描述: 通过登录账号获取角色
     * @param loginName
    * @return: java.util.List<com.pengniao.rdpm.entity.TRight>
    * @Author: lj
    * @Date: 2019/10/29 9:58
    */
    @Override
    public List<TRight> getRightByLoginName(String loginName) {
        return rightMapper.getRightByLoginName(loginName);
    }


}
