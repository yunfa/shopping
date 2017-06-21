package com.sojson.common.dao;

import java.util.List;
import java.util.Map;

import com.sojson.common.model.UUserRole;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface UUserRoleMapper {

    int insert(UUserRole record);

    int insertSelective(UUserRole record);

    int deleteByUserId(Long id);

    int deleteRoleByUserIds(Map<String, Object> resultMap);

    List<Long> findUserIdByRoleId(Long id);
}
