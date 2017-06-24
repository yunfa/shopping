package com.alpha.permission.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alpha.common.model.PermissionBean;
import com.alpha.core.mybatis.page.Pagination;
import com.alpha.permission.bo.PermissionBo;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface PermissionService {

    int deleteByPrimaryKey(Long id);

    PermissionBean insert(PermissionBean record);

    PermissionBean insertSelective(PermissionBean record);

    PermissionBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PermissionBean record);

    int updateByPrimaryKey(PermissionBean record);

    Map<String, Object> deletePermissionById(String ids);

    Pagination<PermissionBean> findPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

    List<PermissionBo> selectPermissionById(Long id);

    Map<String, Object> addPermission2Role(Long roleId, String ids);

    Map<String, Object> deleteByRids(String roleIds);

    //根据用户ID查询权限（permission），放入到Authorization里。
    Set<String> findPermissionByUserId(Long userId);
}
