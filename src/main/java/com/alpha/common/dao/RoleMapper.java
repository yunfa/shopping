package com.alpha.common.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alpha.common.model.RoleBean;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface RoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(RoleBean record);

    int insertSelective(RoleBean record);

    RoleBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleBean record);

    int updateByPrimaryKey(RoleBean record);

    Set<String> findRoleByUserId(Long id);

    List<RoleBean> findNowAllPermission(Map<String, Object> map);

    void initData();
}
