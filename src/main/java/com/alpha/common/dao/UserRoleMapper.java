package com.alpha.common.dao;

import java.util.List;
import java.util.Map;

import com.alpha.common.model.UserRoleBean;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface UserRoleMapper {

    int insert(UserRoleBean record);

    int insertSelective(UserRoleBean record);

    int deleteByUserId(Long id);

    int deleteRoleByUserIds(Map<String, Object> resultMap);

    List<Long> findUserIdByRoleId(Long id);
}
