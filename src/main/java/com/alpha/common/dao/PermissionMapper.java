package com.alpha.common.dao;

import java.util.List;
import java.util.Set;

import com.alpha.common.model.PermissionBean;
import com.alpha.permission.bo.PermissionBo;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface PermissionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(PermissionBean record);

    int insertSelective(PermissionBean record);

    PermissionBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PermissionBean record);

    int updateByPrimaryKey(PermissionBean record);

    List<PermissionBo> selectPermissionById(Long id);

    //根据用户ID获取权限的Set集合
    Set<String> findPermissionByUserId(Long id);
}
