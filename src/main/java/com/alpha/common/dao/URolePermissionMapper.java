package com.alpha.common.dao;

import java.util.List;
import java.util.Map;

import com.alpha.common.model.URolePermission;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface URolePermissionMapper {

    int insert(URolePermission record);

    int insertSelective(URolePermission record);

    List<URolePermission> findRolePermissionByPid(Long id);

    List<URolePermission> findRolePermissionByRid(Long id);

    List<URolePermission> find(URolePermission entity);

    int deleteByPid(Long id);

    int deleteByRid(Long id);

    int delete(URolePermission entity);

    int deleteByRids(Map<String, Object> resultMap);
}
