package com.alpha.common.dao;

import java.util.List;
import java.util.Map;

import com.alpha.common.model.UserBean;
import com.alpha.permission.bo.URoleBo;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserBean record);

    int insertSelective(UserBean record);

    UserBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserBean record);

    int updateByPrimaryKey(UserBean record);

    UserBean login(Map<String, Object> map);

    UserBean findUserByEmail(String email);

    List<URoleBo> selectRoleByUserId(Long id);

}
