package com.alpha.common.dao;

import java.util.List;
import java.util.Map;

import com.alpha.common.model.RolePermissionBean;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface RolePermissionMapper {

    int insert(RolePermissionBean record);

    int insertSelective(RolePermissionBean record);

    List<RolePermissionBean> findRolePermissionByPid(Long id);

    List<RolePermissionBean> findRolePermissionByRid(Long id);

    List<RolePermissionBean> find(RolePermissionBean entity);

    int deleteByPid(Long id);

    int deleteByRid(Long id);

    int delete(RolePermissionBean entity);

    int deleteByRids(Map<String, Object> resultMap);
}
