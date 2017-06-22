package com.alpha.permission.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alpha.common.model.URole;
import com.alpha.core.mybatis.page.Pagination;
import com.alpha.permission.bo.RolePermissionAllocationBo;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface RoleService {

    int deleteByPrimaryKey(Long id);

    int insert(URole record);

    int insertSelective(URole record);

    URole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(URole record);

    int updateByPrimaryKey(URole record);

    Pagination<URole> findPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

    Map<String, Object> deleteRoleById(String ids);

    Pagination<RolePermissionAllocationBo> findRoleAndPermissionPage(Map<String, Object> resultMap, Integer pageNo,
                                                                     Integer pageSize);

    //根据用户ID查询角色（role），放入到Authorization里。
    Set<String> findRoleByUserId(Long userId);

    List<URole> findNowAllPermission();

    //初始化数据
    void initData();
}
