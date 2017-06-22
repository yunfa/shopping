package com.alpha.common.dao;

import java.util.List;
import java.util.Map;

import com.alpha.common.model.UUser;
import com.alpha.permission.bo.URoleBo;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface UUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UUser record);

    int insertSelective(UUser record);

    UUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UUser record);

    int updateByPrimaryKey(UUser record);

    UUser login(Map<String, Object> map);

    UUser findUserByEmail(String email);

    List<URoleBo> selectRoleByUserId(Long id);

}
