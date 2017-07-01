package com.alpha.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;

import com.alpha.common.model.RoleBean;
import com.alpha.common.model.UserBean;
import com.alpha.core.mybatis.page.Pagination;
import com.alpha.permission.bo.URoleBo;
import com.alpha.permission.bo.UserRoleAllocationBo;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface UserService {

	int deleteByPrimaryKey(Long id);

	UserBean insert(UserBean record);

	UserBean insertSelective(UserBean record);

	UserBean selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UserBean record);

	int updateByPrimaryKey(UserBean record);

	UserBean login(String email, String pswd);

	UserBean findUserByEmail(String email);

	Pagination<UserBean> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

	Map<String, Object> deleteUserById(String ids);

	Map<String, Object> updateForbidUserById(Long id, Long status);

	Pagination<UserRoleAllocationBo> findUserAndRole(ModelMap modelMap, Integer pageNo, Integer pageSize);

	List<URoleBo> selectRoleByUserId(Long id);

	Map<String, Object> addRole2User(Long userId, String ids);

	Map<String, Object> deleteRoleByUserIds(String userIds);

	UserBean md5Pswd(UserBean user);

	String md5Pswd(String email, String pswd);

	List<Map<String, Object>> toTreeData(List<RoleBean> roles);
}
