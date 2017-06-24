package com.alpha.permission.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.common.dao.RoleMapper;
import com.alpha.common.dao.RolePermissionMapper;
import com.alpha.common.dao.UserMapper;
import com.alpha.common.model.RoleBean;
import com.alpha.core.mybatis.BaseMybatisDao;
import com.alpha.core.mybatis.page.Pagination;
import com.alpha.core.shiro.token.manager.TokenManager;
import com.alpha.permission.bo.RolePermissionAllocationBo;
import com.alpha.permission.service.RoleService;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
@Service
@SuppressWarnings("unchecked")
public class RoleServiceImpl extends BaseMybatisDao<RoleMapper> implements RoleService {

    private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RoleBean record) {
        return roleMapper.insert(record);
    }

    @Override
    public int insertSelective(RoleBean record) {
        return roleMapper.insertSelective(record);
    }

    @Override
    public RoleBean selectByPrimaryKey(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(RoleBean record) {
        return roleMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(RoleBean record) {
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Pagination<RoleBean> findPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    public Pagination<RolePermissionAllocationBo> findRoleAndPermissionPage(Map<String, Object> resultMap,
                                                                            Integer pageNo, Integer pageSize) {
        return super.findPage("findRoleAndPermission", "findCount", resultMap, pageNo, pageSize);
    }

    @Override
    public Map<String, Object> deleteRoleById(String ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            int count = 0;
            String resultMsg = "删除成功。";
            String[] idArray = new String[] {};
            if (StringUtils.contains(ids, ",")) {
                idArray = ids.split(",");
            } else {
                idArray = new String[] { ids };
            }

            c: for (String idx : idArray) {
                Long id = new Long(idx);
                if (new Long(1).equals(id)) {
                    resultMsg = "操作成功，But'系统管理员不能删除。";
                    continue c;
                } else {
                    count += this.deleteByPrimaryKey(id);
                }
            }
            resultMap.put("status", 200);
            resultMap.put("count", count);
            resultMap.put("resultMsg", resultMsg);
        } catch (Exception e) {
            logger.error("根据IDS删除用户出现错误，ids:{}", ids, e);
            resultMap.put("status", 500);
            resultMap.put("message", "删除出现错误，请刷新后再试！");
        }
        return resultMap;
    }

    @Override
    public Set<String> findRoleByUserId(Long userId) {
        return roleMapper.findRoleByUserId(userId);
    }

    @Override
    public List<RoleBean> findNowAllPermission() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", TokenManager.getUserId());
        return roleMapper.findNowAllPermission(map);
    }

    /**
     * 每20分钟执行一次
     */
    @Override
    public void initData() {
        roleMapper.initData();
    }

}
