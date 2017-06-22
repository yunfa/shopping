package com.alpha.common.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alpha.common.model.URole;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface URoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(URole record);

    int insertSelective(URole record);

    URole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(URole record);

    int updateByPrimaryKey(URole record);

    Set<String> findRoleByUserId(Long id);

    List<URole> findNowAllPermission(Map<String, Object> map);

    void initData();
}
